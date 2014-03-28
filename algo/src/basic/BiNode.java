package basic;

public class BiNode {
	public String name;
	public BiNode left;
	public BiNode right;

	public BiNode(String value) {
		this.name = value;
	}

	@Override
	public String toString() {
		return name;
	}
}
