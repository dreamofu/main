package dynamicprog;

public class MPD {

	
	static void mpd(int[] x){
		int[] dp = new int[x.length + 1];
		int max = 0;
		
		//1. init dp
		dp[0] = 0;
		dp[1] = 0;

		// bottom up
		for(int i=2; i<=x.length; i++){
			if(dp[i-1] >= 0){
				dp[i] = dp[i-1] + x[i-2] - x[i-1];
			}else{
				dp[i] = x[i-2] - x[i-1];
			}
			
			if(dp[i] > max){
				max = dp[i];
			}
		}
		
		System.out.println(max);
	}
	
	
	public static void main(String[] args) {
		int[] x = { 2, 4, 1, 16, 7, 5, 11, 9 };
		MPD.mpd(x);
	}
}
