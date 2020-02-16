package com.leetcode.solutions.Count_Negative_Numbers_in_a_Sorted_Matrix_1351;

/**
 * 1351. Count Negative Numbers in a Sorted Matrix
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 *
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 *
 * Return the number of negative numbers in grid.
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().countNegatives(new int[][]{{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}}));
    }

    public int countNegatives(int[][] grid) {
        if (grid.length == 0) return 0;
        int ans = 0;
        int rows = grid.length;
        int columns = grid[0].length;

        for (int i = 0, j = columns - 1; i < rows && j >= 0; ) {
            if (grid[i][j] < 0) {
                ans += rows - i;
                j--;
            } else {
                i++;
            }
        }
        return ans;
    }
}
