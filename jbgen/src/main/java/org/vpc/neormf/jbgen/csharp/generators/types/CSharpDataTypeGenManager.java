package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.types.*;
import org.vpc.neormf.jbgen.java.types.AnyTypeDelegate;

import java.util.Hashtable;
import java.util.NoSuchElementException;

public class CSharpDataTypeGenManager {
    private static Hashtable map=new Hashtable();
    static{
        register(AnyType.class,new CSharpAnyTypeGen());
        register(AnyTypeDelegate.class,new CSharpAnyTypeDelegateGen());
        register(BooleanType.class,new CSharpBooleanTypeGen());
        register(DTOChoiceTypeForBD.class,new CSharpDTOChoiceTypeGen());
        register(DateTimeType.class,new CSharpDateTimeTypeGen());
        register(DateType.class,new CSharpDateTypeGen());
        register(TimeType.class,new CSharpTimeTypeGen());
        register(DayMonthType.class,new CSharpDayMonthTypeGen());
        register(DoubleType.class,new CSharpDoubleTypeGen());
        register(IntType.class,new CSharpIntTypeGen());
        register(ListChoiceType.class,new CSharpListChoiceTypeGen());
        register(StringType.class,new CSharpStringTypeGen());
        register(TimeDayType.class,new CSharpTimeDayTypeGen());
        register(BlobType.class,new CSharpBlobTypeGen());
    }
    
    public static void register(Class datatypeClass,CSharpDataTypeGen gen){
        map.put(datatypeClass,gen);
    }

    public static CSharpDataTypeGen getDatatypeGenerator(Class datatypeClass){
        CSharpDataTypeGen d=(CSharpDataTypeGen) map.get(datatypeClass);
        if(d==null){
            throw new NoSuchElementException(datatypeClass.getName());
        }
        return d;
    }
}
