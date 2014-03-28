package algo.dp;

/*
 * 字符串的编辑距离
 */
public class EditDistance {

	static void editDist(char[] x, char[] y, int[][] dp) {
		// 1. 设置dp初值
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = j;
		}

		// 2. 自底向上dp
		for (int i = 1; i <= x.length; i++) {
			for (int j = 1; j <= y.length; j++) {
				if (x[i-1] == y[j-1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
				}
			}
		}

		// 3. 依据最优值，输出最优解
		output(x, y, dp);
	}

	private static void output(char[] x, char[] y, int[][] dp) {
		int i = x.length;
		int j = y.length;
		
		while (i > 0 && j > 0) {
			if (dp[i][j] == dp[i - 1][j - 1]) {
				System.out.println(String.format("%c == %c", x[i-1], y[j-1]));
				i--;
				j--;
			} else {
				if (dp[i][j] == 1 + dp[i - 1][j]) {
					System.out.println(String.format("delete %c", x[i-1]));
					i--;
				} else if (dp[i][j] == 1 + dp[i][j - 1]) {
					System.out.println(String.format("delete %c", y[j-1]));
					j--;
				} else if (dp[i][j] == 1 + dp[i - 1][j - 1]) {
					System.out.println(String.format("change %c to %c", x[i-1], y[j-1]));
					i--;
					j--;
				}
			}
		}
	}

	private static int min(int i, int j, int k) {
		return Math.min(i, Math.min(j, k));
	}
	
	public static void main(String[] args) {
		char[] x = "smile".toCharArray();
		char[] y = "sunning".toCharArray();
		int[][] dp = new int[x.length + 1][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[y.length + 1];
		}
		editDist(x, y, dp);
	}
}
