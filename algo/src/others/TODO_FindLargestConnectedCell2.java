package others;

/*
 * 为了不重复搜索,只向右或向下搜索,这样只能找出部分最长的联通区,不能找到最长的联通区
 */
public class TODO_FindLargestConnectedCell2 {

	/*
	 * 从i,j结点搜索可行的解
	 */
	static void search(int[][] cell, int i, int j, String path) {
		// 搜索当前结点
		if (cell[i][j] == 1) {
			path = String.format("%s (%d,%d)", path, i, j);
			System.out.println(path);
		}

		// right direction
		if (i < cell.length && (j + 1) < cell.length) {
			if (cell[i][j + 1] == 1) {
				search(cell, i, j + 1, path);
			} else {
				search(cell, i, j + 1, "");
			}
		}
		// bottom direction
		if ((i + 1) < cell.length && j < cell.length) {
			if (cell[i + 1][j] == 1) {
				search(cell, i + 1, j, path);
			} else {
				search(cell, i + 1, j, "");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[4][4];
		matrix[0] = new int[] { 0, 1, 0, 0 };
		matrix[1] = new int[] { 1, 1, 1, 1 };
		matrix[2] = new int[] { 0, 0, 0, 1 };
		matrix[3] = new int[] { 0, 1, 1, 0 };

		search(matrix, 0, 0, "");
	}
}
