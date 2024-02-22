package application;


	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
	import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

	public class fourController {

	    @FXML
	    private Button btnAjouter;

	    @FXML
	    private Button btnSvg;

	    @FXML
	    private Button btnmodifier;
	    @FXML
	    private TableView<fourclass> tableFour;

	    @FXML
	    private TableColumn<fourclass, String> colAdresse;

	    @FXML
	    private TableColumn<fourclass, String> colEmail;

	    @FXML
	    private TableColumn<fourclass, String> colNumero;

	    @FXML
	    private TableColumn<fourclass, String> colReseau;

	    @FXML
	    private TextField textAdress;

	    @FXML
	    private TextField textEmail;

	    @FXML
	    private TextField textNumero;

	    @FXML
	    private TextField textSocial;
	   @FXML
		private void initialize() {
	    	
	    	
	    	 
	    	colAdresse.setCellValueFactory(new PropertyValueFactory<fourclass, String>("Adress"));
	    	colEmail.setCellValueFactory(new PropertyValueFactory<fourclass, String>("email"));
	    	colNumero.setCellValueFactory(new PropertyValueFactory<fourclass, String>("numero"));
	    	 colReseau.setCellValueFactory(new PropertyValueFactory<fourclass, String>("Raison_s"));
	    	
	    	 
	    	
	     }

	    @FXML
	    void AjouterFour(MouseEvent event) {
	    	if(!textAdress.getText().equals("")&&!textEmail.getText().equals("")&&!textNumero.getText().equals("")&&!textSocial.getText().equals("")) {
	    		tableFour.getItems().add(new fourclass(
	    				textSocial.getText(),
	    				textAdress.getText(),
	    				textNumero.getText(),
	    				textEmail.getText()
	        			 ));
	    		textSocial.setText("");
	    		textAdress.setText("");
	    		textEmail.setText("");
	        	
	    		textNumero.setText("");
	    	 }
	    	 else {
	    		 Alert erreur=new Alert(Alert.AlertType.ERROR);
					erreur.setTitle("Les données");
					erreur.setContentText("Saisi les donneés");
					erreur.show();}
	    	 }


	

	    @FXML
	    void SauvegarderFour(MouseEvent event) {
	    	 String query;
	    	 Date d;
	                 if(tableFour.getItems().size()>=1) {
	                	 fourclass l[]=new fourclass[tableFour.getItems().size()];
	                     for(int i=0;i<tableFour.getItems().size();i++) {
	                   	  l[i]=tableFour.getItems().get(i);
	                   	  }
	                     PreparedStatement ps=null;
	                   	  for(int i=0;i<tableFour.getItems().size();i++) {
	                   		
	                   		
	                   		query="INSERT INTO stock.fournisseur(RESEAU_SOCIAL,ADRESS,NUMERO_TELEPHONE,EMAIL)VALUES(?,?,?,?);";
	                   		try {
								ps=ConnectionBDprod.getConnection().prepareStatement(query);
								ps.setString(1,l[i].getRaison_s());
								ps.setString(2,l[i].getAdress());
								ps.setString(3,l[i].getNumero());
								ps.setString(4,l[i].getEmail());
								
								ps.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
								
	                   	
	                      
	                     }
	                   	btnSvg.getScene().getWindow().hide();
	                   	page l1=new page();
	       	    	 l1.start(new Stage());
	                 }

	    }

	    @FXML
	    void modifierfour(MouseEvent event) {
	    	int i=tableFour.getSelectionModel().getSelectedIndex();
            if(i>=0) {
            	textAdress.setText(colAdresse.getCellData(i).toString());
            	textEmail.setText(colEmail.getCellData(i).toString());
            	textNumero.setText(colNumero.getCellData(i).toString());
            	textSocial.setText(colReseau.getCellData(i).toString());
        
          	 
          	 
          	 
          	  
          	tableFour.getItems().remove(tableFour.getSelectionModel().getSelectedIndex());
            }
            if(i<=-1) {
            	Alert erreur=new Alert(Alert.AlertType.ERROR);
				erreur.setTitle("Les données");
				erreur.setContentText("Saisi le produit dans  le table qui modifier");
				erreur.show();
            }
	    }
}
