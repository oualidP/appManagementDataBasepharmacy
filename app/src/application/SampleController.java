package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class SampleController {
	
	
	    @FXML
	    private AnchorPane Apane;

	    @FXML
	    private Button btn;

	    @FXML
	    private AnchorPane panecover;

	    @FXML
	    private PasswordField txtpasse;

	    @FXML
	    private TextField txtuser;

	    @FXML
	    void btnValider(ActionEvent event) {
	    	/*btn.getScene().getWindow().hide();
	    	page p=new page();
	    	p.start(new Stage());
	    	*/
	    	String user=txtuser.getText();
			String pass=txtpasse.getText();
			
			PreparedStatement ps=null ;
			ResultSet rs = null;
			      if(txtuser.getText().equals("")||txtpasse.getText().equals("")) {
			    	  Alert ereur=new Alert(Alert.AlertType.ERROR) ;
			    	  ereur.setTitle("Les donneés");
			    	  ereur.setContentText("remplie les données");
			    	  ereur.show();
			      }
			
			
				String query="SELECT * FROM login.login WHERE user=? AND password=?;";
				try {
					ps=ConnectionBDLogin.getConnection().prepareStatement(query);
					ps.setString(1,user);
					ps.setString(2,pass);
					rs=ps.executeQuery();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					if(rs.next()) {
						btn.getScene().getWindow().hide();
						page p=new page();
						p.start(new Stage());
						
					}
					else {
						if(!user.toString().equals("")&&!pass.toString().equals("")) {
						Alert erreur=new Alert(Alert.AlertType.ERROR);
						erreur.setTitle("Base de données");
						erreur.setContentText("No connectées");
						erreur.show();}
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
	  
}
	    @FXML
	    void btnEntre(MouseEvent event) {
	    	
             btn.setBackground(new Background(new BackgroundFill(Color.rgb(230,230, 230), null, null)));
                      
	    }

	    @FXML
	    void btnSortie(MouseEvent event) {
	    	
            btn.setBackground(new Background(new BackgroundFill(Color.rgb(255,255, 255), null, null)));
	    }
	   
	    	
				

	    }
	   
