package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// Basics
			MainPage start = new MainPage();

			try {
				CommonClass.readBands();
				CommonClass.readUnits();
				CommonClass.readInterviews();
				CommonClass.readCandidates();
				CommonClass.readUsers();
			} catch (Exception ex) {
				System.out.println("Files Empty");
			}
			CommonClass.scene = new Scene(start, 700, 700);
			CommonClass.primaryStage.setTitle("Login");
			CommonClass.primaryStage.setScene(CommonClass.scene);

			CommonClass.primaryStage.show();
		} catch (Exception e) {
			System.out.println("");
			;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
