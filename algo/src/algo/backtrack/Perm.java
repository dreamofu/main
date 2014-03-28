package algo.backtrack;

import java.util.Arrays;

/*
 * 求一个数组的全排列
 * 第一次可以选数组中的任一元素，第二次可以选剩余的任一元素。。。直到元素被选完为止
 * 因此，问题的解可以组织成一棵树，显然可以用回溯来求解
 */
public class Perm {

	static void perm(int[] data, int[] path) {
		if (data.length == 0) {
			System.out.println(Arrays.toString(path));
		} else {
			for (int d : data) {
				perm(remove(data, d), concat(path, d));
			}
		}
	}

	private static int[] remove(int[] data, int d) {
		int[] res = new int[data.length - 1];
		for (int i = 0, j = 0; i < data.length; i++) {
			if (data[i] == d) {
				continue;
			}
			res[j++] = data[i];
		}
		return res;
	}

	private static int[] concat(int[] path, int d) {
		int[] res = new int[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = d;
		return res;
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 3, 4 };
		perm(data, new int[0]);
	}
}
