/*
 * CopyrightPlugin (c) 2004 Your Corporation. All Rights Reserved.
 */
package org.vpc.neormf.jbgen.util;

import java.util.*;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbensalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project New Entreprise Object Relational Mapping Framework (neormf)
 * @creation on Date: 8 avr. 2004 Time: 14:07:38
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class StringListFilter {
    String[] acceptedPatterns;
    String[] rejectedPatterns;

    public StringListFilter(String compositePattern) {
        if (compositePattern == null || compositePattern.length() == 0) {
            acceptedPatterns = new String[0];
            rejectedPatterns = new String[0];
        } else {
            ArrayList a = new ArrayList();
            ArrayList r = new ArrayList();
            boolean regexp = compositePattern.trim().startsWith("regexp:");

            if (regexp) {
                compositePattern = compositePattern.substring("regexp:".length()).trim();
            }
            for (StringTokenizer stringTokenizer = new StringTokenizer(compositePattern, " \t\n\r,;"); stringTokenizer.hasMoreTokens();) {
                String s = stringTokenizer.nextToken();
                if (s.startsWith("-")) {
                    s = s.substring(1).trim();
                    if (s.length() == 0) {
                        throw new IllegalArgumentException("expected filter definition after op '-'");
                    }
                    if (!regexp) {
                        s = JBGenUtils.toRegexpPattern(s);
                    }

                    r.add(s);
                } else if (s.startsWith("+")) {
                    s = s.substring(1).trim();
                    if (s.length() == 0) {
                        throw new IllegalArgumentException("expected filter definition after op '+'");
                    }
                    if (!regexp) {
                        s = JBGenUtils.toRegexpPattern(s);
                    }
                    a.add(s);
                } else {
                    throw new IllegalArgumentException("expected operator '+' (inclusion) or '-' exclusion");
                }
            }
            acceptedPatterns = (String[]) a.toArray(new String[a.size()]);
            rejectedPatterns = (String[]) r.toArray(new String[r.size()]);
        }
    }

    public boolean accept(String name) {
        int accepted = -1;
        int rejected = -1;

        if (acceptedPatterns.length > 0) {
            for (int i = 0; i < acceptedPatterns.length; i++) {
                accepted = 0;
                if (name.matches(acceptedPatterns[i])) {
                    accepted = 1;
                    break;
                }
            }
        }
        if (rejectedPatterns.length > 0) {
            for (int i = 0; i < rejectedPatterns.length; i++) {
                rejected = 0;
                if (name.matches(rejectedPatterns[i])) {
                    rejected = 1;
                    break;
                }
            }
        }

        if (accepted == -1 && rejected == -1) {
            return true;
        } else if (accepted == -1) {
            return rejected == 0;
        } else if (rejected == -1) {
            return accepted == 1;
        } else {
            return accepted == 1 && rejected == 0;
        }
    }

    public void checkValuesIn(Set possibleValues) {
        String[][] patterns = new String[][]{acceptedPatterns, rejectedPatterns};
        for (int x = 0; x < patterns.length; x++) {
            for (int i = 0; i < patterns[x].length; i++) {
                if (patterns[x][i].startsWith("regexp:")) {
                    continue;
                }
                if (patterns[x][i].indexOf('*') >= 0
                        || patterns[x][i].indexOf('?') >= 0
                ) {
                    continue;
                }
                JBGenUtils.checkInPossibleValues(patterns[x][i], possibleValues);
            }
        }
    }

    public void checkValuesIn(String[] possibleValues) {
        checkValuesIn(new HashSet(Arrays.asList(possibleValues)));
    }
}
