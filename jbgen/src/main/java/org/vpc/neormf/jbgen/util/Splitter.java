package org.vpc.neormf.jbgen.util;


import org.vpc.neormf.commons.util.Utils;

import java.util.ArrayList;

/**
 * class presentation
 *
 * @author taha BEN SALAH (tbsalah)
 * @version 1.0
 * @copyrights (c) 2004, Vpc Open Source Foundary
 * @project Neormf
 * @creation on Date: 22 juil. 2004 Time: 09:57:40
 * @modification on ---- by -----
 * @modification on ---- by -----
 * @modification on ---- by -----
 */
public class Splitter {
    private Parenthese[] parentheses = {new Parenthese('(', ')'), new Parenthese('[', ']'), new Parenthese('{', '}')};
    private int[] openParentheses = new int[3];
    private Cote[] cotes = {new Cote('\"', '\\'), new Cote('\'', '\\')};
    private String delemeters = ",;";
    private String whites = " \t\n\r";
    private String escapeChars = "\\";
    private boolean returnDelemeters = false;

    public boolean isReturnDelemeters() {
        return returnDelemeters;
    }

    public void setReturnDelemeters(boolean returnDelemeters) {
        this.returnDelemeters = returnDelemeters;
    }

    public Token[] split(String message) {
        ArrayList al = new ArrayList();
        StringBuilder sb = null;
        int startindex = -1;
        boolean wasDelemeter = false;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            boolean traited = false;
            if (escapeChars.indexOf(c) >= 0) {
                wasDelemeter = false;
                i++;
                c = message.charAt(i);
                if (sb == null) {
                    sb = new StringBuilder();
                    startindex = i;
                }
                sb.append(c);
                continue;
            }
            for (int j = 0; j < cotes.length; j++) {
                if (cotes[j].openChar == c) {
                    wasDelemeter = false;
                    if (sb != null) {
//                        System.out.println("<1> pbm " + getClass());
                        sb.append(c);
//                        traited = true;
//                        break;
                    }else{
                        startindex = i;
                    }
                    sb = new StringBuilder();
                    i++;
                    while (i < message.length()) {
                        c = message.charAt(i);
                        if (cotes[j].escapeChar == c) {
                            i++;
                            c = message.charAt(i);
                            sb.append(c);
                        } else if (cotes[j].closeChar == c) {
                            al.add(new Token(sb.toString(),
                                    cotes[j],
                                    startindex,
                                    message.substring(startindex, i + 1)));
                            sb = null;
                            break;
                        } else {
                            sb.append(c);
                        }
                        i++;
                    }
                    traited = true;
                    break;
                }
            }
            if (traited) {
                continue;
            }
            for (int j = 0; j < parentheses.length; j++) {
                if (parentheses[j].openChar == c) {
                    wasDelemeter = false;
                    sb.append(c);
                    i++;
                    while (i < message.length()) {
                        c = message.charAt(i);
                        sb.append(c);
                        for (int k = 0; k < parentheses.length; k++) {
                            if (parentheses[k].openChar == c) {
                                openParentheses[k]++;
                                break;
                            } else if (parentheses[k].closeChar == c) {
                                openParentheses[k]--;
                                break;
                            }
                        }
                        boolean noMoreOpenParentheses = true;
                        for (int k = 0; k < parentheses.length; k++) {
                            if (openParentheses[k] > 0) {
                                //continue
                                noMoreOpenParentheses = false;
                                break;
                            } else if (openParentheses[k] < 0) {
                                //problem
                                throw new IllegalArgumentException("expected " + parentheses[k]);
                            }
                        }
                        if (noMoreOpenParentheses) {
                            break;
                        }
                    }
                    traited = true;
                    break;
                }
            }
            if (traited) {
                continue;
            }
            if (whites.indexOf(c) >= 0) {
                wasDelemeter = false;
                if (sb != null) {
                    al.add(new Token(sb.toString(),
                            null,
                            startindex,
                            message.substring(startindex, i)));
                    sb = null;
                }
            } else if (delemeters.indexOf(c) >= 0) {
                if (sb != null) {
                    al.add(new Token(sb.toString(),
                            null,
                            startindex,
                            message.substring(startindex, i)));
                    if (returnDelemeters) {
                        al.add(new Delimiter(String.valueOf(c), i));
                    }
                    sb = null;
                } else /*if(wasDelemeter)*/ {
//                    al.add(
//                            new Token(
//                                    "",
//                                    null,
//                                    startindex,
//                                    ""
//                            )
//                    );
                    if (returnDelemeters) {
                        al.add(new Delimiter(String.valueOf(c), i));
                    }
                }
                wasDelemeter = true;
            } else {
                wasDelemeter = false;
                if (sb == null) {
                    sb = new StringBuilder();
                    startindex = i;
                }
                sb.append(c);
            }
        }
        if (sb != null) {
            al.add(new Token(sb.toString(),
                    null,
                    startindex,
                    message.substring(startindex)));
        }
        return (Token[]) al.toArray(new Token[al.size()]);
    }


    public String getDelemeters() {
        return delemeters;
    }

    public void setDelemeters(String delemeters) {
        this.delemeters = delemeters;
    }

    public String getWhites() {
        return whites;
    }

    public void setWhites(String whites) {
        this.whites = whites;
    }

    public Parenthese[] getParentheses() {
        return parentheses;
    }

    public void setParentheses(Parenthese[] parentheses) {
        this.parentheses = parentheses;
    }

    public Cote[] getCotes() {
        return cotes;
    }

    public void setCotes(Cote[] cotes) {
        this.cotes = cotes;
    }

    public static class Parenthese {
        private char openChar;
        private char closeChar;

        public Parenthese(char openChar, char closeChar) {
            this.openChar = openChar;
            this.closeChar = closeChar;
        }

        public char getOpenChar() {
            return openChar;
        }

        public void setOpenChar(char openChar) {
            this.openChar = openChar;
        }

        public char getCloseChar() {
            return closeChar;
        }

        public void setCloseChar(char closeChar) {
            this.closeChar = closeChar;
        }

        public String toString() {
            return "Parenthese{" +
                    "openChar=" + openChar +
                    ", closeChar=" + closeChar +
                    "}";
        }

    }

    public static class Cote {
        private char openChar;
        private char closeChar;
        private char escapeChar;

        public Cote(char openChar) {
            this(openChar, openChar, openChar);
        }

        public Cote(char openChar, char escapeChar) {
            this(openChar, openChar, escapeChar);
        }

        public Cote(char openChar, char closeChar, char escapeChar) {
            this.openChar = openChar;
            this.closeChar = closeChar;
            this.escapeChar = escapeChar;
        }

        public char getOpenChar() {
            return openChar;
        }

        public void setOpenChar(char openChar) {
            this.openChar = openChar;
        }

        public char getCloseChar() {
            return closeChar;
        }

        public void setCloseChar(char closeChar) {
            this.closeChar = closeChar;
        }

        public char getEscapeChar() {
            return escapeChar;
        }

        public void setEscapeChar(char escapeChar) {
            this.escapeChar = escapeChar;
        }

        public String toString() {
            return "Cote{" +
                    "openChar=" + openChar +
                    ", closeChar=" + closeChar +
                    ", escapeChar=" + escapeChar +
                    "}";
        }

    }

    public static class Delimiter extends Token {
        public Delimiter(String body, int position) {
            super(body, null, position, body);
        }

        public String toString() {
            return "Delimiter{" +
                    "body='" + getBody() + "'" +
                    ", position=" + getPosition() +
                    "}";
        }
    }

    public static class Token {
        private String body;
        private Cote cote;
        private int position;
        private String originalBody;

        public Token(String body, Cote cote, int position, String originalBody) {
            this.body = body;
            this.cote = cote;
            this.position = position;
            this.originalBody = originalBody;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Cote getCote() {
            return cote;
        }

        public void setCote(Cote cote) {
            this.cote = cote;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getOriginalBody() {
            return originalBody;
        }

        public void setOriginalBody(String originalBody) {
            this.originalBody = originalBody;
        }

        public String toString() {
            return "Token{" +
                    "body='" + body + "'" +
                    ", cote=" + cote +
                    ", position=" + position +
                    ", originalBody='" + originalBody + "'" +
                    "}";
        }

    }

}
