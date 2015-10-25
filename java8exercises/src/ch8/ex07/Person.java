package ch8.ex07;

// 回答はPersonTest.javaに記載

public class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return name.substring(0, name.indexOf(' '));
	}

	public String getMiddleName() {
		int space1 = name.indexOf(" ");
		int space2 = name.lastIndexOf(" ");
		if (space1 == space2)
			return null;
		else
			return name.substring(space1 + 1, space2);
	}

	public String getLastName() {
		return name.substring(name.lastIndexOf(' ') + 1);
	}

	@Override
	public String toString() {
		return name;
	}
}
