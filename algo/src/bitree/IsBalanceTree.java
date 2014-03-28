package bitree;

import basic.BiNode;

/*
 * 题目：
 * 输入一棵二叉树的根结点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意结点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * 
 * 分析：
 * 思路1：判断以任意结点为根结点的子树是否平衡：求子树的左右子树深度差，若小于1，则是平衡的，否则不平衡。
 * 然后从根结点递归地遍历每个结点，判断以每个结点为根的子树是否平衡，最终判断整棵树是否平衡。
 * 以上思路的缺点是需要重复检查子树是否平衡。
 * 思路2：若已求出左右子树是否平衡，并已知左右子树的深度，则可以直接判断当前结点是否平衡。此处，需要先求出
 * 左右子树的深度，然后用于判断当前结点是否平衡，故采用后序遍历。
 */
public class IsBalanceTree {

	/*
	 * 判断以node为根的树是否平衡，并计算以node为根的树的深度
	 */
	static boolean isBalance(BiNode node, int[] depth) {
		if (node == null) {
			depth[0] = 0;
			return true;
		}

		int[] leftDepth = new int[1];
		int[] rightDepth = new int[1];
		/*
		 * 若左右子树都平衡，则进一步判断当前结点是否平衡；同时，根据左右子树的深度，计算当前结点的深度
		 */
		if (isBalance(node.left, leftDepth) && isBalance(node.right, rightDepth)) {
			depth[0] = (leftDepth[0] > rightDepth[0]) ? leftDepth[0] + 1 : rightDepth[0] + 1;
			if (Math.abs(leftDepth[0] - rightDepth[0]) <= 1) {
				return true;
			}
			return false;
		}
		return false;
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

		int[] depth = new int[1];
		System.out.println(isBalance(root9, depth));
		System.out.println(depth[0]);

		BiNode root9_l_r_1 = new BiNode("8");
		root9_l_r.left = root9_l_r_1;
		System.out.println(isBalance(root9, depth));
		System.out.println(depth[0]);

	}
}
