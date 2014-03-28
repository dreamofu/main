package algo.recursive;

import basic.BiNode;
import basic.BiTree;
/**
 * 题目：
 * 给定二叉树的先序和中序遍历结果，重建该二叉树
 * 
 * 思路：
 * 由先序找到根节点，然后再中序中中找到左右子树，而左右子树的构建过程类似于根节点，因此是一个典型的递归
 *
 */
public class RebuildBiTree {

	public static BiTree constructTree(String[] preOrder, String[] inOrder) {
		BiNode node = construct(preOrder, inOrder);
		return new BiTree(node);
	}

	public static BiNode construct(String[] preOrder, String[] inOrder) {
		if (preOrder.length == 0) {
			return null;
		} else if (preOrder.length == 1) {
			return new BiNode(preOrder[0]);
		} else {
			BiNode root = new BiNode(preOrder[0]);
			/* split array */
			int i = 0;
			for (i = 0; i < inOrder.length; i++) {
				if (inOrder[i].equals(preOrder[0])) {
					break;
				}
			}
			String[] preLeft = new String[i];
			String[] preRight = new String[preOrder.length - i - 1];
			String[] inLeft = new String[i];
			String[] inRight = new String[inOrder.length - i - 1];
			for (int j = 0; j < preLeft.length; j++) {
				preLeft[j] = preOrder[j + 1];
			}
			for (int j = 0; j < preRight.length; j++) {
				preRight[j] = preOrder[j + i + 1];
			}
			for (int j = 0; j < inLeft.length; j++) {
				inLeft[j] = inOrder[j];
			}
			for (int j = 0; j < inRight.length; j++) {
				inRight[j] = inOrder[j + i + 1];
			}
			root.left = construct(preLeft, inLeft);
			root.right = construct(preRight, inRight);
			return root;
		}
	}

	public static void main(String[] args) {
		String[] preOrder = "1,2,4,7,3,5,6,8".split(",");
		String[] inOrder = "4,7,2,1,5,3,8,6".split(",");
		BiTree tree = constructTree(preOrder, inOrder);
		//tree.widthFirst();
		tree.preOrder();
		tree.inOrder();
	}
}
