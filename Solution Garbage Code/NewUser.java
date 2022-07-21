package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

public class NewUser extends BorderPane {
	Button next = new Button("Submit");
	Button back = new Button("Back");
	Button clear = new Button("Clear");
	TextField nameT = new TextField();
	TextField idT = new TextField();
	TextField pass = new TextField();
	TextField confirm = new TextField();
	Label error = new Label("Password is incorrect");
	Label name = new Label("Name");
	Label password = new Label("Password ");
	Label gender = new Label("Gender");
	Label id = new Label("ID");
	Label confirmation = new Label("Confirm Password");
	RadioButton male = new RadioButton("Male");
	RadioButton female = new RadioButton("Female");
	RadioButton interviewer = new RadioButton("Interviewer");
	RadioButton hr = new RadioButton("HR Presenter");
	ToggleGroup group = new ToggleGroup();
	ToggleGroup group2 = new ToggleGroup();
	Label type = new Label("User Type");
	VBox labels = new VBox();
	VBox fields = new VBox();
	HBox actions = new HBox();
	HBox hbox = new HBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	VBox last = new VBox();
	Glow glow = new Glow();

	public NewUser() {

		// Design and Style

		hr.setToggleGroup(group);
		interviewer.setToggleGroup(group);

		hbox2.getChildren().addAll(hr, interviewer);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(30);

		male.setToggleGroup(group2);
		female.setToggleGroup(group2);

		hbox3.getChildren().addAll(male, female);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(30);

		next.setPrefSize(70, 20);
		clear.setPrefSize(70, 20);
		back.setPrefSize(70, 20);
		pass.setPrefSize(175, 10);
		confirm.setPrefSize(175, 10);

		glow.setLevel(0.2);
		next.setEffect(glow);
		back.setEffect(glow);
		clear.setEffect(glow);

		nameT.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		pass.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		idT.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		confirm.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		labels.getChildren().addAll(name, id, type, gender, password, confirmation);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(37);

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));


		fields.getChildren().addAll(nameT, idT, hbox2, hbox3, pass, confirm);
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
		last.setPadding(new Insets(20, 40, 40, 20));

		setCenter(last);

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		// Handlers

		clear.setOnAction(e -> {
			error.setVisible(false);
			idT.clear();
			group.getSelectedToggle().setSelected(false);
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
			if (!nameT.getText().isEmpty() && !idT.getText().isEmpty() && group.getSelectedToggle() != null
					&& group2.getSelectedToggle() != null && !pass.getText().isEmpty()) {
				if (idT.getText().length() == 10) {
					try {
						Long.parseLong(idT.getText());
					} catch (Exception ex) {
						error.setText("ID should contain 10 Numbers only");
						error.setVisible(true);
						return;
					}
					if (pass.getText().equals(confirm.getText())) {
						RadioButton rb = (RadioButton) group.getSelectedToggle();
						RadioButton rb2 = (RadioButton) group2.getSelectedToggle();
						if (rb.getText().equals("HR Presenter")) {
							HR newHR = new HR(nameT.getText(), rb2.getText(), Long.parseLong(idT.getText()),
									pass.getText());
							SLLNode<Person> person = CommonClass.users.head;
							boolean found = false;
							while (person != null) {
								if (newHR.equals(person.info)) {
									found = true;
									error.setText("User already Exists");
									error.setVisible(true);
									return;
								}
								person = person.next;
							}
							if (!found) {
								CommonClass.users.addToTail(newHR);
								CommonClass.writeUsers();
								CommonClass.scene = new Scene(new MainPage(), 700, 700);
								CommonClass.primaryStage.setTitle("Login");
								CommonClass.primaryStage.setScene(CommonClass.scene);
							}
						} else {
							Interviewer newInter = new Interviewer(nameT.getText(), rb2.getText(),
									Long.parseLong(idT.getText()), pass.getText(), true);
							SLLNode<Person> person = CommonClass.users.head;
							boolean found = false;
							while (person != null) {
								if (person.info.equals(newInter)) {
									found = true;
									error.setText("User already Exists");
									error.setVisible(true);
									return;
								}
								person = person.next;
							}
							if (!found) {
								CommonClass.users.addToTail(newInter);
								CommonClass.writeUsers();
								CommonClass.scene = new Scene(new MainPage(), 760, 760);
								CommonClass.primaryStage.setTitle("Login");
								CommonClass.primaryStage.setScene(CommonClass.scene);

							}
						}
					} else {
						error.setText("Password confirmation does not match the password");
						error.setVisible(true);
						return;
					}
				} else {
					error.setText("ID should contain 10 digits");
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
			if (e.getCode() == KeyCode.ENTER) {
				error.setVisible(false);
				if (!nameT.getText().isEmpty() && !idT.getText().isEmpty() && group.getSelectedToggle() != null
						&& group2.getSelectedToggle() != null && !pass.getText().isEmpty()) {
					if (idT.getText().length() == 10) {
						try {
							Long.parseLong(idT.getText());
						} catch (Exception ex) {
							error.setText("ID should contain 10 Numbers only");
							error.setVisible(true);
							return;
						}
						if (pass.getText().equals(confirm.getText())) {
							RadioButton rb = (RadioButton) group.getSelectedToggle();
							RadioButton rb2 = (RadioButton) group2.getSelectedToggle();
							if (rb.getText().equals("HR Presenter")) {
								HR newHR = new HR(nameT.getText(), rb2.getText(), Long.parseLong(idT.getText()),
										pass.getText());
								SLLNode<Person> person = CommonClass.users.head;
								boolean found = false;
								while (person != null) {
									if (newHR.equals(person.info)) {
										found = true;
										error.setText("User already Exists");
										error.setVisible(true);
										return;
									}
									person = person.next;
								}
								if (!found) {
									CommonClass.users.addToTail(newHR);
									CommonClass.writeUsers();
									CommonClass.scene = new Scene(new MainPage(), 700, 700);
									CommonClass.primaryStage.setTitle("Login");
									CommonClass.primaryStage.setScene(CommonClass.scene);
								}
							} else {
								Interviewer newInter = new Interviewer(nameT.getText(), rb2.getText(),
										Long.parseLong(idT.getText()), pass.getText(), true);
								SLLNode<Person> person = CommonClass.users.head;
								boolean found = false;
								while (person != null) {
									if (person.info.equals(newInter)) {
										found = true;
										error.setText("User already Exists");
										error.setVisible(true);
										return;
									}
									person = person.next;
								}
								if (!found) {
									CommonClass.users.addToTail(newInter);
									CommonClass.writeUsers();
									CommonClass.scene = new Scene(new MainPage(), 760, 760);
									CommonClass.primaryStage.setTitle("Login");
									CommonClass.primaryStage.setScene(CommonClass.scene);

								}
							}
						} else {
							error.setText("Password confirmation does not match the password");
							error.setVisible(true);
							return;
						}
					} else {
						error.setText("ID should contain 10 digits");
						error.setVisible(true);
						return;
					}
				} else {
					error.setText("Fill all information fields");
					error.setVisible(true);
					return;
				}
			}
		});

	}

}
