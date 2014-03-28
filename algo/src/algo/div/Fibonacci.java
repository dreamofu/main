package algo.div;

/*
 * 递归、非递归计算Fibonacci数列
 */
public class Fibonacci {

	public static int fabonacci(int n) {
		if (n <= 1) {
			return n;
		}

		int low = 0;
		int high = 1;
		for (int i = 2; i <= n; i++) {
			int tmp = high;
			high += low;
			low = tmp;
		}
		return high;
	}

	public static int fabonacci2(int n) {
		if (n <= 1) {
			return n;
		}

		return fabonacci2(n - 1) + fabonacci2(n - 2);
	}

	public static void main(String[] args) {
		int n = 10;
		System.out.println(fabonacci(n));
		System.out.println(fabonacci2(n));
	}

}
