package bitree;

import basic.BiNode;

/**
 * 题目：
 * 输入两棵二叉树A和B，判断树B是不是A的子结构。
 * 
 * 思路：
 * 在A中首先看以rootA为根的子树是否包含B，若不包含，则以相同的方式继续搜索它的左右子树；
 * 而判断一个树是否包含另一个树，可以采用递归来实现，首先比较两棵树的根节点，然后依次递归地比较他们的左右子树
 * 
 */
public class SubTree {

	/*
	 * 判断A是否包含B
	 */
	public static boolean containSubTree(BiNode nodeA, BiNode nodeB) {
		if (nodeB == null) {
			return true;
		}
		if (nodeA == null) {
			return false;
		}

		/* 注意不要写成if/else的结构，在根节点中查不到，要继续在左右子树中查 */
		boolean result = compareSubTree(nodeA, nodeB);
		if (!result) {
			result = containSubTree(nodeA.left, nodeB);
		}
		if (!result) {
			result = containSubTree(nodeA.right, nodeB);
		}
		return result;
	}

	/*
	 * 比较以nodeA为根的子树是否包含以nodeB为根的子树
	 */
	private static boolean compareSubTree(BiNode nodeA, BiNode nodeB) {
		if (nodeB == null || (nodeA == null && nodeB == null)) {
			return true;
		}

		if (nodeA.name.equals(nodeB.name)) {
			return compareSubTree(nodeA.left, nodeB.left)
					&& compareSubTree(nodeA.right, nodeB.right);
		}

		return false;
	}

	public static void main(String[] args) {
		BiNode root9 = new BiNode("9");
		BiNode root9_l = new BiNode("9");
		BiNode root9_r = new BiNode("4");
		BiNode root9_l_l = new BiNode("8");
		BiNode root9_l_r = new BiNode("7");
		BiNode root9_l_l_l = new BiNode("5");
		root9.left = root9_l;
		root9.right = root9_r;
		root9_l.left = root9_l_l;
		root9_l.right = root9_l_r;
		root9_l_l.left = root9_l_l_l;

		System.out.println(containSubTree(root9, root9_l));
	}
}
