package com.leetcode.solutions.Number_of_Substrings_Containing_All_Three_Characters_1358;

import static java.lang.Math.min;

/**
 * 1358. Number of Substrings Containing All Three Characters
 * <p>
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 * <p>
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 */

/*
    Example 1:
    Input: s = "abcabc"
    Output: 10
    Explanation: The substrings containing at least one occurrence of
    the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).

    Example 2:
    Input: s = "aaacb"
    Output: 3
    Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".

    Example 3:
    Input: s = "abc"
    Output: 1
 */

/*
    Constraints:
    3 <= s.length <= 5 x 10^4
    s only consists of a, b or c characters.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().numberOfSubstrings("ababbbc"));
    }

    /**
     * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/discuss/516977/JavaC%2B%2BPython-Easy-and-Concise
     * T(n) = O(n)
     * S(n) = O(1)
     */
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int result = 0;
        int i = 0;
        int[] count = {0, 0, 0};

        for (int j = 0; j < n; j++) {
            ++count[s.charAt(j) - 'a'];
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                --count[s.charAt(i++) - 'a'];
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/discuss/516977/JavaC%2B%2BPython-Easy-and-Concise
     * T(n) = O(n)
     * S(n) = O(1)
     */
    public int numberOfSubstrings2(String s) {
        int n = s.length();
        int result = 0;
        int[] index = {-1, -1, -1};

        for (int i = 0; i < n; i++) {
            index[s.charAt(i) - 'a'] = i;
            result += min(index[0], min(index[1], index[2]));
        }
        return result;
    }

//    /**
//     * Own solution
//     * not bad, but not clear
//     * T(n) = O(n)
//     * S(n) = O(1)
//     */
//    public int numberOfSubstrings(String s) {
//        char[] chars = s.toCharArray();
//        int n = chars.length;
//        int i = 0;
//        int j = 0;
//        int answer = 0;
//        int[] c = new int[3];
//
//        while (true) {
//
//            while (!(c[0] != 0 && c[1] != 0 && c[2] != 0) && j < n) {
//                c[chars[j] - 'a']++;
//                j++;
//            }
//
//            while (c[0] != 0 && c[1] != 0 && c[2] != 0) {
//                answer += n - j + 1;
//                c[chars[i] - 'a']--;
//                i++;
//            }
//            if (j == n) return answer;
//        }
//    }
}
