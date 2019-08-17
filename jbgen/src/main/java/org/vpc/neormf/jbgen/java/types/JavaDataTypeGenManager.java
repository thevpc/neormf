package org.vpc.neormf.jbgen.java.types;

import org.vpc.neormf.commons.types.*;

import java.util.Hashtable;
import java.util.NoSuchElementException;

public class JavaDataTypeGenManager {
    private static Hashtable map=new Hashtable();
    static{
        register(AnyType.class,new JavaAnyTypeGen());
        register(AnyTypeDelegate.class,new JavaAnyTypeDelegateGen());
        register(BooleanType.class,new JavaBooleanTypeGen());
        register(DTOChoiceTypeForBD.class,new JavaDTOChoiceTypeGen());
        register(DateTimeType.class,new JavaDateTimeTypeGen());
        register(DateType.class,new JavaDateTypeGen());
        register(TimeType.class,new JavaTimeTypeGen());
        register(DayMonthType.class,new JavaDayMonthTypeGen());
        register(DoubleType.class,new JavaDoubleTypeGen());
        register(IntType.class,new JavaIntTypeGen());
        register(ListChoiceType.class,new JavaListChoiceTypeGen());
        register(StringType.class,new JavaStringTypeGen());
        register(TimeDayType.class,new JavaTimeDayTypeGen());
        register(BlobType.class,new JavaBlobTypeGen());
    }
    
    public static void register(Class datatypeClass, JavaDataTypeGen gen){
        map.put(datatypeClass,gen);
    }

    public static JavaDataTypeGen getDatatypeGenerator(Class datatypeClass){
        JavaDataTypeGen d=(JavaDataTypeGen) map.get(datatypeClass);
        if(d==null){
            throw new NoSuchElementException(datatypeClass.getName());
        }
        return d;
    }
}
