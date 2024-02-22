package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class prodController {

    @FXML
    private DatePicker DateUtilisation;

    @FXML
    private Button btnAjouter;
    @FXML
    private AnchorPane PanStock;
    

    @FXML
    private Button btnEnregistrer;

    @FXML
    private ComboBox<String> combForme;

    @FXML
    private ComboBox<String> combType;

    @FXML
    private TextField textNom;
    @FXML

    private Button btnModifier;


    @FXML
    private TextField textPPV;
    @FXML
    private TableView<tableProduit> TableProd;
    @FXML
    private TableColumn<tableProduit, String> colonneForme;

    @FXML
    private TableColumn<tableProduit, String> colonneNom;

    @FXML
    private TableColumn<tableProduit, Double> colonnePPV;

    @FXML
    private TableColumn<tableProduit, LocalDate> colonnePeremption;

    @FXML
    private TableColumn<tableProduit, Integer> colonneStock;

    @FXML
    private TableColumn<tableProduit, String> colonneType;

    @FXML
    private TextField textStock;
     @FXML
     private void initialize() throws SQLException {
    	
    	 
    	 Statement st2 = null;
     	ResultSet rst2 = null;
     	st2=ConnectionBDprod.getConnection().createStatement();
     	rst2=st2.executeQuery("SElECT * FROM stock.type_produit;");
     	while(rst2.next()) {
     		combType.getItems().add(rst2.getString("TYPE_PRODUIT"));
     	}
     	combType.setVisibleRowCount(3);
     	Statement st1 = null;
     	ResultSet rst1 = null;
     	st1=ConnectionBDprod.getConnection().createStatement();
     	rst1=st1.executeQuery("SElECT * FROM stock.forme;");
     	while(rst1.next()) {
     		combForme.getItems().add(rst1.getString("FORME"));
     	}
     	combForme.setVisibleRowCount(3);
    	 
    	 colonneNom.setCellValueFactory(new PropertyValueFactory<tableProduit,String>("Nom"));
    	 colonnePeremption.setCellValueFactory(new PropertyValueFactory<tableProduit,LocalDate>("peremption"));
    	 colonneType.setCellValueFactory(new PropertyValueFactory<tableProduit,String>("Type"));
    	 colonnePPV.setCellValueFactory(new PropertyValueFactory<tableProduit,Double>("PPV"));
    	 colonneForme.setCellValueFactory(new PropertyValueFactory<tableProduit,String>("Forme"));
    	 colonneStock.setCellValueFactory(new PropertyValueFactory<tableProduit,Integer>("stock"));
    	 
    	
     }
     @FXML
     private AnchorPane PaneAjouter;
    
    
     @FXML
     void AjouterProduit(MouseEvent event) {
    	 if(!textNom.getText().equals("")&&combType.getValue()!=null&&combType.getValue()!=null&&!textPPV.equals(null)&&DateUtilisation.getValue()!=null) {
    		 TableProd.getItems().add(new tableProduit(
        			 textNom.getText(),
        			 DateUtilisation.getValue(),
        			Double.parseDouble(textPPV.getText()),
        			 combType.getValue(),
        			 combForme.getValue(),
        			 Integer.parseInt(textStock.getText())));
        	 textNom.setText("");
        	 DateUtilisation.setValue(null);
        	 textPPV.setText("");
        	 combType.setValue(null);
        	 combForme.setValue(null);
        	 textStock.setText("");
    	 }
    	 else {
    		 Alert erreur=new Alert(Alert.AlertType.ERROR);
				erreur.setTitle("Les données");
				erreur.setContentText("Saisi les donneés");
				erreur.show();}
    	 }

     @FXML
     void EnregistrerProduit(MouseEvent event) throws SQLException {
    	 String query;
    	 Date d;
                 if(TableProd.getItems().size()>=1) {
                	 tableProduit l[]=new tableProduit[TableProd.getItems().size()];
                     for(int i=0;i<TableProd.getItems().size();i++) {
                   	  l[i]=TableProd.getItems().get(i);
                   	  }
                     PreparedStatement ps=null;
					ResultSet rs=null;
                   	  for(int i=0;i<TableProd.getItems().size();i++) {
                   		d=java.sql.Date.valueOf(l[i].getPeremption());
                   		
                   		query="INSERT INTO stock.produit(NOM_PRODUIT,PEREMPTION,FORME,TYPE_PRODUIT,PPV,STOCK)VALUES(?,?,?,?,?,?);";
                   		ps=ConnectionBDprod.getConnection().prepareStatement(query);
						
                   		ps.setString(1,l[i].getNom());
                   		ps.setDate(2,(java.sql.Date) d);
                   		ps.setString(3,l[i].getForme());
                   		ps.setString(4,l[i].getType());
                   		ps.setDouble(5, l[i].getPPV());
                   		ps.setInt(6,l[i].getStock());
                   		ps.executeUpdate();
							
                   	
                      
                     }
                   btnEnregistrer.getScene().getWindow().hide();
                   page ll=new page();
      	    	 ll.start(new Stage());
                 }
                  
     }
     @FXML
     void ModifierProduit(MouseEvent event) {
                  int i=TableProd.getSelectionModel().getSelectedIndex();
                  if(i>=0) {
                	  textNom.setText(colonneNom.getCellData(i).toString());
              
                	 
                	  textPPV.setText(String.valueOf(colonnePPV.getCellData(i).doubleValue()));
                	  combType.setValue(colonnePPV.getCellData(i).toString());
                	  combForme.setValue(colonneForme.getCellData(i).toString());
                	  textStock.setText(String.valueOf(colonneStock.getCellData(i).intValue()));
                	 TableProd.getItems().remove(TableProd.getSelectionModel().getSelectedIndex());
                  }
                  if(i<=-1) {
                  	Alert erreur=new Alert(Alert.AlertType.ERROR);
      				erreur.setTitle("Les données");
      				erreur.setContentText("Saisi le produit dans  le table qui modifier");
      				erreur.show();
                  }
     }
    

    

    	 
     }
     

