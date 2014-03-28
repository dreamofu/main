package algo.dp.knapsack;

/*
 * 硬币兑换问题：{1,2,5,10}这四个硬币，组合成面值n，所需的最少硬币数为多少
 * 
 * 其实是一个（恰好装满的）完全背包问题：
 * n看做背包容量，有四种不同的物品{1,2,5,10}，不限量，且它们的价值都是1，最终求如何选择物品，使总的价值（硬币的个数）最少？
 * dp[i][j]变为前i种物品放入容量为j的背包后，取得的最小价值
 */
public class CoinExchange {
	static int N = 4;
	static int V = 7;
	static int[] weights = { 1, 2, 5, 10 };
	static int[] values = { 1, 1, 1, 1 };

	static void knapsackComplete_full(int[][] dp) {
		// 1. 设置dp初值
		/*
		 * 将第一个物品放入背包，当背包容量是物品重量的倍数时（此时保证装满），价值为物品价值*倍数，否则为0
		 */
		for (int j = 1; j <= V; j++) {
			if (weights[0] <= j && j % weights[0] == 0) {
				dp[1][j] = values[0] * (j / weights[0]);
			} else {
				dp[1][j] = Integer.MAX_VALUE; //使用MAX_VALUE作为非法的标识
			}
		}

		// 2.自底向上dp
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= V; j++) {
				dp[i][j] = Integer.MAX_VALUE; //首先标记为非法
				for (int k = 0; k <= V / weights[i - 1]; k++) {
					if (j - k * weights[i - 1] >= 0){
						if(dp[i - 1][j - k * weights[i - 1]] != Integer.MAX_VALUE && dp[i - 1][j - k * weights[i - 1]] + k * values[i - 1] < dp[i][j]) {
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
			for (; m <= V / weights[i - 1]; m++) {
				if (dp[i][j] == dp[i - 1][j - m * weights[i - 1]] + m * values[i - 1]) {
					res[--k] = m;
					break;
				}
			}
			j -= m * weights[i-1];
			i--;
		}
		
		for (i = 0; i < N; i++) {
			System.out.print(res[i] + " ");
		}
	}

	public static void main(String[] args) {
		int[][] dp = new int[N + 1][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[V + 1];
		}
		knapsackComplete_full(dp);
	}
	
}
