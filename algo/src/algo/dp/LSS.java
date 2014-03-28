package algo.dp;

/*
 * 整数数组的最长子数组和（Longest Subarray Sum）
 * 
 * dp[i]表示以num_i结尾的最长连续子数组之和，则有以下的状态转移矩阵：
 * dp[i] = dp[i-1] + num_i  if dp[i-1] > 0
 * dp[i] = num_i if dp[i-1] < 0
 * 
 */
public class LSS {

	static int maxSum = -1;

	static void lss(int[] x, int[] dp) {
		// 1. 设置dp的初值
		dp[0] = 0;

		// 2. 自底向上dp
		for (int i = 1; i <= x.length; i++) {
			if (dp[i - 1] >= 0) {
				dp[i] = dp[i - 1] + x[i - 1];
			} else {
				dp[i] = x[i - 1];
			}

			if (dp[i] > maxSum) {
				maxSum = dp[i];
			}
		}

		// 3. 依据最优值，输出最优解
		System.out.println(maxSum);
	}

	public static void main(String[] args) {
		int[] x = { -2, 11, -4, 13, -5, -2 };
		lss(x, new int[x.length + 1]);
	}
}
