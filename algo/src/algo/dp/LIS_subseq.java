package algo.dp;

import java.util.Arrays;

/*
 * 最长（不连续）递增子序列
 */
public class LIS_subseq {

	static int maxLen = -1;
	static int maxIdx = -1;

	/*
	 * dp保存以尾字符结尾的最长递增子序列 
	 * path保存拼接前面的哪一个子序列
	 */
	static void lis(int[] x, int[] dp, int[] path) {
		// 1. 设置dp初值
		dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			dp[i] = 0;
		}
		path[0] = -1;
		path[1] = -1;

		// 2. 自底向上dp
		for (int i = 2; i <= x.length; i++) {
			dp[i] = 1; // default value
			for (int j = 1; j < i; j++) {
				if (x[i - 1] >= x[j - 1] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
					path[i] = j;
				}
			}

			if (dp[i] > maxLen) {
				maxLen = dp[i];
				maxIdx = i;
			}
		}

		// 3. 依据最优值，输出最优解
		output(x, dp, path);
	}

	private static void output(int[] x, int[] dp, int[] path) {
		int i = maxIdx;
		int k = maxLen;
		int[] res = new int[k];
		while (i > 0) {
			res[--k] = x[i - 1];
			i = path[i];
		}
		System.out.println(Arrays.toString(res));
	}

	public static void main(String[] args) {
		int[] data = { 1, -1, 2, -3, 4, -5, 6, -7 };
		lis(data, new int[data.length + 1], new int[data.length + 1]);

		maxLen = -1;
		maxIdx = -1;
		data = new int[] { 4, 2, 6, 3, 1, 5 };
		lis(data, new int[data.length + 1], new int[data.length + 1]);

	}
}
