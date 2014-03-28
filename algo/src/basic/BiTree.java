package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BiTree {

	public BiNode root;

	public BiTree(BiNode root) {
		this.root = root;
	}

	/**
	 * recursive
	 */
	public void preOrder() {
		preOrderTraverse(root);
		System.out.println();
	}

	public void inOrder() {
		inOrderTraverse(root);
		System.out.println();
	}

	public void postOrder() {
		postOrderTraverse(root);
		System.out.println();
	}

	private void preOrderTraverse(BiNode node) {
		if (node == null) {
			return;
		} else {
			visit(node);
			preOrderTraverse(node.left);
			preOrderTraverse(node.right);
		}
	}

	private void inOrderTraverse(BiNode node) {
		if (node == null) {
			return;
		} else {
			inOrderTraverse(node.left);
			visit(node);
			inOrderTraverse(node.right);
		}
	}

	private void postOrderTraverse(BiNode node) {
		if (node == null) {
			return;
		} else {
			postOrderTraverse(node.left);
			postOrderTraverse(node.right);
			visit(node);
		}
	}

	/**
	 * 非递归的先序、中序、后序都需要用一个stack来保存已访问的结点，以便后续再次访问
	 * 先序的遍历过程：
	 * 从根节点开始，依次访问根节点，根结点->left, 根结点->left->left...
	 * 当left为空时，说明是当前结点和左子树已经访问完毕，接下来应访问右子树，即先弹栈，并指向右子树
	 * 右子树的访问过程类似于根节点
	 */
	public void preOrderNR() {
		Stack<BiNode> s = new Stack<BiNode>();
		BiNode p = root;
		while (p != null || !s.empty()) {
			if (p != null) {
				s.push(p);
				visit(p);
				p = p.left;
			} else {
				p = s.pop();
				p = p.right;
			}
		}
	}

	/**
	 * 最简洁的非递归先序(深度遍历)
	 */
	public void preOrderNR2() {
		Stack<BiNode> s = new Stack<BiNode>();
		s.push(root);
		while (!s.isEmpty()) {
			BiNode p = s.pop();
			visit(p);
			if (p.right != null) {
				s.push(p.right);
			}
			if (p.left != null) {
				s.push(p.left);
			}
		}
	}

	/**
	 * 中序的遍历过程：
	 * 从根节点开始，先对遇到的结点压栈，依次压栈根节点，根结点->left, 根结点->left->left...
	 * 当left为空，表明左子树访问完毕，则应弹栈，访问当前的根节点，然后指向右子树
	 * 右子树的访问过程类似于根节点
	 */
	public void inOrderNR() {
		Stack<BiNode> s = new Stack<BiNode>();
		BiNode p = root;
		while (p != null || !s.empty()) {
			if (p != null) {
				s.push(p);
				p = p.left;
			} else {
				p = s.pop();
				visit(p);
				p = p.right;
			}
		}
	}

	/**
	 * 后序的遍历过程：
	 * 先沿着左子树走到最左深处left most，并压栈相应的结点
	 * 判断如果没有右子树或是右子树已经访问过，则访问当前结点，并标记当前结点被访问，
	 * 并弹栈（当前结点的左子树、右子树已经先于当前结点被访问过，因此以当前结点为根的子树已经访问完毕）
	 * 若干有右子树且右子树没有被访问，则将当前结点压栈，并使指针指向右子树
	 * 右子树的访问过程类似于根结点
	 */
	public void postOrderNR() {
		Stack<BiNode> s = new Stack<BiNode>();
		BiNode p = root;
		BiNode visited = null;

		while (p != null) {
			// walk to left most
			while (p.left != null) {
				s.push(p);
				p = p.left;
			}

			// no right tree/right_tree is just visited
			while (p.right == null || p.right == visited) {
				visit(p);
				visited = p;
				if (s.empty()) {
					return;
				}
				p = s.pop();
			}

			// handle right tree
			s.push(p);
			p = p.right;
		}
	}

	/**
	 * 广度优先遍历/层次遍历
	 * 用一个队列保存当前节点的子女节点，遍历每个结点时，先访问当前结点，并将其左右孩子增加到队列
	 */
	public void widthFirst() {
		List<BiNode> queue = new ArrayList<BiNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BiNode p = queue.remove(0);
			visit(p);
			if (p.left != null) {
				queue.add(p.left);
			}
			if (p.right != null) {
				queue.add(p.right);
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		List<BiNode> queue = new ArrayList<BiNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BiNode p = queue.remove(0);
			buf.append(p.name + " ");
			if (p.left != null) {
				queue.add(p.left);
			}
			if (p.right != null) {
				queue.add(p.right);
			}
		}

		return buf.toString();
	}

	protected void visit(BiNode TreeNode) {
		System.out.print(TreeNode.name + " ");
	}
}
