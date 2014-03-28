package algo.dp;

/*
 * 最大数对之差（Maximum Pair Diff）
 * 
 * 在数组中，数字减去它右边的数字得到一个数对之差。求所有数对之差的最大值。
 * 例如在数组{2, 4, 1, 16, 7, 5, 11, 9}中，数对之差的最大值是11，是16减去5的结果。
 */
public class MPD {

	static int maxDiff = -1;

	static void mpd(int[] x, int[] dp) {
		if (x.length < 2) {
			maxDiff = -1;
		}

		// 1. 设置dp的初值
		dp[2] = x[1] - x[0];

		// 2. 自底向上dp
		for (int i = 3; i <= x.length; i++) {
			if (dp[i - 1] >= 0) {
				dp[i] = dp[i - 1] + x[i - 2] - x[i - 1];
			} else {
				dp[i] = x[i - 2] - x[i - 1];
			}

			if (dp[i] > maxDiff) {
				maxDiff = dp[i];
			}
		}

		// 3. 依据最优值，输出最优解
		System.out.println(maxDiff);
	}

	public static void main(String[] args) {
		int[] x = { 2, 4, 1, 16, 7, 5, 11, 9 };
		mpd(x, new int[x.length + 1]);
	}
}
