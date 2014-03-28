package others;

/*
 * String表示的大数相加
 */
public class BigNumberAdd_bad {

	static int ZERO = 48;

	public static String add(String num1, String num2) {
		//S1: 倒序
		String num1r = "";
		for (int j = num1.length() - 1; j >= 0; j--) {
			num1r += num1.toCharArray()[j];
		}
		String num2r = "";
		for (int j = num2.length() - 1; j >= 0; j--) {
			num2r += num2.toCharArray()[j];
		}

		//S2: 从低位向高位相加，考虑进位
		boolean overflow = false;
		String result = "";
		for (int i = 0; i < num1r.length() && i < num2r.length(); i++) {
			int value = (int) num1r.toCharArray()[i] - ZERO
					+ (int) num2r.toCharArray()[i] - ZERO;
			if (overflow) {
				value += 1;
			}

			if (value >= 10) {
				value -= 10;
				overflow = true;
			} else {
				overflow = false;
			}
			result += value;
		}

		/*
		 * S3: 拼接多余的部分， 考虑三种情况：
		 *  num1的长度比num2长，考虑S2的进位，和自身的进位
		 *  num2的长度比num1长 ，考虑S2的进位，和自身的进位
		 *  num1的长度等于num2，注意考虑S2的进位
		 */
		if (result.length() < num1r.length()) {
			for (int i = result.length(); i < num1r.length(); i++) {
				int value = 0;
				if (overflow) {
					value = (int) num1r.toCharArray()[i] - ZERO + 1;
				} else {
					value = (int) num1r.toCharArray()[i] - ZERO;
				}

				if (value >= 10) {
					value -= 10;
					overflow = true;
				} else {
					overflow = false;
				}
				result += value;
			}

			if (overflow) {
				result += "1";
			}
		} else if (result.length() < num2r.length()) {
			for (int i = result.length(); i < num2r.length(); i++) {
				int value = 0;
				if (overflow) {
					value = (int) num2r.toCharArray()[i] - ZERO + 1;
				} else {
					value = (int) num2r.toCharArray()[i] - ZERO;
				}

				if (value >= 10) {
					value -= 10;
					overflow = true;
				} else {
					overflow = false;
				}
				result += value;
			}

			if (overflow) {
				result += "1";
			}
		} else if (overflow) {
			result += "1";
		}

		String tmp = "";
		for (int i = result.length() - 1; i >= 0; i--) {
			tmp += result.toCharArray()[i];
		}
		return tmp;
	}

	public static void main(String[] args) {
		String num1 = "10";
		String num2 = "999";
		System.out.println(add(num1, num2));
	}
	
}
