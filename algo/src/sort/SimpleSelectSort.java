package sort;

import java.util.Arrays;

/*
 * 简单选择排序
 * 每次从data[i:]中选择一个最小的元素，放到i的位置上，重复n-1次
 */
public class SimpleSelectSort {

	static void sort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			int minIdx = selectMin(data, i); // 选择data[i:]中最小的元素，返回下标
			if (minIdx != i) {
				swap(data, i, minIdx);
			}
		}
	}

	private static int selectMin(int[] data, int start) {
		int min = data[start];
		int minIdx = start;
		for (int i = start + 1; i < data.length; i++) {
			if (data[i] < min) {
				min = data[i];
				minIdx = i;
			}
		}
		return minIdx;
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
