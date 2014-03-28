package algo.backtrack;

/*
 * 完全背包的回溯解法
 * 
 * 每个物品就不单纯是取舍的二值问题了，而是取0件、1件、。。k件的问题
 */
public class KnapsackComplete {
	static int maxValue = -1;
	static int V = 10;
	static int N = 3;
	static int[] weights = { 3, 4, 5 };
	static int[] values = { 4, 6, 7 };

	/**
	 * 
	 * @param n
	 *            对第n个物品进行选择：取0件、1件、。。k件
	 * @param path
	 *            当前搜素路径，保存每个商品各取多少件
	 * @param sumWeight
	 *            当前的总重量
	 */
	static void knapsackComplete(int n, int sumWeight, int[] path) {
		if (n > N) {
			int value = 0;
			for (int i = 0; i < values.length; i++) {
				if (path[i] > 0) {
					value += path[i] * values[i];
				}
			}
			
			if (value > maxValue) {
				maxValue = value;
			}
		} else {
			for (int cnt = 0; cnt <= V / weights[n - 1]; cnt++) {
				if (sumWeight + cnt * weights[n - 1] <= V) {
					knapsackComplete(n + 1, sumWeight + cnt * weights[n - 1], concat(path, cnt));
				} else {
					break;
				}
			}
		}
	}

	private static int[] concat(int[] path, int cnt) {
		int[] res = new int[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = cnt;
		return res;
	}

	public static void main(String[] args) {
		knapsackComplete(1, 0, new int[0]);
		System.out.println(maxValue);
	}
}
