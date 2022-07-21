package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ModifyCandidateInfo extends BorderPane {
	ListView<Candidate> list = new ListView<Candidate>();
	VBox vbox = new VBox();
	HBox hbox = new HBox();
	HBox hbox1 = new HBox();

	public ModifyCandidateInfo() {
		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		AddCandidate modify = new AddCandidate();
		hbox1.setAlignment(Pos.CENTER);

		SLLNode<Candidate> candidate = CommonClass.candidateList.head;
		if (candidate == null) {
			modify.error.setText("No Candidates");
			modify.error.setVisible(true);
			modify.next.setVisible(false);
			modify.hbox2.setVisible(false);
			modify.clear.setVisible(false);
		} else {
			modify.nameT.setText(candidate.info.name);
			modify.idT.setText("" + candidate.info.id);
			modify.group.selectToggle(null);
			RadioButton rb = new RadioButton(candidate.info.gender);
			if (rb.getText().equals("Male")) {
				modify.group.selectToggle(modify.male);
			} else
				modify.group.selectToggle(modify.female);

			modify.cvT.setText(candidate.info.cv);
			modify.expT.setText("" + candidate.info.exp);
			modify.eduT.getSelectionModel().select(candidate.info.educationLevel);
			modify.posT.getSelectionModel().select(candidate.info.position + " -- " + candidate.info.band);
		}
		while (candidate != null) {
			list.getItems().add(candidate.info);
			candidate = candidate.next;
		}

		hbox.setSpacing(10);
		vbox.setSpacing(-40);
		hbox.setPadding(new Insets(20, 20, 20, 20));
		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(list, modify);
		
		list.setMaxSize(180, 300);
		list.setStyle("-fx-background-color: lightblue; -fx-text-fill: black;");
		if(!list.getItems().isEmpty())
			list.getSelectionModel().select(0);
		else
			hbox.getChildren().remove(0);

		setCenter(hbox);

		// Handler
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Candidate>() {
			@Override
			public void changed(ObservableValue<? extends Candidate> observable, Candidate Candidate,
					Candidate newValue) {
				Candidate anotherCandidate = list.getSelectionModel().getSelectedItem();

				modify.nameT.setText(anotherCandidate.name);
				modify.idT.setText("" + anotherCandidate.id);
				modify.group.selectToggle(null);
				RadioButton rb = new RadioButton(anotherCandidate.gender);
				if (rb.getText().equals("Male")) {
					modify.group.selectToggle(modify.male);
				} else
					modify.group.selectToggle(modify.female);
				modify.cvT.setText(anotherCandidate.cv);
				modify.expT.setText("" + anotherCandidate.exp);
				modify.posT.getSelectionModel().select(anotherCandidate.position + " -- " + anotherCandidate.band);
				modify.eduT.getSelectionModel().select(anotherCandidate.educationLevel);
				return;
			}
		});

		modify.next.setOnAction(e -> {
			modify.error.setVisible(false);
			if (!modify.nameT.getText().isEmpty() && !modify.idT.getText().isEmpty() && !modify.exp.getText().isEmpty()
					&& !modify.cvT.getText().isEmpty() && modify.group.getSelectedToggle() != null) {
				if (modify.idT.getText().length() == 10) {
					try {

						Long longID = Long.parseLong(modify.idT.getText());
						if (longID < 0) {
							modify.error.setText("ID should not be signed");
							modify.error.setVisible(true);
							return;
						}
					} catch (Exception ex) {
						modify.error.setText("ID should contains 10 digit Numbers only ");
						modify.error.setVisible(true);
						return;
					}
					try {
						int experience = Integer.parseInt(modify.expT.getText());
						if (experience < 0) {
							modify.error.setText("Years of Expereince should not be signed");
							modify.error.setVisible(true);
							return;
						}
					} catch (Exception ex) {
						modify.error.setText("Years of Experience should be numerical Integer value");
						modify.error.setVisible(true);
						return;
					}
					RadioButton rb = (RadioButton) modify.group.getSelectedToggle();
					SLLNode<Band> currentBand = CommonClass.bandList.head;
					String[] arr = modify.posT.getSelectionModel().getSelectedItem().split(" -- ");

					while (currentBand != null) {

						if (currentBand.info.name.equals(arr[1]))
							break;
						currentBand = currentBand.next;
					}

					Job[] job = currentBand.info.jobs;
					Job current = job[0];
					for (Job currentJob : job) {
						if (currentJob.name.equals(arr[0]))
							break;
					}
					SLLNode<Candidate> anotherCandidate = CommonClass.candidateList.head;
					while (anotherCandidate != null) {
						if (anotherCandidate.info.equals(list.getSelectionModel().getSelectedItem())) {
							anotherCandidate.info.name = modify.nameT.getText();
							anotherCandidate.info.id = Long.parseLong(modify.idT.getText());
							anotherCandidate.info.exp = Integer.parseInt(modify.expT.getText());
							anotherCandidate.info.gender = rb.getText();
							anotherCandidate.info.cv = modify.cvT.getText();
							anotherCandidate.info.position = current;
							anotherCandidate.info.educationLevel = modify.eduT.getSelectionModel().getSelectedItem();
							CommonClass.writeCandidates();
							CommonClass.scene = new Scene(new ModifyCandidateInfo(), 700, 700);
							CommonClass.primaryStage.setScene(CommonClass.scene);
							CommonClass.primaryStage.setTitle("Modify Candidate Information");
							CommonClass.primaryStage.show();
							break;
						}
						anotherCandidate = anotherCandidate.next;
					}
					return;
				} else {
					modify.error.setText("ID should contains 10 digits");
					modify.error.setVisible(true);
					return;
				}
			} else {
				modify.error.setText("Fill all information fields");
				modify.error.setVisible(true);
				return;
			}
		});
		setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				modify.error.setVisible(false);
				if (!modify.nameT.getText().isEmpty() && !modify.idT.getText().isEmpty()
						&& !modify.exp.getText().isEmpty() && !modify.cvT.getText().isEmpty()
						&& modify.group.getSelectedToggle() != null) {
					if (modify.idT.getText().length() == 10) {
						try {

							Long longID = Long.parseLong(modify.idT.getText());
							if (longID < 0) {
								modify.error.setText("ID should not be signed");
								modify.error.setVisible(true);
								return;
							}
						} catch (Exception ex) {
							modify.error.setText("ID should contains 10 digit Numbers only ");
							modify.error.setVisible(true);
							return;
						}
						try {
							int experience = Integer.parseInt(modify.expT.getText());
							if (experience < 0) {
								modify.error.setText("Years of Expereince should not be signed");
								modify.error.setVisible(true);
								return;
							}
						} catch (Exception ex) {
							modify.error.setText("Years of Experience should be numerical Integer value");
							modify.error.setVisible(true);
							return;
						}
						RadioButton rb = (RadioButton) modify.group.getSelectedToggle();
						SLLNode<Band> currentBand = CommonClass.bandList.head;
						String[] arr = modify.posT.getSelectionModel().getSelectedItem().split(" -- ");

						while (currentBand != null) {

							if (currentBand.info.name.equals(arr[1]))
								break;
							currentBand = currentBand.next;
						}

						Job[] job = currentBand.info.jobs;
						Job current = job[0];
						for (Job currentJob : job) {
							if (currentJob.name.equals(arr[0]))
								break;
						}
						SLLNode<Candidate> anotherCandidate = CommonClass.candidateList.head;
						while (anotherCandidate != null) {
							if (anotherCandidate.info.equals(list.getSelectionModel().getSelectedItem())) {
								anotherCandidate.info.name = modify.nameT.getText();
								anotherCandidate.info.id = Long.parseLong(modify.idT.getText());
								anotherCandidate.info.exp = Integer.parseInt(modify.expT.getText());
								anotherCandidate.info.gender = rb.getText();
								anotherCandidate.info.cv = modify.cvT.getText();
								anotherCandidate.info.position = current;
								anotherCandidate.info.educationLevel = modify.eduT.getSelectionModel()
										.getSelectedItem();
								CommonClass.writeCandidates();
								CommonClass.scene = new Scene(new ModifyCandidateInfo(), 700, 700);
								CommonClass.primaryStage.setScene(CommonClass.scene);
								CommonClass.primaryStage.setTitle("Modify Candidate Information");
								CommonClass.primaryStage.show();
								break;
							}
							anotherCandidate = anotherCandidate.next;
						}

						return;
					} else {
						modify.error.setText("ID should contains 10 digits");
						modify.error.setVisible(true);
						return;
					}
				} else {
					modify.error.setText("Fill all information fields");
					modify.error.setVisible(true);
					return;
				}
			}
		});
	}
}
