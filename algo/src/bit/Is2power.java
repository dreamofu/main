package bit;

/*
 * 题目：
 * 判断一个正数是否为2的次方
 * 
 * 思路：
 * 当一个正数的二进制表示中只有一位为1，其余为0时，则满足要求
 * 而问题又可以转换为判断二进制表示中1的个数是否为1
 */
public class Is2power {

	public static boolean is2power(int n) {
		return NumOf1.numof1_2(n) == 1 ? true : false;
	}

	public static void main(String[] args) {
		int n = 16;
		System.out.println(is2power(n));
		n = 17;
		System.out.println(is2power(n));
	}
}
