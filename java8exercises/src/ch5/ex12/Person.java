package ch5.ex12;

import java.time.ZoneId;
import java.util.Objects;

public class Person {
	private String firstName;
	private String lastName;
	private ZoneId zone;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setZone(ZoneId zone) {
		Objects.requireNonNull(zone, "`zone` argument must be non-null.");

		this.zone = zone;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public ZoneId getZone() {
		return this.zone;
	}
}
