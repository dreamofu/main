package algo.dp.knapsack;

/*
 * 完全背包恰好装满的情况
 * 
 */
public class KnapsackComplete_full {
	static int N = 3;
	static int V = 10;
	static int[] weights = { 5, 4, 5 };
	static int[] values = { 4, 6, 8 };

	static void knapsackComplete_full(int[][] dp) {
		// 1. 设置dp初值
		/*
		 * 将第一个物品放入背包，当背包容量是物品重量的倍数时（此时保证装满），价值为物品价值*倍数，否则为0
		 */
		for (int j = 1; j <= V; j++) {
			if (weights[0] <= j && j % weights[0] == 0) {
				dp[1][j] = values[0] * (j / weights[0]);
			} else {
				dp[1][j] = Integer.MIN_VALUE; //使用MIN_VALUE作为非法的标识
			}
		}

		// 2.自底向上dp
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= V; j++) {
				dp[i][j] = Integer.MIN_VALUE; //首先标记为非法
				for (int k = 0; k <= V / weights[i - 1]; k++) {
					if (j - k * weights[i - 1] >= 0){
						if(dp[i - 1][j - k * weights[i - 1]] != Integer.MIN_VALUE && dp[i - 1][j - k * weights[i - 1]] + k * values[i - 1] > dp[i][j]) {
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
				if (dp[i][j] == dp[i - 1][j - m * weights[i - 1]] + m * values[i - 1]) {
					res[--k] = m;
					break;
				}
			}
			j -= m * weights[i-1];
			i--;
		}
		
		for(i=0; i<N; i++){
			System.out.print(res[i]+" ");
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
