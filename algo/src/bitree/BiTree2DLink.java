package bitree;

import basic.BiNode;

/*
 * 题目：
 * 将二元查找树转换为排序的双向链表
 * 
 * 分析：
 * 所有二叉树相关的题目都可以考虑递归，基本思路如下：
 * 当到达某一结点准备调整以该结点为根结点的子树时，
 * 先调整其左子树将左子树转换成一个排好序的左子链表，
 * 再调整其右子树转换成以排序的右子链表，
 * 最后链接左子链表的最右结点（左子树的最大结点）、当前结点和右子链表的最左结点（右子树的最小结点）。
 * 从树的根结点开始递归调整所有结点，最终会得到排序的双向链表。
 * 
 * 从以上的分析可以看出，我们需要得到左子树的最大结点，或是右子树的最小结点，让递归函数返回该结点即可
 * 最终的要返回排序链表的头指针，即最小结点
 */
public class BiTree2DLink {

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

		BiNode head = convert(root9);
		while (head != null) {
			System.out.println(head.name);
			head = head.right;
		}
	}

	public static BiNode convert(BiNode head) {
		return convert(head, true);
	}

	/**
	 * 将以node为根结点的二叉树转换为排序双向链表， 第一步是将以node为根的子树转换为排序链表；
	 * 第二步是依据node为左子树或是右子树，确定返回最大结点或是最小结点
	 * 
	 * @param node
	 *            根结点
	 * 
	 * @param asRight
	 *            是否为右子树，用于确定返回最大结点或是最小结点
	 * 
	 * @return 左子树的最大结点或右子树的最小结点
	 */
	public static BiNode convert(BiNode node, boolean asRight) {
		if (node == null) {
			return null;
		}

		BiNode left = null;
		BiNode right = null;
		// 若左子树不为空，将左子树转成排序链表，并返回最大结点
		if (node.left != null) {
			left = convert(node.left, false);
		}
		// 若左子树不为空，连接左子树的最大结点与根节点
		if (left != null) {
			left.right = node;
			node.left = left;
		}
		// 若右子树不为空，将右子树转成排序链表，并返回最小结点
		if (node.right != null) {
			right = convert(node.right, true);
		}
		// 若右子树不为空，连接根节点与右子树的最小结点
		if (right != null) {
			right.left = node;
			node.right = right;
		}

		// 返回以当前结点node为根结点的子树的最大或最小结点
		BiNode tmp = node;
		// 若是右子树，返回最小结点
		if (asRight) {
			while (tmp.left != null) {
				tmp = tmp.left;
			}
		} else { // 若是左子树，返回最大结点
			while (tmp.right != null) {
				tmp = tmp.right;
			}
		}
		return tmp;
	}
}
