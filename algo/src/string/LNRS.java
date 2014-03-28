package string;

/*
 * 
 */
public class LNRS {

	static int maxLen = -1;
	static int maxIdx = -1;

	static void lnrs(char[] x, int[] dp) {
		// 1. 设置dp的出初始值
		dp[0] = 0;
		dp[1] = 1;

		// 2. 自底向上dp
		int lastIdx = 1; // 保存lrns(n-1)的起始下标
		for (int i = 2; i <= x.length; i++) {
			for (int j = i - 1; j >= lastIdx; j--) { // 从尾部开始遍历，看是否有重复
				if (x[j - 1] == x[i - 1]) {
					dp[i] = i - j;
					lastIdx = j + 1;
					break;
				} else if (j == lastIdx) {
					dp[i] = dp[i - 1] + 1;
				}
			}

			if (dp[i] > maxLen) {
				maxLen = dp[i];
				maxIdx = i - 1;
			}
		}

		// 3. 依据最优值，输出最优解
		output(x, dp);
	}

	static void output(char[] x, int[] dp) {
		for (int i = maxIdx - maxLen + 1; i <= maxIdx; i++) {
			System.out.print(x[i]);
		}
		System.out.println("\n" + maxLen);
	}

	public static void main(String[] args) {
		char[] data = "abcacbdef".toCharArray();
		int[] dp = new int[data.length + 1];
		lnrs(data, dp);
	}
}
