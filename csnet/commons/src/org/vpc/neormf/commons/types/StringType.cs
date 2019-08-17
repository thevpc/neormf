using System;
using org.vpc.neormf.commons.util;
/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 1 avr. 2004 Time: 22:26:31
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
namespace org.vpc.neormf.commons.types
{
	public class StringType : DataType 
							{
    public static int TYPE = 10;
    private int minLength = 0;
    private int maxLength = 256;
    private bool multiline = false;

    public StringType():this(true){
        
    }
    
    public StringType(bool nullable):base(nullable){
    }

		public StringType(bool nullable, int minLength, int maxLength,bool multiline):base(nullable){
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.multiline=multiline;
    }

    public override Object ValidateValue(Object obj, String fieldName) {
        String s = (String) Utils.CastTo(obj, typeof(String));
        if (s != null && s.Length == 0) {
            s = null;
        }
        s = (String) base.ValidateValue(s, fieldName);
        if (s == null) {
            return null;
        }
        if (this.minLength > 0 && s.Length < this.minLength) {
            throw new DataException("org.vpc.neormf.commons.types.StringType.TooShort.Message", new Object[]{fieldName});
        }
        if (this.maxLength > 0 && s.Length > this.maxLength) {
            throw new DataException("org.vpc.neormf.commons.types.StringType.TooLong.Message", new Object[]{fieldName});
        }
        return s;
    }

    public override Type ToImplType() {
        return typeof(String);
    }

    public String ToCode() {
        return "new " + GetType().Name + "(" + IsNullable() +
                "," + minLength +
                "," + maxLength +
                "," + multiline +
                ")";
    }

    public override int GetTypeId() {
        return TYPE;
    }

    public int GetMinLength() {
        return minLength;
    }

    public int GetMaxLength() {
        return maxLength;
    }

    public bool IsMultiline() {
        return multiline;
    }
}
}