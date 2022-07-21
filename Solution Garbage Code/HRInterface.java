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

public class HRInterface extends BorderPane {
	Button apps = new Button("Applications");
	Button hier = new Button("Hierarchy");
	Button view = new Button("Interviews");
	Button logOut = new Button("Log out");
	Label info = new Label("Welcome to xyzSoft System");
	HBox hbox1 = new HBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	VBox vbox = new VBox();
	Glow glow = new Glow();

	public HRInterface() {

		// Design and Style

		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));

		logOut.setPrefSize(140, 50);
		apps.setPrefSize(185, 50);
		hier.setPrefSize(185, 50);
		view.setPrefSize(185, 50);

		glow.setLevel(0.1);
		logOut.setEffect(glow);
		apps.setEffect(glow);
		hier.setEffect(glow);
		view.setEffect(glow);

		info.setFont(Font.font("Century", FontWeight.LIGHT, 26));
		info.setWrapText(true);
		info.setTextFill(Color.DARKGREEN);

		apps.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		hier.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		view.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");

		logOut.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");

		hbox1.getChildren().addAll(apps, view, hier);
		hbox1.setSpacing(20);
		hbox1.setAlignment(Pos.CENTER);

		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setSpacing(40);
		vbox.getChildren().addAll(info, hbox1, logOut);

		setCenter(vbox);

		// Handlers

		logOut.setOnAction(e -> {
			CommonClass.scene = new Scene(new MainPage(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Login");
			CommonClass.primaryStage.show();
		});

		apps.setOnAction(e -> {
			CommonClass.scene = new Scene(new Applications(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Applications");
			CommonClass.primaryStage.show();
		});
		hier.setOnAction(e -> {
			CommonClass.scene = new Scene(new Hierarchy(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Hierarchy");
			CommonClass.primaryStage.show();
		});
		view.setOnAction(e -> {
			CommonClass.scene = new Scene(new Viewing(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("View Other Data");
			CommonClass.primaryStage.show();
		});
	}

}
