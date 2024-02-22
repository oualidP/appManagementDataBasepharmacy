package application;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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

public class ventemiseController {
	  @FXML
	    private Button btnModifier;

	    @FXML
	    private Button btnSauvegarder;

	    @FXML
	    private Button btnSupprimer;

	    @FXML
	    private TableColumn<venteaffClass, Date> colDate;

	    @FXML
	    private TableColumn<venteaffClass, Date> colDateV;

	    @FXML
	    private TableColumn<venteaffClass, Time> colHeure;

	    @FXML
	    private TableColumn<venteaffClass, Integer> colN;

	    @FXML
	    private TableColumn<venteaffClass,String> colNom;
	    @FXML
	    private TableColumn<venteaffClass,String> colType;

	    @FXML
	    private TableColumn<venteaffClass, Integer> colQuentite;

	    @FXML
	    private DatePicker date;

	    @FXML
	    private TableView<venteaffClass> tableVente;

	    @FXML
	    private TextField textNom_p;

	    @FXML
	    private TextField textQuentite;

	    @FXML
	    private ComboBox<String> textType;
	    @FXML
	    private void initialize() throws SQLException {
	   	
	   	 
	    	 colType.setCellValueFactory(new PropertyValueFactory<venteaffClass,String>("Type"));
	   	 colNom.setCellValueFactory(new PropertyValueFactory<venteaffClass,String>("nom"));
	   	 colDate.setCellValueFactory(new PropertyValueFactory<venteaffClass, Date>("date"));
	   	
	   	 colN.setCellValueFactory(new PropertyValueFactory<venteaffClass, Integer>("n"));
	   	 colQuentite.setCellValueFactory(new PropertyValueFactory<venteaffClass, Integer>("quentite"));
	   	colDateV.setCellValueFactory(new PropertyValueFactory<venteaffClass, Date>("dateV"));
	   	colHeure.setCellValueFactory(new PropertyValueFactory<venteaffClass, Time>("heure"));
	   	
	   	textType.getItems().addAll("Espéce","crédit");
ArrayList<venteaffClass> list=new ArrayList<venteaffClass>();
Statement st = null;
ResultSet rst = null;
st=ConnectionBDprod.getConnection().createStatement();
rst=st.executeQuery("SElECT * FROM stock.ligne_vente;");

while(rst.next()) {
	int n=rst.getInt("NUMERO_VENTE");
	String nom=rst.getString("NOM_PRODUIT");
	java.sql.Date d=rst.getDate("PEREMPTION");
	int q=rst.getInt("QUENTITE");
	Statement st1 = null;
	ResultSet rst1 = null;
	st1=ConnectionBDprod.getConnection().createStatement();
	rst1=st1.executeQuery("SElECT * FROM stock.vente WHERE NUMERO_VENTE='"+n+"' ;");
	java.sql.Date dVente=null;
	java.sql.Time tVENTE=null;
	String type=null;
	while(rst1.next()) {
		dVente=rst1.getDate("DATE");
		tVENTE=rst1.getTime("Time");
		type=rst1.getString("TYPE");
	}
	venteaffClass j=new venteaffClass(n, nom, d, q,dVente, tVENTE,type);
	
	list.add(j);
	
}
for(int i=0;i<list.size();i++) {
	tableVente.getItems().add((venteaffClass)list.get(i));
}	
    		
    	}
	   	


