package sort;

import java.util.Arrays;

/*
 * 冒泡排序
 * 每次找出一个最值，重复n-1次
 */
public class BubbleSort_wrong {

	static void sort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[i]) {
					swap(data, i, j);
				}
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
