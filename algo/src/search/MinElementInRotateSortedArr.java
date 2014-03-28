package search;

/*
 * 找出旋转数组的最小元素
 * 
 * 思路：
 * 只要是有序的数组，就要想到折半查找
 */
public class MinElementInRotateSortedArr {

	public static int minElement(int[] data) {

		if (data[0] < data[data.length - 1]) { // 未做旋转
			return data[0];
		} else {
			int low = 0;
			int high = data.length - 1;
			while (low < high) {
				int mid = (low + high) / 2;
				/* 注意终止条件：当low,high相邻，low指向最大的元素，high指向最小的元素，此时mid==low，且不变 */
				if (low == mid) {
					break;
				}

				if (data[mid] < data[high]) {
					high = mid; // 由于mid可能是最小元素，因此不能写成mid-1
				} else if (data[mid] > data[high]) {
					low = mid;
				}
			}
			return data[high];
		}
	}

	public static void main(String[] args) {
		int[] arr = { 3, 4, 6, 8, 1, 2 };
		System.out.println(minElement(arr));
	}
}
