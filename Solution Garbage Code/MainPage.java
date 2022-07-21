package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainPage extends BorderPane {
	Boolean checkHR;
	Button next = new Button("Submit");
	Button clear = new Button("Clear");
	TextField pass = new TextField();
	TextField idT = new TextField();
	Label password = new Label("Password ");
	Label id = new Label("ID");
	Label error = new Label("Password is incorrect");
	Hyperlink newUser = new Hyperlink("New User?");
	Hyperlink newPass = new Hyperlink("Forget Password?");
	VBox labels = new VBox();
	VBox fields = new VBox();
	VBox last = new VBox();
	HBox hbox = new HBox();
	HBox actions = new HBox();
	Glow glow = new Glow();

	public MainPage() {

		// Design and Styles
		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		next.setPrefSize(70, 20);
		clear.setPrefSize(70, 20);
		pass.setPrefSize(175, 10);

		glow.setLevel(0.2);
		next.setEffect(glow);
		clear.setEffect(glow);

		pass.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		idT.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");

		newUser.setTextFill(Color.web("#1b338c"));
		newPass.setTextFill(Color.web("#1b338c"));

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));

		labels.getChildren().addAll(id, password);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(37);

		fields.getChildren().addAll(idT, pass);
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(30);

		hbox.getChildren().addAll(labels, fields);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(30);

		actions.getChildren().addAll(newUser, newPass, clear);
		actions.setAlignment(Pos.CENTER);
		actions.setSpacing(30);

		last.getChildren().addAll(error, hbox, actions, next);
		last.setAlignment(Pos.CENTER);
		last.setSpacing(30);
		newUser.setUnderline(true);
		newPass.setUnderline(true);

		setCenter(last);
		CommonClass.primaryStage.setTitle("Login");

		// Handlers

		// Creating new User or new password
		newUser.setOnMouseClicked(e -> {
			CommonClass.scene = new Scene(new NewUser(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("New User");
			CommonClass.primaryStage.show();
		});
		newPass.setOnMouseClicked(e -> {
			CommonClass.scene = new Scene(new NewPassword(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("New Password");
			CommonClass.primaryStage.show();
		});

		// clear and next buttons
		clear.setOnAction(e -> {
			error.setVisible(false);
			pass.clear();
			idT.requestFocus();
		});

		next.setOnAction(e -> {
			error.setVisible(false);
			if (!idT.getText().isEmpty() && !pass.getText().isEmpty()) {
				if (idT.getText().length() == 10) {
					try {
						Long.parseLong(idT.getText());
					} catch (Exception ex) {
						error.setText("ID should contain 10 numbers only");
						error.setVisible(true);
						return;
					}
					SLLNode<Person> tmp = CommonClass.users.head;
					while (tmp != null) {
						if (tmp.info.id == Long.parseLong(idT.getText())) {
							break;
						} else {
							tmp = tmp.next;
						}
					}
					if (tmp == null) {
						error.setText("No Such User in the System");
						error.setVisible(true);
					} else {
						if (tmp.info instanceof Interviewer) {
							Interviewer inter = (Interviewer) tmp.info;
							if (inter.password.equals(pass.getText())) {
								CommonClass.id = inter.id;
								CommonClass.hrCheck = false;
								CommonClass.scene = new Scene(new InterviewerInterface(), 700, 700);
								CommonClass.primaryStage.setScene(CommonClass.scene);
								CommonClass.primaryStage.setTitle("Interviewer " + inter.name);
								CommonClass.primaryStage.show();
							} else {
								error.setText("Password is incorrect");
								error.setVisible(true);
							}

						} else {
							HR hr = (HR) tmp.info;
							if (hr.password.equals(pass.getText())) {
								CommonClass.hrCheck = true;
								CommonClass.scene = new Scene(new HRInterface(), 700, 700);
								CommonClass.primaryStage.setScene(CommonClass.scene);
								CommonClass.primaryStage.setTitle("HR " + hr.name);
								CommonClass.primaryStage.show();
							} else {
								error.setText("Password is incorrect");
								error.setVisible(true);
							}
						}
					}
				} else {
					error.setText("ID should contain 10 digits");
					error.setVisible(true);
				}
			} else {
				error.setText("Fill all information Fields");
				error.setVisible(true);
			}
		});

		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				error.setVisible(false);
				if (!idT.getText().isEmpty() && !pass.getText().isEmpty()) {
					if (idT.getText().length() == 10) {
						try {
							Long.parseLong(idT.getText());
						} catch (Exception ex) {
							error.setText("ID should contain 10 numbers only");
							error.setVisible(true);
							return;
						}
						SLLNode<Person> tmp = CommonClass.users.head;
						while (tmp != null) {
							if (tmp.info.id == Long.parseLong(idT.getText())) {
								break;
							} else {
								tmp = tmp.next;
							}
						}
						if (tmp == null) {
							error.setText("No Such User in the System");
							error.setVisible(true);
						} else {
							if (tmp.info instanceof Interviewer) {
								Interviewer inter = (Interviewer) tmp.info;
								if (inter.password.equals(pass.getText())) {
									CommonClass.id = inter.id;
									CommonClass.hrCheck = false;
									CommonClass.name = inter.name;
									CommonClass.scene = new Scene(new InterviewerInterface(), 700, 700);
									CommonClass.primaryStage.setScene(CommonClass.scene);
									CommonClass.primaryStage.setTitle("Interviewer " + inter.name);
									CommonClass.primaryStage.show();
								} else {
									error.setText("Password is incorrect");
									error.setVisible(true);
								}

							} else {
								HR hr = (HR) tmp.info;
								if (hr.password.equals(pass.getText())) {
									CommonClass.hrCheck = true;
									CommonClass.name = hr.name;
									CommonClass.scene = new Scene(new HRInterface(), 700, 700);
									CommonClass.primaryStage.setScene(CommonClass.scene);
									CommonClass.primaryStage.setTitle("HR " + hr.name);
									CommonClass.primaryStage.show();
								} else {
									error.setText("Password is incorrect");
									error.setVisible(true);
								}
							}
						}
					} else {
						error.setText("ID should contain 10 digits");
						error.setVisible(true);
					}
				} else {
					error.setText("Fill all information Fields");
					error.setVisible(true);
				}
			}
		});
	}
}
