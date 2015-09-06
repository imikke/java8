package ch5.ex12;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * CalendarExクラスは、サーバ上で動作するアプリケーションを想定している。
 * 各クライアントからイベントが送信されて登録される。
 */
public class CalendarEx {
	private ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC")); // UTC
	private long eventId = 0;
	private HashMap<Long, Event> events = new HashMap<>();
	private HashMap<String, List<Event>> notifications = new HashMap<>();

	/**
	 * registerEventメソッドは、イベントを登録する。
	 * イベントの通知設定する場合は、本メソッドを呼ぶ前にEventオブジェクトにセットしておかなければいけない。
	 * 
	 * @param event
	 */
	public void registerEvent(Event event) {
		Objects.requireNonNull(event, "`event` argument must be non-null.");

		eventId += 1;
		events.put(eventId, event);
		if (event.getNotification() == true) {
			List<Event> nEvents = notifications.get(event.notificationTime());
			if (nEvents == null) {
				nEvents = new ArrayList<>();
			}
			nEvents.add(event);
			notifications.put(event.notificationTime(), nEvents);
		}
	}

	/**
	 * setDateTimeメソッドは、テスト用にカレンダーの時刻を進める。
	 * 
	 * @param now
	 */
	public void setDateTime(ZonedDateTime now) {
		Objects.requireNonNull(now, "`now` argument must be non-null.");

		this.now = now.withZoneSameInstant(ZoneId.of("UTC"));
	}

	/**
	 * notifyEventメソッドは、参加者にイベントを通知する。
	 * 具体的な通知手段は未実装。
	 * 
	 * @param event
	 */
	private void notifyEvent(Event event) {
		Objects.requireNonNull(event, "`event` argument must be non-null.");

		// not implement how to notify an event.
		System.out.println("Email To:");
		for (Person p : event.getPeople()) {
			System.out.println(" " + p.getName());
		}
	}

	/**
	 * startメソッドは、カレンダーアプリケーションを起動する。
	 */
	public void start() {
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						now = ZonedDateTime.now(ZoneId.of("UTC"));
						Thread.sleep(1000);
						System.out.println(now);
						List<Event> nEvents = notifications.get(now.toString());
						if (nEvents != null) {
							for (Event e : nEvents) {
								notifyEvent(e);
							}
							notifications.put(now.toString(), null);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		};
		new Thread(runner).start();
	}

	public static void main(String[] args) {
		// カレンダーアプリケーションの起動
		CalendarEx cal = new CalendarEx();
		cal.start();

		// クライアント側の処理
		Person personA = new Person("Taro", "Yamada");
		Person personB = new Person("Hanako", "Suzuki");

		ZoneId newYork = ZoneId.of("America/New_York");
		ZoneId berlin = ZoneId.of("Europe/Berlin");

		personA.setZone(newYork);
		personB.setZone(berlin);

		// ニューヨークの人がカレンダーにイベントを登録する
		ZonedDateTime from = ZonedDateTime.of(
				LocalDateTime.of(2015, 9, 5, 10, 0), newYork);
		ZonedDateTime until = ZonedDateTime.of(
				LocalDateTime.of(2015, 9, 5, 11, 0), newYork);
		Event event = new Event(from, until, "sales meeting", personA, personB);
		event.setNotification(true);
		cal.registerEvent(event);

		// イベントの１時間前が来たと仮定する
		cal.setDateTime(ZonedDateTime.of(LocalDateTime.of(2015, 9, 5, 9, 0),
				newYork));
		/*
		 * Result:
		 * 2015-09-05T13:00Z[UTC]
		 * Email To:
		 * Taro Yamada
		 * Hanako Suzuki
		 * 2015-09-06T08:03:19.775Z[UTC]
		 * 2015-09-06T08:03:20.777Z[UTC]
		 * 2015-09-06T08:03:21.783Z[UTC]
		 * 2015-09-06T08:03:22.787Z[UTC]
		 */
	}
}
