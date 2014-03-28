package array;

/*
 * 题目：
 * 有序二维数组，每一行右边的值大于左边的值，每一列下边的值大于上边的值，写查找函数
 * 
 * 分析：
 * 要点是不用局限于从左上角的点出发开始查找，而要试着从其他三个顶点开始查找
 */
public class FindElementInSortedArray {

	public static boolean exist(int[][] a, int element) {
		int len = a.length - 1;
		for (int i = 0, j = len; i <= len && j >= 0;) {
			if (a[i][j] == element) {
				return true;
			} else if (a[i][j] < element) {
				i++;
			} else {
				j--;
			}
		}
		return false;
	}

}
