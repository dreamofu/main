package algo.backtrack;

import java.util.Arrays;
import java.util.Stack;

/*
 * 青蛙跳台阶，n个台阶，每次1、2步，共多少种跳法
 */
public class FrogJump {
	static int[] STEPS = { 2, 1 };

	//==============================================================================
	// 法1：使用stack保存当前的搜索路径
	static int num = 0; //多少种解法
	static Stack<Integer> stack = new Stack<Integer>(); //保存当前的搜索路径

	public static void divide(int n) {
		if (n == 0) {
			System.out.println(stack);
			++num;
		} else {
			for (int step : STEPS) {
				/* 由于跳跃是包含次序的，而分硬币是不含次序的，故这里不需要次序的限制条件 */
				if (step <= n /* && (stack.isEmpty() || step <= stack.peek()) */) {
					stack.push(step);
					divide(n - step);
					stack.pop();
				}
			}
		}
	}
	
	//==============================================================================
	// 法2：使用函数参数保存当前的搜索路径
	static int numJump = 0;

	public static void divide(int n, int[] path) {
		if (n == 0) {
			System.out.println(Arrays.toString(path));
			numJump++;
		} else {
			for (int step : STEPS) {
				if (step <= n) {
					divide(n - step, concat(path, step));
				}
			}
		}
	}
	
	private static int[] concat(int[] path, int k) {
		int[] res = new int[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = k;
		return res;
	}

	public static void main(String[] args) {
		int n = 10;
		divide(n);
		System.out.println("num=" + num);
		
		divide(n, new int[0]);
		System.out.println("numJump=" + numJump);
	}
}
