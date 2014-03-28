package algo.dp;

/**
 * 题目： 求一个整数数组的最大子段和
 * 
 * 思路： 解法一：按照动态规划的思想，将长度为n的数组一分为二， 最大子段后要么出现在上半部分，要么出现在下半部分，要么有一段在上半部分，有一段在下半部分。
 * 出现第三种情况时，从中心度开始向左，向右，找出累计和最大的两段。 比较这三种情况，则最大子段和为三者最大的那个 解法二：
 * 当我们加上一个正数时，和会增加；当我们加上一个负数时，和会减少。
 * 如果当前得到的和是个负数，那么这个和在接下来的累加中应该抛弃并重新清零，不然的话这个负数将会减少接下来的和。
 * 
 */
public class LSS_bad {

	/**
	 * 第一种解法
	 */
	public static int maxSubSum(int[] a, int left, int right) {
		// 递归结束条件，问题规模缩小到可以直接求解
		if (left == right) {
			return a[left] > 0 ? a[left] : 0;
		}

		int sum = 0;
		int mid = (left + right) / 2;
		int leftSum = maxSubSum(a, left, mid);
		int rightSum = maxSubSum(a, mid + 1, right);
		int s1 = 0;
		int lefts = 0;
		for (int i = mid; i >= left; i--) {
			lefts += a[i];
			if (lefts > s1) {
				s1 = lefts;
			}
		}
		int s2 = 0;
		int rights = 0;
		for (int i = mid + 1; i <= right; i++) {
			rights += a[i];
			if (rights > s2) {
				s2 = rights;
			}
		}
		sum = s1 + s2;
		if (leftSum > sum) {
			sum = leftSum;
		}
		if (rightSum > sum) {
			sum = rightSum;
		}
		return sum;
	}

	/**
	 * 第二种解法
	 */
	public static int maxSubSum2(int[] a) {
		int sum = 0, b = 0;
		for (int i = 0; i < a.length; i++) {
			if (b > 0) {
				b += a[i];
			} else {
				b = a[i];
			}
			if (b > sum) {
				sum = b;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a = { -2, 11, -4, 13, -5, -2 };
		int result = maxSubSum(a, 0, a.length - 1);
		System.out.println(result);
		result = maxSubSum2(a);
		System.out.println(result);
	}
}
