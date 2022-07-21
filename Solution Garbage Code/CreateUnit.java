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

public class CreateUnit extends BorderPane {
	Button back = new Button("Back");
	Button next = new Button("Submit");
	Button clear = new Button("Clear");
	TextField unit = new TextField("Software Unit");
	Label unitName = new Label("Unit Name");
	RadioButton dir = new RadioButton("Directorate");
	RadioButton div = new RadioButton("Division");
	RadioButton dep = new RadioButton("Departement");
	ToggleGroup group = new ToggleGroup();
	Label unitLevel = new Label("Unit Level ");
	Label error = new Label();
	VBox labels = new VBox();
	VBox actions = new VBox();
	HBox hbox = new HBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	VBox last = new VBox();
	Glow glow = new Glow();

	public CreateUnit() {
		
		// Design and Style
		div.setToggleGroup(group);
		dir.setToggleGroup(group);
		dep.setToggleGroup(group);

		back.setPrefSize(80, 20);
		next.setPrefSize(80, 20);
		clear.setPrefSize(80, 20);
		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5;");
		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5;");
		unit.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");

		glow.setLevel(0.3);
		back.setEffect(glow);
		next.setEffect(glow);
		clear.setEffect(glow);

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));

		hbox.getChildren().addAll(back, next);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(30);

		hbox2.getChildren().addAll(div, dir, dep);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(20);

		actions.getChildren().addAll(unit, hbox2);
		actions.setSpacing(30);
		actions.setAlignment(Pos.CENTER);

		hbox3.getChildren().addAll(labels, actions);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(30);

		labels.getChildren().addAll(unitName, unitLevel);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(30);

		last.getChildren().addAll(error, hbox3, clear, hbox);
		last.setAlignment(Pos.CENTER);
		last.setSpacing(30);
		
		setCenter(last);
		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		// Handlers

		clear.setOnAction(e -> {
			unit.clear();
			if (group.getSelectedToggle() != null)
				group.getSelectedToggle().setSelected(false);
		});

		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new Hierarchy(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});

		next.setOnAction(e -> {
			if (!unit.getText().isEmpty() && group.getSelectedToggle() != null) {
				RadioButton rb = (RadioButton) group.getSelectedToggle();
				Units newUnit = new Units(unit.getText(), rb.getText());
				SLLNode<Units> unit = CommonClass.unitList.head;
				boolean found = false;
				while (unit != null) {
					if (newUnit.exists(unit.info)) {
						found = true;
					}
					unit = unit.next;
				}
				if (found) {
					error.setText("Such Unit already Exists in the system");
					error.setVisible(true);
					return;
				} else {
					CommonClass.unitList.addToTail(newUnit);
					CommonClass.writeUnits();
					CommonClass.scene = new Scene(new CreateUnit(), 700, 700);
					CommonClass.primaryStage.setScene(CommonClass.scene);
					CommonClass.primaryStage.setTitle("Create Unit");
					CommonClass.primaryStage.show();
				}
			} else {
				error.setText("Fill all information fields");
				error.setVisible(true);
				return;
			}
		});
		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				if (!unit.getText().isEmpty() && group.getSelectedToggle() != null) {
					RadioButton rb = (RadioButton) group.getSelectedToggle();
					Units newUnit = new Units(unit.getText(), rb.getText());
					SLLNode<Units> unit = CommonClass.unitList.head;
					boolean found = false;
					while (unit != null) {
						if (newUnit.exists(unit.info)) {
							found = true;
						}
						unit = unit.next;
					}
					if (found) {
						error.setText("Such Unit already Exists in the system");
						error.setVisible(true);
						return;
					} else {
						CommonClass.unitList.addToTail(newUnit);
						CommonClass.writeUnits();
						CommonClass.scene = new Scene(new CreateUnit(), 700, 700);
						CommonClass.primaryStage.setScene(CommonClass.scene);
						CommonClass.primaryStage.setTitle("Create Unit");
						CommonClass.primaryStage.show();
					}
				} else {
					error.setText("Fill all information fields");
					error.setVisible(true);

				}
			}
		});
	}
}
