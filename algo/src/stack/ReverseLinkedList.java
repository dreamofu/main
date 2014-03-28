package stack;

import basic.LinkedNode;

/*
 * 反转链表
 * 
 * 递归思路：先反转后面n-1长度的链表，然后将当前结点追加到尾部
 */
public class ReverseLinkedList {

	/*
	 * 反转链表(递归)
	 */
	public static LinkedNode reverseLinkedList(LinkedNode head) {
		// 若节点为空或只有单个节点，则直接返回
		if (head == null || head.next == null) {
			return head;
		}
		
		// 先将后面n-1长度的链表反转
		LinkedNode reversedLeft = reverseLinkedList(head.next);
		// 走到链表的最后一个节点
		LinkedNode tmp = reversedLeft;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		// 将头结点增加到反转链表的尾部
		tmp.next = head;
		head.next = null;
		
		return reversedLeft;
	}

	/*
	 * 反转链表（非递归） 用三个指针，priorNode, currNode, nextNode
	 */
	public static LinkedNode reverseLinkedList2(LinkedNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		LinkedNode priorNode = null;
		LinkedNode currNode = head;
		LinkedNode nextNode = null;
		while(currNode != null){
			nextNode = currNode.next;
			currNode.next = priorNode;
			priorNode = currNode;
			currNode = nextNode;
		}
		return priorNode;
	}

	public static void main(String[] args) {
		LinkedNode head = new LinkedNode();
		head.name = "head";
		LinkedNode node1 = new LinkedNode();
		node1.name = "node1";
		LinkedNode node2 = new LinkedNode();
		node2.name = "node2";
		head.next = node1;
		node1.next = node2;

		LinkedNode newHead = reverseLinkedList(head);
		//LinkedNode newHead = reverseLinkedList2(head);
		LinkedNode tmp = newHead;
		while (tmp != null) {
			System.out.println(tmp.name);
			tmp = tmp.next;
		}

	}
}
