package sort;

import java.util.Arrays;

public class BubbleSort {

	/*
	 * 通过两两比较，将大的元素放到后面，第一次找到一个最大的数，然后不考虑最后一个数，
	 * 再次从头开始两两比较，将次大的数放到后面。。依次类推
	 */
	static void sort(int[] data){
		for(int i=data.length-1; i>=0; i--){
			for(int j=0; j<i; j++){
				if(data[j] > data[j+1]){
					swap(data, j, j+1);
				}
			}
		}
	}

	private static void swap(int[] data, int i, int j) {
		int temp = data[j];
		data[j] = data[i];
		data[i] = temp;
	}
	
	public static void main(String[] args) {
		int[] data = {4,2,6,8,1,23, 18};
		BubbleSort.sort(data);
		System.out.println(Arrays.toString(data));
	}
}
