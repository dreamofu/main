package backtrack;

import java.util.Arrays;

public class CoinComb {

	static int[] VALUE = {10, 5, 2, 1};
	
	/*
	 * left 剩余未分配的面值
	 */
	static void coinComb(int left, int[] path){
		if(left == 0){
			System.out.println(Arrays.toString(path));
		}else{
			for(int v : VALUE){
				if(v <= left && (path.length == 0 || v <= path[path.length-1])){
					coinComb(left - v, concat(path, v));
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
		CoinComb.coinComb(7, new int[0]);
	}
}
