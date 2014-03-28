package divide;

import java.util.Arrays;

public class QuickSort {

	static void sort(int[] data, int begin, int end){
		if(begin < end){
			int pivot = partition(data, begin, end);
			sort(data, begin, pivot-1);
			sort(data, pivot+1, end);
		}
	}

	/*
	 * �ҳ����ݵ�һ����֧�㣬��С�ڸ�����Ԫ�ط���ߣ����ڸ�Ԫ�صķ��ұ�
	 * ���ظ÷�֧���λ��
	 */
	private static int partition(int[] data, int begin, int end) {
		int pivotKey = data[begin];
		int low = begin;
		int high = end;
		while(low < high){
			while(data[high] >= pivotKey && low < high){
				high --;
			}
			swap(data, low, high);
			while(data[low] < pivotKey && low < high){
				low ++;
			}
			swap(data, low, high);
		}
		return low;
	}

	private static void swap(int[] data, int low, int high) {
		int temp = data[high];
		data[high] = data[low];
		data[low] = temp;
	}

	public static void main(String[] args) {
		int[] data = { 1, 3, 2, 7, 5, 8 };
		QuickSort.sort(data, 0, data.length-1);
		System.out.println(Arrays.toString(data));
	}
}
