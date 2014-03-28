package backtrack;

import java.util.Arrays;

public class DiceSum {

	static int[] DICE = {1,2,3,4,5,6};
	
	/*
	 * left 剩余未分配的点数
	 * NUM_DICE 限制回溯的最大深度
	 */
	static void dice(int left, int[] path, final int NUM_DICE){
		if(path.length == NUM_DICE){
			if(left == 0){
				System.out.println(Arrays.toString(path));
			}
		}else{
			for(int d : DICE){
				if(d <= left){
					dice(left - d, concat(path, d), NUM_DICE);
				}
			}
		}
	}
	
	private static int[] concat(int[] path, int d) {
		int[] res = new int[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = d;
		return res;
	}
	
	public static void main(String[] args) {
		DiceSum.dice(8, new int[0], 5);
	}
}
