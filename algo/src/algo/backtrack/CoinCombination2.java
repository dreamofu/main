package algo.backtrack;

import java.util.Arrays;

/*
 * 硬币组合问题
 * 
 * 将所有解组织成一棵树
 * 第一层决定选择10元硬币的个数，可以选择0,1,...n/10
 * 第二层决定选择5元硬币的个数，可以选择0,1,...n/5
 * ...
 */
public class CoinCombination2 {

	static int[] coins = { 10, 5, 2, 1 };
	static int N = 7;

	static void cc(int depth, int sum, int[] path) {
		if (depth > coins.length) {
			if (sum == N) { // 输出可行解
				System.out.println(Arrays.toString(path));
			}
		} else {
			for (int k = 0; k <= N / coins[depth - 1]; k++) { // coins[n-1]的可选个数
				if (sum + k * coins[depth - 1] <= N) {
					cc(depth + 1, sum + k * coins[depth - 1], concat(path, k));
				}
			}
		}
	}

	private static int[] concat(int[] path, int k) {
		int[] res = new int[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = k;
		return res;
	}

	public static void main(String[] args) {
		cc(1, 0, new int[0]);
	}
}
