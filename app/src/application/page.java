package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class page extends Application{
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("entre.fxml"));
			Scene scene = new Scene(root,612,434);
			scene.getStylesheets().add(getClass().getResource("NewFile.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().setValue(Boolean.FALSE);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/*	public static void main(String[] args) {
		launch(args);
	}

*/
}
