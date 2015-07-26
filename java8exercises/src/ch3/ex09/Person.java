package ch3.ex09;

public class Person {

	private String firstName;
	private String lastName;

	public Person(String sFirstName, String sLastName) {
		firstName = sFirstName;
		lastName = sLastName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}
}
