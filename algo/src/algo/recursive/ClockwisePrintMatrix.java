package algo.recursive;

/*
 * 题目：
 * 顺时针打印矩阵
 * 
 * 分析：
 * 典型的递归思想，将打印矩阵看做每次打印一圈，在打印圈数少1的小矩阵，因此可以递归求解
 */
public class ClockwisePrintMatrix {

	static void print(int[][] matrix, int left, int right, int high, int low) {
		if (right >= left && high >= low) {
			// high
			for (int i = left; i <= right; i++) {
				System.out.print(matrix[low][i] + " ");
			}
			// right
			for (int i = low + 1; i <= high; i++) {
				System.out.print(matrix[i][right] + " ");
			}
			// low
			for (int i = right - 1; i >= left; i--) {
				System.out.print(matrix[high][i] + " ");
			}
			// left
			for (int i = high - 1; i >= low + 1; i--) {
				System.out.print(matrix[i][left] + " ");
			}

			print(matrix, left + 1, right - 1, high - 1, low + 1);
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[4][];
		matrix[0] = new int[] { 1, 2, 3, 4 };
		matrix[1] = new int[] { 5, 6, 7, 8 };
		matrix[2] = new int[] { 9, 10, 11, 12 };
		matrix[3] = new int[] { 13, 14, 15, 16 };
		print(matrix, 0, 3, 3, 0);
	}
}
