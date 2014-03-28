package others;

public class BigNumberMult {

	static final char POS_CHAR = '+';
	static final char NEG_CHAR = '-';

	static String multi(String f, String s) {
		// 1. 截取符号位，判断最终结果的符号
		char signA = f.charAt(0);
		char signB = s.charAt(0);
		char sign = '+';
		if (signA == '+' || signA == '-') {
			sign = signA;
			f = f.substring(1);
		}
		if (signB == '+' || signB == '-') {
			if (sign == signB) {
				sign = '+';
			} else {
				sign = '-';
			}
			s = s.substring(1);
		}

		// 2. 将大数翻转并转换成字符数组、计算每一位、计算进位、倒序输出、添加符号位
		char[] a = new StringBuffer(f).reverse().toString().toCharArray();
		char[] b = new StringBuffer(s).reverse().toString().toCharArray();
		int len = a.length + b.length; // 结果的最大长度
		int[] result = new int[len];
		// 计算每一位的结果
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				result[i + j] += (int) (a[i] - '0') * (int) (b[j] - '0');
			}
		}
		// 考虑进位问题（如果是大于10的就向前一位进位，本身进行除10取余）
		for (int i = 0; i < result.length; i++) {
			if (result[i] > 10) {
				result[i + 1] += result[i] / 10;
				result[i] %= 10;
			}
		}
		// 倒序输出最终结果该字段用于标识是否有前置0，如果是0就不需要打印或者存储下来
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		for (int i = len - 1; i >= 0; i--) {
			if (result[i] == 0 && flag) {
				continue;
			} else {
				flag = false;
			}
			sb.append(result[i]);
		}
		if (!sb.toString().equals("")) { // 结果不为0，添加符号位
			if (sign == '-') {
				sb.insert(0, sign);
			}
		} else { // 结果为0的情况
			sb.append(0);
		}
		
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(multi("1000", "0"));
	}
}
