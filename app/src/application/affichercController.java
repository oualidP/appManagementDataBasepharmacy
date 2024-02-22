package application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class affichercController {
	  @FXML
	    private DatePicker date1;
	  @FXML
	    private Button btnVide;

	    @FXML
	    private DatePicker date2;
	 @FXML
	    private Button btnAfficher;
	 @FXML
	    private TableView<affCommande> tableAfficher;

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
	    private void initialize() {
	   	
	   	 
	   	 
	   	 colNom.setCellValueFactory(new PropertyValueFactory<affCommande,String>("Nom"));
	   	 colUtilisation.setCellValueFactory(new PropertyValueFactory<affCommande,Date>("peremption"));
	   	 colType.setCellValueFactory(new PropertyValueFactory<affCommande,String>("Type"));
	   	 colPPV.setCellValueFactory(new PropertyValueFactory<affCommande,Double>("PPV"));
	   	 colForme.setCellValueFactory(new PropertyValueFactory<affCommande,String>("Forme"));
	   	 colStock.setCellValueFactory(new PropertyValueFactory<affCommande,Integer>("stock"));
	   	 colnumero.setCellValueFactory(new PropertyValueFactory<affCommande,Integer>("n"));
	   	 colRaison.setCellValueFactory(new PropertyValueFactory<affCommande,String>("Raison_S"));
	   	colDate.setCellValueFactory(new PropertyValueFactory<affCommande,Date>("Date_commande"));
	   	 
	   	
	    }

	    @FXML
	    void afficher(MouseEvent event) throws SQLException {
	    	ArrayList list;
	    	if(date1.getValue()==null&&date2.getValue()==null) {
	    		Statement st = null;
	        	ResultSet rst = null;
	        	st=ConnectionBDprod.getConnection().createStatement();
	        	rst=st.executeQuery("SElECT * FROM stock.commande;");
	        	list=new ArrayList<affCommande>();
	        	
	    			while(rst.next()) {
	    				int a=rst.getInt("NUMERO_COMMANDE");
	    				Date b=rst.getDate("DATE_COMMANDE");
	    				
	    				String r=rst.getString("REASEAU_SOCIAL");
	    				Statement st1 = null;
	    	        	ResultSet rst1 = null;
	    	        	st1=ConnectionBDprod.getConnection().createStatement();
	    	        	rst1=st1.executeQuery("SElECT * FROM stock.ligne_commande WHERE NUMERO_COMMANDE='"+a+"';");
	    	        	while(rst1.next()) {
	    	        		String k1=rst1.getString("NOM_PRODUIT");
	    	        		Date k2=rst1.getDate("PEREMPTION");
	    	        		Statement st2 = null;
	        	        	ResultSet rst2 = null;
	        	        	st2=ConnectionBDprod.getConnection().createStatement();
	        	        	rst2=st2.executeQuery("SElECT * FROM stock.produit WHERE NOM_PRODUIT='"+k1+"' AND PEREMPTION='"+k2+"';");
	        	        	while(rst2.next()) {
	        				String ll=rst2.getString("FORME");
	        				String d=rst2.getString("TYPE_PRODUIT");
	        				Double nmb=rst2.getDouble("PPV");
	        				int l=rst2.getInt("STOCK");
	        				affCommande l3=new affCommande(a,k1, nmb, d, ll, l, k2, r, b);
	        				list.add(l3);
	        				}
	        				
	        				}
	        	        	
	    	        	}
	    	        	
	    				
	    			
	      
	    			
	    			for(int i=0;i<list.size();i++) {
		        		tableAfficher.getItems().add((affCommande) list.get(i));
		        		
		        	}
	    	}
	    	else {
	    		Statement st = null;
	        	ResultSet rst = null;
	        	st=ConnectionBDprod.getConnection().createStatement();
	        	Date d1=java.sql.Date.valueOf(date2.getValue());
	        	Date d2=java.sql.Date.valueOf(date1.getValue());
	        	rst=st.executeQuery("SElECT * FROM stock.commande WHERE DATE_COMMANDE<='"+d2+"'"
	        			
	        			+"AND DATE_COMMANDE>'"+d1+"'" +";");
	        	list=new ArrayList<affCommande>();
	        	
	    			while(rst.next()) {
	    				int a=rst.getInt("NUMERO_COMMANDE");
	    				Date b=rst.getDate("DATE_COMMANDE");
	    				
	    				String r=rst.getString("REASEAU_SOCIAL");
	    				Statement st1 = null;
	    	        	ResultSet rst1 = null;
	    	        	st1=ConnectionBDprod.getConnection().createStatement();
	    	        	
	    	        	rst1=st1.executeQuery("SElECT * FROM stock.ligne_commande WHERE NUMERO_COMMANDE='"+a+"';");
	    	        	while(rst1.next()) {
	    	        		String k1=rst1.getString("NOM_PRODUIT");
	    	        		Date k2=rst1.getDate("PEREMPTION");
	    	        		Statement st2 = null;
	        	        	ResultSet rst2 = null;
	        	        	st2=ConnectionBDprod.getConnection().createStatement();
	        	        	rst2=st2.executeQuery("SElECT * FROM stock.produit WHERE NOM_PRODUIT='"+k1+"' AND PEREMPTION='"+k2+"';");
	        	        	while(rst2.next()) {
	        				String ll=rst2.getString("FORME");
	        				String d=rst2.getString("TYPE_PRODUIT");
	        				Double nmb=rst2.getDouble("PPV");
	        				int l=rst2.getInt("STOCK");
	        				affCommande l3=new affCommande(a,k1, nmb, d, ll, l, k2, r, b);
	        				list.add(l3);}
	        				
	        				}
	        	        	
	    	        	}
	    	        	
	    				
	    			
	      
	    			
	    			for(int i=0;i<list.size();i++) {
		        		tableAfficher.getItems().add((affCommande) list.get(i));
		        		
		        	}
	    			list.clear();
	    		
	    	}
	    	
	    	
    			
	    }
	    @FXML
	    void ViderTable(MouseEvent event) {
	    	if(tableAfficher.getItems().size()>0) {
	    		
		        
	    		while(tableAfficher.getItems()!=null) {
	    			if(tableAfficher.getItems()!=null) {
	    			tableAfficher.getItems().remove(0);
	    			}
	    		}
	    			
	    			
	    			
	    			
	    		
	}
	    }

}
