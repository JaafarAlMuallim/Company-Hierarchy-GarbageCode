package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class CreateBand extends BorderPane {
	Button back = new Button("Back");
	Button next = new Button("Submit");
	Button clear = new Button("Clear");
	Label bandL = new Label("Band Name");
	Label unitL = new Label("Unit Name");
	Label error = new Label();
	TextField band = new TextField("Engineering Band");
	ComboBox<Units> unit = new ComboBox<Units>();
	HBox hbox = new HBox();
	HBox hbox2 = new HBox();
	VBox labels = new VBox();
	VBox fields = new VBox();
	VBox vbox = new VBox();
	Glow glow = new Glow();

	public CreateBand() {

		// Design and Style

		back.setPrefSize(80, 20);
		next.setPrefSize(80, 20);
		clear.setPrefSize(80, 20);
		band.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		unit.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");

		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5;");
		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5;");

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));


		glow.setLevel(0.2);
		back.setEffect(glow);
		next.setEffect(glow);
		clear.setEffect(glow);

		SLLNode<Units> units = CommonClass.unitList.head;
		if (units == null) {
			error.setText("No Units has been created yet");
			error.setVisible(true);
		}
		while (units != null) {
			unit.getItems().add(units.info);
			units = units.next;
		}

		hbox.getChildren().addAll(back, next);
		labels.getChildren().addAll(bandL, unitL);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(27);

		fields.getChildren().addAll(band, unit);
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(20);

		hbox2.getChildren().addAll(labels, fields, clear);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(50);

		hbox.setAlignment(Pos.BOTTOM_CENTER);
		hbox.setSpacing(40);
		hbox.setPadding(new Insets(0, 20, 40, 20));

		vbox.getChildren().addAll(error, hbox2, clear, hbox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(30);

		setCenter(vbox);

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		// Handlers

		clear.setOnAction(e -> {
			band.clear();
			unit.getSelectionModel().select(0);
		});

		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new Hierarchy(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});

		next.setOnAction(e -> {
			error.setVisible(false);
			if (!band.getText().isEmpty()) {
				SLLNode<Units> bandUnit = CommonClass.unitList.head;
				while (bandUnit != null) {
					if (bandUnit.info.equals(unit.getSelectionModel().getSelectedItem())) {
						break;
					}
					bandUnit = bandUnit.next;
				}
				Band newBand = new Band(band.getText(), bandUnit.info);
				SLLNode<Band> band = CommonClass.bandList.head;
				if (band == null) {
					CommonClass.bandList.addToTail(newBand);
					CommonClass.writeBands();
					CommonClass.scene = new Scene(new JobsAndBasicSalary(), 700, 700);
					CommonClass.primaryStage.setScene(CommonClass.scene);
					CommonClass.primaryStage.setTitle("Specify Jobs And Basic Salary");
					CommonClass.primaryStage.show();
					return;
				} else {
					boolean found = false;
					while (band != null) {
						if (newBand.equals(band.info)) {
							found = true;
							break;
						}
						band = band.next;
					}
					if (!found) {
						CommonClass.bandList.addToTail(newBand);
						CommonClass.writeBands();
						CommonClass.bandsNum++;
						CommonClass.scene = new Scene(new JobsAndBasicSalary(), 700, 700);
						CommonClass.primaryStage.setScene(CommonClass.scene);
						CommonClass.primaryStage.setTitle("Specify Jobs And Basic Salary");
						CommonClass.primaryStage.show();
					} else {
						error.setText("Such Band already Exists in the system");
						error.setVisible(true);
					}
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
				if (!band.getText().isEmpty()) {
					SLLNode<Units> bandUnit = CommonClass.unitList.head;
					while (bandUnit != null) {
						if (bandUnit.info.equals(unit.getSelectionModel().getSelectedItem())) {
							break;
						}
						bandUnit = bandUnit.next;
					}
					Band newBand = new Band(band.getText(), bandUnit.info);
					SLLNode<Band> band = CommonClass.bandList.head;
					if (band == null) {
						CommonClass.bandList.addToTail(newBand);
						CommonClass.writeBands();
						CommonClass.scene = new Scene(new JobsAndBasicSalary(), 700, 700);
						CommonClass.primaryStage.setScene(CommonClass.scene);
						CommonClass.primaryStage.setTitle("Specify Jobs And Basic Salary");
						CommonClass.primaryStage.show();
						return;
					} else {
						boolean found = false;
						while (band != null) {
							if (newBand.equals(band.info)) {
								found = true;
								break;
							}
							band = band.next;
						}
						if (!found) {
							CommonClass.bandList.addToTail(newBand);
							CommonClass.writeBands();
							CommonClass.scene = new Scene(new JobsAndBasicSalary(), 700, 700);
							CommonClass.primaryStage.setScene(CommonClass.scene);
							CommonClass.primaryStage.setTitle("Specify Jobs And Basic Salary");
							CommonClass.primaryStage.show();
						} else {
							error.setText("Such Band already Exists in the system");
							error.setVisible(true);
						}
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
