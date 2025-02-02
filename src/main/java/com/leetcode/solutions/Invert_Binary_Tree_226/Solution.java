package com.leetcode.solutions.Invert_Binary_Tree_226;

/**
 * 226. Invert Binary Tree
 * <p>
 * https://leetcode.com/problems/invert-binary-tree/
 * <p>
 * Given the root of a binary tree, invert the tree, and return its root.
 */

/*
    Example 1:
    Input: root = [4,2,7,1,3,6,9]
    Output: [4,7,2,9,6,3,1]

    Example 2:
    Input: root = [2,1,3]
    Output: [2,3,1]

    Example 3:
    Input: root = []
    Output: []
 */

/*
    Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100
 */

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        if (root.left != null) invertTree(root.left);
        if (root.right != null) invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;

    TreeNode(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

}
