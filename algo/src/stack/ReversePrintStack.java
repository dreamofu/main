package stack;

import java.util.Stack;

/*
 * 题目：
 * 逆向打印栈
 * 
 * 思路：
 * 将包含n个元素的栈看作是栈顶元素和剩余n-1个其他元素，要逆向打印，只需先打印n-1个其他元素，再打印栈顶元素即可
 * 
 */
public class ReversePrintStack {

	/*
	 * 1. pop the top element 2. reverse print the remaining stack 3. print the
	 * top element
	 */
	public static void reversePrint(Stack<Integer> stack) {
		/*
		 * 递归的结束条件是栈为空
		 */
		if (!stack.isEmpty()) {
			int top = stack.pop();
			reversePrint(stack);
			System.out.print(top + " ");
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
		reversePrint(stack);
	}
}
