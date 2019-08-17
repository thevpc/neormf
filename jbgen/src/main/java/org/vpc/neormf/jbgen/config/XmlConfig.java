package org.vpc.neormf.jbgen.config;

import org.xml.sax.SAXException;
import org.vpc.neormf.jbgen.JBGenMain;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: vpc
 * Date: 2 nov. 2006
 * Time: 02:00:12
 * To change this template use File | Settings | File Templates.
 */
public class XmlConfig extends AbstractConfig {

    ConfigNode root;

    public XmlConfig(JBGenConfig defaults, String prefix, JBGenMain jbgen) {
        super(defaults, prefix,jbgen);
    }

    public void load(File propertiesFile) throws IOException {
        root = ConfigNode.load(propertiesFile);
    }

    public void store(File propertiesFile) throws IOException {
        root.store(propertiesFile);
    }

    protected ConfigNode[] getCurrentNodes(ConfigFilter[] path) {
        if (root != null) {
            int depth=0;
            JBGenConfig x=this;
            while(x!=null){
                depth++;
                x=x.getDefaults();
            }
            ConfigNode[] nodes = root.getChildren(path);
            for (int i = 0; i < nodes.length; i++) {
                nodes[i].setLevel(depth);
            }
            return nodes;
        }
        return ConfigNode.EMPTY;
    }
}
