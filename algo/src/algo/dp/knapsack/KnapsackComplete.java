package algo.dp.knapsack;

/*
 * 完全背包
 */
public class KnapsackComplete {

	static int N = 3;
	static int V = 10;
	static int[] weights = { 3, 4, 5 };
	static int[] values = { 4, 6, 7 };

	static void knapsackComplete(int[][] dp) {
		// 1. 设置dp初值
		for (int j = 1; j <= V; j++) { // 将第一个物品放入背包，当背包容量大于物品重量时，价值为物品价值*k，否则为0
			if (weights[0] <= j) {
				dp[1][j] = values[0] * (j / weights[0]);
			} else {
				dp[1][j] = 0;
			}
		}

		// 2.自底向上dp
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= V; j++) {
				dp[i][j] = -1;
				for (int k = 0; k <= V / weights[i - 1]; k++) {
					if (j - k * weights[i - 1] >= 0) {
						if (dp[i - 1][j - k * weights[i - 1]] + k * values[i - 1] > dp[i][j]) {
							dp[i][j] = dp[i - 1][j - k * weights[i - 1]] + k * values[i - 1];
						}
					} else {
						break;
					}
				}
			}
		}

		// 3. 依据最优值，输出最优解
		output(dp);
	}

	private static void output(int[][] dp) {
		System.out.println(dp[N][V]);
		int i = N;
		int j = V;
		int k = N;
		int[] res = new int[k];
		while (i > 0 && j > 0) {
			int m = 0;
			for (; m <= V / weights[k - 1]; m++) {
				if (dp[i][j] == dp[i - 1][j - m * weights[k - 1]] + m * values[i - 1]) {
					res[--k] = m;
					break;
				}
			}
			j -= m * weights[i - 1];
			i--;
		}

		for(i = 0; i<N; i++){
			System.out.print(res[i]+" ");
		}
	}

	public static void main(String[] args) {
		int[][] dp = new int[N + 1][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[V + 1];
		}
		knapsackComplete(dp);
	}
}
