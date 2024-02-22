package application;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

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
import javafx.stage.Stage;

public class ventesFXContoller {
	@FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSauvegarder;
    
	  @FXML
	    private TableColumn<venteClass,String> colNom;

	    @FXML
	    private TableColumn<venteClass, Integer> colQuentite;

	    @FXML
	    private TableColumn<venteClass,String> colType;

	    @FXML
	    private TableView<venteClass> tableVente;
	   
	    @FXML
	    private TextField textNom_p;

	    @FXML
	    private TextField textQuentite;

	    @FXML
	    private ComboBox<String> textType;
	    @FXML
	    private TableColumn<venteClass,LocalDate> colDate;

	    @FXML
	    private DatePicker date;
	    @FXML
	    private void initialize() {
	    	
	    	
	    	 
	    	colNom.setCellValueFactory(new PropertyValueFactory<venteClass,String>("nom"));
	    	colQuentite.setCellValueFactory(new PropertyValueFactory<venteClass, Integer>("Quentite"));
	    	colType.setCellValueFactory(new PropertyValueFactory<venteClass, String>("type"));
	    	colDate.setCellValueFactory(new PropertyValueFactory<venteClass, LocalDate>("Date"));
	    	textType.getItems().addAll("Espéce","crédit");
	    	
	    	
	    	
	    	 
	    	
	     }
	    @FXML
	   
	    void ModifierVente(MouseEvent event) throws SQLException {
                       int i=tableVente.getSelectionModel().getSelectedIndex();
                       if(i>=0) {
                    	   textNom_p.setText(colNom.getCellData(i).toString());
                    	   textType.setValue(colType.getCellData(i).toString());
                    	   textQuentite.setText(String.valueOf(colQuentite.getCellData(i).intValue()));
                    	   tableVente.getItems().remove(tableVente.getSelectionModel().getSelectedIndex());
                       }
                       if(i<=-1) {
                         	Alert erreur=new Alert(Alert.AlertType.ERROR);
             				erreur.setTitle("Les données");
             				erreur.setContentText("Saisi le ligne dans  le table qui modifier");
             				erreur.show();
                         }
	        		
	        	}

                
	    

	    @FXML
	    void SauvegarderVente(MouseEvent event) throws SQLException {
                 ArrayList<venteClass> k=new ArrayList<venteClass>();
	    	for(int i=0;i<tableVente.getItems().size();i++) {
	    		  k.add(tableVente.getItems().get(i));
	    		
        	}
	    	for(int i=0;i<tableVente.getItems().size();i++) {
	    		
	    		 int n=0; 
	    		Statement st11 = null;
	        	ResultSet rst11 = null;
	        	st11=ConnectionBDprod.getConnection().createStatement();
	        	rst11=st11.executeQuery("select max(NUMERO_VENTE)from stock.vente ;");
	        	if(rst11.next()) {
	        	n=rst11.getInt("max(NUMERO_VENTE)");
	        	String query="INSERT INTO stock.ligne_vente(NUMERO_VENTE,NOM_PRODUIT,PEREMPTION,QUENTITE)VALUES(?,?,?,?);";
	       	 PreparedStatement ps=null;
	       	ps=ConnectionBDprod.getConnection().prepareStatement(query);
	       	ps.setInt(1, n);
	       	ps.setString(2, k.get(i).getNom());
	       	Date m=java.sql.Date.valueOf(k.get(i).getDate());
            ps.setDate(3, m);
	        ps.setInt(4,k.get(i).getQuentite());
	       	ps.executeUpdate();
	    	}
      	
	    	}
	    	btnSauvegarder.getScene().getWindow().hide();
	    	page l=new page();
	    	l.start(new Stage());
        	
	    }
           
	    @FXML
	    
	    void ajouterVente(MouseEvent event) throws SQLException {
	    	int j_commande=0;
	    	
	    	
	    	String nom=textNom_p.getText();
	    	Date l=java.sql.Date.valueOf(date.getValue());;
            int i_produit=0;
            j_commande=0;
            Statement st1 = null;
        	ResultSet rst1 = null;
        	st1=ConnectionBDprod.getConnection().createStatement();
        	rst1=st1.executeQuery("SElECT * FROM stock.produit WHERE NOM_PRODUIT='"+nom+"'AND PEREMPTION='"+l+"';");
        	while(rst1.next()) {
        		int stock=rst1.getInt("STOCK");
        		i_produit=stock;
        	}
        	 Statement st2 = null;
	        	ResultSet rst2 = null;
        	st2=ConnectionBDprod.getConnection().createStatement();
        	rst2=st2.executeQuery("SElECT * FROM stock.ligne_vente WHERE NOM_PRODUIT='"+nom+"'AND PEREMPTION='"+l+"';");
        	while(rst2.next()) {
        		
        		int quentite=rst2.getInt("QUENTITE");
        		int ii=0;
        		while(ii<tableVente.getItems().size()) {
        			venteClass ll=tableVente.getItems().get(ii);
        		j_commande+=ll.getQuentite();
        		ii++;
        		}
        		j_commande+=quentite;
        	}
        	j_commande+=Integer.parseInt(textQuentite.getText());
        	
        	if(i_produit>=j_commande) {
        		if(tableVente.getItems().size()==0) {
   	    		 PreparedStatement ps=null;
   	    		String query="INSERT INTO stock.vente(TYPE,DATE,TIME)VALUES(?,?,?);";
   	    		ps=ConnectionBDprod.getConnection().prepareStatement(query);
   	    		ps.setString(1, textType.getValue());
   	    		ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDateTime.now().toLocalDate()));
   	    		ps.setTime(3, java.sql.Time.valueOf(java.time.LocalDateTime.now().toLocalTime()));
   	    		ps.executeUpdate();
   	    		
   	    	}
        		
        		tableVente.getItems().add(new venteClass(textNom_p.getText(), textType.getValue(),  Integer.parseInt(textQuentite.getText()),date.getValue()));
        	}
        	else {
        		if(i_produit<j_commande) {
        			Alert erreur=new Alert(Alert.AlertType.ERROR);
					erreur.setTitle("produit");
					erreur.setContentText("le produit n'est pas existe dans le stock");
					erreur.show();
        		}

	    }
        	textNom_p.setText("");
        	textQuentite.setText("");
        	textType.setValue(null);
        	date.setValue(null);

	    }  
}
