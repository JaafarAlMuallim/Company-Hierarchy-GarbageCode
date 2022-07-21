package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Review extends BorderPane {
	Button back = new Button("Back");
	Glow glow = new Glow();
	VBox last = new VBox();
	VBox box = new VBox();
	TreeView<String> root = new TreeView<String>();
	TreeItem<String> rootNode = new TreeItem<String>("xyzSoft Company");

	public Review() {
		setBackground(new Background((new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))));
		rootNode.setExpanded(true);

		SLLNode<Units> unit = CommonClass.unitList.head;
		SLLNode<Band> band;
		while (unit != null) {
			band = CommonClass.bandList.head;
			TreeItem<String> elem = new TreeItem<String>(unit.info.name + " - " + unit.info.level);
			rootNode.getChildren().add(elem);
			while (band != null) {
				if (band.info.unit.name.equals(unit.info.name)) {
					elem.getChildren().add(new TreeItem<String>(band.info.name));
				}
				band = band.next;
			}
			unit = unit.next;
		}

		TreeView<String> treeView = new TreeView<String>(rootNode);

		back.setPrefSize(130, 50);
		back.setEffect(glow);

		glow.setLevel(0.1);
		back.setStyle(
				"-fx-text-fill: #000000; -fx-background-color: #c8a2c8; -fx-border-radius: 20; -fx-background-radius: 20;");

		last.getChildren().addAll(treeView, back);
		last.setSpacing(40);
		last.setAlignment(Pos.CENTER);

		setCenter(last);
		back.setOnAction(e -> {
			CommonClass.scene = new Scene(new Hierarchy(), 700, 700);
			CommonClass.primaryStage.setScene(CommonClass.scene);
			CommonClass.primaryStage.setTitle("Login");
			CommonClass.primaryStage.show();
		});
	}
}
