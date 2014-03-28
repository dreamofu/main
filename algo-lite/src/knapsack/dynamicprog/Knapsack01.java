package knapsack.dynamicprog;

public class Knapsack01 {

	
	static void knapsack01(int[] weights, int[] values, int W){
		int numItems = weights.length;
		int[][] dp = new int[numItems+1][W+1];
		
		//1. init dp
		// ����һ����Ʒ��������Ϊ0-V�ı������ɻ�õ�����ֵ
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
				// ���������Է��µ�i����Ʒ�����зš��������֣�ѡ���ֵ���
				if(j >= weights[i-1]){
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]] + values[i-1]);
				}else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		//3. top down
		// ����ÿ����Ʒ�Ƿ�װ�뱳��
		boolean[] res = new boolean[numItems+1];
		int k = res.length - 1;
		int i = numItems;
		int j = W;
		while (i>0 && j>0){
			if(dp[i][j] == dp[i-1][j]){ //���ŵ�i����Ʒ
				i--;
				res[k--] = false;
			}else {
				j -= weights[i-1];
				i--;
				res[k--] = true;
			}
		}
		// ��ӡ�����뱳������Ʒ
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
