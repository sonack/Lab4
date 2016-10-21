package cn.edu.hit.exp1;

import java.util.HashMap;

/**
 * @author author
 *
 */
public class SingleTerm {
/**
 */
private int parameter;
/**
 */
private HashMap<Character, Integer> var = new HashMap<>();
/**
 */
private static final int ASCII_NUM_0 = 48,
                    ASCII_NUM_9 = 57;

/**
 * @param product product
 */
public SingleTerm(final String product) {
    parameter = 1;
    String[] multiplier = product.split("\\*");
    for (int i = 0; i < multiplier.length; i++) {
        if (multiplier[i].charAt(0) >= ASCII_NUM_0
        && multiplier[i].charAt(0) <= ASCII_NUM_9) {   // 判断是不是单个数字
        parameter *= Integer.parseInt(multiplier[i]);
        } else  {
        String[] s = multiplier[i].split("\\^"); // 处理指数
        char varName = s[0].charAt(0); // 获取变量名
        int power = 1;  // 默认指数是1，否则是指定指数
        if (s.length > 1) {
             power = Integer.parseInt(s[1]);
        }
        if (var.containsKey(varName)) {
                var.put(varName, power + var.get(varName));
        } else  {
                var.put(varName, power);
        }
        }
    }
    }

    @Override
    public final String toString() {
    String s = "";
    s += Integer.toString(parameter);
    for (Character c : var.keySet()) {
        s += "*";
        if (var.get(c) == 1) {
        s += Character.toString(c);
        } else  {
        s += Character.toString(c);
        s += "^";
        s += Integer.toString(var.get(c));
        }
    }
    if (s.toString().startsWith("1*")) {
        return s.substring(2);
    }
    return s;
    }


/**
 * @param varName varName
 * @param value value
 */
public final void  simplify(final char varName, final int value) {
    if (var.containsKey(varName)) {
        parameter *= Math.pow(value, var.get(varName));
        var.remove(varName);
    }
    }


/**
 * @param varName varName
 */
public final void derivative(final char varName) {
    if (var.containsKey(varName)) {
        int power = var.get(varName);
        parameter *= power;
        var.put(varName, power - 1);
        if (var.get(varName) == 0) {
        var.remove(varName);
        }
    } else  {
        parameter = 0;
    }
    }
}
