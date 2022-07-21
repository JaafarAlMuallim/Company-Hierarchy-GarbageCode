package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
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

public class AddCandidate extends BorderPane {
	Button next = new Button("Submit");
	Button back = new Button("Back");
	Button clear = new Button("Clear");
	TextField nameT = new TextField("");
	TextField idT = new TextField();
	TextArea cvT = new TextArea();
	TextField expT = new TextField();
	ComboBox<String> eduT = new ComboBox<String>();
	ComboBox<String> posT = new ComboBox<String>();
	RadioButton male = new RadioButton("Male");
	RadioButton female = new RadioButton("Female");
	ToggleGroup group = new ToggleGroup();
	Label name = new Label("Name");
	Label gender = new Label("Gender");
	Label id = new Label("ID");
	Label cv = new Label("CV");
	Label exp = new Label("Years Of Expereince");
	Label edu = new Label("Education Level");
	Label pos = new Label("Position");
	Label error = new Label();
	VBox vbox1 = new VBox();
	VBox labels = new VBox();
	VBox fields = new VBox();
	HBox hbox1 = new HBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	Glow glow = new Glow();

	public AddCandidate() {

		// Design and Style

		male.setToggleGroup(group);
		female.setToggleGroup(group);

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		next.setPrefSize(80, 20);
		clear.setPrefSize(80, 20);
		back.setPrefSize(80, 20);

		glow.setLevel(0.2);
		next.setEffect(glow);
		back.setEffect(glow);
		clear.setEffect(glow);

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));


		nameT.setPrefSize(120, 20);
		idT.setPrefSize(120, 20);
		cvT.setPrefSize(200, 20);
		expT.setPrefSize(120, 20);

		SLLNode<Band> band = CommonClass.bandList.head;
		while (band != null) {
			Job[] job = band.info.jobs;
			for (int i = 0; i < job.length; i++) {
				posT.getItems().add(job[i].name + " -- " + band.info.name);
			}
			band = band.next;
		}
		if (!posT.getItems().isEmpty()) {
			posT.getSelectionModel().select(0);
		}

		ArrayList<String> levels = new ArrayList<String>();
		levels.add("High School");
		levels.add("Diploma");
		levels.add("Bachelor Degree");
		levels.add("Master Degree");
		levels.add("Phd");

		eduT.getItems().addAll(levels);
		eduT.getSelectionModel().selectFirst();
		nameT.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		idT.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		cvT.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		expT.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");

		hbox1.setSpacing(40);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(back, next);

		labels.getChildren().addAll(name, gender, id, cv, exp, edu, pos);
		labels.setSpacing(30);
		labels.setAlignment(Pos.CENTER);

		hbox3.getChildren().addAll(male, female);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(30);

		fields.getChildren().addAll(nameT, hbox3, idT, cvT, expT, eduT, posT);
		fields.setSpacing(22);
		fields.setAlignment(Pos.CENTER);

		hbox2.getChildren().addAll(labels, fields);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(50);

		vbox1.setSpacing(40);
		vbox1.setPadding(new Insets(20, 140, 20, 140));
		vbox1.setAlignment(Pos.CENTER);
		vbox1.getChildren().addAll(error, hbox2, clear, hbox1);
		setCenter(vbox1);

		// Handlers

		clear.setOnAction(e -> {
			error.setVisible(false);
			nameT.clear();
			idT.clear();
			cvT.clear();
			expT.clear();
			posT.getSelectionModel().select(0);
			if (group.getSelectedToggle() == null)
				return;
			else
				group.getSelectedToggle().setSelected(false);
		});

		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new Applications(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});

		next.setOnAction(e -> {
			if (!nameT.getText().isEmpty() && !idT.getText().isEmpty() && !expT.getText().isEmpty()
					&& eduT.getSelectionModel() != null && !cvT.getText().isEmpty() && group.getSelectedToggle() != null
					&& posT.getSelectionModel() != null) {
				if (idT.getText().length() == 10) {
					try {
						if (Long.parseLong(idT.getText()) < 0) {
							error.setText("ID should not be signed");
							error.setVisible(true);
						}
					} catch (Exception ex) {
						error.setText("ID should contains 10 Numbers only");
						error.setVisible(true);
						return;
					}
					try {
						if (Integer.parseInt(expT.getText()) < 0) {
							error.setText("Years of Expereince should not be signed");
							error.setVisible(true);
						}
					} catch (Exception ex) {
						error.setText("Years of Experience should be numerical Integer value");
						error.setVisible(true);
						return;
					}
					String[] arr = posT.getSelectionModel().getSelectedItem().split(" -- ");
					RadioButton rb = (RadioButton) group.getSelectedToggle();
					SLLNode<Band> currentBand = CommonClass.bandList.head;
					while (currentBand != null) {

						if (currentBand.info.name.equals(arr[1]))
							break;
						currentBand = currentBand.next;
					}
					Job[] job = currentBand.info.jobs;
					for (Job currentJob : job) {
						if (currentJob.name.equals(arr[0])) {
							Candidate newCandidate = new Candidate(nameT.getText(), rb.getText(),
									Long.parseLong(idT.getText()), currentJob, currentBand.info,
									Integer.parseInt(expT.getText()), eduT.getSelectionModel().getSelectedItem(),
									cvT.getText());
							SLLNode<Person> person = CommonClass.users.head;
							boolean found = false;
							while (person != null) {
								if (newCandidate.equals(person.info)) {
									found = true;
									break;
								}
								person = person.next;
							}
							if (!found) {
								CommonClass.candidateList.addToTail(newCandidate);
								CommonClass.writeCandidates();
								CommonClass.scene = new Scene(new AddCandidate(), 700, 700);
								CommonClass.primaryStage.setScene(CommonClass.scene);
								CommonClass.primaryStage.setTitle("Add Candidate");
								CommonClass.primaryStage.show();
								return;
							} else {
								error.setText("Such User exists with the same ID");
								error.setVisible(true);
							}
						}

					}
				} else {
					error.setText("ID should contains 10 digits");
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
				if (!nameT.getText().isEmpty() && !idT.getText().isEmpty() && !expT.getText().isEmpty()
						&& eduT.getSelectionModel() != null && !cvT.getText().isEmpty()
						&& group.getSelectedToggle() != null && posT.getSelectionModel() != null) {
					if (idT.getText().length() == 10) {
						try {
							if (Long.parseLong(idT.getText()) < 0) {
								error.setText("ID should not be signed");
								error.setVisible(true);
							}
						} catch (Exception ex) {
							error.setText("ID should contains 10 Numbers only");
							error.setVisible(true);
							return;
						}
						try {
							if (Integer.parseInt(expT.getText()) < 0) {
								error.setText("Years of Expereince should not be signed");
								error.setVisible(true);
							}
						} catch (Exception ex) {
							error.setText("Years of Experience should be numerical Integer value");
							error.setVisible(true);
							return;
						}
						String[] arr = posT.getSelectionModel().getSelectedItem().split(" -- ");
						RadioButton rb = (RadioButton) group.getSelectedToggle();
						SLLNode<Band> currentBand = CommonClass.bandList.head;
						while (currentBand != null) {

							if (currentBand.info.name.equals(arr[1]))
								break;
							currentBand = currentBand.next;
						}
						Job[] job = currentBand.info.jobs;
						for (Job currentJob : job) {
							if (currentJob.name.equals(arr[0])) {
								Candidate newCandidate = new Candidate(nameT.getText(), rb.getText(),
										Long.parseLong(idT.getText()), currentJob, currentBand.info,
										Integer.parseInt(expT.getText()), eduT.getSelectionModel().getSelectedItem(),
										cvT.getText());
								SLLNode<Person> person = CommonClass.users.head;
								boolean found = false;
								while (person != null) {
									if (newCandidate.equals(person.info)) {
										found = true;
										break;
									}
									person = person.next;
								}
								if (!found) {
									CommonClass.candidateList.addToTail(newCandidate);
									CommonClass.writeCandidates();
									CommonClass.scene = new Scene(new AddCandidate(), 700, 700);
									CommonClass.primaryStage.setScene(CommonClass.scene);
									CommonClass.primaryStage.setTitle("Add Candidate");
									CommonClass.primaryStage.show();
									return;
								} else {
									error.setText("Such User exists with the same ID");
									error.setVisible(true);
								}
							}

						}
					} else {
						error.setText("ID should contains 10 digits");
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
