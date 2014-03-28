package algo.dp;

import java.util.Arrays;

/*
 * 最长公共子串(要求连续)
 * 
 * 纯递归法，求解了大量重复子问题，造成效率低下
 */
public class LCS_substr_bad {

	static char[] lcs2(char[] x, char[] y) {
		int maxLen = -1;
		char[] max = null;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				char[] res = lcsSuffix(sub(x, 0, i), sub(y, 0, j));
				if (res.length > maxLen) {
					maxLen = res.length;
					max = res;
				}
			}
		}

		return max;
	}

	/*
	 * 找出以x,y结尾的最长公共子串
	 */
	static char[] lcsSuffix(char[] x, char[] y) {
		if (x.length == 0 || y.length == 0) {
			return new char[0];
		}

		if (x[x.length - 1] == y[y.length - 1]) {
			return concat(
					lcsSuffix(sub(x, 0, x.length - 2), sub(y, 0, y.length - 2)),
					x[x.length - 1]);
		} else {
			return new char[0];
		}
	}

	/* 拼接lcs和字符c */
	private static char[] concat(char[] lcs, char c) {
		if (lcs.length == 0) {
			char[] res = new char[1];
			res[0] = c;
			return res;
		} else {
			char[] res = new char[lcs.length + 1];
			for (int i = 0; i < lcs.length; i++) {
				res[i] = lcs[i];
			}
			res[res.length - 1] = c;
			return res;
		}
	}

	/* 取x的一个子串 */
	private static char[] sub(char[] x, int i, int j) {
		if (i > j) {
			return new char[0];
		}

		char[] res = new char[j - i + 1];
		for (int m = i; m <= j; m++) {
			res[m - i] = x[m];
		}
		return res;
	}

	public static void main(String[] args) {
		char[] x = "ABACBDAB".toCharArray();
		char[] y = "BDCABA".toCharArray();
		System.out.println(Arrays.toString(lcs2(x, y)));
	}
}
