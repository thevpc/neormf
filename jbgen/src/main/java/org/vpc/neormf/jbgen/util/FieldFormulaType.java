package org.vpc.neormf.jbgen.util;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 20 avr. 2004 Time: 17:38:52
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public enum FieldFormulaType {
    /**
     * not a formula type
     */
    none,
    /**
     * formula calculated with a select statement
     * field.jobAnalysisOffset.definition=JOB_ANALYSIS_OFFSET(INTEGER,true)
     * field.jobAnalysisOffset.getterImpl=SQL_QUERY:Select JOB.ANALYSIS_OFFSET From JOB where JOB.ID=JOB_EXEC.JOB_ID
     */
    sqlQuery,
    /**
     * field.capRate.getterImpl=SQL_CALL:{RETURN} := conv_curr({rcrRate},{var_date}, {rcrCurrency},{var_currency},get_params_base_curncy())
     * field.capRate.definition=CAP_RATE(DOUBLE,true)
     * field.capRate.getterImpl=SQL_CALL:{RETURN} := conv_curr({rcrRate},{var_date}, {rcrCurrency},{var_currency},get_params_base_curncy())
     */
    sqlCall,
    /**
     * field.destName.definition=DEST_NAME(VARCHAR,true,256)
     * field.destName.getterImpl=SQL_VIEW:CAP_RATES_VIEW(DISC_ID,DEST_ID,PROD_CODE).DEST_NAME
     */
    sqlView,
    /**
     * field.connectivity.definition=CONNECTIVITY(JAVA_OBJECT:java.util.Collection,true)
     * field.connectivity.getterImpl=RELATION:code->ALL_DIR_CONN:carCode
     */
    sqlMasterDetail,
    
    sqlDetailMaster,
    /**
     * field.capRate.definition=CAP_RATE(DOUBLE,true)
     * field.capRate.getterImpl=SQL_FUNCTION:conv_curr({rcrRate},{var_date}, {rcrCurrency},{var_currency},get_params_base_curncy())
     */
    sqlFunction,
    /**
     * field.prodCode.definition=PROD_CODE(VARCHAR,false,10)
     * field.prodCode.getterImpl=JAVA:return null;
     */
    code
}
