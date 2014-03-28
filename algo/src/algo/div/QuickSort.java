package algo.div;

import java.util.Arrays;

/*
 * 快速排序
 * 找出数据的一个分隔partition点，使左边的数字都小于分割点，右边的数字都大于分割点
 * 然后递归地排序左右两个部分
 * 
 */
public class QuickSort {

	public static void quickSort(int[] data, int begin, int end) {
		if (begin < end) { // 至少有两个元素，才需要排序
			int pivot = partition(data, begin, end); // 找出分割点
			quickSort(data, begin, pivot - 1); //排序左半部分
			quickSort(data, pivot + 1, end); //排序右半部分
		}
	}

	/*
	 * 对data进行分隔，比data[0]小的元素放左边，比data[0]大的元素放右边，返回data[0]的最后位置
	 */
	private static int partition(int[] data, int start, int end) {
		int pivotKey = data[start];
		int low = start, high = end;
		while (low < high) {
			while (low < high && data[high] >= pivotKey)
				high--;
			swap(data, low, high); // 交换分割点与比分割点小的数据，此时high指向分割点
			while (low < high && data[low] <= pivotKey)
				low++;
			swap(data, low, high); // 交换分割点与比分隔点大的数据，此时low指向分割点
		}

		return low;
	}

	static void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 2, 7, 5, 8 };
		quickSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}
}
