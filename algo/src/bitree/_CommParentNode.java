package bitree;

import basic.BiNode;

/*
 * 题目：
 * 输入二叉树中的两个结点A,B，输出这两个结点在树中最低的公共父结点。
 * 
 * 思路：
 * 若A,B分布位于root结点的左右子树，则root结点就是最低的公共父节点；
 * 若A,B都位于root的左子树中，则公共父节点在左子树中；
 * 若A,B都位于root的右子树中，则公共父节点在右子树中；
 * 这里需要实现一个方法，判断给定的结点是在左子树，还是右子树。
 */
public class _CommParentNode {

	/**
	 * 在以node为根结点的子树中查找dest结点
	 * 
	 * @param node
	 *            以node为根的子树
	 * @param dest
	 *            待查找的结点
	 * @return -2 not found, -1 left, 0 node, 1 right
	 */
	static int find(BiNode node, BiNode dest) {
		if (node == null || dest == null) {
			return -2;
		}

		if (node.name.equals(dest.name)) {
			return 0;
		}
		int left = find(node.left, dest);
		if (left != -2) {
			return -1;
		}
		int right = find(node.right, dest);
		if (right != -2) {
			return 1;
		}
		return -2;
	}

	/**
	 * 求a,b的最低公共父节点
	 * 
	 * @param node
	 * @param a
	 * @param b
	 * @return
	 */
	static BiNode lowestCommParent(BiNode node, BiNode a, BiNode b) {
		if (node == null || a == null || b == null || node.name.equals(a.name)
				|| node.name.equals(b.name)) {
			return null;
		}

		int posA = find(node, a);
		int posB = find(node, b);
		if (posA == -2 || posB == -2) {
			return null;
		}
		if (posA != posB) {
			return node;
		} else if (posA == posB && posA == -1) {
			return lowestCommParent(node.left, a, b);
		} else {
			return lowestCommParent(node.right, a, b);
		}
	}

	public static void main(String[] args) {
		BiNode root9 = new BiNode("root");
		BiNode root9_l = new BiNode("l");
		BiNode root9_r = new BiNode("r");
		BiNode root9_l_l = new BiNode("a");
		BiNode root9_l_r = new BiNode("l_r");
		BiNode root9_l_r_l = new BiNode("b");
		root9.left = root9_l;
		root9.right = root9_r;
		root9_l.left = root9_l_l;
		root9_l.right = root9_l_r;
		root9_l_r.left = root9_l_r_l;

		System.out.println(lowestCommParent(root9, root9_l_l, root9_l_r_l));
		System.out.println(lowestCommParent(root9, root9_l_l, root9_r));

	}
}
