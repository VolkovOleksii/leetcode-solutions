package com.leetcode.solutions.Jump_Game_IV_1345;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
        System.out.println(new Solution().minJumps(new int[]{11,22,7,7,7,7,7,7,7,22,13}));
    }

    public int minJumps(int[] arr) {
        if (arr.length < 2) return arr.length - 1;

        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            graph.put(i, new Node(i));
        }

        Map<Integer, Set<Node>> connectedNodes = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            connectedNodes.putIfAbsent(arr[i], new HashSet<>());
            connectedNodes.get(arr[i]).add(graph.get(i));
        }

        for (int i = 0; i < arr.length; i++) {
            Node node = graph.get(i);
            node.addAll(connectedNodes.get(arr[i]));
            node.add(graph.get(i + 1));
            node.add(graph.get(i - 1));
        }

        Set<Node> visitedNodes = new HashSet<>();
        Queue<Route> routes = new LinkedList<>();

        Node targetNode = graph.get(0);
        routes.offer(new Route(0, graph.get(arr.length - 1)));
        visitedNodes.add(graph.get(arr.length - 1));

        Route curr;
        while ((curr = routes.poll()) != null) {
            final int length = curr.lengthTo;
            if (curr.to.contains(targetNode)) return length + 1;

            curr.to.getNodes().forEachRemaining(
                    node -> {
                        if (visitedNodes.add(node)) routes.offer(new Route(length + 1, node));
                    }
            );
        }

        /*
         * Time Limit Exceeded, so dirty hack above
         */
//        Node targetNode = graph.get(arr.length - 1);
//        routes.offer(new Route(0, graph.get(0)));
//        visitedNodes.add(graph.get(0));
//
//        Route curr = null;
//
//        while ((curr = routes.poll()) != null) {
//            final int length = curr.lengthTo;
//            if (curr.to.id == targetNode.id) return length;
//
//            curr.to.getNodes().forEachRemaining(
//                    node -> {
//                        if (visitedNodes.add(node)) routes.offer(new Route(length + 1, node));
//                    }
//            );
//        }

        throw new RuntimeException();
    }

    private static class Node {
        public final int id;
        private final Set<Node> nodes;

        Node(int id) {
            this.id = id;
            this.nodes = new HashSet<>();
        }

        Iterator<Node> getNodes() {
            return nodes.iterator();
        }

        boolean contains(Node node) {
            return nodes.contains(node);
        }

        void add(Node node) {
            if (node != null) nodes.add(node);
        }

        void addAll(Collection<Node> nodes) {
            this.nodes.addAll(nodes);
        }

        @Override
        public boolean equals(Object o) {
            return this == o;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    private static class Route {
        public final int lengthTo;
        public final Node to;

        public Route(int lengthTo, Node to) {
            this.lengthTo = lengthTo;
            this.to = to;
        }
    }
}
