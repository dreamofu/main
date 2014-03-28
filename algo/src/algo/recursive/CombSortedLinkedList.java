package algo.recursive;

import basic.LinkedList;
import basic.LinkedNode;

/**
 * 题目：
 * 合并两个已排序的链表
 * 
 * 思路：
 * 正常的思路和递归两种方法
 */
public class CombSortedLinkedList {

	/**
	 * non-recursive
	 */
	public static LinkedList combSortedList(LinkedList one, LinkedList two) {
		LinkedNode head = null;
		LinkedNode p = null;
		LinkedNode i = one.head;
		LinkedNode j = two.head;
		while (i != null && j != null) {
			if (i.name.compareTo(j.name) < 0) {
				if (p == null) {
					head = i;
					p = i;
					i = i.next;
				} else {
					p.next = i;
					i = i.next;
					p = p.next;
				}
			} else {
				if (p == null) {
					head = j;
					p = j;
					j = j.next;
				} else {
					p.next = j;
					j = j.next;
					p = p.next;
				}
			}
		}

		while (i != null) {
			p.next = i;
			i = i.next;
		}
		while (j != null) {
			p.next = j;
			j = j.next;
		}
		return new LinkedList(head);
	}

	/**
	 * recursive
	 */
	public static LinkedList combSortedListRec(LinkedList one, LinkedList two) {
		if (one.head == null) {
			return two;
		}
		if (two.head == null) {
			return one;
		}

		if (one.head.name.compareTo(two.head.name) < 0) {
			LinkedNode head = one.head;
			LinkedList list = combSortedListRec(new LinkedList(one.head.next),
					two);
			head.next = list.head;
			return new LinkedList(head);
		} else {
			LinkedNode head = two.head;
			LinkedList list = combSortedListRec(one, new LinkedList(
					two.head.next));
			head.next = list.head;
			return new LinkedList(head);
		}
	}

	public static void main(String[] args) {
		LinkedNode headOne = new LinkedNode();
		headOne.name = "1";
		LinkedNode one1 = new LinkedNode();
		one1.name = "4";
		LinkedNode one2 = new LinkedNode();
		one2.name = "7";
		headOne.next = one1;
		one1.next = one2;
		LinkedList one = new LinkedList(headOne);
		System.out.println(one);

		LinkedNode headTwo = new LinkedNode();
		headTwo.name = "2";
		LinkedNode two1 = new LinkedNode();
		two1.name = "3";
		LinkedNode two2 = new LinkedNode();
		two2.name = "5";
		headTwo.next = two1;
		two1.next = two2;
		LinkedList two = new LinkedList(headTwo);
		System.out.println(two);

		// System.out.println(combSortedList(one, two));
		System.out.println(combSortedListRec(one, two));
	}
}
