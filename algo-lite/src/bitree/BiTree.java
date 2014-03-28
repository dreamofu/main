package bitree;

class BiNode {
	public int value;
	public BiNode left;
	public BiNode right;
	
	public BiNode(int v){
		value = v;
	}
	
	public boolean isLeaf(){
		if (left == null && right == null){
			return true;
		}
		return false;
	}
}

public class BiTree {
	
	BiNode root;
	
	public BiTree(BiNode root){
		this.root = root;
	}
}
