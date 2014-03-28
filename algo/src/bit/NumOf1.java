package bit;

/*
 * 题目：
 * 求一个数的二进制表示中1的个数
 * 
 * 思路：
 * 一种思路是将不同的位与1相与；
 * 另一种方法基于这样的观察：把一个数减去1，然后和自身相与，会把该整数最右边的一个1变成0
 */
public class NumOf1 {

	public static int numof1(int n) {
		int flag = 1;
		int num = 0;
		while (flag != 0) {
			if ((n & flag) != 0) {
				num++;
			}
			flag = flag << 1;
		}
		return num;
	}

	/*
	 * 基于这样一种观察： 把一个数减去1，然后和自身相与，会把该整数最右边的一个1变成0
	 */
	public static int numof1_2(int n) {
		int num = 0;
		while (n != 0) {
			++num;
			n = n & (n - 1);
		}
		return num;
	}

	public static void main(String[] args) {
		int n = 9;
		System.out.println(numof1(n));
		System.out.println(numof1_2(n));
	}
}
