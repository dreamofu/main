package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 最长公共前缀Longest Common Prefix/最长可重叠的重复子串Longest Repeat Substring
 * 
 * 思路：
 * 构造后缀数组，若子串重复出现，一定出现在不同的后缀中，通过对后缀排序，并比较相邻的后缀，来发现最长的重复子串
 */
public class LRS_LCP_overlap {

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

	// 找出相同的前缀
	static char[] cmp(char[] cs, char[] cs2) {
		String s = "";
		for (int i = 0; i < cs.length && i < cs2.length; i++) {
			if (cs[i] == cs2[i]) {
				s += cs[i];
			} else {
				break;
			}
		}

		return s.toCharArray();
	}

	static char[][] sort(char[][] suffix) {
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
