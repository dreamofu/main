package bitree;

/*
 * 题目：
 * 输入一个整数数组，判断该数组是不是某二元查找树的后序遍历的结果。
 * 
 * 思路：
 * 在后续遍历得到的序列中，最后一个元素为树的根结点。
 * 从头开始扫描这个序列，比根结点小的元素都应该位于序列的左半部分；
 * 从第一个大于跟结点开始到跟结点前面的一个元素为止，所有元素都应该大于跟结点，因为这部分元素对应的是树的右子树。
 * 根据这样的划分，把序列划分为左右两部分，我们递归地确认序列的左、右两部分是不是都是二元查找树。
 */
public class IsBiSearchTreePostOrder {

	public static boolean isPostOrder(int[] seq) {
		boolean result = true;

		int rootVal = seq[seq.length - 1];
		/* 找出右子树起始下标 */
		int i = -1;
		for (i = 0; i < seq.length - 1; i++) {
			if (seq[i] > rootVal) {
				break;
			}
		}

		/* 判断所有右子树结点都大于左子树 */
		if (i > 0 && i < seq.length - 1) { // 只有当左右子树都存在时，才需要比较，否则，无需比较
			for (int j = i; j < seq.length - 1; j++) {
				if (seq[j] < rootVal) {
					result = false;
					break;
				}
			}
		}

		/* 根结点满足，且存在左子树，继续判断左子树是否满足 */
		if (result && i > 0) {
			int[] leftSeq = new int[i];
			for (int j = 0; j < i; j++) {
				leftSeq[j] = seq[j];
			}
			result = isPostOrder(leftSeq);
		}

		/* 根结点、左子树满足，且存在右子树，继续判断右子树是否满足 */
		if (result && i < seq.length - 1) {
			int[] rightSeq = new int[seq.length - i - 1];
			for (int j = 0; j < rightSeq.length; j++) {
				rightSeq[j] = seq[j + i];
			}
			result = isPostOrder(rightSeq);
		}

		return result;
	}

	public static void main(String[] args) {
		int[] seq = { 5, 9, 8, 13, 10 };
		System.out.println(isPostOrder(seq));
		seq = new int[] { 5, 12, 8, 13, 10 };
		System.out.println(isPostOrder(seq));
	}
}
