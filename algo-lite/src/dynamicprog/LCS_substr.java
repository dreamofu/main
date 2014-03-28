package dynamicprog;

import java.util.Arrays;

public class LCS_substr {
	
	
	static void lcs(char[] x, char[] y){
		int[][] dp = new int[x.length+1][y.length+1];
		int maxLen = 0;
		int maxI = 0;
		int maxJ = 0;
		
		//1. init dp array
		for(int i=0; i<=x.length; i++){
			dp[i][0] = 0;
		}
		for(int j=0; j<=y.length; j++){
			dp[0][j] = 0;
		}
		
		//2. bottom-up 
		for(int i=1; i<=x.length; i++){
			for(int j=1; j<=y.length; j++){
				if(x[i-1] == y[j-1]){
					dp[i][j] = dp[i-1][j-1] + 1;
					if (dp[i][j] > maxLen){
						maxLen = dp[i][j];
						maxI = i - 1;
						maxJ = j - 1;
					}
				}else{
					dp[i][j] = 0;
				}
			}
		}
		
		//3. top-down
		// 从x的maxI处往前maxLen个字符/从y的maxJ处往前maxLen个字符
		char[] res = new char[maxLen];
		int k = res.length - 1;
		for(int i = maxI; i>maxI - maxLen; i--){
			res[k--] = x[i];
		}
		
		System.out.println(Arrays.toString(res));
	}
	
	
	public static void main(String[] args) {
		char[] x = "ABACBDAB".toCharArray();
		char[] y = "BDCABA".toCharArray();
		LCS_substr.lcs(x, y);
	}
}
