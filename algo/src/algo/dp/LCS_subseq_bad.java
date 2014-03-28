package algo.dp;

import java.util.Arrays;
/*
 * 两个字符串的最长公共子序列（注意：子序列不要求是连续的）
 *
 * 纯递归法求解，重复求解大量子问题，造成效率低下
 */
public class LCS_subseq_bad {

	static char[] lcs(char[] x, char[] y) {
		// 递归的初始值
		if (x.length == 0 || y.length == 0) {
			return new char[0];
		}
		
		// 如果最后一个字符相等，则lcs(x[1:m],y[1:n]) = lcs(x[1:m-1],y[1:n-1])+x_m
		if (x[x.length - 1] == y[y.length - 1]) {
			return concat(
					lcs(sub(x, 0, x.length - 2), sub(y, 0, y.length - 2)),
					x[x.length - 1]);
		}
		// 如果最后一个字符不等，则lcs(x[1:m],y[1:n]) = maxLen{lcs(x[1:m],y[1:n-1]), lcs(x[1:m-1],y[1:n])}
		char[] lcs1 = lcs(sub(x, 0, x.length - 2), y);
		char[] lcs2 = lcs(x, sub(y, 0, y.length - 2));
		if (lcs1.length > lcs2.length) {
			return lcs1;
		} else {
			return lcs2;
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
		char[] x = "ABCBDAB".toCharArray();
		char[] y = "BDCABA".toCharArray();
		System.out.println(Arrays.toString(lcs(x, y)));
	}
}
