package stack;

import java.util.Stack;

/*
 * 题目：
 * 设计包含min函数的stack，实现push, pop, min方法
 * 
 * 思路：
 * 使用一个辅助栈来保存当前的最小元素，且每次push,pop时，修改对应的辅助栈
 */
public class MinElementStack {

	static Stack<Integer> data = new Stack<Integer>();
	static Stack<Integer> min = new Stack<Integer>();

	public static void push(int value) {
		data.push(value);
		if (min.isEmpty() || value < min.peek()) {
			min.push(value);
		} else {
			min.push(min.peek());
		}
	}

	public static Integer pop() {
		min.pop();
		return data.pop();
	}

	public static Integer min() {
		return min.peek();
	}

	public static void main(String[] args) {
		push(3);
		push(4);
		push(1);
		push(2);
		push(5);
		System.out.println(min);

		pop();
		pop();
		System.out.println(min);
		System.out.println(min());
	}
}
