package com.leetcode.solutions.Minimum_Number_of_Steps_to_Make_Two_Strings_Anagram_1347;

/**
 * 1347. Minimum Number of Steps to Make Two Strings Anagram
 *
 * https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
 *
 * Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
 * Return the minimum number of steps to make t an anagram of s.
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 */

/*
    Example 1:
    Input: s = "bab", t = "aba"
    Output: 1
    Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.

    Example 2:
    Input: s = "leetcode", t = "practice"
    Output: 5
    Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.

    Example 3:
    Input: s = "anagram", t = "mangaar"
    Output: 0
    Explanation: "anagram" and "mangaar" are anagrams.

    Example 4:
    Input: s = "xxyyzz", t = "xxyyzz"
    Output: 0

    Example 5:
    Input: s = "friend", t = "family"
    Output: 4
 */

/*
    Constraints:
    1 <= s.length <= 50000
    s.length == t.length
    s and t contain lower-case English letters only.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minSteps("bab", "aba"));
        System.out.println(new Solution().minSteps("leetcode", "practice"));
        System.out.println(new Solution().minSteps("anagram", "mangaar"));
        System.out.println(new Solution().minSteps("xxyyzz", "xxyyzz"));
        System.out.println(new Solution().minSteps("friend", "family"));
    }

    /**
     * T(n) = O(n)
     * S(n) = O(1) {S(n) = O(n) in this case, because s.toCharArray() - is copy of original array}
     */
    public int minSteps(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int[] result = new int[26];

        for (int i = 0; i < s.length(); i++) {
            result[sChars[i] - 'a']++;
            result[tChars[i] - 'a']--;
        }
        int answer = 0;
        for (int curr: result) if (curr > 0) answer += curr;
        return answer;
    }
}
