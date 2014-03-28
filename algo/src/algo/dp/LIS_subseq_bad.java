package algo.dp;

import java.util.Arrays;

/*
 * 最长递增子序列（不要求连续）
 */
public class LIS_subseq_bad {

	static int[] lis(int[] data) {
		int maxLen = -1;
		int[] max = null;
		for (int i = 0; i < data.length; i++) {
			int[] res = lisSuffix(sub(data, 0, i));
			if (res.length > maxLen) {
				maxLen = res.length;
				max = res;
			}
		}
		return max;
	}

	/*
	 * 以尾字符结尾的单增子序列
	 */
	static int[] lisSuffix(int[] data) {
		if (data.length == 0) {
			return new int[0];
		}

		int maxLen = -1;
		int[] max = null;
		for (int i = 0; i < data.length - 1; i++) {
			if (data[data.length - 1] >= data[i]) {
				int[] res = lisSuffix(sub(data, 0, i));
				if (res.length > maxLen) {
					maxLen = res.length;
					max = res;
				}
			}
		}
		
		if(maxLen > 0){
			return concat(max, data[data.length-1]);
		}else{
			int[] result = new int[1];
			result[0] = data[data.length - 1];
			return result;
		}
	}

	private static int[] sub(int[] x, int i, int j) {
		if (i > j) {
			return new int[0];
		}

		int[] res = new int[j - i + 1];
		for (int m = i; m <= j; m++) {
			res[m - i] = x[m];
		}
		return res;
	}
	
	/* 拼接data和字符c */
	private static int[] concat(int[] data, int c) {
		if (data.length == 0) {
			int[] res = new int[1];
			res[0] = c;
			return res;
		} else {
			int[] res = new int[data.length + 1];
			for (int i = 0; i < data.length; i++) {
				res[i] = data[i];
			}
			res[res.length - 1] = c;
			return res;
		}
	}
	
	public static void main(String[] args) {
		int[] data = { 1, -1, 2, -3, 4, -5, 6, -7 };
		System.out.println(Arrays.toString(lis(data)));
		data = new int[]{4, 2, 6, 3, 1, 5};
		System.out.println(Arrays.toString(lis(data)));
	}
}
