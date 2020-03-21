package com.EasyCode;
/*
 * @lc app=leetcode.cn id=28 lang=java
 *
 * [28] 实现 strStr()
 */

// @lc code=start
class Solution {
    public int strStr(String haystack, String needle) {
        if("".equals(needle)){
            return 0;
        }
        return haystack.indexOf(needle);
    }
}
// @lc code=end

