package others;

/*
 * 给定一个正数s，打印所有和为s的连续整数序列（长度至少为2）
 * 例如：15 = 1+2+3+4+5 = 4+5+6 = 7+8
 * 
 * 思路：定义两个量low, high表示序列的起始和结束数字，low=1, high<=(s+1)/2
 * 当累加值小于s时，可以增大high；大于s时，可以增大low
 */
public class ContinuousSum {

	static void continuousSum(int sum) {
		int low = 1, high = 2;
		while (low < high && high <= (sum + 1) / 2) {
			// 计算累加值
			int total = 0;
			for (int n = low; n <= high; n++) {
				total += n;
			}

			// 依据累加值移动low, high
			if (total == sum) {
				System.out.println(String.format("%d+...+%d = %d", low, high, sum));
				high++;
			} else if (total < sum) {
				high++;
			} else {
				low++;
			}
		}

	}

	public static void main(String[] args) {
		continuousSum(15);
	}
}
