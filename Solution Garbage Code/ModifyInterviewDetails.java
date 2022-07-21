package application;

import javafx.scene.layout.BorderPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ModifyInterviewDetails extends BorderPane {
	VBox vbox = new VBox();
	HBox hbox = new HBox();
	HBox hbox1 = new HBox();

	public ModifyInterviewDetails() {

		// exactly like creating report

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));
		CreateReport modify = new CreateReport();
		modify.error.setVisible(false);
		SLLNode<Candidate> candidate = CommonClass.candidateList.head;

		while (candidate != null) {
			if (candidate.info.status.equals("Hold") || candidate.info.status.equals("Failed"))
				modify.candidates.getItems().add(candidate.info);
			candidate = candidate.next;
		}
		if (modify.candidates.getItems().isEmpty()) {
			modify.error.setText("No Candidates Available");
			modify.error.setVisible(true);
		} else {
			modify.candidates.getSelectionModel().selectFirst();
			SLLNode<Interview> finterview = CommonClass.interviewList.head;
			while (finterview != null) {
				if (finterview.info.candidate.equals(modify.candidates.getSelectionModel().getSelectedItem()))
					break;
				finterview = finterview.next;
			}
			if (finterview.info.log != null && finterview.info.outcome != null) {
				modify.fields.setVisible(true);
				modify.labels.setVisible(true);
				modify.clear.setVisible(true);
				modify.next.setVisible(true);
				modify.error.setVisible(false);
				modify.log.setText(finterview.info.log);
				modify.log.setText(finterview.info.log);
				RadioButton rb = new RadioButton(finterview.info.outcome);
				if (rb.getText().equals("Hold"))
					modify.group.selectToggle(modify.hold);
				else if (rb.getText().equals("Failed"))
					modify.group.selectToggle(modify.fail);
			}

		}
		modify.candidates.getSelectionModel().selectFirst();

		setCenter(modify);

		// Handlers

		modify.back.setOnAction(e -> {
			CommonClass.scene = new Scene(new Viewing(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});

		modify.candidates.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Candidate>() {
			@Override
			public void changed(ObservableValue<? extends Candidate> observable, Candidate oldValue,
					Candidate newValue) {
				Candidate applicant = modify.candidates.getSelectionModel().getSelectedItem();

				SLLNode<Interview> finterview = CommonClass.interviewList.head;

				while (finterview != null) {
					if (finterview.info.candidate.equals(applicant))
						break;
					finterview = finterview.next;
				}
				modify.fields.setVisible(true);
				modify.labels.setVisible(true);
				modify.clear.setVisible(true);
				modify.next.setVisible(true);
				modify.error.setVisible(false);
				modify.log.setText(finterview.info.log);
				if (modify.group.getSelectedToggle() != null) {
					RadioButton rb = new RadioButton(finterview.info.outcome);
					if (rb.getText().equals("Passed") || rb.getText().equals("Employed"))
						modify.group.selectToggle(modify.pass);
					else if (rb.getText().equals("Hold"))
						modify.group.selectToggle(modify.hold);
					else
						modify.group.selectToggle(modify.fail);
					return;
				}
			}
		});

		modify.next.setOnAction(e -> {
			modify.error.setVisible(false);
			if (!modify.log.getText().isEmpty() && modify.group.getSelectedToggle() != null) {

				SLLNode<Interview> interviews = CommonClass.interviewList.head;
				SLLNode<Candidate> applicant = CommonClass.candidateList.head;
				while (interviews != null) {
					if (interviews.info.candidate.equals(modify.candidates.getSelectionModel().getSelectedItem())) {
						break;
					}
					interviews = interviews.next;
				}
				while (applicant != null) {
					if (applicant.info.equals(modify.candidates.getSelectionModel().getSelectedItem())) {
						break;
					}
					applicant = applicant.next;
				}
				interviews.info.log = modify.log.getText();
				RadioButton rb = (RadioButton) modify.group.getSelectedToggle();
				if (rb.getText().equals("Fail")) {
					interviews.info.outcome = "Failed";
					interviews.info.candidate.status = "Failed";
					applicant.info.status = "Failed";
				} else if (rb.getText().equals("Pass")) {
					interviews.info.outcome = "Passed";
					interviews.info.candidate.status = "Passed";
					applicant.info.status = "Passed";
				} else {
					interviews.info.outcome = "Hold";
					interviews.info.candidate.status = "Hold";
					applicant.info.status = "Hold";
				}
				CommonClass.writeCandidates();
				CommonClass.writeInterviews();
				CommonClass.scene = new Scene(new ModifyInterviewDetails(), 700, 700);
				CommonClass.primaryStage.setScene(CommonClass.scene);
				CommonClass.primaryStage.setTitle("Modify Interview Details");

			} else {
				modify.error.setText("Fill all information fields");
				modify.error.setVisible(true);
				return;
			}
		});
		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				modify.error.setVisible(false);
				if (!modify.log.getText().isEmpty() && modify.group.getSelectedToggle() != null) {

					SLLNode<Interview> interviews = CommonClass.interviewList.head;
					SLLNode<Candidate> applicant = CommonClass.candidateList.head;
					while (interviews != null) {
						if (interviews.info.candidate.equals(modify.candidates.getSelectionModel().getSelectedItem())) {
							break;
						}
						interviews = interviews.next;
					}
					while (applicant != null) {
						if (applicant.info.equals(modify.candidates.getSelectionModel().getSelectedItem())) {
							break;
						}
						applicant = applicant.next;
					}
					interviews.info.log = modify.log.getText();
					RadioButton rb = (RadioButton) modify.group.getSelectedToggle();
					if (rb.getText().equals("Fail")) {
						interviews.info.outcome = "Failed";
						interviews.info.candidate.status = "Failed";
						applicant.info.status = "Failed";
					} else if (rb.getText().equals("Pass")) {
						interviews.info.outcome = "Passed";
						interviews.info.candidate.status = "Passed";
						applicant.info.status = "Passed";
					} else {
						interviews.info.outcome = "Hold";
						interviews.info.candidate.status = "Hold";
						applicant.info.status = "Hold";
					}
					CommonClass.writeCandidates();
					CommonClass.writeInterviews();
					CommonClass.scene = new Scene(new ModifyInterviewDetails(), 700, 700);
					CommonClass.primaryStage.setScene(CommonClass.scene);
					CommonClass.primaryStage.setTitle("Modify Interview Details");

				} else {
					modify.error.setText("Fill all information fields");
					modify.error.setVisible(true);
					return;
				}
			}
		});
	}
}
