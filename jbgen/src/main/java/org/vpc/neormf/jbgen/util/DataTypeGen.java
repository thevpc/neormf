package org.vpc.neormf.jbgen.util;

import org.vpc.neormf.commons.types.DataType;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 9 févr. 2009
 * Time: 23:04:08
 * To change this template use File | Settings | File Templates.
 */
public interface DataTypeGen {
    String toCode(DataType type);
}
