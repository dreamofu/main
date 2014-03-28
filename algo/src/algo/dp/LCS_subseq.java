package algo.dp;

import java.util.Arrays;

/*
 * 最长公共子序列LCS(Longest Common Subsequence)
 * 
 * DP最终处理的还是数值（通过求极值来求最优解），找到了最优值，就找到了最优方案。
 * 当找到最优值之后，直接根据状态转移矩阵倒推最优方案。
 */
public class LCS_subseq {

	/*
	 * 用一个二维的表格dp来保存已经发现的结果，自底向上的方式进行DP
	 * 当遇到重叠的子问题时，由于dp中已经保存了结果，因此可以直接查询，而不用重新计算！
	 */
	static void lcs(char[] x, char[] y, int[][] dp) {
		// 1. 设置dp的初值
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = 0;
		}

		// 2. 自底向上进行DP
		/*
		 * 之所有可以按从i=1~len, j=1~len的双重循环赋值，因为dp[i][j]仅依赖于它的左边、左上、上边三个值，
		 * 而按行遍历的方式，正好符合这样的依赖关系！
		 */
		for (int i = 1; i <= x.length; i++) {
			for (int j = 1; j <= y.length; j++) {
				if (x[i - 1] == y[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else if (dp[i - 1][j] > dp[i][j - 1]) { //#############自底向上时，依赖i-1的值
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}
		
		// 3. 依据找到的最优值，输出最优解
		output(x, y, dp);
	}
	
	/*
	 * 如何根据已经发现的最优值，找出最优解？
	 * 
	 * 输出最优解与找出最优值的过程正好相反，找最优值是自底向上的，而输出最优解是自顶向下的。
	 * 输出最优解的退出条件是达到dp的初值
	 * 输出解的过程中，每次都退回到当前最优值所依赖的下一层的最优值元素上
	 */
	static void output(char[] x, char[] y, int[][] dp) {
		int i = x.length;
		int j = y.length;
		int k = dp[x.length][y.length]; // 取得最优值
		char[] res = new char[k];
		while (i > 0 && j > 0) { //当i=0或j=0时，已经到达了dp的初值，此时应退出
			if (dp[i][j] == dp[i - 1][j - 1] + 1) { // 通过拼接lcs(i-1,j-1)和相等的字符得来的
				res[--k] = x[i - 1];
				i--; //此处不要忘了退到依赖的<i-1,j-1>上
				j--;
			} else if (dp[i][j] == dp[i - 1][j]) { //#############，当退回去找最优解时，退到依赖的i-1的值上
				i--; 
			} else {
				j--;
			}
		}
		System.out.println(Arrays.toString(res));
	}
	
	
	public static void main(String[] args) {
		char[] x = "ABCBAB".toCharArray();
		char[] y = "BCABA".toCharArray();
		int[][] dp = new int[x.length + 1][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[y.length + 1];
		}
		lcs(x, y, dp);
	}
}
