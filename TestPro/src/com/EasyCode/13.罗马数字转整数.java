package com.EasyCode;

import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=13 lang=java
 *
 * [13] 罗马数字转整数
 */

// @lc code=start
class Solution {
    public int romanToInt(String str) {

        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("IV", 4);
        map2.put("IX", 9);
        map2.put("XL", 40);
        map2.put("XC", 90);
        map2.put("CD", 400);
        map2.put("CM", 900);
        int initCnt = 0;
        int result = getTotalNum(str, initCnt, map2, map);

        return result;

    }

    public int getTotalNum(String str, int rest, Map<String, Integer> map2, Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            String key = entry.getKey();
            int index = str.indexOf(key);
            if (index >= 0) {
                rest = rest + map2.get(key);
                str = str.substring(0, index) + str.substring(index + 2);
                if (str.length() > 0) {
                    getTotalNum(str, rest, map2, map);
                }
            }
        }
        if (str.length() > 0) {
            char chars[] = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                rest = rest + map.get("" + chars[i]);
            }
        } 
        return rest;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt("III"));
    }
}
// @lc code=end
