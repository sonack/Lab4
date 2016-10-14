package exp1;

import java.util.Scanner;
//2.5
//2.3
public class input {
    String text;

    public input(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean expressionLegal() {
        boolean lastIsOperator = false;
        if (text.length() == 0)
            return false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 48 && c <= 57)
                lastIsOperator = false;
            else if (c == '+' || c == '*' || c == '^') {
                if (lastIsOperator || i == 0)
                    return false;
                lastIsOperator = true;
            } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                if (lastIsOperator)
                    lastIsOperator = false;
                else if (i != 0)
                    return false;
            } else
                return false;
        }
        return true;
    }

    public boolean simplifyLegal() {
        if (text.equals("!simplify"))
            return true;
        if (!text.startsWith("!simplify "))
            return false;
        String assignment[] = text.split(" ");
        for (int i = 1; i < assignment.length; i++) {
            String s[] = assignment[i].split("=");
            if (s.length != 2)
                return false;
            if (s[0].length() != 1 || s[0].charAt(0) < 'a' || s[0].charAt(0) > 'z')
                return false;
            for (int j = 0; j < s[1].length(); j++) {
                if (s[1].charAt(j) < 48 || s[1].charAt(j) > 57)
                    return false;
            }
        }
        return true;
    }

    public void simplify(expression p) {
        String assignment[] = text.split(" ");
        for (int i = 1; i < assignment.length; i++) {
            String s[] = assignment[i].split("=");
            p.simplify(s[0].charAt(0), Integer.parseInt(s[1]));
        }
        System.out.println(p);
    }

    public boolean derivativeLegal() {
        if (text.length() != 6)
            return false;
        if (text.charAt(5) < 'a' || text.charAt(5) > 'z' || !text.startsWith("!d/d "))
            return false;
        return true;
    }

    public void derivative(expression p) {
        p.derivative(text.charAt(5));
        System.out.println(p);
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        expression p = null;
        while (true) {
            input ipt = new input(in.nextLine());
            if (ipt.expressionLegal())
                p = new expression(ipt.getText());
            else if (ipt.simplifyLegal() && p != null)
                ipt.simplify(p);
            else if (ipt.derivativeLegal() && p != null)
                ipt.derivative(p);
            else {
                System.out.println("Error!");
                return;
            }
        }
    }
}
