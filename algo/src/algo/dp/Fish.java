package algo.dp;

/*
 * 20条鱼放到20个桶里，共有多少种不同的排列？
 * 
 * dp[i][j] 表示在前i个桶放j条鱼的不同放法
 */
public class Fish {

	static void fish(int[][] dp) {
		// 1. 设置DP的初值
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = 0;
		}
		for (int i = 0; i <= 10; i++) {// 第一个桶放0-10条鱼都是合法的
			dp[1][i] = 1;
		}

		// 2. 自底向上的方式DP
		for (int backet = 2; backet < dp.length; backet++) {
			for (int fish = 0; fish < dp[0].length; fish++) {
				for (int k = 0; k <= 10 && fish - k >= 0; k++) {
					dp[backet][fish] += dp[backet - 1][fish - k];
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] dp = new int[21][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[21];
		}
		fish(dp);
		System.out.println(dp[20][20]);
		
		dp = new int[21][];
		for(int i=0; i<dp.length; i++){
			dp[i] = new int[181];
		}
		fish(dp);
		System.out.println(dp[20][180]);
		
		dp = new int[3][];
		for(int i=0; i<dp.length; i++){
			dp[i] = new int[11];
		}
		fish(dp);
		System.out.println(dp[2][10]);
	}
}
