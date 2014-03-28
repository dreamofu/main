package knapsack.backtrack;

import java.util.Arrays;

public class Knapsack01 {
	static int maxValue = 0;
	static boolean[] maxPath = null;
	static int[] weights = { 3, 4, 5 };
	static int[] values = { 4, 6, 7 };
	static int NUM_ITEMS = weights.length;
	
	/*
	 * left 表示剩下的背包容量
	 */
	static void knapsack(int left, boolean[] path){
		if(path.length == NUM_ITEMS){
			if(sum(path) > maxValue){
				maxValue = sum(path);
				maxPath = path;
			}
		}else{
			int itemIdx = path.length;
			// 放进第i个物品
			if (weights[itemIdx] <= left){
				knapsack(left - weights[itemIdx], concat(path, true));
			}
			// 不放第i个物品
			knapsack(left, concat(path, false));
		}
	}
	
	private static int sum(boolean[] path){
		int sum = 0;
		for(int i=0; i<path.length;i++){
			if(path[i]){
				sum += values[i];
			}
		}
		return sum;
	}
	
	private static boolean[] concat(boolean[] path, boolean d) {
		boolean[] res = new boolean[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = d;
		return res;
	}

	public static void main(String[] args) {
		Knapsack01.knapsack(10, new boolean[0]);
		System.out.println(Arrays.toString(maxPath));
		System.out.println(maxValue);
	}

}
