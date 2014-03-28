package algo.recursive;

/**
 * 题目： 求数组的全排列
 * 
 * 思路： 求长度为n的数组的全排列，可以按如下的方式产生： 首先选择全排序的第一个元素，可以选第1个，第2个，。。。，第n个
 * 确定第一个元素之后，将第一个元素拼接上剩余n-1个元素的全排列，就得到了长度为n的全排列 
 * 因此是一个典型的递归思路
 */
public class Perm {

	public static void perm(int[] data, int k, int m) {
		// 递归结束条件：当只有一个元素，表示排列已确定
		if (k == m) {
			for (int i = 0; i <= m; i++) {
				System.out.print(data[i]);
			}
			System.out.println();
		} else {
			for (int i = k; i <= m; i++) {
				swap(data, k, i); // 依次选择第一个元素为data[i]
				perm(data, k + 1, m); // 找出以data[i]开头的全排列
				swap(data, k, i); // 恢复原数组
			}
		}
	}

	private static void swap(int[] data, int k, int i) {
		int temp = data[k];
		data[k] = data[i];
		data[i] = temp;
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 3, 4 };
		perm(data, 0, data.length - 1);
	}
}
