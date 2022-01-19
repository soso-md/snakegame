package snake;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	
	private static int speed = 150;
	
	@FXML
	private AnchorPane StartAnchorPane;

	public void startGame(ActionEvent e) throws IOException {
		speed = 15;
		StackPane root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		StartAnchorPane.getChildren().setAll(root);	
	}
	
	public void startSimpleGame(ActionEvent e) throws IOException {
		speed = 7;
		StackPane root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		StartAnchorPane.getChildren().setAll(root);	
	}
	
	public void startExpertGame(ActionEvent e) throws IOException {
		System.out.println("EXPERT");
		speed = 40;
		StackPane root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		StartAnchorPane.getChildren().setAll(root);	
	}

	public static int getSpeed() {
		return speed;
	}
	
	
}
