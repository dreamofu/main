package basic;

public class LinkedList {

	public LinkedNode head;

	
	public LinkedList() {
	}

	public LinkedList(LinkedNode head) {
		super();
		this.head = head;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		LinkedNode p = head;
		while (p != null) {
			buf.append(p.name + " ");
			p = p.next;
		}
		return buf.toString();
	}

}
