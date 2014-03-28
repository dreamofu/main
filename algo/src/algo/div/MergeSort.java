package algo.div;

import java.util.Arrays;

/*
 * 合并排序 
 * 将数组分为left,right两部分，分别做排序，然后合并已排序的left,right两部分
 * 时间复杂度O(nlogn)
 * 
 */
public class MergeSort {

	/**
	 * 归并排序
	 */
	public static void mergeSort(int[] data, int left, int right) {
		if (left < right) { //至少是两个元素时，才需要排序
			int mid = (left + right) / 2;
			mergeSort(data, left, mid); //排序左半个数组
			mergeSort(data, mid + 1, right); //排序右半个数组
			merge(data, left, mid, right); //合并左右两个已排序的数组
		}
	}

	/*
	 * 合并两个已排序的子数组a[left:mid], a[mid+1:right]
	 */
	private static void merge(int[] a, int left, int mid, int right) {
		int[] tmp = new int[right-left+1]; //1不要丢了

		// copy left, right
		int i = left, j = mid + 1, k = 0;
		for (; i <= mid && j <= right;) {
			if (a[i] <= a[j]) {
				tmp[k++] = a[i++];
			} else {
				tmp[k++] = a[j++];
			}
		}
		// copy remain if necessary
		if (i <= mid) {
			for (int m = i; m <= mid; m++) {
				tmp[k++] = a[m];
			}
		} else if (j <= right) {
			for (int m = j; m <= right; m++) {
				tmp[k++] = a[m];
			}
		}
		
		// copy back 
		for (i = left; i <= right; i++) {
			a[i] = tmp[i-left];
		}
	}
	
	//TODO 改成非递归的版本
	

	public static void main(String[] args) {
		int[] a = { 1, 3, 2, 7, 5, 8 };
		mergeSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}
}
