package algo.dp.knapsack;

/*
 * 01背包问题
 */
public class Knapsack01 {

	static int N = 3;
	static int V = 10;
	static int[] weights = { 3, 4, 5 };
	static int[] values = { 4, 6, 7 };

	static void knapsack01(int[][] dp) {
		// 1. 设置dp初值
		for (int j = 1; j <= V; j++) { // 将第一个物品放入背包，当背包容量大于物品重量时，价值为物品价值，否则为0
			if (weights[0] <= j) {
				dp[1][j] = values[0];
			} else {
				dp[1][j] = 0;
			}
		}

		// 2.自底向上dp
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= V; j++) {
				if (j - weights[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j
							- weights[i - 1]]
							+ values[i - 1]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		// 3. 依据最优值，输出最优解
		output(dp);
	}

	/*
	 * 倒序的方式（从dp[N][V]推到dp[1][1]）从最优值寻求最优解
	 */
	private static void output(int[][] dp) {
		System.out.println(dp[N][V]);
		int i = N;
		int j = V;
		int k = N;
		boolean[] res = new boolean[k]; // 保存每个物品是否被选择
		while (i > 0 && j > 0) { // 从dp[1][1]开始有效，故循环条件是i>0,j>0
			if (dp[i][j] == dp[i - 1][j]) {
				res[--k] = false; // 没有选择第i个物品
				i--;
			} else {
				res[--k] = true;
				j -= weights[i - 1]; // 选择第i个物品
				i--;
			}
		}

		for (i = 0; i < N; i++) {
			if (res[i]) {
				System.out.print(i + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] dp = new int[N + 1][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[V + 1];
		}
		knapsack01(dp);
	}
}
