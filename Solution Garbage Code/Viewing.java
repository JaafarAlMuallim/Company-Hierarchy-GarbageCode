package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class Viewing extends BorderPane {

	// should show view interview details, Modify interview details schedule
	// interview,
	Button scheduleInterview = new Button("Schedule an Interview");
	Button modifyInterview = new Button("Modify Interview Details");
	Button viewDetails = new Button("View Interview Details");
	Label info = new Label("Schedule Interviews, Modify And View");
	HBox hbox1 = new HBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	VBox last = new VBox();
	Button back = new Button("Back");
	Glow glow = new Glow();

	public Viewing() {

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));
		scheduleInterview.setPrefSize(180, 50);
		modifyInterview.setPrefSize(180, 50);
		viewDetails.setPrefSize(180, 50);
		back.setPrefSize(130, 50);

		glow.setLevel(0.1);

		scheduleInterview.setEffect(glow);
		viewDetails.setEffect(glow);
		modifyInterview.setEffect(glow);
		back.setEffect(glow);

		info.setFont(Font.font("Century", FontWeight.LIGHT, 26));
		info.setWrapText(true);
		info.setTextFill(Color.DARKMAGENTA);

		scheduleInterview.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		modifyInterview.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		viewDetails.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");

		hbox1.getChildren().addAll(scheduleInterview, modifyInterview, viewDetails);
		hbox1.setSpacing(20);
		hbox1.setAlignment(Pos.CENTER);

		last.getChildren().addAll(info, hbox1, back);
		last.setSpacing(40);
		last.setAlignment(Pos.CENTER);
		
		setCenter(last);

		// Handlers
		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new HRInterface(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Login");
			CommonClass.primaryStage.show();
		});
		scheduleInterview.setOnAction(e -> {
			CommonClass.scene = new Scene(new ScheduleInterview(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Schedule Interview");
			CommonClass.primaryStage.show();
		});
		modifyInterview.setOnAction(e -> {
			CommonClass.scene = new Scene(new ModifyInterviewDetails(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Modify Interview Details");
			CommonClass.primaryStage.show();
		});
		viewDetails.setOnAction(e -> {
			CommonClass.scene = new Scene(new ViewInterviewDetails(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("View Interview Details");
			CommonClass.primaryStage.show();
		});
	}

}
