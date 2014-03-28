package bitree;

import java.util.Arrays;

public class PathSum {

	static void pathSum(BiNode node, int[] path, final int SUM){
		if(node.isLeaf()){
			if(sum(path) + node.value == SUM){
				System.out.println(Arrays.toString(path) + ", " + node.value);
			}
		}else{
			if(node.left != null){
				pathSum(node.left, concat(path, node.value), SUM);
			}
			if(node.right != null){
				pathSum(node.right, concat(path, node.value), SUM);
			}
		}
	}

	private static int sum(int[] path) {
		int res = 0;
		for(int p : path){
			res += p;
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
		BiNode root9 = new BiNode(10);
		BiNode root9_l = new BiNode(5);
		BiNode root9_r = new BiNode(12);
		BiNode root9_l_l = new BiNode(4);
		BiNode root9_l_r = new BiNode(7);
		root9.left = root9_l;
		root9.right = root9_r;
		root9_l.left = root9_l_l;
		root9_l.right = root9_l_r;
		
		PathSum.pathSum(root9, new int[0], 22);
	}
}
