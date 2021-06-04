package com.leetcode.solutions.Regular_Expression_Matching_10;

/**
 * 10. Regular Expression Matching
 * <p>
 * https://leetcode.com/problems/regular-expression-matching/
 * <p>
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 */

/*
    Example 1:
    Input: s = "aa", p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".

    Example 2:
    Input: s = "aa", p = "a*"
    Output: true
    Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

    Example 3:
    Input: s = "ab", p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".

    Example 4:
    Input: s = "aab", p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

    Example 5:
    Input: s = "mississippi", p = "mis*is*p*."
    Output: false
 */

/*
    Constraints:
    0 <= s.length <= 20
    0 <= p.length <= 30
    s contains only lowercase English letters.
    p contains only lowercase English letters, '.', and '*'.
    It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) return true;
        if (s.length() != 0 && p.length() == 0) return false;
        return isMatch(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean isMatch(char[] s, char[] p, int sPointer, int pPointer) {
        if (pPointer == p.length && sPointer == s.length) return true;
        for (int i = pPointer; i < p.length; i++) {
            char pChar = p[i];
            boolean isQuantifier = lookupQuantifier(p, i);

            if (isQuantifier) {
                int sPointerLoop = sPointer;
                if (isMatch(s, p, sPointerLoop, i + 2)) return true;
                while (sPointerLoop < s.length && charEqual(s[sPointerLoop], pChar)) {
                    if (isMatch(s, p, ++sPointerLoop, i + 2)) return true;
                }
                i++;
            } else {
                if (sPointer < s.length && !charEqual(s[sPointer], pChar)) return false;
                sPointer++;
            }
        }
        return sPointer == s.length;
    }

    private boolean lookupQuantifier(char[] p, int pPointer) {
        return pPointer + 1 < p.length && p[pPointer + 1] == '*';
    }

    private boolean charEqual(char s, char p) {
        return p == '.' || s == p;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("mississippi", "mis*is*ip*."));
    }
}