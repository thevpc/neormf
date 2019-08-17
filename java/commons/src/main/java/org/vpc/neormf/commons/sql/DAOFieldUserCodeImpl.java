package org.vpc.neormf.commons.sql;

import java.util.Hashtable;
import java.util.Set;

public class DAOFieldUserCodeImpl extends DAOFieldImpl {

    private CodeLanguage language;
    private Hashtable<CodeLanguage,String> codeMapping;

    public DAOFieldUserCodeImpl(CodeLanguage languages,String userCodes) {
        this(new CodeLanguage[]{languages}, new String[]{userCodes});
    }
    
    public DAOFieldUserCodeImpl(CodeLanguage[] languages,String userCodes[]) {
        super(DAOFieldImplType.code);
        this.language=languages[0];
        codeMapping=new Hashtable<CodeLanguage, String>(languages.length);
        for (int i = 0; i < languages.length; i++) {
            codeMapping.put(languages[i],userCodes[i]);
        }
    }

    public String getUserCode() {
        return codeMapping.get(language);
    }

    public CodeLanguage getLanguage() {
        return language;
    }

    public CodeLanguage[] getLanguages() {
        Set<CodeLanguage> languages = codeMapping.keySet();
        return languages.toArray(new CodeLanguage[languages.size()]);
    }

    public String getUserCode(CodeLanguage language) {
        return codeMapping.get(language);
    }

}