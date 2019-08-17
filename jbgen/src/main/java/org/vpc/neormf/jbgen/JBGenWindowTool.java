package org.vpc.neormf.jbgen;

import org.vpc.neormf.jbgen.util.JTextAreaTLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Taha BEN SALAH (taha.bensalah@gmail.com)
 * @creationtime 24 janv. 2007 09:13:46
 */
public class JBGenWindowTool extends JFrame {
    JBGenMain jbgen;
    JTextAreaTLog log = new JTextAreaTLog();
    JTextField folder;
    JButton folderLookup;
    JButton runButton;

    public JBGenWindowTool(JBGenMain main) throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            //
        }
        this.jbgen = main;
        folder = new JTextField();
        folder.setText(getLastFolder().getPath());
        jbgen.setLog(log);
        setTitle("JBGen v " + JBGenVersion.getVersion());
        folder.setEditable(false);
        folderLookup = new JButton("...");
        folderLookup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser c = new JFileChooser(folder.getText());
                c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                c.setSelectedFile(new File(folder.getText()));
                if (JFileChooser.APPROVE_OPTION == c.showOpenDialog(JBGenWindowTool.this)) {
                    setLastFolder(c.getSelectedFile());
                }
            }
        });
        runButton = new JButton("Run...");
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        generate(new File(folder.getText()));
                    }
                }).start();
            }
        });
        Box b1 = Box.createHorizontalBox();
        b1.add(folder);
        b1.add(folderLookup);
        Box b2 = Box.createHorizontalBox();
        b2.add(Box.createHorizontalGlue());
        b1.add(runButton);

        Box b3 = Box.createVerticalBox();
        b3.add(b1);
        b3.add(b2);
        JPanel p = new JPanel(new BorderLayout());
        p.add(b3, BorderLayout.NORTH);

        p.add(b3, BorderLayout.NORTH);
        JScrollPane pane = new JScrollPane(log.getArea());
        pane.setPreferredSize(new Dimension(650, 400));
        p.add(pane, BorderLayout.CENTER);
        getContentPane().add(p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showWindow() {
        setVisible(true);
        pack();
        jbgen.getLog().info(jbgen.getHeaderCartridge());
        jbgen.getLog().info("Window Mode Activated.");
        jbgen.getLog().info("To use console mode, specify ModuleFolder as a command Argument");
        jbgen.getLog().info("Syntaxe : JBGenMain <ModuleFolder>");
    }

    public void setLastFolder(File file) {
        try {
            folder.setText(file.getCanonicalPath());
        } catch (IOException e) {
            folder.setText(file.getAbsolutePath());
        }
        Properties properties = new Properties();
        File configFile = new File(System.getProperty("user.home") + "/.java/vpc/neormf/jbgen.prp");
        configFile.getParentFile().mkdirs();
        try {
            properties.setProperty("selected.folder", file.getCanonicalPath());
        } catch (IOException e) {
            properties.setProperty("selected.folder", file.getAbsolutePath());
        }
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(configFile);
            properties.store(fs, "JBGen");
        } catch (Throwable ee) {
            //do nothing

        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    //do nothing
                }
            }
        }
    }

    public File getLastFolder() {
        Properties properties = new Properties();
        File configFile = new File(System.getProperty("user.home") + "/.java/vpc/neormf/jbgen.prp");
        configFile.getParentFile().mkdirs();
        if (configFile.exists()) {
            FileInputStream fs = null;
            try {
                fs = new FileInputStream(configFile);
                properties.load(fs);
            } catch (Throwable e) {
                //do nothing
            } finally {
                if (fs != null) {
                    try {
                        fs.close();
                    } catch (IOException e) {
                        //do nothing
                    }
                }
            }
        }
        String selectedFolder = properties.getProperty("selected.folder");
        if (selectedFolder == null) {
            selectedFolder = ".";
        }
        try {
            return new File(selectedFolder).getCanonicalFile();
        } catch (IOException e) {
            return new File(selectedFolder).getAbsoluteFile();
        }
    }

    public void generate(File folder){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                folderLookup.setEnabled(false);
                runButton.setEnabled(false);
            }
        });
        try {
            setLastFolder(folder);
            log.clear();
            jbgen.generate(folder);
        } catch (Throwable e1) {
            jbgen.getLog().error(e1);
        }finally{
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    folderLookup.setEnabled(true);
                    runButton.setEnabled(true);
                }
            });
        }
    }
}
