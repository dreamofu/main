package sort;

import java.util.Arrays;

/*
 * 折半插入排序
 * 是直接插入排序的改进，不用遍历来确定插入位置，而采用二分查找来确定插入位置
 */
public class BinaryInsertSort {

	static void sort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			int idx = findInsertIdx(data, i); //二分查找方式找出插入位置

			for (int j = i - 1; j >= idx; j--) {
				swap(data, j, j + 1);
			}
		}
	}

	/*
	 * 从data[:i-1]决定data[i]的插入位置
	 */
	private static int findInsertIdx(int[] data, int i) {
		int low = 0, high = i - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (data[i] < data[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	static void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	public static void main(String[] args) {
		int[] data = { 1, 3, 2, 7, 5, 8 };
		sort(data);
		System.out.println(Arrays.toString(data));
	}
}
