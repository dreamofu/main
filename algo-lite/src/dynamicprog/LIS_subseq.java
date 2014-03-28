package dynamicprog;

import java.util.Arrays;

public class LIS_subseq {

	
	static void lis(int[] x){
		int[] dp = new int[x.length+1];
		int maxLen = 0;
		int maxI = 0;
		
		//1. init dp
		dp[0] = 1;
		
		//2. bottom-up
		for(int i=1; i<=x.length; i++){
			int max = 1;
			for(int j=1; j<i; j++){
				if(x[j-1] < x[i-1] && (dp[j] + 1)> max){
					max = dp[j]+1;
				}
			}
			dp[i] = max;
			
			if(dp[i] > maxLen){
				maxLen = dp[i];
				maxI = i-1;
			}
		}
		
		//3. top-down
		int[] res = new int[maxLen];
		int k = res.length -1;
		int i = maxI;
		res[k--] = x[i];
		while(i>0){
			int j = i-1;
			for(;j>=0;j--){
				if(dp[i+1] == dp[j+1] + 1){
					res[k--] = x[j];
					break;
				}
			}
			i = j;
		}
		
		System.out.println(Arrays.toString(res));
	}
	
	public static void main(String[] args) {
		int[] x = { 1, -1, 2, -3, 4, -5, 6, -7 };
		LIS_subseq.lis(x);
		
		x = new int[] { 4, 2, 6, 3, 1, 5 };
		LIS_subseq.lis(x);
	}
}
