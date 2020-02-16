package com.leetcode.solutions.Product_of_the_Last_K_Numbers_1352;

import java.util.ArrayList;
import java.util.List;

/**
 * 1352. Product of the Last K Numbers
 * <p>
 * https://leetcode.com/problems/product-of-the-last-k-numbers/
 * <p>
 * Implement the class ProductOfNumbers that supports two methods:
 * <p>
 * 1. add(int num)
 * Adds the number num to the back of the current list of numbers.
 * <p>
 * 2. getProduct(int k)
 * Returns the product of the last k numbers in the current list.
 * <p>
 * You can assume that always the current list has at least k numbers.
 * At any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
 */

/*
    Example:
    Input
    ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
    [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
    Output
    [null,null,null,null,null,null,20,40,0,null,32]

    Explanation
    ProductOfNumbers productOfNumbers = new ProductOfNumbers();
    productOfNumbers.add(3);        // [3]
    productOfNumbers.add(0);        // [3,0]
    productOfNumbers.add(2);        // [3,0,2]
    productOfNumbers.add(5);        // [3,0,2,5]
    productOfNumbers.add(4);        // [3,0,2,5,4]
    productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
    productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
    productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
    productOfNumbers.add(8);        // [3,0,2,5,4,8]
    productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32
 */
public class Solution {
    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
        productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32
    }
}

// prefix array solution ???
// Prefix sum https://en.wikipedia.org/wiki/Prefix_sum
// best solution https://leetcode.com/problems/product-of-the-last-k-numbers/discuss/510260/JavaC%2B%2BPython-Prefix-Product
class ProductOfNumbers {

    private List<Integer> prefix = new ArrayList<>();

    public ProductOfNumbers() {
    }

    /**
     * T = O(1)
     * S = O(number of calls from last "zero" call)
     */
    public void add(int num) {
        if (num != 0) {
            prefix.add(prefix.size() != 0 ? num * prefix.get(prefix.size() - 1) : num);
        } else {
            prefix = new ArrayList<>(); // or just prefix.clear();
        }
    }

    /**
     * T(k) = O(1)
     * S = O(number of calls from last "zero" call)
     */
    public int getProduct(int k) {
        if (k == prefix.size()) return prefix.get(prefix.size() - 1);
        return k < prefix.size()
                ? prefix.get(prefix.size() - 1) / prefix.get(prefix.size() - 1 - k)
                : 0;
    }
}

//// KISS solution
//// leetcode: Time Limit Exceeded
//// VERY BAD SOLUTION!!!
//class ProductOfNumbers {
//
//    private final List<Integer> nums = new ArrayList<>();
//
//    public ProductOfNumbers() {
//    }
//
//    /**
//     * T = O(1)
//     */
//    public void add(int num) {
//        nums.add(num);
//    }
//
//    /**
//     * T(k) = O(k)
//     */
//    public int getProduct(int k) {
//        int product = 1;
//        for (int i = 0; i < k; i++) {
//            product *= nums.get(nums.size() - 1 - i);
//        }
//        return product;
//    }
//}