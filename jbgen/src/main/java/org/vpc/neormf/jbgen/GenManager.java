package org.vpc.neormf.jbgen;

import org.vpc.neormf.jbgen.projects.*;

import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 12 juil. 2006
 * Time: 17:03:57
 * To change this template use File | Settings | File Templates.
 */
public class GenManager {
    private final Hashtable map=new Hashtable();

    public GenManager(JBGenMain jbgen) {
        registerTarget(CsharpDAOTarget.NAME,new CsharpDAOTarget(jbgen));
        registerTarget(JavaDAOTarget.NAME,new JavaDAOTarget(jbgen));
        registerTarget(JavaDAOZeroLibTarget.NAME,new JavaDAOZeroLibTarget(jbgen));
        registerTarget(J2eeTarget.NAME,new J2eeTarget(jbgen));
    }

    public void registerTarget(String name, TargetGenerator target) {
        map.put(name, target);
    }

    public TargetGenerator getTarget(String name) {
        TargetGenerator targetGenerator = (TargetGenerator) map.get(name);
        if(targetGenerator ==null){
            throw new NoSuchElementException("Unknown target "+name);
        }
        return targetGenerator;
    }
}
