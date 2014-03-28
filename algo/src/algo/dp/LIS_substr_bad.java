package algo.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目： 最长递增子串（连续）
 * 
 * 思路： 按照动态规划的思路，将长度为n的序列一分为二，则最长单增子序列的情况分三者，
 * 要么出现在上半序列，要么出现在下半序列，要么同时跨越上半、下半两个子序列 第三种情况下，从中点开始向左、向右找出最长的单增序列
 * 从这三种情况中，选择最长的序列即可。
 */
public class LIS_substr_bad {

	public static Integer[] monoAsc(int[] a, int left, int right) {
		List<Integer> res = new LinkedList<Integer>();
		// 递归结束条件
		if (left == right) {
			res.add(a[left]);
			return res.toArray(new Integer[0]);
		}

		int mid = (left + right) / 2;
		Integer[] leftMonoAsc = monoAsc(a, left, mid);
		Integer[] rightMonoAsc = monoAsc(a, mid + 1, right);
		Integer[] maxMonoAsc = new Integer[0];
		if (a[mid] < a[mid + 1]) {
			res.add(a[mid]);
			for (int i = mid - 1; i >= left; i--) {
				if (a[i] < a[i + 1]) {
					res.add(0, a[i]);
				} else {
					break;
				}
			}
			res.add(a[mid + 1]);
			for (int i = mid + 2; i <= right; i++) {
				if (a[i] > a[i - 1]) {
					res.add(a[i]);
				} else {
					break;
				}
			}
			maxMonoAsc = res.toArray(new Integer[0]);
		}

		if (leftMonoAsc.length > maxMonoAsc.length) {
			maxMonoAsc = leftMonoAsc;
		}
		if (rightMonoAsc.length > maxMonoAsc.length) {
			maxMonoAsc = rightMonoAsc;
		}
		return maxMonoAsc;
	}

	public static void main(String[] args) {
		int[] a = { 2, 4, 6, 1, 3, 4, 6, 5, 8, 10, 3 };
		System.out.println(Arrays.toString(monoAsc(a, 0, a.length - 1)));
	}
}
