package algo.backtrack;

/*
 * 01背包的基本解法
 * 
 * 用回溯的思路求解问题，将所有解组织成一颗子集树，在树上以深度优先的方式搜素所有解
 * 树的第一层决定对第一个物品的取舍，第二层决定对第二个物品的取舍。。。
 * 
 */
public class Knapsack01 {

	static int maxValue = -1;
	static int V = 10;
	static int N = 3;
	static int[] weights = { 3, 4, 5 };
	static int[] values = { 4, 6, 7 };

	/**
	 * @param n 对第n个物品进行取舍
	 * @param sumWeight 当前物品的重量
	 * @param path 当前的搜索luj
	 */
	static void knapsack01(int n,  int sumWeight, boolean[] path) {
		if (n > N) { // 已经完成对所有物品的取舍，得到一个可行解
			int value = 0;
			for (int i = 0; i < values.length; i++) {
				if (path[i]) {
					value += values[i];
				}
			}
			if (value > maxValue) {
				maxValue = value;
			}
		} else {
			/* 对第n个物品的取 */
			if (sumWeight + weights[n - 1] <= V) {
				knapsack01(n + 1, sumWeight + weights[n - 1], concat(path, true));
			}
			/* 对第n个物品的舍 */
			knapsack01(n + 1, sumWeight, concat(path, false));
		}
	}

	private static boolean[] concat(boolean[] path, boolean b) {
		boolean[] res = new boolean[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = b;
		return res;
	}

	public static void main(String[] args) {
		knapsack01(1, 0, new boolean[0]);
		System.out.println(maxValue);
	}
}
