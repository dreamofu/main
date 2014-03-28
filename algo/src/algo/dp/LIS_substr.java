package algo.dp;

/*
 * 最长（连续）递增子串
 */
public class LIS_substr {

	static int maxLen = -1;
	static int maxIdx = -1;

	static void lis(int[] x, int[] dp) {
		// 1. 设置dp初值
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			dp[i] = 0;
		}

		// 2.自底向上dp
		for (int i = 2; i <= x.length; i++) {
			if (x[i - 1] > x[i - 2]) {
				dp[i] = dp[i - 1] + 1;
			} else {
				dp[i] = 1;
			}

			if (dp[i] > maxLen) {
				maxLen = dp[i];
				maxIdx = i - 1;
			}
		}

		// 3. 依据最优值，输出最优解
		output(x, dp);
	}

	static void output(int[] x, int[] dp) {
		for (int i = maxIdx - maxLen + 1; i <= maxIdx; i++) {
			System.out.print(x[i] + " ");
		}
	}

	public static void main(String[] args) {
		int[] x = { 2, 4, 6, 1, 3, 4, 6, 5, 8, 10, 3 };
		lis(x, new int[x.length + 1]);
	}
}
