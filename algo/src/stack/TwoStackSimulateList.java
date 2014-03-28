package stack;

import java.util.Stack;

/**
 * 题目：
 * 用两个栈模拟一个队列
 * 
 * 思路：
 * 一个栈用于新增，另一个栈用于删除
 * 增加元素时，直接push到新增栈即可
 * 删除元素时，若删除栈非空，直接弹出栈顶；否则，将新增栈中的元素一一弹出到删除栈，然后弹出栈顶。
 *
 */
public class TwoStackSimulateList {

	Stack<Integer> addStack = new Stack<Integer>();
	Stack<Integer> removeStack = new Stack<Integer>();

	public void addToTail(int element) {
		addStack.push(element);
	}

	public int deleteHead() {
		if (removeStack.isEmpty()) {
			while (!addStack.isEmpty()) {
				removeStack.add(addStack.pop());
			}
		}
		if (!removeStack.isEmpty()) {
			return removeStack.pop();
		}

		return -1;
	}

	public static void main(String[] args) {
		TwoStackSimulateList list = new TwoStackSimulateList();
		list.addToTail(1);
		list.addToTail(2);
		System.out.println(list.deleteHead());
		System.out.println(list.deleteHead());
		System.out.println(list.deleteHead());
		list.addToTail(3);
		System.out.println(list.deleteHead());
		list.addToTail(4);
		list.addToTail(5);
		System.out.println(list.deleteHead());
	}
}
