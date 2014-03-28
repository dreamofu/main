package algo.div;

/*
 * 一个整数数组，求连续子序列的最大乘积
 * 乘积和求和的问题不同，在于乘积可能是0
 * 典型的分治思路，数组一分为二，最大乘积可能落在前半部分，后半部分，或是横跨这两部分
 * 若是第三种情况，则从中间开始向两边扩散，记录maxLeft/-minLeft, maxRight/-minRight，选择max{maxLeft*maxRight, -minLeft*-minRight}
 */
public class MaxContSubSeqMulti {

	static class Datum {
		public float max;
		public float min;
	}

	static Datum maxContSubSeqMulti(float[] data, int begin, int end) {
		Datum res = new Datum();
		if (end == begin) {
			res.max = data[begin];
			res.min = data[begin];
			return res;
		} else if (end == begin + 1) {
			res.max = max(data[begin], data[end], data[begin] * data[end]);
			res.min = min(data[begin], data[end], data[begin] * data[end]);
			return res;
		}

		int mid = begin + (end - begin) / 2;
		Datum left = maxContSubSeqMulti(data, begin, mid);
		Datum right = maxContSubSeqMulti(data, mid + 1, end);

		float maxLeft = 0;
		float minLeft = 0;
		float leftMul = 1;
		for (int i = mid; i >= begin; i--) {
			leftMul *= data[i];
			if (leftMul > maxLeft) {
				maxLeft = leftMul;
			}
			if (leftMul < minLeft) {
				minLeft = leftMul;
			}
		}

		float maxRight = 0;
		float minRight = 0;
		float rightMul = 1;
		for (int i = mid + 1; i <= end; i++) {
			rightMul *= data[i];
			if (rightMul > maxRight) {
				maxRight = rightMul;
			}
			if (rightMul < minRight) {
				minRight = rightMul;
			}
		}

		res.min = min(0, maxLeft * minRight, minLeft * maxRight);
		res.max = max(0, maxLeft * maxRight, minLeft * minRight);

		res.min = min(left.min, res.min, right.min);
		res.max = max(left.max, res.max, right.max);
		return res;
	}

	private static float max(float max, float max2, float max3) {
		return Math.max(max, Math.max(max2, max3));
	}

	private static float min(float min, float min2, float min3) {
		return Math.min(min, Math.min(min2, min3));
	}

	public static void main(String[] args) {
		float[] data = { -2.5f, 4, 0, 3, 0.5f, 8, -1 };
		Datum datum = maxContSubSeqMulti(data, 0, data.length - 1);
		System.out.println(datum.max);
	}
}
