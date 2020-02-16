package com.leetcode.solutions.Count_Negative_Numbers_in_a_Sorted_Matrix_1351;

/**
 * 1351. Count Negative Numbers in a Sorted Matrix
 * <p>
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 * <p>
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 * <p>
 * Return the number of negative numbers in grid.
 */

/*
    Example 1:
    Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
    Output: 8
    Explanation: There are 8 negatives number in the matrix.

    Example 2:
    Input: grid = [[3,2],[1,0]]
    Output: 0

    Example 3:
    Input: grid = [[1,-1],[-1,-1]]
    Output: 3

    Example 4:
    Input: grid = [[-1]]
    Output: 1
 */

/*
    Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 100
    -100 <= grid[i][j] <= 100
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().countNegatives(new int[][]{{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}}));
    }

    /**
     * T(n,m) = O(n+m)
     * where n,m - number of columns and rows
     */
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
