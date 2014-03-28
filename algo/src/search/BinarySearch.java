package search;

/*
 * 二分查找
 * 在一个有序数组中执行查找
 */
public class BinarySearch {

	public static int search(int[] data, int key) {
		int low = 0, high = data.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2; // 注意不要写成(low+high)/2，防止溢出
			if (data[mid] == key) {
				return mid;
			} else if (data[mid] < key) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] data = { 1, 3, 4, 6, 8, 9 };
		System.out.println(search(data, 4));
		System.out.println(search(data, 7));
	}
}
