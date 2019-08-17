package org.vpc.neormf.commons.beans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 13 nov. 2006 22:45:27
 */
public abstract class ModuleMetaData {

    private LinkedHashMap<String, DTOMetaData> dtoBeanNameInfosMap;
    private LinkedHashMap<String, DTOMetaData> dtoInfosMap;
    private LinkedHashMap<String, List<DTOMetaData>> dtoInfosByBOMap;

    public ModuleMetaData() {
    }

    public abstract RelationInfo[] getRelationsList();

    public abstract String[] getDTOList();

    private void rebuid() {
        if (dtoInfosMap == null) {
            String[] full = getDTOList();
            dtoBeanNameInfosMap = new LinkedHashMap<String, DTOMetaData>();
            dtoInfosMap = new LinkedHashMap<String, DTOMetaData>();
            dtoInfosByBOMap=new LinkedHashMap<String, List<DTOMetaData>>();
            for (int i = 0; i < full.length; i++) {
                try {
                    DTOMetaData info=((DataTransfertObject) (Class.forName(full[i]).newInstance())).metadata();
                    dtoInfosMap.put(full[i], info);
                    dtoBeanNameInfosMap.put(info.getBeanName(), info);
                    String bn = info.getProperties().getProperty("BusinessObjectName");
                    List<DTOMetaData> list=dtoInfosByBOMap.get(bn);
                    if(list==null){
                        list=new ArrayList<DTOMetaData>();
                        dtoInfosByBOMap.put(bn,list);
                    }
                    list.add(info);
                } catch (InstantiationException ex) {
                    Logger.getLogger(ModuleMetaData.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException("InstantiationException for " + full[i], ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ModuleMetaData.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException("IllegalAccessException for " + full[i], ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ModuleMetaData.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException("ClassNotFoundException for " + full[i], ex);
                }
            }
        }
    }

    public DTOMetaData getDataInfoByBeansName(String name) {
        rebuid();
        DTOMetaData info=dtoBeanNameInfosMap.get(name);
        if(info==null){
            throw new NoSuchElementException("Bean "+name+" not found");
        }
        return info;
    }
    
    public DTOMetaData getDataInfoByDTOClassName(String name) {
        rebuid();
        DTOMetaData info=dtoInfosMap.get(name);
        if(info==null){
            throw new NoSuchElementException("Bean "+name+" not found");
        }
        return info;
    }
    
    public String[] getBeanNames() {
        rebuid();
        Set<String> s=dtoBeanNameInfosMap.keySet();
        return s.toArray(new String[s.size()]);
    }

    public String[] getBOList() {
        rebuid();
        Set<String> s=dtoInfosByBOMap.keySet();
        return s.toArray(new String[s.size()]);
    }

    public DTOMetaData[] getDTOListByBO(String boName) {
        rebuid();
        List<DTOMetaData> list=dtoInfosByBOMap.get(boName);
        if(list==null){
            throw new NoSuchElementException("BusinessObject "+boName+" not found");
        }
        return list.toArray(new DTOMetaData[list.size()]);
    }
}
