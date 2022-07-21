package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ViewInterviewDetails extends BorderPane {
	ListView<Interview> list = new ListView<Interview>();
	Button back = new Button("Back");
	Label all = new Label();
	Label candidate = new Label("Candidate ");
	Label error = new Label();
	VBox labels = new VBox();
	VBox last = new VBox();
	HBox hbox1 = new HBox();
	Glow glow = new Glow();

	public ViewInterviewDetails() {

		// Design and Style

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));
		back.setPrefSize(80, 20);
		back.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5;");

		glow.setLevel(0.3);
		back.setEffect(glow);

		list.setPrefHeight(list.getItems().size());
		list.setMaxSize(180, 300);

		all.setFont(Font.font("Calisto MT", FontWeight.LIGHT, 16));
		all.setWrapText(true);

		error.setAlignment(Pos.CENTER);
		error.setUnderline(true);
		error.setVisible(false);
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Century", FontWeight.LIGHT, 18));

		SLLNode<Interview> interview = CommonClass.interviewList.head;

		while (interview != null) {
			list.getItems().add(interview.info);
			interview = interview.next;
		}
		if (list.getItems().isEmpty()) {
			error.setText("No Interviews performed yet");
			error.setVisible(true);

		} else {
			list.getSelectionModel().selectFirst();
			all.setText(list.getSelectionModel().getSelectedItem().toString2());
		}
		list.setStyle("-fx-background-color: lightblue; -fx-text-fill: black;");
		setLeft(list);

		hbox1.getChildren().addAll(list, all);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(40);

		last.getChildren().addAll(error, hbox1, back);
		last.setAlignment(Pos.CENTER);
		last.setSpacing(40);

		setCenter(last);

		// Handlers

		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new Viewing(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});

		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Interview>() {
			@Override
			public void changed(ObservableValue<? extends Interview> observable, Interview oldValue,
					Interview newValue) {
				all.setText(list.getSelectionModel().getSelectedItem().toString2());
				return;
			}

		});

	}
}
