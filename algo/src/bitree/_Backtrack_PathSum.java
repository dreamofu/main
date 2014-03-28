package bitree;

import java.util.Arrays;
import java.util.Stack;

import basic.BiNode;

/*
 * 题目：
 * 输入一个整数和一棵二元树。从树的根结点开始往下访问一直到叶结点所经过的所有结点形成一条路径。
 * 打印出和与输入整数相等的所有路径。
 * 
 * 思路：
 * 回溯法，树的深度遍历，使用一个stack保存当前的访问路径
 * 若是叶子节点，验证是否满足条件
 * 若不是叶子节点，依据限定函数，选择可以向下的方向继续搜索
 */
public class _Backtrack_PathSum {

	static Stack<Integer> stack = new Stack<Integer>();

	public static void pathSum(BiNode node, int sum) {
		boolean isLeaf = (node.left == null && node.right == null) ? true
				: false;
		/* 若是叶子结点，判断路径和是否与预期值相同 */
		if (isLeaf) {
			if (sum == Integer.parseInt(node.name)) {
				stack.push(Integer.parseInt(node.name));
				System.out.println("* " + stack);
				// System.out.println(stack);
				stack.pop();
			}
		} else {
			/* 非叶子结点，只有当当前的累计和小于sum才需要继续向下搜索 */
			if (Integer.parseInt(node.name) < sum) {
				stack.push(Integer.parseInt(node.name));
				System.out.println("# " + stack);
				sum -= Integer.parseInt(node.name);
				if (node.left != null) {
					pathSum(node.left, sum);
				}
				if (node.right != null) {
					pathSum(node.right, sum);
				}
				stack.pop();
			}
		}
	}

	// -----------------------------------------------------------------------------
	// 标准的二叉树回溯法(递归方式)
	static void pathSum(BiNode node, int sum, int[] path) {
		boolean isLeaf = (node.left == null && node.right == null) ? true
				: false;
		if (isLeaf) {
			if (sum == Integer.parseInt(node.name)) {
				System.out.println(Arrays.toString(path) + " " + node.name);
			}
		} else {
			if (Integer.parseInt(node.name) < sum) {
				if(node.left != null){
					pathSum(node.left, sum-Integer.parseInt(node.name), append(path, node.name));
				}
				if(node.right != null){
					pathSum(node.right, sum-Integer.parseInt(node.name), append(path, node.name));
				}
			}
		}
	}
	
	private static int[] append(int[] path, String name) {
		int[] res = null;
		if (path == null || path.length == 0) {
			res = new int[] { Integer.parseInt(name) };
		} else {
			res = new int[path.length + 1];
			for (int i = 0; i < path.length; i++) {
				res[i] = path[i];
			}
			res[res.length - 1] = Integer.parseInt(name);
		}
		return res;
	}

		
	public static void main(String[] args) {
		BiNode root9 = new BiNode("10");
		BiNode root9_l = new BiNode("5");
		BiNode root9_r = new BiNode("12");
		BiNode root9_l_l = new BiNode("4");
		BiNode root9_l_r = new BiNode("7");
		root9.left = root9_l;
		root9.right = root9_r;
		root9_l.left = root9_l_l;
		root9_l.right = root9_l_r;

		pathSum(root9, 22);
		System.out.println("#######################");
		pathSum(root9, 22, new int[0]);
	}
}
