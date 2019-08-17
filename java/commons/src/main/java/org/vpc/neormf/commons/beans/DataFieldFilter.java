/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vpc.neormf.commons.beans;

/**
 *
 * @author vpc
 */
public interface DataFieldFilter {
    boolean accept(DTOFieldMetaData field);
}
