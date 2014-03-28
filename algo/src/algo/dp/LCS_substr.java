package algo.dp;

import java.util.Arrays;

/*
 * 最长公共子串
 * 
 * 动态规划的思路
 */
public class LCS_substr {

	static int xIdx = -1;
	static int yIdx = -1;
	static int maxLen = -1;

	static void lcs(char[] x, char[] y, int[][] dp) {
		// 1. 设置dp的初值
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = 0;
		}

		// 2. 自底向上的dp
		for (int i = 1; i <= x.length; i++) {
			for (int j = 1; j <= y.length; j++) {
				if (x[i - 1] == y[j - 1]) { // 条件
					dp[i][j] = dp[i - 1][j - 1] + 1; // 结果
					if (dp[i][j] > maxLen) {
						maxLen = dp[i][j];
						xIdx = i;
						yIdx = j;
					}
				} else {
					dp[i][j] = 0;
				}
			}
		}

		// 2. 依据找出的最优值，输出最优解
		output(x, y, dp);
	}

	static void output(char[] x, char[] y, int[][] dp) {
		int i = xIdx;
		int j = yIdx;
		int k = dp[i][j];
		char[] res = new char[k];
		while (i > 0 && j > 0) {
			/* 用结果反推满足的条件 */
			if (dp[i][j] == dp[i - 1][j - 1] + 1) {// 结果
				res[--k] = x[i - 1]; // 条件
				i--;
				j--;
			} else { //不等，则公共子串结束
				break;
			}
		}
		System.out.println(Arrays.toString(res));
	}

	public static void main(String[] args) {
		char[] x = "ABACBDAB".toCharArray();
		char[] y = "BDCABA".toCharArray();
		int[][] dp = new int[x.length + 1][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[y.length + 1];
		}
		lcs(x, y, dp);
	}
}
