package ch4.ex02;

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

	@Override
	public void start(Stage stage) {
		// 普通のフィールドを使用
		Member member = new Member();
		System.out.println("create member1");
		member.setId(1);
		member.setName("Yamada");
		Label message1 = new Label(member.getId() + " " + member.getName());
		message1.setFont(new Font(50));

		// xxxProperty()を使用
		System.out.println("change property");
		member.idProperty().set(2);
		member.nameProperty().set("Suzuki");
		Label message2 = new Label(member.idProperty().get() + " "
				+ member.nameProperty().get());
		message2.setFont(new Font(50));

		VBox root = new VBox();
		root.getChildren().addAll(message1, message2);
		stage.setScene(new Scene(root));
		stage.setTitle("Member");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
