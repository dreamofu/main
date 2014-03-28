package bitree;

import basic.BiNode;

/*
 * 题目：
 * 将二元查找树转换为排序的双向链表
 * 
 * 分析：
 * 中序遍历整棵树。按照这个方式遍历树，比较小的结点先访问。
 * 如果我们每访问一个结点，假设之前访问过的结点已经调整成一个排序双向链表，
 * 我们再调整当前结点的指针将其链接到链表的末尾。当所有结点都访问过之后，
 * 整棵树也就转换成一个排序双向链表了。 
 * 实现：每遍历到一个结点，需要链表将当前结点链到之前链表的尾结点，因此需要保存当前的链表尾结点
 * 
 */
public class BiTree2DLink_2 {

	static BiNode head = null; // 排序链表的头结点
	static BiNode tail = null; // 排序链表的尾结点

	/**
	 * 中序遍历，当遍历到某个结点时，将这个结点与链表的尾结点链接上
	 * 
	 * @param node
	 */
	static void convert(BiNode node) {
		// 此处进行null判断，下面就无需判断node.left/node.right是否为null
		if (node == null) {
			return;
		}
		convert(node.left);
		// 将当前结点链接到尾结点上，并将当前结点设为尾结点
		if (tail != null) {
			tail.right = node;
			node.left = tail;
			// 第一次tailNode不为空时，赋值给head
			if (head == null) {
				head = tail;
			}
		}
		tail = node; // 当前结点已连上链表，尾结点变为当前结点
		convert(node.right);
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

		convert(root9);
		while (head != null) {
			System.out.println(head.name);
			head = head.right;
		}
	}
}
