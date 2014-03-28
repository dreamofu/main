package algo.backtrack;

import java.util.Arrays;


/*
 * 20条鱼分在20个桶里的分法，每个桶能放0-10条鱼
 */
public class Fish {

	static int sum = 0;

	static void divide(int num, int[] path) {
		if (path.length == 3) {
			if (num == 0) {
				System.out.println(Arrays.toString(path));
				sum++;
			}
			return;
		}

		for (int n = 0; n <= 10; n++) {
			if (n <= num) {
				divide(num - n, concat(path, n));
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
		int num = 10;
		divide(num, new int[0]);
		System.out.println("sum=" + sum);
	}
}
