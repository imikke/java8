package ch5.ex12;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Event {

	private ZonedDateTime from; // UTC
	private ZonedDateTime until; // UTC
	private String name;
	private Person[] people;
	private Boolean notification = false;

	/**
	 * コンストラクタ
	 * 
	 * @param from
	 * @param until
	 * @param eventName
	 * @param people
	 * @exception NullPointerException
	 *                引数from、untilのいずれかがnullの場合
	 * @exception IllegalArgumentException
	 *                引数peopleが0以下の場合
	 */
	public Event(ZonedDateTime from, ZonedDateTime until, String eventName,
			Person... people) {
		Objects.requireNonNull(from, "`from` argument must be non-null.");
		Objects.requireNonNull(until, "`from` argument must be non-null.");
		if (people.length < 1) {
			throw new IllegalArgumentException(
					"`people` argument is biggger than 1.");
		}

		this.from = from.withZoneSameInstant(ZoneId.of("UTC"));
		this.until = until.withZoneSameInstant(ZoneId.of("UTC"));
		this.setName(eventName);
		this.people = people;
	}

	public void setNotification(Boolean value) {
		this.notification = value;
	}

	public boolean getNotification() {
		return this.notification;
	}

	public String notificationTime() {
		return this.from.minusHours(1).toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person[] getPeople() {
		return this.people;
	}

}
