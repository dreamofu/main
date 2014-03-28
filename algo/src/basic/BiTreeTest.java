package basic;

import org.junit.Test;

public class BiTreeTest {
	private BiTree tree;

	private void init() {
		BiNode root = new BiNode("d");
		BiNode left = new BiNode("l");
		BiNode right = new BiNode("r");
		BiNode left_left = new BiNode("l_l");
		BiNode left_right = new BiNode("l_r");
		BiNode left_right_right = new BiNode("l_r_r");
		root.left = left;
		root.right = right;
		root.left.left = left_left;
		root.left.right = left_right;
		root.left.right.right = left_right_right;

		tree = new BiTree(root);
	}

	@Test
	public void testTraverse() {
		init();
		// tree.preOrder();
		// tree.preOrderNR();
		// tree.inOrder();
		// tree.inOrderNR();
		// tree.postOrder();
		// tree.postOrderNR();
		tree.widthFirst();
	}
}
