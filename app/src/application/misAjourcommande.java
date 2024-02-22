package application;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class misAjourcommande {
	@SuppressWarnings("unlikely-arg-type")
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("misefx.fxml"));
			Scene scene = new Scene(root,718,615);
			//scene.getStylesheets().add(getClass().getResource("NewFile3.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().setValue(Boolean.FALSE);
			
          
			
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				
				@Override
				public void handle(WindowEvent arg0) {
					
					
					
					
							page l=new page();
							l.start(new Stage());
						
					
					
				}
						
					
					
					
						
           
				
		
            
           	});
		
            
				
				
			
            
           
           
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
