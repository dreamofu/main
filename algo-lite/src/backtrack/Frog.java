package backtrack;

import java.util.Arrays;

public class Frog {

	static int[] JUMP = {1, 2};
	
	/*
	 * left Ê£ÓàµÄÌ¨½×
	 */
	static void frog(int left, int[] path){
		if(left == 0){
			System.out.println(Arrays.toString(path));
		}else{
			for(int jump : JUMP){
				if(jump <= left){
					frog(left - jump, concat(path, jump));
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
		Frog.frog(10, new int[0]);
	}
}
