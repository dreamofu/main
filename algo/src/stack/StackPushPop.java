package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 题目： 
 * 输入两个整数序列。其中一个序列表示栈的push顺序，判断另一个序列有没有可能是对应的pop顺序。
 * 
 * 思路： 
 * 如果我们希望pop的数字正好是栈顶数字，直接pop出栈即可；
 * 如果希望pop的数字目前不在栈顶，我们就到push序列中还没有被push到栈里的数字中去搜索这个数字，并把在它之前的所有数字都push进栈。
 * 如果所有的数字都被push进栈仍然没有找到这个数字，表明该序列不可能是一个pop序列。
 */
public class StackPushPop {

	private List<Integer> pushSeq; //用一个队列保存还未push到栈中的元素

	public StackPushPop(List<Integer> pushSeq) {
		super();
		this.pushSeq = pushSeq;
	}

	public void setPushSeq(List<Integer> pushSeq) {
		this.pushSeq = pushSeq;
	}

	public boolean isPopSeq(List<Integer> popSeq) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int popElem : popSeq) {
			// 若是栈顶元素，直接弹出
			if (!stack.isEmpty() && stack.peek().intValue() == popElem) {
				stack.pop();
			} else if (pushSeq.contains(popElem)) {
				// 将该元素之前的所有元素push进栈，最后弹出那个元素
				int i = 0;
				for (; i < pushSeq.size(); i++) {
					stack.push(pushSeq.get(i));
					if (pushSeq.get(i) == popElem) {
						break;
					}
				}
				pushSeq = pushSeq.subList(i + 1, pushSeq.size());

				stack.pop();
			} else {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		StackPushPop spp = new StackPushPop(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(spp.isPopSeq(Arrays.asList(4, 5, 3, 2, 1)));
		spp.setPushSeq(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(spp.isPopSeq(Arrays.asList(4, 3, 5, 1, 2)));
		spp.setPushSeq(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(spp.isPopSeq(Arrays.asList(2, 1, 4, 3, 5)));
		spp.setPushSeq(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(spp.isPopSeq(Arrays.asList(3, 5, 4, 2, 1)));
	}
}
