package bitree;

import basic.BiNode;
import basic.BiTree;

/**
 * 题目： 
 * 输入一颗二元查找树，将该树转换为它的镜像。
 * 
 * 思路：
 * 基于递归的思想，先交换根节点的左右子节点，然后递归地镜像左右子树
 */
public class _MirrorBiTree {

	public static BiNode mirror(BiNode node) {
		if (node == null) {
			return null;
		}

		BiNode tmp = node.right;
		node.right = node.left;
		node.left = tmp;

		mirror(node.left);
		mirror(node.right);

		return node;
	}

	public static void main(String[] args) {
		BiNode root9 = new BiNode("1");
		BiNode root9_l = new BiNode("2");
		BiNode root9_r = new BiNode("3");
		BiNode root9_l_l = new BiNode("4");
		BiNode root9_l_r = new BiNode("5");
		BiNode root9_l_l_l = new BiNode("6");
		root9.left = root9_l;
		root9.right = root9_r;
		root9_l.left = root9_l_l;
		root9_l.right = root9_l_r;
		root9_l_l.left = root9_l_l_l;

		BiTree tree = new BiTree(root9);
		tree.widthFirst();
		System.out.println();
		BiTree mTree = new BiTree(mirror(root9));
		mTree.widthFirst();

	}
}
