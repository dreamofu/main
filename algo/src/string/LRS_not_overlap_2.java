package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 最长不重叠的重复子串
 * 
 * 思路：
 * 在求最长可重叠重复子串的基础上，增加一个限制条件，不可重叠
 */
public class LRS_not_overlap_2 {
	static char[] lrs(char[] data) {
		// 构造后缀数组
		char[][] suffix = suffix(data);
		// 排序后缀数组
		suffix = sort(suffix);

		// 比较相邻的后缀，找出最长的重复子串
		int maxLen = -1;
		char[] max = null;
		for (int i = 0; i < suffix.length - 1; i++) {
			char[] samePrefix = cmp(suffix[i], suffix[i + 1]);
			if (samePrefix.length > maxLen) {
				maxLen = samePrefix.length;
				max = samePrefix;
			}
		}

		return max;
	}

	// 找出相同的前缀，并要求不重叠
	private static char[] cmp(char[] cs, char[] cs2) {
		int idxOffset = Math.abs(cs.length - cs2.length); 
		String s = "";
		// 不重叠即要求在进行前缀比较时，不能让重复子串超过两者的长度差
		for (int i = 0; i < cs.length && i < cs2.length && s.length() < idxOffset; i++) {
			if (cs[i] == cs2[i]) {
				s += cs[i];
			} else {
				break;
			}
		}

		return s.toCharArray();
	}

	private static char[][] sort(char[][] suffix) {
		List<String> tmp = new ArrayList<String>();
		for (int i = 0; i < suffix.length; i++) {
			tmp.add(new String(suffix[i]));
		}
		Collections.sort(tmp);
		char[][] res = new char[tmp.size()][];
		for (int i = 0; i < res.length; i++) {
			res[i] = tmp.get(i).toCharArray();
		}
		return res;
	}

	static char[][] suffix(char[] data) {
		char[][] res = new char[data.length][];
		for (int i = 0; i < res.length; i++) {// 生成每个后缀
			res[i] = new char[data.length - i]; // 每个后缀的长度递减
			for (int j = i; j < data.length; j++) { // 每个后缀取子串[i:]
				res[i][j - i] = data[j];
			}
		}
		return res;
	}

	public static void main(String[] args) {
		char[] data = "banana".toCharArray();
		System.out.println(Arrays.toString(lrs(data)));
	}
}
