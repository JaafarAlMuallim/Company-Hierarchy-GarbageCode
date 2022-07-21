package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.HBox;

public class JobsAndBasicSalary extends BorderPane {
	int added = 0, removed = 0;
	int base = 5;
	Button next = new Button("Submit");
	Button back = new Button("Back");
	Button clear = new Button("Clear");
	Button add = new Button("Add Job");
	Button remove = new Button("Remove Job");
	Label basicSalary = new Label("Basic Salary");
	Label error = new Label("Band should contain at least one job");
	Label jobName1 = new Label("Job 1");
	Label jobName2 = new Label("Job 2");
	Label jobName3 = new Label("Job 3");
	Label jobName4 = new Label("Job 4");
	Label jobName5 = new Label("Job 5");

	TextField salary1 = new TextField("14000");
	TextField salary2 = new TextField("12000");
	TextField salary3 = new TextField("14000");
	TextField salary4 = new TextField("10000");
	TextField salary5 = new TextField("8000");

	List<TextField> salary = new ArrayList<TextField>();

	TextField job1 = new TextField("Program Manager");
	TextField job2 = new TextField("Product Manager");
	TextField job3 = new TextField("Senior Engineer");
	TextField job4 = new TextField("Lead Engineer");
	TextField job5 = new TextField("Engineer");

	List<TextField> textFields = new ArrayList<TextField>();

	VBox vbox = new VBox();
	VBox labels = new VBox();
	VBox salarys = new VBox();
	VBox last = new VBox();
	HBox hbox1 = new HBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	Glow glow = new Glow();

	public JobsAndBasicSalary() {
		// Design and Style
		textFields.add(job1);
		textFields.add(job2);
		textFields.add(job3);
		textFields.add(job4);
		textFields.add(job5);
		salary.add(salary1);
		salary.add(salary2);
		salary.add(salary3);
		salary.add(salary4);
		salary.add(salary5);

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		add.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		remove.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		next.setPrefSize(90, 20);
		clear.setPrefSize(90, 20);
		back.setPrefSize(90, 20);
		add.setPrefSize(90, 20);
		remove.setPrefSize(90, 20);

		glow.setLevel(0.3);
		next.setEffect(glow);
		back.setEffect(glow);
		clear.setEffect(glow);
		add.setEffect(glow);
		remove.setEffect(glow);

		hbox2.getChildren().addAll(remove, add, clear);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(40);

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));

		job1.setPrefSize(160, 20);
		job2.setPrefSize(160, 20);
		job3.setPrefSize(160, 20);
		job4.setPrefSize(160, 20);
		job5.setPrefSize(160, 20);

		job1.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		job2.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		job3.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		job4.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		job5.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		salary1.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		salary2.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		salary3.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		salary4.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
		salary5.setStyle(
				"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");

		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(back, next);
		hbox1.setSpacing(40);
		hbox1.setPadding(new Insets(20, 20, 20, 20));

		vbox.getChildren().addAll(job1, job2, job3, job4, job5);
		vbox.setPadding(new Insets(20, 20, 20, 20));
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);

		labels.getChildren().addAll(jobName1, jobName2, jobName3, jobName4, jobName5);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(27);

		salarys.getChildren().addAll(salary1, salary2, salary3, salary4, salary5);
		salarys.setAlignment(Pos.CENTER);
		salarys.setSpacing(20);

		hbox3.setSpacing(20);
		hbox3.getChildren().addAll(labels, vbox, salarys);
		hbox3.setAlignment(Pos.CENTER);

		last.getChildren().addAll(error, hbox3, hbox2, hbox1);
		last.setAlignment(Pos.CENTER);
		last.setSpacing(25);
		last.setPadding(new Insets(20, 20, 20, 20));

		setCenter(last);

		// Handlers

		clear.setOnAction(e -> {
			error.setVisible(false);
			for (int i = 0; i < textFields.size(); i++) {
				if (!textFields.get(i).getText().isEmpty() && !salary.get(i).getText().isEmpty()) {
					textFields.get(i).clear();
					salary.get(i).clear();
				}
			}
		});

		back.setOnAction(e -> {
			CommonClass.bandList.deleteFromTail();
			CommonClass.writeBands();
			CommonClass.bandsNum--;
			CommonClass.scene = new Scene(new CreateBand(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Login");
			CommonClass.primaryStage.show();
		});

		add.setOnAction(e -> {
			error.setVisible(false);
			added++;
			TextField jobName = new TextField();
			TextField xSalary = new TextField();
			Label newJob = new Label("Job " + (base + added));
			xSalary.setStyle(
					"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
			jobName.setStyle(
					"-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
			salarys.getChildren().add(xSalary);
			labels.getChildren().add(newJob);
			vbox.getChildren().add(jobName);
			salary.add(xSalary);
			textFields.add(jobName);
		});

		remove.setOnAction(e -> {
			removed = base + added;
			if (removed == 1) {
				error.setVisible(true);
			} else {
				salarys.getChildren().remove(removed - 1);
				labels.getChildren().remove(removed - 1);
				vbox.getChildren().remove(removed - 1);
				salary.remove(removed - 1);
				textFields.remove(removed - 1);
				added--;
			}
		});

		next.setOnAction(e -> {
			Job[] currentJobs = new Job[textFields.size()];
			for (int i = 0; i < textFields.size(); i++) {
				if (!textFields.get(i).getText().isEmpty() && !salary.get(i).getText().isEmpty()) {
					try {
						Integer.parseInt(salary.get(i).getText());
						Job x = new Job(textFields.get(i).getText(), Integer.parseInt(salary.get(i).getText()));
						currentJobs[i] = x;

					} catch (Exception ex) {
						error.setText("Salary Fields should contain Integer values");
						error.setVisible(true);
						return;
					}

				} else {
					error.setText("Fill all information fields");
					error.setVisible(true);
					return;
				}
			}
			CommonClass.bandList.tail.info.assign(currentJobs);
			CommonClass.writeBands();
			CommonClass.scene = new Scene(new CreateBand(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Create Band");
		});

		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				Job[] currentJobs = new Job[textFields.size()];
				for (int i = 0; i < textFields.size(); i++) {
					if (!textFields.get(i).getText().isEmpty() && !salary.get(i).getText().isEmpty()) {
						try {
							Integer.parseInt(salary.get(i).getText());
							Job x = new Job(textFields.get(i).getText(), Integer.parseInt(salary.get(i).getText()));
							currentJobs[i] = x;

						} catch (Exception ex) {
							error.setText("Salary Fields should contain Integer values");
							error.setVisible(true);
							return;
						}

					} else {
						error.setText("Fill all information fields");
						error.setVisible(true);
						return;
					}
				}
				CommonClass.bandList.tail.info.assign(currentJobs);
				CommonClass.writeBands();
				CommonClass.scene = new Scene(new CreateBand(), 700, 700);
				CommonClass.primaryStage.setScene(CommonClass.scene);
				CommonClass.primaryStage.setTitle("Create Band");
			}
		});
	}
}
