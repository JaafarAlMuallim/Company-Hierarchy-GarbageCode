package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
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

public class FillOffer extends BorderPane {
	Button next = new Button("Submit");
	Button back = new Button("Back");
	Button clear = new Button("Reset");
	ListView<Candidate> cand = new ListView<Candidate>();
	Label slidingSalary = new Label("Salary");
	Label error = new Label();

	HBox hbox = new HBox();
	VBox fields = new VBox();
	VBox labels = new VBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	VBox vbox = new VBox();
	Glow glow = new Glow();
	Slider slider = new Slider();

	public FillOffer() {
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(250);
		slider.setBlockIncrement(500f);
		
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));

		SLLNode<Candidate> candidate = CommonClass.candidateList.head;
		while (candidate != null) {
			if (candidate.info.status.equals("Passed")) {
				cand.getItems().add(candidate.info);
			}
			candidate = candidate.next;
		}
		if (!cand.getItems().isEmpty()) {
			cand.getSelectionModel().select(0);
			Candidate applicant = cand.getSelectionModel().getSelectedItem();
			SLLNode<Band> band = CommonClass.bandList.head;
			boolean found = false;
			int bonus = 0;
			Job currentJob = cand.getSelectionModel().getSelectedItem().position;
			while (band != null) {
				Job[] jobs = band.info.jobs;
				for (Job job : jobs) {
					if (job.name.equals(cand.getSelectionModel().getSelectedItem().position.name)) {
						found = true;
						if (band.info.unit.level.equals("Division"))
							bonus = 1000;
						else if (band.info.unit.level.equals("Directorate"))
							bonus = 500;
						break;
					}
				}
				if (found)
					break;
				band = band.next;
			}

			double min = Math.max(currentJob.basicSalary + (500 * (applicant.exp - 2)) + bonus,
					currentJob.basicSalary + bonus);

			double max = currentJob.basicSalary + (500 * (applicant.exp + 2) + bonus);

			double housingMin = min * 0.25;
			double transportationMin = min * 0.1;

			double housingMax = max * 0.25;
			double transportationMax = max * 0.1;

			slider.setMin(min + housingMin + transportationMin);
			slider.setMax(max + housingMax + transportationMax);
			slider.adjustValue(slider.getMin());
			slidingSalary.setText("" + slider.getMin());
			slider.setVisible(true);
			slidingSalary.setVisible(true);

		} else {
			error.setText("No Candidates has passed the interview yet");
			error.setVisible(true);
			slider.setVisible(false);
			next.setVisible(false);
			cand.setVisible(false);
			clear.setVisible(false);
			slidingSalary.setVisible(false);
		}

		next.setPrefSize(70, 20);
		clear.setPrefSize(70, 20);
		back.setPrefSize(70, 20);
		glow.setLevel(0.2);

		next.setEffect(glow);
		back.setEffect(glow);
		clear.setEffect(glow);

		slidingSalary.setFont(Font.font("Verdana", FontWeight.MEDIUM, 18));

		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		hbox.getChildren().addAll(back, next);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(30);

		fields.getChildren().addAll(cand, slider);
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(35);

		hbox2.getChildren().addAll(labels, fields);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(30);

		hbox3.getChildren().addAll(clear);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(30);

		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(error, hbox2, slidingSalary, hbox3, hbox);
		vbox.setSpacing(30);

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		setCenter(vbox);

		// Handlers

		clear.setOnAction(e -> {
			if (slider.isVisible())
				slider.setValue(slider.getMin());
		});

		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new Applications(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				slidingSalary.setText("" + newValue.floatValue());
			}
		});

		cand.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Candidate>() {
			@Override
			public void changed(ObservableValue<? extends Candidate> observable, Candidate oldValue,
					Candidate newValue) {
				if(cand.getItems().isEmpty())
					return;
				SLLNode<Band> band = CommonClass.bandList.head;
				boolean found = false;
				int bonus = 0;
				Job currentJob = cand.getSelectionModel().getSelectedItem().position;
				while (band != null) {
					Job[] jobs = band.info.jobs;
					for (Job job : jobs) {
						if (job.name.equals(cand.getSelectionModel().getSelectedItem().position.name)) {
							found = true;
							if (band.info.unit.level.equals("Division"))
								bonus = 1000;
							else if (band.info.unit.level.equals("Directorate"))
								bonus = 500;
							break;
						}
					}
					if (found)
						break;
					band = band.next;
				}

				double min = Math.max(
						currentJob.basicSalary + (500 * (cand.getSelectionModel().getSelectedItem().exp - 2)) + bonus,
						currentJob.basicSalary + bonus);

				double max = currentJob.basicSalary
						+ (500 * (cand.getSelectionModel().getSelectedItem().exp + 2) + bonus);

				double housingMin = min * 0.25;
				double transportationMin = min * 0.1;

				double housingMax = max * 0.25;
				double transportationMax = max * 0.1;

				slider.setMin(min + housingMin + transportationMin);
				slider.setMax(max + housingMax + transportationMax);
				slider.adjustValue(slider.getMin());
				slidingSalary.setText("" + slider.getMin());
				slider.setVisible(true);
				slidingSalary.setVisible(true);
				return;
			}
		});

		next.setOnAction(e -> {
			SLLNode<Candidate> candi = CommonClass.candidateList.head;
			while (candi != null) {
				if (cand.getSelectionModel().getSelectedItem().equals(candi.info)) {
					break;
				}
				candi = candi.next;
			}
			Candidate employed = candi.info;
			Job job = employed.position;
			Employee newEmployee = new Employee(employed.name, employed.gender, employed.id,
					Double.parseDouble(slidingSalary.getText()));
			cand.getItems().remove(cand.getSelectionModel().getSelectedIndex());
			job.employeeNum++;
			CommonClass.users.addToTail(newEmployee);
			candi.info.status = "Employed";
			CommonClass.writeCandidates();
			CommonClass.writeUsers();
			CommonClass.scene = new Scene(new FillOffer(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Fill Offer");
		});

		setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case ENTER:
				SLLNode<Candidate> candi = CommonClass.candidateList.head;
				while (candi != null) {
					if (cand.getSelectionModel().getSelectedItem().equals(candi.info)) {
						candi.info.status = "Employed";
						break;
					}
					candi = candi.next;
				}
				Candidate employed = candi.info;
				Job job = employed.position;
				Employee newEmployee = new Employee(employed.name, employed.gender, employed.id,
						Double.parseDouble(slidingSalary.getText()));

				cand.getSelectionModel().getSelectedItem().status = "Employed";
				cand.getItems().remove(cand.getSelectionModel().getSelectedIndex());
				job.employeeNum++;
				CommonClass.users.addToTail(newEmployee);
				CommonClass.writeCandidates();
				CommonClass.writeUsers();
				CommonClass.scene = new Scene(new FillOffer(), 700, 700);
				CommonClass.primaryStage.setScene(CommonClass.scene);
				CommonClass.primaryStage.setTitle("Fill Offer");
				return;
			default:
				return;
			}
		});

	}
}