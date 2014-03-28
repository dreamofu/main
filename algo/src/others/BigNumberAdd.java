package others;

public class BigNumberAdd {

	static String add(String f, String s) {
		// 1. 符号位

		// 2. 翻转两个字符串，并转换成数组，对应位相加，处理进位，倒序输出
		char[] a = new StringBuffer(f).reverse().toString().toCharArray();
		char[] b = new StringBuffer(s).reverse().toString().toCharArray();
		// 计算两个长字符串中的较长字符串的长度
		int len = Math.max(a.length, b.length);
		int[] result = new int[len + 1];
		for (int i = 0; i < len + 1; i++) { // 对应位相加
			// 如果当前的i超过了其中的一个，就用0代替，和另一个字符数组中的数字相加
			int aint = i < a.length ? (a[i] - '0') : 0;
			int bint = i < b.length ? (b[i] - '0') : 0;
			result[i] = aint + bint;
		}
		// 处理进位（如果大于10的就向前一位进位，本身进行除10取余）
		for (int i = 0; i < result.length; i++) {
			if (result[i] > 10) {
				result[i + 1] += result[i] / 10;
				result[i] %= 10;
			}
		}
		// 倒序输出（flag用于标识是否有前置0，如果有就不要存储)
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		for (int i = len; i >= 0; i--) {
			if (result[i] == 0 && flag) {
				continue;
			} else {
				flag = false;
			}
			sb.append(result[i]);
		}
		return sb.toString();

	}

}