	    @FXML
	    void SauvegarderVente(MouseEvent event) throws SQLException {
	    	boolean tp=false;
	    	boolean tp1=false;
                if(sup.size()>=1) {
                	String l;
                	System.out.print(sup.size());
                	for(int i=0;i<sup.size();i++) {
                		 PreparedStatement pss=null;
                    	 l="DELETE FROM `stock`.`ligne_vente` WHERE (`NUMERO_VENTE`"
      							+ " = '"+sup.get(i).getN()+"' AND NOM_PRODUIT='"+sup.get(i).getNom()+"'"
									+ "AND PEREMPTION='"+sup.get(i).getDate()+"')";
                    	  pss=ConnectionBDprod.getConnection().prepareStatement(l);
                     		pss.execute();
                     		
                	}
                	tp=true;
                }
                if(modf.size()>=1) {
                	
                	String query = null;
                	String query1;
                	for(int i=0;i<modf.size();i++) {
                   		    PreparedStatement ps=null;
                   		    query="INSERT INTO stock.ligne_vente(NUMERO_VENTE,NOM_PRODUIT,PEREMPTION,QUENTITE)VALUES(?,?,?,?);";
                 	       	ps=ConnectionBDprod.getConnection().prepareStatement(query);
                 	   	ps.setInt(1, modf.get(i).getN());
            	       	ps.setString(2, modf.get(i).getNom());
            	       	
                        ps.setDate(3,modf.get(i).getDate());
            	        ps.setInt(4,modf.get(i).getQuentite());
            	       	ps.executeUpdate();
            	       	PreparedStatement pss=null;
                   	
               		query1="DELETE FROM `stock`.`ligne_VENTE` WHERE (`NUMERO_VENTE`"
    							+ "= '"+modfsup.get(i).getN()+"' AND NOM_PRODUIT='"+modfsup.get(i).getNom()+"'"
    									+ "AND PEREMPTION='"+modfsup.get(i).getDate()+"')";
                  		pss=ConnectionBDprod.getConnection().prepareStatement(query1);
                  		pss.execute();
                  		tp1=true;
            	      
               	}
                }
                
                textNom_p.setText("");
   	    	 textQuentite.setText("");
   	    	 textType.setValue(null);
   	    	 date.setValue(null);
   	    	 if(tp||tp1) {
   	    		 Alert message=new Alert(Alert.AlertType.INFORMATION);
   	    		message.setTitle("Le sauvegradge");
   	    		message.setContentText("Le sauvegradge est valide");
   	    		message.show();
   	    		tp=false;
   	    		tp1=false;
   	    	 }
	    }

	    @FXML
	    void Selection(MouseEvent event) {
	    	int i=tableVente.getSelectionModel().getSelectedIndex();
	    	if(i>=0) {
	    		venteaffClass l=tableVente.getItems().get(i);
	    		textNom_p.setText(l.getNom());
	    		textQuentite.setText(String.valueOf(l.getQuentite()));
	    		textType.setValue(l.getType());
	    		
	    	}
	    }
        ArrayList <venteaffClass>sup=new ArrayList<venteaffClass>();
        ArrayList <venteaffClass>modf=new ArrayList<venteaffClass>();
        ArrayList <venteaffClass>modfsup=new ArrayList<venteaffClass>();
        
	    @FXML
	    void SupprimerVente(MouseEvent event) {
	    	int i=tableVente.getSelectionModel().getSelectedIndex();
	    	 if(i>=0) {
	    		 sup.add(tableVente.getItems().get(i));
	    		 tableVente.getItems().remove(i);
	    		 
	    	 }
	    	 else {
	    		 Alert erreur=new Alert(Alert.AlertType.ERROR);
					erreur.setTitle("erreur");
					erreur.setContentText("Selection un ligne de commande");
					erreur.show();
	    	 }
	    	 textNom_p.setText("");
	    	 textQuentite.setText("");
	    	 textType.setValue(null);
	    }

