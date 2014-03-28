package dynamicprog;

public class LSS {

	static void lss(int[] x){
		int[] dp = new int[x.length + 1];
		int max = 0;
		
		//1. init dp
		dp[0] = 0;
		
		//2. bottom up
		for(int i=1; i<=x.length; i++){
			if(dp[i-1] > 0){
				dp[i] = dp[i-1] + x[i-1];
			}else{
				dp[i] = x[i-1];
			}
			
			if(dp[i] > max){
				max = dp[i];
			}
		}
		
		//3. top down
		System.out.println("max:" + max);
		
	}
	
	
	public static void main(String[] args) {
		int[] x = { -2, 11, -4, 13, -5, -2 };
		LSS.lss(x);
	}
}
