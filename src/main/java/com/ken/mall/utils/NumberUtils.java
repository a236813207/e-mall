package com.ken.mall.utils;

/**
 * @author Ken
 * @date 2020/3/21
 * @description
 */
public class NumberUtils {

    public static String getCode(Integer length) {
        String s = "";
        while (s.length() < length) {
            s += (int) (Math.random() * 10);
        }
        return s;
    }

}
