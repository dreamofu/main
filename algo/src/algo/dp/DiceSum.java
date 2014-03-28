package algo.dp;

/*
 * 5个骰子仍在地上，点数之和为s，求所有可能的情况
 * 
 * 设dp[i][j]表示前i个骰子的点数和为j的不同方法
 * 则有以下的状态转移方程：
 * dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] + ... + dp[i-1][j-6]
 * 分别对应第i个骰子出现1点，2点，。。6点的情况 
 *  
 */
public class DiceSum {

	static final int INVALID = Integer.MIN_VALUE; // 非法标记
	static final int S = 8; // 点数和

	static void diceSum(int[][] dp) {
		// 1. 设置dp的初始值
		for (int j = 1; j <= 6; j++) {
			dp[1][j] = 1;
		}
		for (int j = 7; j < dp.length; j++) {
			dp[1][j] = INVALID;
		}

		// 2. 自底向上dp
		for (int i = 2; i <= 5; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				int sum = 0, k = 1;
				while (j - k >= 1 && dp[i - 1][j - k] != INVALID) {
					sum += dp[i - 1][j - k];
					k++;
				}
				dp[i][j] = sum;
			}
		}

		// 3. 输出最优解
		/*
		 * for(int i=1; i<=5;i++){ for(int j=1; j<dp[i].length;j++){
		 * System.out.print(dp[i][j]+"\t"); } System.out.println(); }
		 */
		System.out.println(dp[5][dp[5].length - 1]);
	}

	public static void main(String[] args) {
		int[][] dp = new int[6][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[S + 1];
		}
		diceSum(dp);
	}
}
