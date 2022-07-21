package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
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

public class CreateReport extends BorderPane {

	ListView<Candidate> candidates = new ListView<Candidate>();
	TextArea log = new TextArea("Interview Log containing date, time, notes and comments...");
	Button next = new Button("Submit");
	Button back = new Button("Back");
	Button clear = new Button("Clear");
	Label logL = new Label("Interview Log");
	Label outcome = new Label("Interview Outome");
	Label error = new Label();
	RadioButton pass = new RadioButton("Pass");
	RadioButton hold = new RadioButton("Hold");
	RadioButton fail = new RadioButton("Fail");
	ToggleGroup group = new ToggleGroup();
	VBox fields = new VBox();
	VBox labels = new VBox();
	VBox last = new VBox();
	HBox hbox1 = new HBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	HBox hbox4 = new HBox();
	Glow glow = new Glow();

	public CreateReport() {
		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		pass.setToggleGroup(group);
		hold.setToggleGroup(group);
		fail.setToggleGroup(group);
		log.setWrapText(true);
		
		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));


		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		clear.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		candidates.setMaxSize(180, 300);
		candidates.setStyle("-fx-background-color: lightblue; -fx-text-fill: black;");

		SLLNode<Interview> interview = CommonClass.interviewList.head;

		while (interview != null) {
			if (interview.info.interviewer.id.equals(CommonClass.id)) {
				if (interview.info.candidate.status.equals("Interviewing"))
					candidates.getItems().add(interview.info.candidate);
			}
			interview = interview.next;
		}

		if (candidates.getItems().isEmpty()) {
			fields.setVisible(false);
			labels.setVisible(false);
			clear.setVisible(false);
			error.setText("No Scheduled Interviews");
			next.setVisible(false);
			error.setVisible(true);
		} else {
			candidates.getSelectionModel().selectFirst();
		}

		next.setPrefSize(90, 20);
		clear.setPrefSize(90, 20);
		back.setPrefSize(90, 20);

		log.setPrefSize(400, 20);

		glow.setLevel(0.2);
		next.setEffect(glow);
		back.setEffect(glow);
		clear.setEffect(glow);

		labels.getChildren().addAll(logL, outcome);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(80);

		hbox1.getChildren().addAll(pass, hold, fail);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(80);

		hbox2.getChildren().addAll(back, next);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(30);

		fields.getChildren().addAll(log, hbox1);
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(70);

		hbox3.getChildren().addAll(labels, fields);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(30);

		last.getChildren().addAll(error, hbox3, clear, hbox2);
		last.setAlignment(Pos.CENTER);
		last.setSpacing(40);

		hbox4.getChildren().addAll(candidates, last);
		hbox4.setAlignment(Pos.CENTER);
		hbox4.setSpacing(30);

		setCenter(hbox4);

		// Handlers

		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new InterviewerInterface(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Login");
			CommonClass.primaryStage.show();
		});

		clear.setOnAction(e -> {
			log.clear();
			if (group.getSelectedToggle() != null)
				group.getSelectedToggle().setSelected(false);

			candidates.getSelectionModel().select(0);
			error.setVisible(false);
		});

		next.setOnAction(e -> {
			error.setVisible(false);
			if (!log.getText().isEmpty() && group.getSelectedToggle() != null) {

				SLLNode<Interview> interviews = CommonClass.interviewList.head;

				while (interviews != null) {
					if (interviews.info.candidate.equals(candidates.getSelectionModel().getSelectedItem())) {
						break;
					}
					interviews = interviews.next;
				}
				
				SLLNode<Candidate> cand = CommonClass.candidateList.head;
				while (cand != null) {
					if(cand.info.equals(candidates.getSelectionModel().getSelectedItem()))
						break;
					cand = cand.next;
				}
				Candidate selectedApp = cand.info;
				
				interviews.info.log = log.getText();
				RadioButton rb = (RadioButton) group.getSelectedToggle();

				if (rb.getText().equals("Fail")) {
					interviews.info.outcome = "Failed";
					interviews.info.candidate.status = "Failed";
					selectedApp.status = "Failed";
				} else if (rb.getText().equals("Pass")) {
					interviews.info.outcome = "Passed";
					interviews.info.candidate.status = "Passed";
					selectedApp.status = "Passed";
				} else if (interviews.info.candidate.heldInterviews >= 3 && rb.getText().equals("Hold")) {
					interviews.info.outcome = "Failed";
					interviews.info.candidate.status = "Failed";
					selectedApp.status = "Failed";
				} else {
					interviews.info.outcome = "Hold";
					selectedApp.status = "Hold";
					interviews.info.candidate.status = "Hold";
				}

				
				CommonClass.writeCandidates();
				CommonClass.writeInterviews();

				candidates.getItems().remove(candidates.getSelectionModel().getSelectedIndex());

				log.setText("Interview Log containing date, time, notes and comments...");
				interviews.info.interviewer.time.put(interviews.info.time, true);
				if (group.getSelectedToggle() != null)
					group.getSelectedToggle().setSelected(false);

				if (!candidates.getItems().isEmpty()) {
					candidates.getSelectionModel().select(0);
					interviews = CommonClass.interviewList.head;
				} else {
					error.setText("No More Interviews scheduled");
					error.setVisible(true);
					next.setVisible(false);
				}

			} else {
				error.setText("Fill all information fields");
				error.setVisible(true);
				return;
			}
		});

	}
}
