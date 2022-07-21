package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NewPassword extends BorderPane {
	Button next = new Button("Submit");
	Button back = new Button("Back");
	Button clear = new Button("Clear");
	TextField id = new TextField();
	TextField pass = new TextField();
	TextField confirm = new TextField();
	Label error = new Label("Password is incorrect");
	Label idL = new Label("ID");
	Label password = new Label("New Password ");
	Label confirmation = new Label("Confirm Password");
	VBox labels = new VBox();
	VBox fields = new VBox();
	HBox actions = new HBox();
	HBox hbox = new HBox();
	VBox last = new VBox();
	Glow glow = new Glow();

	public NewPassword() {

		// Design and Style
		next.setPrefSize(70, 20);
		clear.setPrefSize(70, 20);
		back.setPrefSize(70, 20);
		pass.setPrefSize(175, 10);
		id.setPrefSize(175, 10);
		confirm.setPrefSize(175, 10);

		glow.setLevel(0.2);
		next.setEffect(glow);
		clear.setEffect(glow);
		back.setEffect(glow);

		id.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		pass.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		confirm.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		labels.getChildren().addAll(idL, password, confirmation);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(37);

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));

		
		fields.getChildren().addAll(id, pass, confirm);
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(30);

		actions.getChildren().addAll(labels, fields);
		actions.setAlignment(Pos.CENTER);
		actions.setSpacing(30);

		hbox.getChildren().addAll(back, next);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(30);

		last.getChildren().addAll(error, actions, clear, hbox);
		last.setAlignment(Pos.CENTER);
		last.setSpacing(30);

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		setCenter(last);

		// Handlers
		clear.setOnAction(e -> {
			error.setVisible(false);
			id.clear();
			pass.clear();
			confirm.clear();
		});

		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new MainPage(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Login");
			CommonClass.primaryStage.show();
		});

		next.setOnAction(e -> {
			error.setVisible(false);
			if (!id.getText().isEmpty() && !pass.getText().isEmpty() && !confirm.getText().isEmpty()) {
				if (id.getText().length() == 10) {
					try {
						Integer.parseInt(id.getText());
					} catch (Exception ex) {
						error.setText("ID should contain 10 numbers only");
						error.setVisible(true);
						return;
					}
					SLLNode<Person> tmp = CommonClass.users.head;

					while (tmp != null) {
						if (tmp.info.id == Integer.parseInt(id.getText())) {
							break;
						}
						tmp = tmp.next;
					}
					if (tmp.info instanceof Interviewer) {
						Interviewer inter = (Interviewer) tmp.info;
						if (pass.getText().equals(confirm.getText())) {
							inter.password = pass.getText();
							CommonClass.writeUsers();
							CommonClass.scene = new Scene(new MainPage(), 700, 700);
							CommonClass.primaryStage.setScene(CommonClass.scene);
							CommonClass.primaryStage.show();
						}

					} else if (tmp.info instanceof HR) {
						HR hr = (HR) tmp.info;
						if (pass.getText().equals(confirm.getText())) {
							hr.password = pass.getText();
							CommonClass.writeUsers();
							CommonClass.scene = new Scene(new MainPage(), 700, 700);
							CommonClass.primaryStage.setScene(CommonClass.scene);
							CommonClass.primaryStage.show();
						} else {
							error.setText("Password Confirmation does not match new password");
							error.setVisible(true);
							return;
						}
					} else {
						error.setText("User does not exist");
						error.setVisible(true);
						return;
					}

				} else {
					error.setText("ID should have 10 digits");
					error.setVisible(true);
					return;
				}
			} else {
				error.setText("Fill all information fields");
				error.setVisible(true);
				return;
			}
		});

		setOnKeyPressed(e -> {
			error.setVisible(false);
			if (!id.getText().isEmpty() && !pass.getText().isEmpty() && !confirm.getText().isEmpty()) {
				if (id.getText().length() == 10) {
					try {
						Integer.parseInt(id.getText());
					} catch (Exception ex) {
						error.setText("ID should contain 10 numbers only");
						error.setVisible(true);
						return;
					}
					SLLNode<Person> tmp = CommonClass.users.head;

					while (tmp != null) {
						if (tmp.info.id == Integer.parseInt(id.getText())) {
							break;
						}
						tmp = tmp.next;
					}
					if (tmp.info instanceof Interviewer) {
						Interviewer inter = (Interviewer) tmp.info;
						if (pass.getText().equals(confirm.getText())) {
							inter.password = pass.getText();
							CommonClass.writeUsers();
							CommonClass.scene = new Scene(new MainPage(), 700, 700);
							CommonClass.primaryStage.setScene(CommonClass.scene);
							CommonClass.primaryStage.show();
						}

					} else if (tmp.info instanceof HR) {
						HR hr = (HR) tmp.info;
						if (pass.getText().equals(confirm.getText())) {
							hr.password = pass.getText();
							CommonClass.writeUsers();
							CommonClass.scene = new Scene(new MainPage(), 700, 700);
							CommonClass.primaryStage.setScene(CommonClass.scene);
							CommonClass.primaryStage.show();
						} else {
							error.setText("Password Confirmation does not match new password");
							error.setVisible(true);
							return;
						}
					} else {
						error.setText("User does not exist");
						error.setVisible(true);
						return;
					}

				} else {
					error.setText("ID should have 10 digits");
					error.setVisible(true);
					return;
				}
			} else {
				error.setText("Fill all information fields");
				error.setVisible(true);
				return;
			}
		});
	}
}
