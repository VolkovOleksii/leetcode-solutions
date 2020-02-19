package com.leetcode.solutions.Jump_Game_IV_1345;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 1345. Jump Game IV
 * <p>
 * https://leetcode.com/problems/jump-game-iv/
 * <p>
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * In one step you can jump from index i to index:
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 * Notice that you can not jump outside of the array at any time.
 */

/*
    Example 1:
    Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
    Output: 3
    Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

    Example 2:
    Input: arr = [7]
    Output: 0
    Explanation: Start index is the last index. You don't need to jump.

    Example 3:
    Input: arr = [7,6,9,6,9,6,9,7]
    Output: 1
    Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

    Example 4:
    Input: arr = [6,1,9]
    Output: 2

    Example 5:
    Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
    Output: 3
 */

/*
    Constraints:
    1 <= arr.length <= 5 * 10^4
    -10^8 <= arr[i] <= 10^8
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
    }

    /**
     * Based on https://leetcode.com/problems/jump-game-iv/discuss/502699/JavaC%2B%2B-BFS-Solution-Clean-code-O(N)
     * T(n) = O(n)
     * S(n) = O(n)
     */
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n < 3) return n - 1;

        Map<Integer, List<Integer>> connectedCells = new HashMap<>();
        for (int i = 0; i < n; i++) {
            connectedCells.computeIfAbsent(arr[i], (key) -> new LinkedList<>()).add(i);
        }

        boolean[] visitedCells = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        visitedCells[0] = true;
        queue.offer(0);
        int length = 0;

        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                Integer curr = queue.poll();

                if (curr == n - 1) return length;

                List<Integer> routs = connectedCells.get(arr[curr]);
                routs.add(curr + 1);
                routs.add(curr - 1);

                for (int next : routs) {
                    if (next >= 0 && next < n && !visitedCells[next]) {
                        visitedCells[next] = true;
                        queue.offer(next);
                    }
                }

                routs.clear();
            }
            length++;
        }

        throw new RuntimeException();
    }
}
