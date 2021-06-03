package com.leetcode.solutions.Max_Area_of_Island_695;

/**
 * 695 Max Area of Island
 * <p>
 * https://leetcode.com/problems/max-area-of-island/
 * <p>
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected
 * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * The area of an island is the number of cells with a value 1 in the island.
 * Return the maximum area of an island in grid. If there is no island, return 0.
 */

/*
    Example 1:
    Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
                   [0,0,0,0,0,0,0,1,1,1,0,0,0],
                   [0,1,1,0,1,0,0,0,0,0,0,0,0],
                   [0,1,0,0,1,1,0,0,1,0,1,0,0],
                   [0,1,0,0,1,1,0,0,1,1,1,0,0],
                   [0,0,0,0,0,0,0,0,0,0,1,0,0],
                   [0,0,0,0,0,0,0,1,1,1,0,0,0],
                   [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Output: 6
                  [[ , ,1, , , , ,1, , , , , ],
                   [ , , , , , , ,1,1,1, , , ],
                   [ ,1,1, ,1, , , , , , , , ],
                   [ ,1, , ,1,1, , ,2, ,2, , ],
                   [ ,1, , ,1,1, , ,2,2,2, , ],
                   [ , , , , , , , , , ,2, , ],
                   [ , , , , , , ,1,1,1, , , ],
                   [ , , , , , , ,1,1, , , , ]]
    Explanation: The answer is not 11, because the island must be connected 4-directionally.

    Example 2:
    Input: grid = [[0,0,0,0,0,0,0,0]]
    Output: 0
 */

/*
    Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    grid[i][j] is either 0 or 1.
 */
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                max = Math.max(max, islandWalker(grid, i, j));
            }
        }
        return max;
    }

    private int islandWalker(int[][] grid, int m, int n) {
        if (m >= 0 && m < grid.length && n >= 0 && n < grid[0].length && grid[m][n] == 1) {
            grid[m][n] = 0;
            return 1
                    + islandWalker(grid, m, n + 1)
                    + islandWalker(grid, m + 1, n)
                    + islandWalker(grid, m - 1, n)
                    + islandWalker(grid, m, n - 1);
        }

        return 0;
    }
}
