package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
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

public class Applications extends BorderPane {

	// Should show add candidate, schedule interview, fill offer, modify candidate info
	Button addCandidate = new Button("Add New Candidate");
	Button fillOffer = new Button("Fill Job Offer");
	Button modifyCandidate = new Button("Modify Candidate Information");
	HBox hbox1 = new HBox();
	HBox hbox2 = new HBox();
	Label info = new Label("You can add or modify candidates and fill offers");
	VBox last = new VBox();
	Button back = new Button("Back");
	Glow glow = new Glow();
	
	public Applications() {
		addCandidate.setPrefSize(180, 50);
		fillOffer.setPrefSize(180, 50);
		modifyCandidate.setPrefSize(180, 50);
		back.setPrefSize(100, 50);
		
		glow.setLevel(0.1);

		addCandidate.setEffect(glow);
		fillOffer.setEffect(glow);
		modifyCandidate.setEffect(glow);
		fillOffer.setEffect(glow);
		back.setEffect(glow);
		

		info.setFont(Font.font("Century", FontWeight.LIGHT, 26));
		info.setWrapText(true);
		info.setTextFill(Color.DARKGREEN);
		info.setContentDisplay(ContentDisplay.CENTER);
		addCandidate.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		fillOffer.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		modifyCandidate.setStyle(
				"-fx-text-fill: #FFFFFF; -fx-background-color: #3e3e8a; -fx-border-radius: 20; -fx-background-radius: 20;");
		back.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");
		
		hbox1.getChildren().addAll(addCandidate, modifyCandidate, fillOffer);
		hbox1.setSpacing(20);
		hbox1.setAlignment(Pos.CENTER);
		
		
		last.getChildren().addAll(info, hbox1, back);
		last.setSpacing(30);
		last.setAlignment(Pos.CENTER);
		setCenter(last);
		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));
		
		
		//Handlers
		
		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new HRInterface(), 800, 800);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("HR Interface");
			CommonClass.primaryStage.show();
		});
		fillOffer.setOnAction(e -> {
			CommonClass.scene = new Scene(new FillOffer(), 800, 800);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Fill Job Offer");
			CommonClass.primaryStage.show();
		});
		addCandidate.setOnAction(e -> {
			CommonClass.scene = new Scene(new AddCandidate(), 800, 800);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Add Candidate");
			CommonClass.primaryStage.show();
		});

		modifyCandidate.setOnAction(e -> {
			CommonClass.scene = new Scene(new ModifyCandidateInfo(), 800, 800);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Modify Candidate Information");
			CommonClass.primaryStage.show();
		});
		
	
	}
}