	    @FXML
	    void modifierVente(MouseEvent event) throws SQLException { 
	    	int i_produit=0;
	    	int j_commande=0;
	    	int i=tableVente.getSelectionModel().getSelectedIndex();
	    	String query;
	    	String query1;
                if(!textNom_p.getText().equals("")&&!textQuentite.getText().equals("")&&textType.getValue()!=null&&date.getValue()!=null) {
                	 Statement pss=null;
                	  ResultSet res=null;
                	 query="SELECT * FROM stock.produit WHERE NOM_PRODUIT='"+textNom_p.getText()+"'AND PEREMPTION='"+Date.valueOf(date.getValue())+"'; ";
                	 
               	  pss=ConnectionBDprod.getConnection().createStatement();
                		res=pss.executeQuery(query) ;
                		if(res.next()) {
                			query1="DELETE FROM `stock`.`stock.ligne_vente` WHERE (`NUMERO_VENTE`"
         							+ " = '"+tableVente.getItems().get(i).getN()+"' AND NOM_PRODUIT='"+tableVente.getItems().get(i).getNom()+"'"
         				+ "AND PEREMPTION='"+tableVente.getItems().get(i).getDate()+"')";
                			PreparedStatement ps=null;
                			ps=ConnectionBDprod.getConnection().prepareStatement(query);
                			if(ps.execute()) {
                				 Statement st1 = null;
                		        	ResultSet rst1 = null;
                		        	st1=ConnectionBDprod.getConnection().createStatement();
                		        	rst1=st1.executeQuery("SElECT * FROM stock.produit WHERE NOM_PRODUIT='"+textNom_p.getText()+"'AND PEREMPTION='"+Date.valueOf(date.getValue())+"';");
                		        	while(rst1.next()) {
                		        		int stock=rst1.getInt("STOCK");
                		        		i_produit=stock;
                		        	}
                		        	 Statement st2 = null;
                			        	ResultSet rst2 = null;
                		        	st2=ConnectionBDprod.getConnection().createStatement();
                		        	rst2=st2.executeQuery("SElECT * FROM stock.ligne_vente WHERE NOM_PRODUIT='"+textNom_p.getText()+"'AND PEREMPTION='"+Date.valueOf(date.getValue())+"';");
                		        	while(rst2.next()) {
                		        		
                		        		int quentite=rst2.getInt("QUENTITE");
                		        		
                		        		j_commande+=quentite;
                		        	}
                		        	j_commande+=Integer.parseInt(textQuentite.getText());
                		        	if(j_commande<=i_produit) {
                		        		modfsup.add(tableVente.getItems().get(i));
                		        		modf.add(new venteaffClass(colN.getCellData(i).intValue(),
                        						textNom_p.getText(), Date.valueOf(date.getValue()),
                        						Integer.parseInt(textQuentite.getText()),
                        						Date.valueOf(colDateV.getCellData(i).toLocalDate()),
                        						Time.valueOf(colHeure.getCellData(i).toLocalTime()), 
                        						textType.getValue()));
                		        	
                		        		tableVente.getItems().add(new venteaffClass(colN.getCellData(i).intValue(),
                        						textNom_p.getText(), Date.valueOf(date.getValue()),
                        						Integer.parseInt(textQuentite.getText()),
                        						Date.valueOf(colDateV.getCellData(i).toLocalDate()),
                        						Time.valueOf(colHeure.getCellData(i).toLocalTime()), 
                        						textType.getValue()));
                		        		
                		        		tableVente.getItems().remove(i);
                		        	}
                		        	else {
                		        		Alert erreur=new Alert(Alert.AlertType.ERROR);
                    					erreur.setTitle("erreur");
                    					erreur.setContentText("le produit n'a pas existe dans le stock");
                    					erreur.show();
                		        	}
                		        	
                				
                				
                			}
                			else {
                				Alert erreur=new Alert(Alert.AlertType.ERROR);
            					erreur.setTitle("erreur");
            					erreur.setContentText("le modification est echoué");
            					erreur.show();
                			}
                			
                			
                		}
                		else {
                			Alert erreur=new Alert(Alert.AlertType.ERROR);
        					erreur.setTitle("erreur");
        					erreur.setContentText("le produit n'a pas exister dans le stock");
        					erreur.show();
                		}
                }
                else {
                	Alert erreur=new Alert(Alert.AlertType.ERROR);
					erreur.setTitle("erreur");
					erreur.setContentText("ajouter les données de commande que tu veux modifier");
					erreur.show();
                }
                
                textNom_p.setText("");
   	    	 textQuentite.setText("");
   	    	 textType.setValue(null);
   	    	 date.setValue(null);
   	    	 
	    }
}
