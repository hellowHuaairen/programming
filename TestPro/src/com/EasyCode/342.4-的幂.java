package com.EasyCode;
/*
 * @lc app=leetcode.cn id=342 lang=java
 *
 * [342] 4的幂
 */

// @lc code=start
class Solution {
    public boolean isPowerOfFour(int n) {
        
        if (n == 0) return false;
        while (n % 4 == 0) n /= 4;
        return n == 1;
    }
}
// @lc code=end

