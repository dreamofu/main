package algo.div;

/*
 * O(n)时间复杂度找出第k大的数
 */
public class KthElement {

	/*
	 * 和快排的主要区别： 
	 * 1）快排当小于2个元素(begin==end)时，不需要做任何操作；而查找元素时，需要处理
	 * 2）快排时pivot表示在整个data数组的下标，仅需要这个下标来划分子数组；而在查找元素时，pivot表示在子数组中的下标
	 */

	/**
	 * 在[begin, end]范围内找出第k大的数
	 * 
	 * @param data
	 * @param begin
	 * @param end
	 * @param k
	 *            第k大的数（k从1开始）
	 * @return
	 */
	static int kthElement(int[] data, int begin, int end, int k) {
		// 简单的错误检查
		if (k > (end - begin + 1)) {
			System.err.println(String.format("%d is larger than data[%d:%d].", k, begin, end));
		}

		/* 只有一个元素， 直接返回 */
		if(begin == end){ 
			return data[begin];
		}else{
			/*
			 * 找到分割点pivot在[begin:end]子数组中的下标，begin在子数组中下标为0
			 * 若pivot == k-1, 直接返回对应元素
			 * 若pivot <  k-1, 在[begin+pivot+1:end]中找出第k-pivot-1大的元素
			 * 若pivot >  k-1, 在[begin:begin+pivot-1]中找出第k大的元素 
			 */
			int pivot = partition(data, begin, end) - begin;
			if (pivot == k - 1) {
				return data[begin + pivot];
			} else if (pivot > k - 1) {
				return kthElement(data, begin, begin + pivot - 1, k);
			} else {
				return kthElement(data, begin + pivot + 1, end, k - pivot - 1);
			}
		}
	}

	/*
	 * 对data进行分隔，比data[0]大的元素放左边，比data[0]小的元素放右边，返回data[0]的最后位置
	 * 找分割点的基本思路：
	 * 从尾巴开始和pivotKey依次比较，当小于pivotKey时，前移，遇到第一个比pivotKey大的元素，将其和pivotKey交换
	 */
	private static int partition(int[] data, int start, int end) {
		int pivotKey = data[start];
		int low = start, high = end;
		while (low < high) {
			while (low < high && data[high] <= pivotKey)
				high--;
			swap(data, low, high); // 交换分割点与比分割点大的数据，此时high指向分割点
			while (low < high && data[low] >= pivotKey)
				low++;
			swap(data, low, high); // 交换分割点与比分隔点小的数据，此时low指向分割点
		}

		return low;
	}

	static void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	public static void main(String[] args) {
		int[] data = { 1, 5, 2, 3, 7, 10 };
		for (int i = 1; i <= data.length; i++) {
			System.out.println(kthElement(data, 0, data.length - 1, i));
		}
	}

}
