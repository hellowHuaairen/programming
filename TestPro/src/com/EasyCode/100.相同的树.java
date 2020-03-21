package com.EasyCode;
import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=100 lang=java
 *
 * [100] 相同的树
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSameTree(final TreeNode p, final TreeNode q) {

        if(p ==null && q ==null) return true;
        if(p ==null || q == null) return false;
        if(p.val != q.val) return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        // final List<Integer> pa = getFirstRootArray(p, new ArrayList<>());
        // final List<Integer> qa = getFirstRootArray(q, new ArrayList<>());
        // if(qa.size() == pa.size()){
        //     for (int i = 0; i < pa.size(); i++) {
        //         if(pa.get(i) != qa.get(i)){
        //             return false;
        //         }
        //     }
        // }else{
        //     return  false;
        // }
        // return true;
    }


    public List<Integer> getFirstRootArray(final TreeNode treeNode, final List<Integer> array) {
        // 先根序对树进行排序

        if (treeNode != null) {
            array.add(treeNode.val);
            if (treeNode.left != null) {
                array.add(treeNode.left.val);
                if(treeNode.left.left != null){
                    getFirstRootArray(treeNode.left, array);
                } 
            }else{
                array.add(null); 
            } 
            if (treeNode.right != null) {
                array.add(treeNode.right.val);
                if(treeNode.right.right != null){
                    getFirstRootArray(treeNode.right, array);
                }
                
            }else{
                array.add(null); 
            }
        }
        return array;
    }

    public static void main(String [] args){
        // System.out.println(null ==null);
        // Solution1 solution = new Solution1();
        // TreeNode p = new TreeNode(1);
        // TreeNode p1 = new TreeNode(2);
        // // TreeNode p2 = new TreeNode(3);
        // p.left = p1;
        // // p.right = p2;
        // TreeNode q = new TreeNode(1);
        // // TreeNode q1 = new TreeNode(2);
        // TreeNode q2 = new TreeNode(2);
        // // q.left =q1;
        // q.right = q2;
        // System.out.println(solution.isSameTree(p, q));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
// @lc code=end

