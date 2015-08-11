package ch4.ex03;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Member extends Application {
	private int id = 0;
	private IntegerProperty idProperty = null;
	private Runnable idRunner = () -> {
		System.out.println("create id property object");
		idProperty = new SimpleIntegerProperty();
	};
	private String name = "";
	private StringProperty nameProperty = null;
	private Runnable nameRunner = () -> {
		System.out.println("create name property object");
		nameProperty = new SimpleStringProperty("");
	};

	private String country = "JPN"; // デフォルト値
	private StringProperty countryProperty = null;
	private Runnable countryRunner = () -> {
		System.out.println("create country property object");
		countryProperty = new SimpleStringProperty("");
	};

	public final IntegerProperty idProperty() {
		if (idProperty == null)
			idRunner.run();
		return idProperty;
	}

	public final void setId(int newValue) {
		if (idProperty != null) {
			idProperty.set(newValue);
		} else {
			id = newValue;
		}
	}

	public final int getId() {
		if (idProperty != null) {
			return idProperty.get();
		} else {
			return id;
		}
	}

	public final StringProperty nameProperty() {
		if (nameProperty == null)
			nameRunner.run();
		return nameProperty;
	}

	public final void setName(String newValue) {
		if (nameProperty != null) {
			nameProperty.set(newValue);
		} else {
			name = newValue;
		}
	}

	public final String getName() {
		if (nameProperty != null) {
			return nameProperty.get();
		} else {
			return name;
		}
	}

	public final StringProperty countryProperty() {
		if (countryProperty == null)
			countryRunner.run();
		return countryProperty;
	}

	public final void setCountry(String newValue) {
		// 変更された時にプロパティを構築する
		this.countryProperty().set(newValue);
	}

	public final String getCountry() {
		if (countryProperty != null) {
			return countryProperty.get();
		} else {
			return country;
		}
	}

	@Override
	public void start(Stage stage) throws Exception {

		Member[] members = new Member[3];
		for (int i = 0; i < members.length; i++)
			members[i] = new Member();

		// デフォルト
		System.out.println("set member1");
		members[0].setId(1);
		members[0].setName("Yamada");

		// デフォルトではない値に設定
		System.out.println("set member2");
		members[1].setId(2);
		members[1].setName("Sato");
		members[1].setCountry("USA");

		// xxxProperty()を使用
		System.out.println("set member3");
		members[2].idProperty();
		members[2].setId(2);
		members[2].nameProperty();
		members[2].setName("Suzuki");
		members[2].countryProperty();
		members[2].setCountry("AUS");

		VBox root = new VBox();
		for (Member member : members) {
			Label message = new Label(member.getId() + " " + member.getName()
					+ " " + member.getCountry());
			message.setFont(new Font(50));
			root.getChildren().add(message);
		}
		stage.setScene(new Scene(root));
		stage.setTitle("Members");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
