package by.htp.xml.model;

public class Mother extends Parent {

	private String maidenName;
	private int children;

	public Mother() {
		super();
	}

	public Mother(String name, String surname, int age) {
		super(name, surname, age);
	}

	public Mother(String maidenName, int children) {
		super();
		this.maidenName = maidenName;
		this.children = children;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	@Override
	public String toString() {
		return super.toString() + " maidenName= " + maidenName + " children= " + children;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

}
