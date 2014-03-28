package algo.backtrack;

import java.util.Arrays;

/*
 * 5个骰子扔在地上，点数之和为s，求所有可能的情况
 * 
 * 典型的回溯：每一层表示一个骰子的值，结束条件是层数=5，剪枝策略是当前sum已经大于s
 */
public class DiceSum {
	static final int NUM_DICE = 5;
	static final int S = 8;
	static final int[] DICES = {1,2,3,4,5,6};
	
	static void diceSum(int depth, int sum, int[] path) {
		if (depth > NUM_DICE) {
			if (sum == S) {
				System.out.println(Arrays.toString(path));
			}
		} else {
			for (int dice : DICES) {
				if (sum + dice <= S) {
					diceSum(depth + 1, sum + dice, concat(path, dice));
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
		diceSum(1, 0, new int[0]);
	}
}
