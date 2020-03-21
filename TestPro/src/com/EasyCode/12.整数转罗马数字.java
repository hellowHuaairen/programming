package com.EasyCode;
/*
 * @lc app=leetcode.cn id=12 lang=java
 *
 * [12] 整数转罗马数字
 */


// @lc code=start
class Solution {
    public String intToRoman(int num) {

        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuffer stringBuffer = new StringBuffer();
        int index = 0;
        while(index < nums.length){
            while(num >= nums[index]){
                stringBuffer.append(romans[index]);
                num -= nums[index];  
            }
            index++;
        }

        return stringBuffer.toString();
    }
}
// @lc code=end

