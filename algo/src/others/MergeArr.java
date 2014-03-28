package others;

import java.util.Arrays;

/*
 * O(1)空间复杂度合并两个已排序的数组
 */
public class MergeArr {

	static void merge(int[] al, int mid) {
		int i = 0, j = mid;
		while (i < j && j <= al.length - 1) {
			if (al[i] <= al[j]) {
				i++;
			} else {
				int tmp = al[j];
				for (int m = j; m >=i+1; m--) {
					al[m] = al[m - 1];
				}
				al[i] = tmp;
				i++;
				j++;
			}
		}
	}

	public static void main(String[] args) {
		int[] al = { 1, 3, 7, 2, 5, 10, 12 };
		int mid = 3;
		merge(al, mid);
		System.out.println(Arrays.toString(al));
	}
}
