package exp1;

import java.util.HashMap;
//2.5
public class singleTerm {
    private int parameter;
    private HashMap<Character, Integer> var = new HashMap<>();

    public singleTerm(String product) {
        parameter = 1;
        String multiplier[] = product.split("\\*");
        for (int i = 0; i < multiplier.length; i++) {
            if (multiplier[i].charAt(0) >= 48 && multiplier[i].charAt(0) <= 57) // 如果乘数为数字
                parameter *= Integer.parseInt(multiplier[i]);
            else {
                String s[] = multiplier[i].split("\\^"); // 如果乘数为单字母
                char varName = s[0].charAt(0); // 变量名
                int power = s.length > 1 ? Integer.parseInt(s[1]) : 1; // 次数
                var.put(varName, var.containsKey(varName) ? power + var.get(varName) : power);
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(Integer.toString(parameter));
        for (Character c : var.keySet()) {
            s.append("*");
            if (var.get(c) == 1)
                s.append(Character.toString(c));
            else {
                s.append(Character.toString(c));
                s.append("^");
                s.append(Integer.toString(var.get(c)));
            }
        }
        return s.toString();
    }

    public void simplify(char varName, int value) {
        if (var.containsKey(varName)) {
            parameter *= Math.pow(value, var.get(varName));
            var.remove(varName);
        } else {
            System.out.println("Error! no variable: " + varName);
            System.exit(0);
        }
    }

    public void derivative(char varName) {
        if (var.containsKey(varName)) {
            int power = var.get(varName);
            parameter *= power;
            var.put(varName, power - 1);
            if (var.get(varName) == 0)
                var.remove(varName);
        } else {
            parameter = 0;
            // System.out.println("Error! no such variable.");
            // System.exit(0);
        }
    }
}
