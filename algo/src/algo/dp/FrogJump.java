package algo.dp;

/*
 * 
 * 跳n个台阶共dp[n]种方法，则有以下的状态转移方程：
 * dp[n] = dp[n-1] + dp[n-2]
 */
public class FrogJump {

	static void jump(int n, int[] dp) {
		// 1. 设置dp初始值
		dp[1] = 1;
		dp[2] = 2;

		// 2. 自底向上dp
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		// 3. 依据最优值，输出最优解
		System.out.println(dp[n]);
	}
	
	public static void main(String[] args) {
		jump(10, new int[11]);
	}
}
