package knapsack.dynamicprog;

public class Knapsack01 {

	
	static void knapsack01(int[] weights, int[] values, int W){
		int numItems = weights.length;
		int[][] dp = new int[numItems+1][W+1];
		
		//1. init dp
		// 将第一个物品放入容量为0-V的背包，可获得的最大价值
		for(int j=0; j<=W; j++){
			if(j >= weights[0]){
				dp[1][j] = values[0];
			}else{
				dp[1][j] = 0;
			}
		}
		
		//2. bottom up
		for(int i=2; i<=numItems; i++){
			for(int j=1; j<=W; j++){
				// 若背包可以放下第i个物品，则有放、不放两种，选择价值大的
				if(j >= weights[i-1]){
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]] + values[i-1]);
				}else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		//3. top down
		// 保存每个物品是否被装入背包
		boolean[] res = new boolean[numItems+1];
		int k = res.length - 1;
		int i = numItems;
		int j = W;
		while (i>0 && j>0){
			if(dp[i][j] == dp[i-1][j]){ //不放第i个物品
				i--;
				res[k--] = false;
			}else {
				j -= weights[i-1];
				i--;
				res[k--] = true;
			}
		}
		// 打印被放入背包的物品
		for(i=1; i<=numItems; i++){
			if(res[i]){
				System.out.println(weights[i-1]);
			}
		}
		
		System.out.println(dp[numItems][W]);
	}
	
	public static void main(String[] args) {
		int[] weights = { 3, 4, 5 };
		int[] values = { 4, 6, 7 };
		Knapsack01.knapsack01(weights, values, 10);
	}
}
