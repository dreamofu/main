package sort;

import java.util.Arrays;

/*
 * 直接插入排序
 * 每次将data[i]插入到前面已排好序的data[:i-1]中，重复n-1次
 */
public class StraightInsertSort {

	static void sort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i - 1; j >= 0 && data[j] > data[j + 1]; j--) {
				swap(data, j, j + 1);
			}
		}
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
