package backtrack;

public class PowerSet {

	static void powerSet(int[] x, boolean[] path){
		if(path.length == x.length){
			System.out.print("#");
			for(int i=0; i<path.length; i++){
				if(path[i]){
					System.out.print(x[i]);
				}
			}
			System.out.println("#");
		}else{
			powerSet(x, concat(path, true));
			powerSet(x, concat(path, false));
		}
	}
	
	private static boolean[] concat(boolean[] path, boolean d) {
		boolean[] res = new boolean[path.length + 1];
		for (int i = 0; i < path.length; i++) {
			res[i] = path[i];
		}
		res[res.length - 1] = d;
		return res;
	}

	public static void main(String[] args) {
		int[] x = { 1, 2, 3 };
		PowerSet.powerSet(x, new boolean[0]);
	}
}
