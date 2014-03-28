package algo.backtrack;

import java.util.Arrays;

/*
 * 正整数n所有可能的和式的组合（如；4=1+1+1+1、1+1+2、1+3、2+1+1、2+2）
 * 典型的回溯思路，和硬币组合问题十分类似
 * 注意是组合，所有必须规定顺序，后分解出来的因子不大于之前的因子
 */
public class NCombination {

	public static void nCombination(int N, int num, int[] path) {
		if (num == 0) {
			System.out.println(Arrays.toString(path));
		} else {
			for (int i = 1; i <= N/2; i++) {
				if (i <= num && (path.length==0 || i <= path[path.length - 1])) {
					nCombination(N, num - i, concat(path, i));
				}
			}
		}
	}

	private static int[] concat(int[] path, int n) {
		int[] res = new int[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = n;
		return res;
	}
	
	public static void main(String[] args) {
		nCombination(10, 10, new int[0]);
	}

}
