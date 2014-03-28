package others;

public class AppleNum {

	public static void main(String[] args) {
		int N = 10;
		int n = 0;
		for (int i = 1;; i++) {
			if ((i * 21 - 1) % 5 == 0) {
				System.out.println(i * 21 + 2);

				if (++n == N) {
					break;
				}
			}
		}
	}
}
