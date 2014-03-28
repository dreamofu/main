package algo.div;

/*
 * 题目：
 * 在数组中，数字减去它右边的数字得到一个数对之差。求所有数对之差的最大值。
 * 例如在数组{2, 4, 1, 16, 7, 5, 11, 9}中，数对之差的最大值是11，是16减去5的结果。
 * 
 * 思路：
 * 采用分治法，将长度为n的数组一分为二，差的最大值分为三种情况：
 * 一种是存在前半部分中，一种是存在后半部分中，第三种是同时存在前半和后半
 * 若是第三种情况，则从前半部分找出最大值，后半部分找出最小值，想减即可。
 */
public class MPD {

	static int maxDiff(int[] seq, int begin, int end) {
		// 递归结束条件，只有一个或两个数
		if (end == begin) {
			return Integer.MIN_VALUE;
		} else if (end == begin + 1) {
			return seq[begin] - seq[end];
		}

		int mid = (begin + end) / 2;
		int leftMaxDiff = maxDiff(seq, begin, mid);
		int rightMaxDiff = maxDiff(seq, mid + 1, end);

		int maxLeft = Integer.MIN_VALUE;
		for (int i = begin; i <= mid; i++) {
			if (seq[i] > maxLeft) {
				maxLeft = seq[i];
			}
		}
		int minRight = Integer.MAX_VALUE;
		for (int i = mid + 1; i <= end; i++) {
			if (seq[i] < minRight) {
				minRight = seq[i];
			}
		}
		int midMaxDiff = maxLeft - minRight;

		if (leftMaxDiff > midMaxDiff) {
			midMaxDiff = leftMaxDiff;
		}
		if (rightMaxDiff > midMaxDiff) {
			midMaxDiff = rightMaxDiff;
		}

		return midMaxDiff;
	}

	public static void main(String[] args) {
		int[] seq = { 2, 4, 1, 16, 7, 5, 11, 9 };
		System.out.println(maxDiff(seq, 0, seq.length - 1));
	}
}
