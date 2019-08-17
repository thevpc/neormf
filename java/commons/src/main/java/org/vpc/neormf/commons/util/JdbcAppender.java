package org.vpc.neormf.commons.util;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.PatternLayout;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.vpc.neormf.commons.sql.SqlString;
import org.vpc.neormf.commons.sql.SqlParam;

/**
 * class presentation
 * 
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 28 juil. 2004 Time: 11:15:55
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class JdbcAppender extends org.apache.log4j.AppenderSkeleton
    implements org.apache.log4j.Appender {
    public static final SimpleDateFormat TIMESTAMP_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat TIME_FORMAT=new SimpleDateFormat("HH:mm:ss.SSS");
    /**
     * URL of the DB for default connection handling
     */
    protected String databaseURL = "jdbc:odbc:myDB";

    /**
     * User to connect as for default connection handling
     */
    protected String databaseUser = "me";

    /**
     * User to use for default connection handling
     */
    protected String databasePassword = "mypassword";

    /**
     * Connection used by default.  The connection is opened the first time it
     * is needed and then held open until the appender is closed (usually at
     * garbage collection).  This behavior is best modified by creating a
     * sub-class and overriding the <code>getConnection</code> and
     * <code>closeConnection</code> methods.
     */
    protected Connection connection = null;
    protected PreparedStatement preparedStatement = null;

    /**
     * Stores the string given to the pattern layout for conversion into a SQL
     * statement, eg: insert into LogTable (Thread, Class, Message) values
     * ("%t", "%c", "%m")
     *
     * Be careful of quotes in your messages!
     *
     * Also see PatternLayout.
     */
    protected String sqlStatement = "";
    protected SqlString sqlString = null;
    protected PatternLayout[] paramsPatternLayout = new PatternLayout[0];

    /**
     * size of LoggingEvent buffer before writting to the database.
     * Default is 1.
     */
    protected int bufferSize = 1;

    /**
     * ArrayList holding the buffer of Logging Events.
     */
    protected ArrayList buffer;

    /**
     * Helper object for clearing out the buffer
     */
    protected ArrayList removes;

    public JdbcAppender() {
      super();
      buffer = new ArrayList(bufferSize);
      removes = new ArrayList(bufferSize);
    }

    /**
     * Adds the event to the buffer.  When full the buffer is flushed.
     */
    public void append(LoggingEvent event) {
      buffer.add(event);

      if (buffer.size() >= bufferSize)
        flushBuffer();
    }

    /**
     * Override this to link with your connection pooling system.
     *
     * By default this creates a single connection which is held open
     * until the object is garbage collected.
     */
    protected Connection getConnection() throws SQLException {
        if (!DriverManager.getDrivers().hasMoreElements())
           setDriver("sun.jdbc.odbc.JdbcOdbcDriver");

        if (connection == null) {
          connection = DriverManager.getConnection(databaseURL, databaseUser,
                      databasePassword);
        }

        return connection;
    }

    public PreparedStatement getStatement() throws SQLException{
        if(preparedStatement==null){
            sqlString=new SqlString();
            StringBuffer buffer=new StringBuffer();
            ArrayList logLayoutCharsList=new ArrayList();
            for(int i=0;i<sqlStatement.length();i++){
                char c=sqlStatement.charAt(i);
                if(c=='?'){
                    String s=sqlStatement.substring(i+1);
                    if(
                            s.toUpperCase().startsWith("VARCHAR'") ||
                            s.toUpperCase().startsWith("INTEGER'") ||
                            s.toUpperCase().startsWith("DOUBLE'") ||
                            s.toUpperCase().startsWith("TIME'") ||
                            s.toUpperCase().startsWith("DATE'") ||
                            s.toUpperCase().startsWith("TIMESTAMP'")
                    ){
                        int x=s.indexOf('\'');
                        int y=s.indexOf('\'',x+1);
                        String sqlType=s.substring(0,x);
                        String valueLogType=s.substring(x+1,y);
                        i=i+1+y;
                        if(valueLogType.equals("%d")){
                            if(sqlType.equalsIgnoreCase("date")){
                                valueLogType="%d{"+DATE_FORMAT.toPattern()+"}";
                            }else if(sqlType.equalsIgnoreCase("time")){
                                valueLogType="%d{"+TIME_FORMAT.toPattern()+"}";
                            }else if(sqlType.equalsIgnoreCase("timestamp")){
                                valueLogType="%d{"+TIMESTAMP_FORMAT.toPattern()+"}";
                            }
                        }
                        sqlString.setParam(-1,null,sqlType);
                        logLayoutCharsList.add(new PatternLayout(valueLogType));
                    }else{
                        throw new IllegalArgumentException("expected ?SQL_TYPE:LOG_PARAM");
                    }
                    buffer.append('?');
                }else{
                    buffer.append(c);
                }
            }
            paramsPatternLayout= (PatternLayout[]) logLayoutCharsList.toArray(new PatternLayout[logLayoutCharsList.size()]);
            sqlString.setBuffer(buffer.toString());
            preparedStatement=getConnection().prepareStatement(sqlString.getBuffer());
        }
        return preparedStatement;
    }

    /**
     * Closes the appender, flushing the buffer first then closing the default
     * connection if it is open.
     */
    public void close()
    {
      flushBuffer();

      try {
        if (connection != null && !connection.isClosed())
            connection.close();
      } catch (SQLException e) {
          errorHandler.error("Error closing connection", e, ErrorCode.GENERIC_FAILURE);
      }
      this.closed = true;
    }

    /**
     * loops through the buffer of LoggingEvents, gets a
     * sql string from getLogStatement() and sends it to execute().
     * Errors are sent to the errorHandler.
     *
     * If a statement fails the LoggingEvent stays in the buffer!
     */
    public void flushBuffer() {
      //Do the actual logging
      removes.ensureCapacity(buffer.size());
      for (Iterator i = buffer.iterator(); i.hasNext();) {
        try {
          LoggingEvent logEvent = (LoggingEvent)i.next();
          PreparedStatement ps=getStatement();
          SqlParam[] sqlParams=sqlString.getParams();
          for(int j=0;j<sqlParams.length;j++){
              PatternLayout patternLayout=paramsPatternLayout[j];
              String strVal=patternLayout.format(logEvent);
              switch(sqlParams[j].getSqlType()){
                  case Types.VARCHAR:{
                      sqlParams[j].setValue(strVal);
                      break;
                  }
                  case Types.INTEGER:{
                      sqlParams[j].setValue(new Integer(strVal));
                      break;
                  }
                  case Types.DOUBLE:{
                      sqlParams[j].setValue(new Double(strVal));
                      break;
                  }
                  case Types.DATE:{
                      java.util.Date d=new java.util.Date();
                      try {
                          d=DATE_FORMAT.parse(strVal);
                      } catch (ParseException e) {
                          e.printStackTrace();
                      }
                      sqlParams[j].setValue(new java.sql.Date(d.getTime()));
                      break;
                  }
                  case Types.TIME:{
                      java.util.Date d=new java.util.Date();
                      try {
                          d=TIME_FORMAT.parse(strVal);
                      } catch (ParseException e) {
                          e.printStackTrace();
                      }
                      sqlParams[j].setValue(new java.sql.Time(d.getTime()));
                      break;
                  }
                  case Types.TIMESTAMP:{
                      java.util.Date d=new java.util.Date();
                      try {
                          d=TIMESTAMP_FORMAT.parse(strVal);
                      } catch (ParseException e) {
                          e.printStackTrace();
                      }
                      sqlParams[j].setValue(new java.sql.Timestamp(d.getTime()));
                      break;
                  }
              }
          }
          sqlString.populateStatement(ps);
          ps.executeUpdate();
          removes.add(logEvent);
        }
        catch (SQLException e) {
          errorHandler.error("Failed to excute sql", e,
                 ErrorCode.FLUSH_FAILURE);
        }
      }

      // remove from the buffer any events that were reported
      buffer.removeAll(removes);

      // clear the buffer of reported events
      removes.clear();
    }


    /** closes the appender before disposal */
    public void finalize() {
      close();
    }


    /**
     * JDBCAppender requires a layout.
     * */
    public boolean requiresLayout() {
      return false;
    }


    /**
     *
     */
    public void setSql(String s) {
      sqlStatement = s;
      if (getLayout() == null) {
          this.setLayout(new PatternLayout(s));
      }
      else {
          ((PatternLayout)getLayout()).setConversionPattern(s);
      }
    }


    /**
     * Returns pre-formated statement eg: insert into LogTable (msg) values ("%m")
     */
    public String getSql() {
      return sqlStatement;
    }


    public void setUser(String user) {
      databaseUser = user;
    }


    public void setURL(String url) {
      databaseURL = url;
    }


    public void setPassword(String password) {
      databasePassword = password;
    }


    public void setBufferSize(int newBufferSize) {
      bufferSize = newBufferSize;
      buffer.ensureCapacity(bufferSize);
      removes.ensureCapacity(bufferSize);
    }

    public String getUser() {
      return databaseUser;
    }


    public String getURL() {
      return databaseURL;
    }


    public String getPassword() {
      return databasePassword;
    }


    public int getBufferSize() {
      return bufferSize;
    }


    /**
     * Ensures that the given driver class has been loaded for sql connection
     * creation.
     */
    public void setDriver(String driverClass) {
      try {
        Class.forName(driverClass);
      } catch (Exception e) {
        errorHandler.error("Failed to load driver", e,
               ErrorCode.GENERIC_FAILURE);
      }
    }
}
