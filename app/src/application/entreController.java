package application;


import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class entreController {
	  @FXML
	    private MenuItem MenuAffichC;

	    @FXML
	    private MenuItem MenuAfficheV;
	    @FXML
	    private MenuItem itemMis;


	    @FXML
	    private MenuItem MenuAjouter;

	    @FXML
	    private Menu MenuCommande;

	    @FXML
	    private MenuItem MenuFournisseur;

	    @FXML
	    private MenuItem MenuLigne;

	    @FXML
	    private MenuItem MenuStock;

	    @FXML
	    private Menu MenuVente;
	    @FXML
	    private AnchorPane Pane;

	    @FXML
	    
	    private Button btnSauvegarder;
	    @FXML
	    private MenuItem MenuventeM;

	    @FXML
	    private ComboBox<String> combType;

	    @FXML
	    private DatePicker date;

	    @FXML
	    private MenuBar mb;
	    @FXML
	    private void initialize() {
	    	
	    	
	    	 
	    	
	    	combType.getItems().addAll("Espéce","crédit");
	    	
	    	
	    	
	    	 
	    	
	     }

	    @FXML
	    private Menu menuProduit;

	    @FXML
	    private TextField textNom;

	    @FXML
	    private TextField textQentité;
	    @FXML
	    void AjouterProduit(ActionEvent event) {
	    	mb.getScene().getWindow().hide();
	    	produit l=new produit();
	    	l.start(new Stage());

	    }
	    @FXML
	    void afficheStock(ActionEvent event) {
	    	mb.getScene().getWindow().hide();
           Stock l=new Stock();
           l.start(new Stage());
	    }
	    @FXML
	    void Ajouterfournisseur(ActionEvent event) {
	    	mb.getScene().getWindow().hide();
              fournisseur l=new fournisseur();
              l.start(new Stage());
	    }
	    @FXML
	    void AjouterCommande(ActionEvent event) {
	    	mb.getScene().getWindow().hide();
               commande l=new commande();
               l.start(new Stage());
	    }
	    @FXML
	    void affichercommande(ActionEvent event) {
	    	mb.getScene().getWindow().hide();
              commande_afficher l=new commande_afficher();
              l.start(new Stage());
	    }
	    @FXML
	    void AjouterVente(ActionEvent event) {
	    	mb.getScene().getWindow().hide();
              ventes l=new ventes();
              l.start(new Stage());
	    }
	    @FXML
	    
	    void AfficheVente(ActionEvent event) {
	    	mb.getScene().getWindow().hide();
              affVentes l=new affVentes();
              l.start(new Stage());
	    }
	    @FXML
	    private ImageView image;
	    
	    @FXML
	    void clic(MouseEvent event) {
	    	/*
           ContextMenu cm=new ContextMenu();
           MenuItem mt=new MenuItem("aaaaaaa");
           cm.getItems().add(mt);
           File f=new File("src/image(2).jpg");
           Image i=new Image(f.toURI().toString());
           image=new ImageView(i);
          
          image.setOnContextMenuRequested(

			
			
				
				Event->cm.show(image,event.getSceneX(),event.getScreenY())
		
		);
		*/
			
		
	    }
	    @FXML
	    void Sauvegarder(MouseEvent event) throws NumberFormatException, SQLException {
int j_commande=0;
	    	
	    	
	    	String nom=textNom.getText();
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
        		
        		
        			
        		
        		
        		j_commande+=quentite;
        	}
        	
        		
        	
        	j_commande+=Integer.parseInt(textQentité.getText());
        	
        	if(i_produit>=j_commande) {
        		Statement st12 = null;
	        	ResultSet rst12 = null;
	        	
        		 PreparedStatement ps=null;
    	    		String query="INSERT INTO stock.vente(TYPE,DATE,TIME)VALUES(?,?,?);";
    	    		ps=ConnectionBDprod.getConnection().prepareStatement(query);
    	    		ps.setString(1, combType.getValue());
    	    		ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDateTime.now().toLocalDate()));
    	    		ps.setTime(3, java.sql.Time.valueOf(java.time.LocalDateTime.now().toLocalTime()));
    	    		ps.executeUpdate();
    	    		st12=ConnectionBDprod.getConnection().createStatement();
    	        	rst12=st12.executeQuery("select max(NUMERO_VENTE)from stock.vente ;");
    	        	int n=0;
    	        	if(rst12.next()) {
    	        	n=rst12.getInt("max(NUMERO_VENTE)");}
        		String queryII="INSERT INTO stock.ligne_vente(NUMERO_VENTE,NOM_PRODUIT,PEREMPTION,QUENTITE)VALUES(?,?,?,?);";
        		
              	 PreparedStatement ps1=null;
              	ps1=ConnectionBDprod.getConnection().prepareStatement(queryII);
                ps1.setInt(1, n);
              	ps1.setString(2,textNom.getText());
              	Date m=java.sql.Date.valueOf(date.getValue());
               ps1.setDate(3, m);
               ps1.setInt(4,Integer.parseInt(textQentité.getText()));
              	
           	ps1.executeUpdate();
           		textNom.setText("");
           		date.setValue(null);
           		combType.setValue(null);
           		textQentité.setText(null);
          
        	}
        	else {
        		Alert erreur=new Alert(Alert.AlertType.ERROR);
				erreur.setTitle("produit");
				erreur.setContentText("le produit n'est pas existe dans le stock");
				erreur.show();
        	}
        	
        	
        	
	    }

	    @FXML
	    void misAjour(ActionEvent event) {
	    	mb.getScene().getWindow().hide();
           misAjourcommande l=new misAjourcommande();
            l.start(new Stage());
	    }
	    @FXML
	    void MenumisVente(ActionEvent event) {
              mb.getScene().getWindow().hide();
              misAjourVente l=new misAjourVente();
              l.start(new Stage());
	    }



	    }

