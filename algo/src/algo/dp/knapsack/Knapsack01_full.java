package algo.dp.knapsack;

/*
 * 01背包恰好装满的情况
 * 
 * 与01背包的唯一区别在于，背包必须装满。
 * 体现在代码上，是在初始化的时候，装满的情况要求物品的总和恰好等于背包容量
 * 如果没装满，则可以用一个大负数来表示非法
 */
public class Knapsack01_full {
	static int N = 3;
	static int V = 10;
	static int[] weights = { 6, 4, 5 };
	static int[] values = { 4, 6, 8 };

	static void knapsack01_full(int[][] dp) {
		// 1. 设置dp初值
		/*
		 * 将第一个物品放入背包，只有当背包容量==物品重量时，才装满，此时价值为物品价值，
		 * 其他情况都是非法的，可以用一个大负数来表示
		 */
		for (int j = 1; j <= V; j++) {
			if (weights[0] == j) {
				dp[1][j] = values[0];
			} else {
				dp[1][j] = Integer.MIN_VALUE; //使用MIN_VALUE作为非法的标识
			}
		}

		// 2.自底向上dp
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= V; j++) {
				if (j - weights[i - 1] >= 0 && dp[i - 1][j - weights[i - 1]] != Integer.MIN_VALUE) { //若子问题合法，原问题才可以分解
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
				} else {
					dp[i][j] = dp[i - 1][j];
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
		boolean[] res = new boolean[k];
		while (i > 0 && j > 0) {
			if (dp[i][j] == dp[i - 1][j]) {
				res[--k] = false;
				i--;
			} else {
				res[--k] = true;
				j -= weights[i - 1];
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
		knapsack01_full(dp);
	}

}
