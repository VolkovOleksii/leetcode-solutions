package com.leetcode.solutions.Jump_Game_IV_1345;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
        System.out.println(new Solution().minJumps(new int[]{7,6,9,6,9,6,9,7}));
    }

    /**
     * The same problem, that in previous solution
     * Time Limit Exceeded
     */
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n < 3) return n - 1;

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        Map<Integer, Set<Integer>> connectedNodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            connectedNodes.putIfAbsent(arr[i], new HashSet<>());
            connectedNodes.get(arr[i]).add(i);
        }

        for (int i = 1; i < n - 1; i++) {
            Set<Integer> vertexes = graph.get(i);
            vertexes.addAll(connectedNodes.get(arr[i]));
            vertexes.add(i + 1);
            vertexes.add(i - 1);
        }
        graph.get(0).addAll(connectedNodes.get(arr[0]));
        graph.get(0).add(1);
        graph.get(n - 1).addAll(connectedNodes.get(arr[n - 1]));
        graph.get(n - 1).add(n - 2);

        Set<Integer> visitedVertexes = new HashSet<>();
        Queue<Pair> routes = new LinkedList<>();

        Integer targetVertex = 0;
        routes.offer(new Pair(0, n - 1));
        visitedVertexes.add(n - 1);

        Pair curr;
        while ((curr = routes.poll()) != null) {
            final int length = curr.depth;
            if (graph.get(curr.to).contains(targetVertex)) return length + 1;

            graph.get(curr.to).forEach(
                    node -> {
                        if (!visitedVertexes.contains(node)) {
                            visitedVertexes.add(node);
                            routes.offer(new Pair(length + 1, node));
                        }
                    }
            );
        }

        throw new RuntimeException();
    }

    private static class Pair {
        public final int depth;
        public final Integer to;

        private Pair(int depth, Integer to) {
            this.depth = depth;
            this.to = to;
        }
    }
}
