package stack;

import java.util.Stack;

/*
 * 题目：
 * 颠倒一个栈
 * 
 * 思路：
 * 栈相关的题大多可用递归的思路来求解。
 * 要颠倒一个长度为n的栈，可用先弹出栈顶元素，将长度为n-1的子栈颠倒，然后将栈顶加到已颠倒的子栈的底部
 * 而将一个元素加入一个栈的底部同样可用递归来求解，先将该元素加入子栈的底部，然后将栈顶元素放回去
 */
public class ReverseStack {

	/**
	 * 1. pop the top element 
	 * 2. reverse the remaining stack 
	 * 3. add the top element to the bottom of the remaining stack
	 */
	public static void reverseStack(Stack<Integer> stack) {
		if (!stack.isEmpty()) {
			int top = stack.pop();
			reverseStack(stack);
			addToStackBottom(stack, top);
		}
	}

	/**
	 * 1. pop the top element 
	 * 2. add the 'newElement' to the bottom of the remaining stack 
	 * 3. push the top element
	 */
	public static void addToStackBottom(Stack<Integer> stack, int newElement) {
		if (stack.isEmpty()) {
			stack.push(newElement);
		} else {
			int top = stack.pop();
			addToStackBottom(stack, newElement);
			stack.push(top);
		}
	}

	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		System.out.println(stack);
		reverseStack(stack);
		System.out.println(stack);
	}
}
