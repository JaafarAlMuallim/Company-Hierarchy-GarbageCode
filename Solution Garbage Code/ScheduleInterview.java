package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

public class ScheduleInterview extends BorderPane {
	ListView<Interviewer> interviewers = new ListView<Interviewer>();
	ListView<Candidate> candidates = new ListView<Candidate>();
	ComboBox<String> jobs = new ComboBox<String>();
	ComboBox<String> times = new ComboBox<String>();
	Button back = new Button("Back");
	Button next = new Button("Schedule");
	Label error = new Label();
	Label inters = new Label("Interviewers List");
	Label candidateId = new Label("Candidates List");
	HBox buttons = new HBox();
	HBox lists = new HBox();
	HBox conditions = new HBox();
	HBox labels = new HBox();
	VBox last = new VBox();
	Glow glow = new Glow();

	public ScheduleInterview() {

		// Design and Styles

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));

		next.setPrefSize(90, 20);
		back.setPrefSize(90, 20);

		glow.setLevel(0.2);

		next.setEffect(glow);
		back.setEffect(glow);

		interviewers.setMaxSize(180, 300);
		candidates.setMaxSize(200, 300);

		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		next.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		SLLNode<Band> band = CommonClass.bandList.head;
		while (band != null) {
			Job[] job = band.info.jobs;
			for (int i = 0; i < job.length; i++) {
				jobs.getItems().add(job[i].name + " -- " + band.info.name);
			}
			band = band.next;
		}
		if (!jobs.getItems().isEmpty()) {
			jobs.getSelectionModel().select(0);
		}
		times.getItems().addAll("8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "13:00", "13:30");
		times.getSelectionModel().select(0);
		SLLNode<Person> inter = CommonClass.users.head;
		while (inter != null) {
			if (inter.info instanceof Interviewer) {
				Interviewer free = (Interviewer) inter.info;
				if (free.time.get(times.getSelectionModel().getSelectedItem()))
					interviewers.getItems().add(free);
			}
			inter = inter.next;
		}

		SLLNode<Candidate> cand = CommonClass.candidateList.head;
		while (cand != null) {
			Candidate newCandidate = (Candidate) cand.info;
			if (!jobs.getItems().isEmpty()) {
				String[] arr = jobs.getSelectionModel().getSelectedItem().split(" -- ");
				if ((newCandidate.status.equals("Active") || newCandidate.status.equals("Hold"))
						&& newCandidate.position.name.equals(arr[0]) && newCandidate.band.name.equals(arr[1])) {
					candidates.getItems().add(cand.info);
				}
			}
			cand = cand.next;
		}

		if (jobs.getItems().isEmpty()) {
			error.setText("No jobs are offered yet");
			error.setFont(Font.font("Century", FontWeight.BOLD, 28));
			error.setVisible(true);
			interviewers.setVisible(false);
			candidates.setVisible(false);
			next.setVisible(false);
			times.setVisible(false);
			jobs.setVisible(false);
			inters.setVisible(false);
			candidateId.setVisible(false);
		}

		if (!interviewers.getItems().isEmpty()) {
			interviewers.getSelectionModel().select(0);
		} else {
			error.setText("No Interviewers available at " + times.getSelectionModel().getSelectedItem());
			error.setVisible(true);
			interviewers.setVisible(false);
			candidates.setVisible(false);
			next.setVisible(false);
			jobs.setVisible(false);
			inters.setVisible(false);
			candidateId.setVisible(false);
		}

		jobs.getSelectionModel().select(0);
		buttons.getChildren().addAll(back, next);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(40);

		conditions.getChildren().addAll(times, jobs);
		conditions.setAlignment(Pos.CENTER);
		conditions.setSpacing(40);

		lists.getChildren().addAll(interviewers, candidates);
		lists.setAlignment(Pos.CENTER);
		lists.setSpacing(60);

		labels.getChildren().addAll(inters, candidateId);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(120);

		last.getChildren().addAll(error, conditions, labels, lists, buttons);
		last.setAlignment(Pos.CENTER);
		last.setSpacing(40);

		times.getSelectionModel().select(0);

		interviewers.setStyle("-fx-background-color: lightblue; -fx-text-fill: black;");
		setCenter(last);

		// Handlers
		back.setOnAction(e -> {
			CommonClass.writeInterviews();
			CommonClass.scene = new Scene(new Viewing(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});

		next.setOnAction(e -> {
			if (interviewers.getSelectionModel() == null) {
				interviewers.getSelectionModel().selectFirst();
			}
			if (candidates.getSelectionModel() == null) {
				candidates.getSelectionModel().selectFirst();
			}

			Candidate selectedApp = candidates.getSelectionModel().getSelectedItem();
			selectedApp.status = "Interviewing";
			selectedApp.heldInterviews++;
			Interviewer selectedInter = interviewers.getSelectionModel().getSelectedItem();

			CommonClass.interviewList
					.addToTail(new Interview(selectedApp, selectedInter, times.getSelectionModel().getSelectedItem()));
			;
			selectedInter.heldInterviewes++;
			interviewers.getItems().remove(interviewers.getSelectionModel().getSelectedIndex());
			selectedInter.time.put(times.getSelectionModel().getSelectedItem(), false);

			candidates.getItems().remove(selectedApp);
			interviewers.getItems().remove(selectedInter);

			CommonClass.writeCandidates();
			CommonClass.writeInterviews();
			CommonClass.scene = new Scene(new ScheduleInterview(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);

		});

		jobs.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if (jobs.getItems().isEmpty()) {
					error.setText("No Jobs in Bands Yet");
					error.setVisible(true);
				} else {
					error.setVisible(false);
					interviewers.setVisible(true);
					candidates.setVisible(true);
					next.setVisible(true);
					inters.setVisible(true);
					candidateId.setVisible(true);
					candidates.getItems().clear();
					SLLNode<Candidate> cand = CommonClass.candidateList.head;
					while (cand != null) {
						Candidate newCandidate = (Candidate) cand.info;
						if (!jobs.getItems().isEmpty()) {
							String[] arr = jobs.getSelectionModel().getSelectedItem().split(" -- ");
							if ((newCandidate.status.equals("Active") || newCandidate.status.equals("Hold"))
									&& newCandidate.position.name.equals(arr[0])
									&& newCandidate.band.name.equals(arr[1])) {
								candidates.getItems().add(cand.info);
							}
						}
						cand = cand.next;
					}

					if (candidates.getItems().isEmpty()) {
						error.setText("No Candidates has applied yet in " + jobs.getSelectionModel().getSelectedItem());
						error.setVisible(true);
						next.setVisible(false);
						inters.setVisible(false);
						candidateId.setVisible(false);
					} else {
						candidates.getSelectionModel().selectFirst();
					}
				}

			}
		});

		times.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				SLLNode<Person> inter = CommonClass.users.head;
				interviewers.getItems().clear();
				error.setVisible(false);
				interviewers.setVisible(true);
				candidates.setVisible(true);
				next.setVisible(true);
				times.setVisible(true);
				jobs.setVisible(true);
				inters.setVisible(true);
				candidateId.setVisible(true);
				while (inter != null) {
					if (inter.info instanceof Interviewer) {
						Interviewer free = (Interviewer) inter.info;
						if (free.time.get(times.getSelectionModel().getSelectedItem()))
							interviewers.getItems().add(free);
					}
					inter = inter.next;
				}
				if (!interviewers.getItems().isEmpty()) {
					interviewers.getSelectionModel().select(0);
					jobs.getSelectionModel().select(0);
					if (candidates.getItems().isEmpty()) {
						error.setText("No Candidates has applied yet in " + jobs.getSelectionModel().getSelectedItem());
						error.setVisible(true);
						next.setVisible(false);
						inters.setVisible(false);
						candidateId.setVisible(false);
					} else {
						candidates.getSelectionModel().selectFirst();
					}
				} else {
					error.setText("No Interviewers available at " + times.getSelectionModel().getSelectedItem());
					error.setVisible(true);
					interviewers.setVisible(false);
					candidates.setVisible(false);
					next.setVisible(false);
					jobs.setVisible(false);
					inters.setVisible(false);
					candidateId.setVisible(false);
				}

			}
		});

		setOnKeyPressed(e -> {
			switch (e.getCode()) {

			case ENTER:
				if (interviewers.getSelectionModel() == null) {
					interviewers.getSelectionModel().selectFirst();
				}
				if (candidates.getSelectionModel() == null) {
					candidates.getSelectionModel().selectFirst();
				}

				candidates.getSelectionModel().getSelectedItem().status = "Interviewing";
				candidates.getSelectionModel().getSelectedItem().heldInterviews++;

				CommonClass.interviewList.addToTail(new Interview(candidates.getSelectionModel().getSelectedItem(),
						interviewers.getSelectionModel().getSelectedItem(),
						times.getSelectionModel().getSelectedItem()));

				candidates.getItems().remove(candidates.getSelectionModel().getSelectedIndex());

				interviewers.getSelectionModel().getSelectedItem().heldInterviewes++;
				interviewers.getItems().remove(interviewers.getSelectionModel().getSelectedIndex());
				interviewers.getSelectionModel().getSelectedItem().time.put(times.getSelectionModel().getSelectedItem(),
						false);
				CommonClass.writeCandidates();
				CommonClass.writeInterviews();
				CommonClass.scene = new Scene(new ScheduleInterview(), 700, 700);
				CommonClass.primaryStage.setScene(CommonClass.scene);
				break;
			case RIGHT:
				if (interviewers.isFocused()) {
					candidates.requestFocus();
				} else {
					interviewers.requestFocus();
				}
				break;
			case LEFT:
				if (interviewers.isFocused()) {
					candidates.requestFocus();
				} else {
					interviewers.requestFocus();
				}
				break;

			default:
				return;
			}

		});

	}
}
