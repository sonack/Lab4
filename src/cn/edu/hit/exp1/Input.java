package cn.edu.hit.exp1;

import java.util.Scanner;

/**
 * @author author
 *
 */
public class Input {

/**
 */
private String text;
/**
 */
private static final int ASCII_NUM_0 = 48,
                      ASCII_NUM_9 = 57,
                      SIX = 6,
                      FIVE = 5;

/**
 * @param txt txt
 */
public Input(final String txt) {
        /**
         * @param txt
         */
    this.text = txt;
    }


/**
 * @return text
 */
public final String getText() {
    return text;
    }


/**
 * @return bool
 */
public final boolean expressionLegal() {
    boolean lastIsOperator = false;
    if (text.length() == 0) {
        return false;
    }
    for (int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);
        if (c >= ASCII_NUM_0 && c <= ASCII_NUM_9) {
        lastIsOperator = false;
        } else if (c == '+' || c == '*' || c == '^') {
        if (lastIsOperator || i == 0) {
            return false;
        }
        lastIsOperator = true;
        } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
        if (lastIsOperator || i == 0) {
            lastIsOperator = false;
        } else  {
            return false;
        }
        } else  {
        return false;
        }
    }
    return true;
    }


/**
 * @return bool
 */
public final boolean simplifyLegal() {
        /**
         * @return
         */
    if (!text.startsWith("!simplify ")) {
        return false;
    }
    String[] assignment = text.split(" ");
    for (int i = 1; i < assignment.length; i++) {
        String[] s = assignment[i].split("=");
        if (s.length != 2) {
        return false;
        }
        if (s[0].length() != 1 || s[0].charAt(0) < 'a'
                               || s[0].charAt(0) > 'z') {
        return false;
        }
        for (int j = 0; j < s[1].length(); j++) {
        if (s[1].charAt(j) < ASCII_NUM_0
                        || s[1].charAt(j) > ASCII_NUM_9) {
            return false;
        }
        }
    }
    return true;
    }


/**
 * @param p p
 */
public final void simplify(final Expression p) {
        /**
         * @param p
         */
    String[] assignment = text.split(" ");
    for (int i = 1; i < assignment.length; i++) {
        String[] s = assignment[i].split("=");
        p.simplify(s[0].charAt(0), Integer.parseInt(s[1]));
    }
    System.out.println(p);
    }


/**
 * @return bool
 */
public final boolean derivativeLegal() {
        /**
         * @return
         */
    if (text.length() != SIX) {
        return false;
    }
    if (text.charAt(FIVE) < 'a' || text.charAt(FIVE) > 'z'
                                || !text.startsWith("!d/d ")) {
        return false;
    }
    return true;
    }


/**
 * @param p p
 */
public final void derivative(final Expression p) {
    p.derivative(text.charAt(FIVE));
    System.out.println(p);
    }


/**
 * @param args args
 */
public static void main(final String[] args) {
    Scanner in = new Scanner(System.in);
    Expression p = null;
    while (true) {
        Input ipt = new Input(in.nextLine());
        if (ipt.expressionLegal()) {
        p = new Expression(ipt.getText());
        } else if (ipt.simplifyLegal() && p != null) {
        ipt.simplify(p);
        } else if (ipt.derivativeLegal() && p != null) {
        ipt.derivative(p);
        } else  {
        System.out.println("Error!");
        }
    }
    }
}
