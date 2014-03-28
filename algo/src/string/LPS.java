package string;

import java.util.Arrays;

/*
 * 求一个字符串的最长回文串(Longest Palindrome Substring)
 */
public class LPS {

	static char[] lps(char[] x) {
		char[] y = new char[x.length * 2 + 1];
		for (int i = 0; i < x.length; i++) {
			y[i] = x[i];
		}
		y[x.length] = '#';
		for (int i = x.length - 1, j = 1; i >= 0; i--, j++) {
			y[x.length + j] = x[i];
		}

		return LRS_LCP_overlap.lrs(y);
	}

	public static void main(String[] args) {
		char[] x = "google".toCharArray();
		System.out.println(Arrays.toString(lps(x)));
	}
}
