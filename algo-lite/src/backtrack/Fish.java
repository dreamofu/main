package backtrack;

import java.util.Arrays;

public class Fish {

	static int[] FISH = {0,1,2,3,4,5,6,7,8,9,10};
	
	/*
	 * left 剩余未放到桶里的鱼
	 * NUM_BUCKET 限制回溯的最大深度
	 */
	static void fish(int left, int[] path, final int NUM_BUCKET){
		/* 放完numBucket个桶，且鱼都放完了，才是一个有效的结果 */
		if(path.length == NUM_BUCKET){
			if(left == 0){
				System.out.println(Arrays.toString(path));
			}
		}else{
			for(int f : FISH){
				if(f <= left){
					fish(left - f, concat(path, f), NUM_BUCKET);
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
		Fish.fish(10, new int[0], 3);
	}
}
