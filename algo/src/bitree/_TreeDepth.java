package bitree;

import basic.BiNode;

/*
 * 题目：
 * 求二叉树的深度
 * 
 * 思路：
 * 递归的思想，树的深度等于左子树的深度+1，或右子树的深度+1，取决于哪个子树较深
 */
public class _TreeDepth {

	/**
	 * 
	 * @param node
	 * @return 以node为根节点的树的深度
	 */
	static int depth(BiNode node) {
		if (node == null) {
			return 0;
		}

		int leftDepth = depth(node.left);
		int rightDepth = depth(node.right);
		return (leftDepth > rightDepth) ? 1 + leftDepth : 1 + rightDepth;
	}

	public static void main(String[] args) {
		BiNode root9 = new BiNode("10");
		BiNode root9_l = new BiNode("8");
		BiNode root9_r = new BiNode("11");
		BiNode root9_l_l = new BiNode("7");
		BiNode root9_l_r = new BiNode("9");
		BiNode root9_l_l_l = new BiNode("6");
		root9.left = root9_l;
		root9.right = root9_r;
		root9_l.left = root9_l_l;
		root9_l.right = root9_l_r;
		root9_l_l.left = root9_l_l_l;

		System.out.println(depth(root9));
		System.out.println(depth(root9_l_l));
	}
}
