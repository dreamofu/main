package algo;

public class Test {

	public static void main(String[] args) {
		for(int i=10;i<99;i++){
			int a = i / 10;
			int b = i - a*10;
			int res = i - a - b;
			System.out.println(String.format("%d\t%d", i, res));
		}
	}
}
