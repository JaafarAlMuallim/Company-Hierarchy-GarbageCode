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

public class Hierarchy extends BorderPane {

	// create job band, create unit, modify band, fourth option review which should
	// show names of bands and the units

	Button createBand = new Button("Create Job Band");
	Button createUnit = new Button("Create Unit");
	Button modifyBand = new Button("Modify Job Band");
	Button review = new Button("Review Hierarchy");
	Label info = new Label("Adding Units, Bands, And Hierarchy Review");
	HBox hbox1 = new HBox();
	HBox hbox2 = new HBox();
	HBox hbox3 = new HBox();
	VBox last = new VBox();
	Button back = new Button("Back");
	Glow glow = new Glow();

	public Hierarchy() {
		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));
		modifyBand.setPrefSize(150, 50);
		createBand.setPrefSize(150, 50);
		createUnit.setPrefSize(150, 50);
		review.setPrefSize(150, 50);
		back.setPrefSize(150, 50);
		glow.setLevel(0.1);
		modifyBand.setEffect(glow);
		createBand.setEffect(glow);
		createUnit.setEffect(glow);
		review.setEffect(glow);
		back.setEffect(glow);

		info.setFont(Font.font("Century", FontWeight.LIGHT, 26));
		info.setWrapText(true);
		info.setTextFill(Color.DARKGREEN);
		

		modifyBand.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		createBand.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		createUnit.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		review.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");

		hbox1.getChildren().addAll(createUnit, createBand, modifyBand);
		hbox1.setSpacing(20);
		hbox1.setAlignment(Pos.CENTER);

		last.getChildren().addAll(info, hbox1, review, back);
		last.setSpacing(20);
		last.setAlignment(Pos.CENTER);

		setCenter(last);

		// Handlers
		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new HRInterface(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});

		createUnit.setOnAction(e -> {
			CommonClass.scene = new Scene(new CreateUnit(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Create Unit");
			CommonClass.primaryStage.show();
		});
		createBand.setOnAction(e -> {
			CommonClass.scene = new Scene(new CreateBand(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Create Band");
			CommonClass.primaryStage.show();
		});
		modifyBand.setOnAction(e -> {
			CommonClass.scene = new Scene(new ModifyBand(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Modify Band");
			CommonClass.primaryStage.show();
		});
		review.setOnAction(e -> {
			CommonClass.scene = new Scene(new Review(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Modify Band");
			CommonClass.primaryStage.show();
		});


	}
}
