package backtrack;

import java.util.Arrays;

public class Perm {

	static void perm(int[] x, int[] path){
		if(x.length == 0){
			System.out.println(Arrays.toString(path));
		}else{
			for(int ele : x){
				perm(delete(x, ele), concat(path, ele));
			}
		}
	}
	
	private static int[] delete(int[] data, int d) {
		int[] res = new int[data.length - 1];
		for (int i = 0, j = 0; i < data.length; i++) {
			if (data[i] == d) {
				continue;
			}
			res[j++] = data[i];
		}
		return res;
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
		int[] x = {1, 2, 3, 4};
		Perm.perm(x, new int[0]);
	}
}
