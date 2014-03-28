package algo.backtrack;

/*
 * 题目：
 * 数组的幂集
 * 
 * 思路：
 * 幂集中的每个元素是一个集合，它可能是空集、或含A中的一个元素、或含两个元素。。。或是等于集合A
 * 反之，从集合A的每个元素来看，它只有两种状态，或属于/或不属于幂集的元素集。
 * 因此，求幂集的过程可以看作依次对A中的元素进行取或舍的过程。
 * 类比0-1背包问题，解空间树都可以组织成这样：第一层决定取或舍第一个元素，第二层决定取或舍第二个元素。。。
 * 最终的所有叶子节点就是集合的所有幂集！！
 */
public class PowerSet {

	/*
	 * 对第n个元素进行取舍
	 */
	static void powerset(int depth, int[] data, boolean[] path) {
		if (depth > data.length) { // 所有元素都完成取舍
			for (int i = 0; i < data.length; i++) {
				if (path[i]) {
					System.out.print(data[i] + " ");
				}
			}
			System.out.println();
		} else {
			// 对第n个元素进行取舍
			powerset(depth + 1, data, concat(path, false));
			powerset(depth + 1, data, concat(path, true));
		}
	}

	private static boolean[] concat(boolean[] path, boolean b) {
		boolean[] res = new boolean[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = b;
		return res;
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 3 };
		powerset(1, data, new boolean[0]);
	}
}
