package org.vpc.neormf.jbgen.csharp.generators.types;

import org.vpc.neormf.commons.beans.BlobData;
import org.vpc.neormf.commons.types.BlobType;
import org.vpc.neormf.commons.types.DataType;
import org.vpc.neormf.jbgen.java.types.JavaDataTypeGen;

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
public class CSharpBlobTypeGen implements CSharpDataTypeGen {
    public String toCode(DataType type) {
        BlobType him=(BlobType) type;
        if(him.toJavaType().equals(BlobData.class)){
            return !type.isNullable() ? (him.getClass().getName()+".BLOB_NON_NULLABLE") :(him.getClass().getName()+".BLOB_NULLABLE");
        }
        return "new " + him.getClass().getName() + "("
                + him.toJavaType().getName()+".class"
                + "," +type.isNullable()
                +")";
    }
}