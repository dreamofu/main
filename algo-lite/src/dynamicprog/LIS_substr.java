package dynamicprog;

import java.util.Arrays;

public class LIS_substr {

	
	static void lis(int[] x){
		int[] dp = new int[x.length + 1];
		int maxLen = 0;
		int maxI = 0;
		
		//1. init dp
		dp[0] = 0;
		dp[1] = 1;
		
		//2. bottom-up
		for(int i=2; i<=x.length; i++){
			if(x[i-1] > x[i-2]){
				dp[i] = dp[i-1] + 1;
			}else{
				dp[i] = 1;
			}
			
			if(dp[i] > maxLen){
				maxLen = dp[i];
				maxI = i-1;
			}
		}
		
		//3. top-down
		int[] res = new int[maxLen];
		int k = res.length - 1;
		for(int i = maxI; i> maxI - maxLen; i--){
			res[k--] = x[i];
		}
		
		System.out.println(Arrays.toString(res));
	}
	
	
	public static void main(String[] args) {
		int[] x = { 2, 4, 6, 1, 3, 4, 6, 5, 8, 10, 3 };
		LIS_substr.lis(x);
	}
}
