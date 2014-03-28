package stack;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * 题目： 
 * 逆序打印链表
 * 
 * 思路：
 * 同逆序打印栈的思路相似，对于长度为n的链表，在当前当前结点之前，先打印后面长度为n-1的子链表
 */
public class ReversePrintLinkedList {

	public static void reversePrint(LinkedList<Integer> linkedList) {
		/*
		 * 递归的结束条件是链表的长度为0
		 */
		if (linkedList.size() > 0) {
			int head = linkedList.poll();
			reversePrint(linkedList); // 先打印子链表
			System.out.print(head + " "); // 再打印头节点
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addAll(Arrays.asList(1, 2, 3, 4, 5));
		reversePrint(list);
	}
}
