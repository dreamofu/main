package others;

import java.math.BigInteger;

public class BigNumberAdd_BigInteger {

	public static void main(String[] args) {
		BigInteger bi = new BigInteger("111111111111111111111111111");
		BigInteger bi2 = new BigInteger("-111111111111112");
		BigInteger biSum = bi.add(bi2);
		BigInteger biMulti = bi.multiply(bi2);
		System.out.println(biSum);
		System.out.println(biMulti);
	}
}
