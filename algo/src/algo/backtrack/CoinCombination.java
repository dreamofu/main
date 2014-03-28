package algo.backtrack;

import java.util.Arrays;
import java.util.Stack;

/*
 * 
 * 使用回溯法来系统地搜索问题的所有解（可以附带找出最优解）
 * 
 * void fn(int num, int[] path){
 *      if(num==0即到达可行解){
 *          print(path); //打印搜索到的结果
 *      }else{
 *          for(int i=f(num); i<=g(num); i++){ //遍历可选节点
 *              if(i满足约束条件){
 *              	fn(num-i, path+i);//选择可选结点，继续向深层搜索
 *              }
 *          }
 *      }
 * }
 * 
 */

public class CoinCombination {

	private static int[] coins = { 10, 5, 2, 1 };

	//===============================================================================
	// 法1：使用stack来保存当前的搜素路径
	private static Stack<Integer> stack = new Stack<Integer>(); // 保存当前的搜索路径

	private static void divide(int num) {
		if (num == 0) { // 到达叶子节点，搜索到一个可行解
			System.out.println(stack);
		} else {
			for (int coin : coins) { // 遍历可选节点
				if (coin <= num && (stack.isEmpty() || coin <= stack.peek())) { // 满足约束条件
					stack.push(coin); // 选择可选结点
					divide(num - coin); // 继续向深层搜索
					stack.pop(); // 以该结点为根的子树搜索完成，搜索下一个可选节点
				}
			}
		}
	}

	// ===============================================================================
	// 法2：将当前搜索路径作为递归函数的参数直接传入
	/**
	 * @param num 待划分的硬币数
	 * @param path 当前的搜索路径
	 */
	private static void divide(int num, int[] path) {
		if (num == 0) { //到达可行解
			System.out.println(Arrays.toString(path)); //输出可行解
		} else {
			for (int coin : coins) { //遍历可选结点
				if (coin <= num
						&& (path.length == 0 || coin <= path[path.length - 1])) { //判断是否满足条件
					divide(num - coin, concat(path, coin)); //继续向深一层搜索
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
		CoinCombination.divide(7);
		System.out.println("##############");
		CoinCombination.divide(7, new int[0]);
	}
}
