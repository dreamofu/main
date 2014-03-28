package string;

import java.util.Arrays;

/*
 * 最长不重复的连续子串
 * 
 * 思路：
 * 考虑以最后一个字符结尾的最长不充分连续子串，容易分析出具备最优子结构特征，可用DP来求解；
 * 这样原问题可以转化为求以S_0, S_1,...,S_n结尾的最长不重复的连续子串，从中选出最长的即可
 */
public class LNRS_bad {

	static char[] lnrs(char[] data) {
		int maxLen = -1;
		char[] max = null;
		for (int i = 0; i < data.length; i++) {
			char[] res = lnrsSuffix(sub(data, 0, i));
			if(res.length > maxLen){
				maxLen = res.length;
				max = res;
			}
		}
		return max;
	}
	
	static char[] lnrsSuffix(char[] data){
		if(data.length == 0){
			return new char[0];
		}
		
		char[] res = lnrsSuffix(sub(data, 0, data.length-2));
		int idx = find(res, data[data.length-1]);
		if(idx < 0){
			return concat(res, data[data.length-1]);
		}else{
			return concat(res, idx+1, res.length-1, data[data.length-1]);
		}
	}
	
	private static int find(char[] res, char c) {
		int idx = -1;
		for (int i = res.length - 1; i >= 0; i--) {
			if (res[i] == c) {
				idx = i;
				break;
			}
		}
		return idx;
	}

	/*拼接 data[i:j]和字符c*/
	private static char[] concat(char[] data, int i, int j, char c) {
		if (i > j) {
			char[] res = new char[1];
			res[0] = c;
			return res;
		} else {
			char[] res = new char[j - i + 2];
			for (int m = i; m <= j; m++) {
				res[m - i] = data[m];
			}
			res[res.length - 1] = c;
			return res;
		}
	}

	/* 拼接data和字符c */
	private static char[] concat(char[] data, char c) {
		if (data.length == 0) {
			char[] res = new char[1];
			res[0] = c;
			return res;
		} else {
			char[] res = new char[data.length + 1];
			for (int i = 0; i < data.length; i++) {
				res[i] = data[i];
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
		char[] data = "abcacbdef".toCharArray();
		System.out.println(Arrays.toString(lnrs(data)));
	}
}
