package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InterviewerInterface extends BorderPane {
//	Button viewDetails = new Button("View Interview Details");
	Button fillInfo = new Button("Fill Interview Information");
	Button logOut = new Button("Log out");
	HBox hbox1 = new HBox();
	VBox vbox = new VBox();
	
	public InterviewerInterface() {
		
		// Design and Styles
		
		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));
		
		fillInfo.setPrefSize(185, 40);
		logOut.setPrefSize(185, 40);
		
		fillInfo.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		logOut.setStyle("-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");
		
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(40);
		hbox1.getChildren().addAll(fillInfo);
		hbox1.setPadding(new Insets(0, 30, 0, 30));
		
		vbox.getChildren().addAll(hbox1, logOut);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(30);
		
		setCenter(vbox);
		
		// Handlers
		
		logOut.setOnAction(e -> {
			CommonClass.scene = new Scene(new MainPage(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Login");
			CommonClass.primaryStage.show();
		});
		
		fillInfo.setOnAction(e -> {
			CommonClass.scene = new Scene(new CreateReport(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Create Interview Report");
			CommonClass.primaryStage.show();
		});
	}
}
