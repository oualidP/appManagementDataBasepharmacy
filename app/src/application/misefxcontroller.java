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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class misefxcontroller {
	

	    @FXML
	    private Button btnSauvegarder;

	    @FXML
	    private Button btnmis;
	    @FXML
	    private ComboBox<String> combForme;

	    @FXML
	    private ComboBox<String> combRaison;

	    @FXML
	    private ComboBox<String> combType;

	    @FXML
	    private DatePicker dateCommande;

	    @FXML
	    private DatePicker dateUtilisation;

	   

	    @FXML
	    private TextField textPPV;

	    @FXML
	    private TextField textStock;

	    @FXML
	    private TextField textproduit;
	    @FXML
	    private Button btnsupprimer;
	    @FXML
	    private TableView<affCommande> tableAfficher;
	    public TableView<affCommande> getTableAfficher() {
			return tableAfficher;
		}
	    public void setTableAfficher(TableView<affCommande> tableAfficher) {
			this.tableAfficher = tableAfficher;
		}

	    @FXML
	    private TableColumn<affCommande, Date> colDate;

	    @FXML
	    private TableColumn<affCommande, String> colForme;

	    @FXML
	    private TableColumn<affCommande, String> colNom;

	    @FXML
	    private TableColumn<affCommande, Double> colPPV;

	    @FXML
	    private TableColumn<affCommande, String> colRaison;

	    @FXML
	    private TableColumn<affCommande, Integer> colStock;

	    @FXML
	    private TableColumn<affCommande, String> colType;

	    @FXML
	    private TableColumn<affCommande, Date> colUtilisation;

	    @FXML
	    private TableColumn<affCommande, Integer> colnumero;
	    @FXML
	     private void initialize() throws SQLException {
	    	
	    	
	    	 Statement st = null;
	        	ResultSet rst = null;
	        	st=ConnectionBDprod.getConnection().createStatement();
	        	rst=st.executeQuery("SElECT * FROM stock.fournisseur;");
	        	while(rst.next()) {
	        		combRaison.getItems().add(rst.getString("REASEAU_SOCIAL"));
	        	}
                     combRaison.setVisibleRowCount(3);
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
	        	
	        	 colNom.setCellValueFactory(new PropertyValueFactory<affCommande,String>("Nom"));
	    	   	 colUtilisation.setCellValueFactory(new PropertyValueFactory<affCommande,Date>("peremption"));
	    	   	 colType.setCellValueFactory(new PropertyValueFactory<affCommande,String>("Type"));
	    	   	 colPPV.setCellValueFactory(new PropertyValueFactory<affCommande,Double>("PPV"));
	    	   	 colForme.setCellValueFactory(new PropertyValueFactory<affCommande,String>("Forme"));
	    	   	 colStock.setCellValueFactory(new PropertyValueFactory<affCommande,Integer>("stock"));
	    	   	 colnumero.setCellValueFactory(new PropertyValueFactory<affCommande,Integer>("n"));
	    	   	 colRaison.setCellValueFactory(new PropertyValueFactory<affCommande,String>("Raison_S"));
	    	   	colDate.setCellValueFactory(new PropertyValueFactory<affCommande,Date>("Date_commande"));
	    	   	
	    	   	
	    	   	Statement stt = null;
	        	ResultSet rstt = null;
	        	stt=ConnectionBDprod.getConnection().createStatement();
	        	rstt=stt.executeQuery("SElECT * FROM stock.commande;");
	        	ArrayList<affCommande> list=new ArrayList<affCommande>();
	        	
	    			while(rstt.next()) {
	    				int a=rstt.getInt("NUMERO_COMMANDE");
	    				Date b=rstt.getDate("DATE_COMMANDE");
	    				
	    				String r=rstt.getString("REASEAU_SOCIAL");
	    				Statement stt1 = null;
	    	        	ResultSet rstt1 = null;
	    	        	stt1=ConnectionBDprod.getConnection().createStatement();
	    	        	rstt1=stt1.executeQuery("SElECT * FROM stock.ligne_commande WHERE NUMERO_COMMANDE='"+a+"';");
	    	        	while(rstt1.next()) {
	    	        		String k1=rstt1.getString("NOM_PRODUIT");
	    	        		Date k2=rstt1.getDate("PEREMPTION");
	    	        		Statement stt2 = null;
	        	        	ResultSet rstt2 = null;
	        	        	stt2=ConnectionBDprod.getConnection().createStatement();
	        	        	rstt2=stt2.executeQuery("SElECT * FROM stock.produit WHERE NOM_PRODUIT='"+k1+"' AND PEREMPTION='"+k2+"';");
	        	        	while(rstt2.next()) {
	        				String ll=rstt2.getString("FORME");
	        				String d=rstt2.getString("TYPE_PRODUIT");
	        				Double nmb=rstt2.getDouble("PPV");
	        				int l=rstt2.getInt("STOCK");
	        				affCommande l3=new affCommande(a,k1, nmb, d, ll, l, k2, r, b);
	        				list.add(l3);
	        
	        	        	}
	    	        	}
	    			}
	    			for(int i=0;i<list.size();i++) {
		        		tableAfficher.getItems().add((affCommande) list.get(i));
		        		
		        	}
	    			int i=tableAfficher.getSelectionModel().getSelectedIndex();
	   	    	 if(i>=0) {
	   	    		 affCommande l=tableAfficher.getItems().get(i);
	   	    		 combForme.setValue(l.getForme());
	   	    		 combRaison.setValue(l.getRaison_S());
	   	    		 combType.setValue(l.getType());
	   	    		 textPPV.setText(String.valueOf(l.getPPV()));
	   	    		 textStock.setText(String.valueOf(l.getStock()));
	   	    		 textproduit.setText(l.getNom());
	   	    		 
	   	    		 
	   	    	 }
	     }

	    ArrayList <affCommande>modf=new ArrayList<affCommande>();
	    ArrayList <affCommande>supri=new ArrayList<affCommande>();
	    @FXML
	   
	    void Sauvegarder(MouseEvent event) throws SQLException {
	    	String query=null;
	    	String query1=null;
	    	String query2=null;
	    	String query3=null;
	    	boolean tp=false;
	    	
                    if(supri.size()>=1) {
                    	 PreparedStatement ps=null;
                    	 PreparedStatement ps1=null;
     					for(int i=0;i<supri.size();i++) {
     						int j=0;
     					query="DELETE FROM `stock`.`ligne_commande` WHERE (`NUMERO_COMMANDE`"
     							+ " = '"+supri.get(i).getN()+"' AND NOM_PRODUIT='"+supri.get(i).getNom()+"'"
     									+ "AND PEREMPTION='"+supri.get(i).getPeremption()+"')";
                   		ps=ConnectionBDprod.getConnection().prepareStatement(query);
                   		ps.execute();
                   		query1="DELETE FROM `stock`.`produit` WHERE ( `NOM_PRODUIT`='"+supri.get(i).getNom()+"'"
     									+ "AND PEREMPTION='"+supri.get(i).getPeremption()+"')";
                   		ps1=ConnectionBDprod.getConnection().prepareStatement(query1);
                   		ps1.execute();
                   		supri.remove(i);
                   		
                   	
                  
                   	 
     					}
     					Alert erreur=new Alert(Alert.AlertType.INFORMATION);
     					erreur.setTitle("Sauvegardage");
     					erreur.setContentText("les donnée est sauvegrader");
     					erreur.show();
     					tp=true;
                    }
                    if(modf.size()>=1) {
                    	for(int i=0;i<modf.size();i++) {
                    		 PreparedStatement pss=null;
                        	 PreparedStatement pss1=null;
                        	 PreparedStatement ps2=null;
                    		query="DELETE FROM `stock`.`ligne_commande` WHERE (`NUMERO_COMMANDE`"
         							+ " = '"+modf.get(i).getN()+"' AND NOM_PRODUIT='"+modf.get(i).getNom()+"'"
         									+ "AND PEREMPTION='"+modf.get(i).getPeremption()+"')";
                       		pss=ConnectionBDprod.getConnection().prepareStatement(query);
                       		pss.execute();
                       		
                       		query1="DELETE FROM `stock`.`produit` WHERE ( `NOM_PRODUIT`='"+modf.get(i).getNom()+"'"
         									+ "AND PEREMPTION='"+modf.get(i).getPeremption()+"')";
                       		pss1=ConnectionBDprod.getConnection().prepareStatement(query1);
                       		pss1.execute();
                       		
                       		query="INSERT INTO stock.produit(NOM_PRODUIT,PEREMPTION,FORME,TYPE_PRODUIT,PPV,STOCK)VALUES(?,?,?,?,?,?);";
                       		ps2=ConnectionBDprod.getConnection().prepareStatement(query);
    						
                       		ps2.setString(1,modf.get(i).getNom());
                       		ps2.setDate(2,modf.get(i).getPeremption());
                       		ps2.setString(3,modf.get(i).getForme());
                       		ps2.setString(4,modf.get(i).getType());
                       		ps2.setDouble(5, modf.get(i).getPPV());
                       		ps2.setInt(6,modf.get(i).getStock());
                       		ps2.executeUpdate();
                       		
                       	 PreparedStatement ps3=null;
                       		String query4="INSERT INTO stock.ligne_commande(NUMERO_COMMANDE,NOM_PRODUIT,PEREMPTION)VALUES(?,?,?)";
                			ps3=ConnectionBDprod.getConnectionCommande().prepareStatement(query4);
                			 ps3.setInt(1,modf.get(i).getN());
                			 ps3.setString(2, modf.get(i).getNom());
                			 ps3.setDate(3,modf.get(i).getPeremption());
                			 ps3.executeUpdate();
                			 modf.remove(i);
                       		
                        }
                    	if(!tp) {
                    		Alert erreur=new Alert(Alert.AlertType.INFORMATION);
         					erreur.setTitle("Sauvegardage");
         					erreur.setContentText("les donnée est sauvegrader");
         					erreur.show();
                    	}
                    }
                    
                    
                    }
                    
	    
	   
	    @FXML
	    
	    void modifier(MouseEvent event) {
	    	 int i=tableAfficher.getSelectionModel().getSelectedIndex();
	    	 if(i>=0&& !textStock.getText().equals("")) {
	    		 
	    		 affCommande l=new affCommande(colnumero.getCellData(i).intValue()
	    				 ,
	    				textproduit.getText(),
	    				Double.parseDouble(textPPV.getText()), 
	    				 combType.getValue(), 
	    				 combForme.getValue(), 
	    				 Integer.parseInt(textStock.getText()),
	    				Date.valueOf(dateUtilisation.getValue()),
	    				combRaison.getValue(),
	    				Date.valueOf(colDate.getCellData(i).toLocalDate()) );
	    		 
	    		 tableAfficher.getItems().remove(i);
	    		 tableAfficher.getItems().add(i, l);
	    		 modf.add(l);
	    	 }
	    	 else {
	    		 Alert erreur=new Alert(Alert.AlertType.ERROR);
					erreur.setTitle("erreur");
					erreur.setContentText("Selection un ligne de commande");
					erreur.show();
	    	 }
	    	 textproduit.setText("");
	    	 textPPV.setText("");
	    	 combType.setValue(null);
	    	 textStock.setText("");
	    	 dateUtilisation.setValue(null);
	    	 combRaison.setValue(null);
	    	 dateCommande.setValue(null);
	    	 dateUtilisation.setValue(null);
	    	 
	    }
	    @FXML
	    void supprimer(MouseEvent event) {
	    	 int i=tableAfficher.getSelectionModel().getSelectedIndex();
	    	 if(i>=0&& !textStock.getText().equals("")) {
	    		 
	    		 affCommande l=new affCommande(colnumero.getCellData(i).intValue()
	    				 ,
	    				textproduit.getText(),
	    				Double.parseDouble(textPPV.getText()), 
	    				 combType.getValue(), 
	    				 combForme.getValue(), 
	    				 Integer.parseInt(textStock.getText()),
	    				Date.valueOf(colUtilisation.getCellData(i).toLocalDate()),
	    				combRaison.getValue(),
	    				Date.valueOf(colDate.getCellData(i).toLocalDate()) );
	    		 
	    		 tableAfficher.getItems().remove(i);
	    		 supri.add(l);
	    	 }
	    	 else {
	    		 Alert erreur=new Alert(Alert.AlertType.ERROR);
					erreur.setTitle("erreur");
					erreur.setContentText("Selection un ligne de commande");
					erreur.show();
	    	 }
	    	 textproduit.setText("");
	    	 textPPV.setText("");
	    	 combType.setValue(null);
	    	 textStock.setText("");
	    	 dateUtilisation.setValue(null);
	    	 combRaison.setValue(null);
	    	 dateCommande.setValue(null);
	    	 dateUtilisation.setValue(null);
	    }
	    @FXML
	    void selection(MouseEvent event) {
	    	int i=tableAfficher.getSelectionModel().getSelectedIndex();
  	    	 if(i>=0) {
  	    		 affCommande l=tableAfficher.getItems().get(i);
  	    		 combForme.setValue(l.getForme());
  	    		 combRaison.setValue(l.getRaison_S());
  	    		 combType.setValue(l.getType());
  	    		 textPPV.setText(String.valueOf(l.getPPV()));
  	    		 textStock.setText(String.valueOf(l.getStock()));
  	    		 textproduit.setText(l.getNom());
  	    		 
  	    		 
  	    	 }
	    }

}
