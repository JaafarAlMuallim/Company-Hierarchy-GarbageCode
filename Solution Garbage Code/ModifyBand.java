package application;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ModifyBand extends BorderPane {
	CreateBand reCreate = new CreateBand();
	JobsAndBasicSalary modify = new JobsAndBasicSalary();
	ListView<Band> list = new ListView<Band>();
	VBox vbox = new VBox();
	HBox hbox = new HBox();
	List<TextField> salary = new ArrayList<TextField>();
	List<TextField> textFields = new ArrayList<TextField>();

	public ModifyBand() {

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		// clear items from other classes
		reCreate.vbox.getChildren().removeAll(reCreate.hbox, reCreate.clear);
		modify.salarys.getChildren().removeAll(modify.salary);
		modify.labels.getChildren().removeAll(modify.jobName1, modify.jobName2, modify.jobName3, modify.jobName4,
				modify.jobName5);
		modify.vbox.getChildren().removeAll(modify.textFields);
		modify.textFields.clear();
		modify.salary.clear();

		list.setPrefHeight(list.getItems().size());
		list.setMaxSize(180, 200);
		list.setStyle("-fx-background-color: lightblue; -fx-text-fill: black;");
		
		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);

		vbox.getChildren().addAll(reCreate, modify);

		hbox.getChildren().addAll(list, vbox);

		SLLNode<Band> band = CommonClass.bandList.head;

		// add all if any band in the list

		while (band != null) {
			list.getItems().add(band.info);
			band = band.next;
		}
		if (!list.getItems().isEmpty()) {
				list.getSelectionModel().selectFirst();
				reCreate.band.setText(list.getSelectionModel().getSelectedItem().toString());
				band = CommonClass.bandList.head;
				reCreate.unit.getSelectionModel().select(list.getSelectionModel().getSelectedItem().unit);;
				modify.base = band.info.jobs.length;
				for (int i = 1; i <= band.info.jobs.length; i++) {
					Label job = new Label("Job " + i);
					TextField newJob = new TextField(band.info.jobs[i - 1].name);
					TextField newSalary = new TextField("" + band.info.jobs[i - 1].basicSalary);
					modify.labels.getChildren().add(job);
					modify.salarys.getChildren().add(newSalary);
					modify.vbox.getChildren().add(newJob);
					modify.salary.add(newSalary);
					modify.textFields.add(newJob);
				}
			} else {
				reCreate.error.setText("No Bands created yet");
				reCreate.error.setVisible(true);
				modify.setVisible(false);
				reCreate.labels.getChildren().clear();
				reCreate.fields.getChildren().clear();
				modify.labels.getChildren().clear();
				modify.textFields.clear();
			}



		hbox.setSpacing(10);

		setCenter(hbox);

		// Handler

		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Band>() {
			@Override
			public void changed(ObservableValue<? extends Band> observable, Band oldValue, Band newValue) {
				modify.salarys.getChildren().clear();
				modify.labels.getChildren().clear();
				modify.vbox.getChildren().removeAll(modify.textFields);
				modify.added = 0;
				modify.removed = 0;
				modify.base = 0;

				Band thisBand = list.getSelectionModel().getSelectedItem();
				modify.textFields.clear();
				modify.salary.clear();
				reCreate.band.setText(thisBand.name);
				modify.labels.getChildren().clear();
				modify.vbox.getChildren().clear();
				for (int i = 1; i <= thisBand.jobs.length; i++) {
					Label job = new Label("Job " + i);
					TextField newJob = new TextField(thisBand.jobs[i - 1].name);
					TextField newSalary = new TextField("" + thisBand.jobs[i - 1].basicSalary);
					modify.base++;

					modify.labels.getChildren().add(job);
					modify.salarys.getChildren().add(newSalary);
					modify.vbox.getChildren().add(newJob);
					modify.salary.add(newSalary);
					modify.textFields.add(newJob);
				}
			}
		});

		modify.next.setOnAction(e -> {
			reCreate.error.setVisible(false);
			modify.error.setVisible(false);
			if (reCreate.band.getText().isEmpty()) {
				reCreate.error.setText("Fill all information fields");
				reCreate.error.setVisible(true);
				return;
			}
			if (list.getSelectionModel() == null) {
				list.getSelectionModel().selectFirst();
			}
			Band modified = list.getSelectionModel().getSelectedItem();
			SLLNode<Band> cross = CommonClass.bandList.head;
			while (cross != null) {
				if (cross.info.equals(modified)) {
					break;
				}
				cross = cross.next;
			}
			Job[] currentJobs = new Job[modify.textFields.size()];
			for (int i = 0; i < modify.textFields.size(); i++) {
				if (!modify.textFields.get(i).getText().isEmpty() && !modify.salary.get(i).getText().isEmpty()) {
					try {
						Integer.parseInt(modify.salary.get(i).getText());
						Job x = new Job(modify.textFields.get(i).getText(),
								Integer.parseInt(modify.salary.get(i).getText()));
						currentJobs[i] = x;

					} catch (Exception ex) {
						modify.error.setText("Salary Fields should contain Integer values");
						modify.error.setVisible(true);
						return;
					}

				} else {
					modify.error.setText("Fill all information fields");
					modify.error.setVisible(true);
					return;
				}
			}
			cross.info.assign(currentJobs);
			cross.info.name = reCreate.band.getText();
			cross.info.unit = reCreate.unit.getSelectionModel().getSelectedItem();

			CommonClass.writeBands();
			CommonClass.scene = new Scene(new ModifyBand(), 800, 800);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Modify Band");
			CommonClass.primaryStage.show();
		});

		modify.back.setOnAction(e -> {
			CommonClass.scene = new Scene(new Hierarchy(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});

		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				reCreate.error.setVisible(false);
				modify.error.setVisible(false);
				if (reCreate.band.getText().isEmpty()) {
					reCreate.error.setText("Fill all information fields");
					reCreate.error.setVisible(true);
					return;
				}
				if (list.getSelectionModel() == null) {
					list.getSelectionModel().selectFirst();
				}
				Band modified = list.getSelectionModel().getSelectedItem();
				SLLNode<Band> cross = CommonClass.bandList.head;
				while (cross != null) {
					if (cross.info.equals(modified)) {
						break;
					}
					cross = cross.next;
				}
				Job[] currentJobs = new Job[modify.textFields.size()];
				for (int i = 0; i < modify.textFields.size(); i++) {
					if (!modify.textFields.get(i).getText().isEmpty() && !modify.salary.get(i).getText().isEmpty()) {
						try {
							Integer.parseInt(modify.salary.get(i).getText());
							Job x = new Job(modify.textFields.get(i).getText(),
									Integer.parseInt(modify.salary.get(i).getText()));
							currentJobs[i] = x;

						} catch (Exception ex) {
							modify.error.setText("Salary Fields should contain Integer values");
							modify.error.setVisible(true);
							return;
						}

					} else {
						modify.error.setText("Fill all information fields");
						modify.error.setVisible(true);
						return;
					}
				}
				cross.info.assign(currentJobs);
				cross.info.name = reCreate.band.getText();
				cross.info.unit = reCreate.unit.getSelectionModel().getSelectedItem();

				CommonClass.writeBands();
				CommonClass.scene = new Scene(new ModifyBand(), 700, 700);
				CommonClass.primaryStage.setScene(CommonClass.scene);
				CommonClass.primaryStage.setTitle("Modify Band");
				CommonClass.primaryStage.show();
			}
		});
	}
}
