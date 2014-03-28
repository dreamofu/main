package dynamicprog;

import java.util.Arrays;

public class LCS_subseq {
	
	
	static void lcs(char[] x, char[] y){
		// dp数组保存了最长公共子序列的长度
		int[][] dp = new int[x.length+1][y.length+1];
		
		//1. dp数组初始化
		for(int i=0; i<=x.length; i++){
			dp[i][0] = 0;
		}
		for(int j=0; j<=y.length; j++){
			dp[0][j] = 0;
		}
		
		//2. 自底向上求解dp
		for(int i=1; i<=x.length; i++){
			for(int j=1; j<=y.length; j++){
				if (x[i-1] == y[j-1]){
					dp[i][j] = dp[i-1][j-1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		//3. 自顶向下输出最优解
		int i = x.length;
		int j = y.length;
		char[] res = new char[dp[x.length][y.length]];
		int k = res.length - 1;
		while(i> 0 && j>0){
			if(dp[i][j] == dp[i-1][j-1] + 1){
				res[k--] = x[i-1];
				i--;
				j--;
			}else if(dp[i][j] == dp[i-1][j]){
				i--;
			}else{
				j--;
			}
		}
		
		System.out.println(Arrays.toString(res));
	}
	

	public static void main(String[] args) {
		char[] x = "ABCBAB".toCharArray();
		char[] y = "BCABA".toCharArray();
		LCS_subseq.lcs(x, y);
	}
}
