package application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class venteaffController {
	@FXML
    private Button btnAfficher;
	  @FXML
	    private TableView<venteaffClass> tableVente;


    @FXML
    private TableColumn<venteaffClass, Date> colDate;

    @FXML
    private TableColumn<venteaffClass, Integer> colN;

    @FXML
    private TableColumn<venteaffClass, String> colNom;
    @FXML
    private TableColumn<venteaffClass, String> colType;

    @FXML
    private TableColumn<venteaffClass, Integer> colQuentite;
    @FXML
    private TableColumn<venteaffClass, Date> colDateV;

    @FXML
    private TableColumn<venteaffClass, Time> colHeure;

    @FXML
    private DatePicker Date1;

    @FXML
    private DatePicker Date2;
  

    
    @FXML
    private void initialize() {
   	
   	 
   	 
   	 colNom.setCellValueFactory(new PropertyValueFactory<venteaffClass,String>("nom"));
   	 colDate.setCellValueFactory(new PropertyValueFactory<venteaffClass, Date>("date"));
   	
   	 colN.setCellValueFactory(new PropertyValueFactory<venteaffClass, Integer>("n"));
   	 colQuentite.setCellValueFactory(new PropertyValueFactory<venteaffClass, Integer>("quentite"));
   	colDateV.setCellValueFactory(new PropertyValueFactory<venteaffClass, Date>("dateV"));
   	colHeure.setCellValueFactory(new PropertyValueFactory<venteaffClass, Time>("heure"));
   	colType.setCellValueFactory(new PropertyValueFactory<venteaffClass,String>("Type"));
   	 
   	
    }
   
    @FXML
    private Button btnVider;
    @FXML
    void viderTable(MouseEvent event) {
    	if(tableVente.getItems().size()>0) {
    		
    		        
    	    		while(tableVente.getItems()!=null) {
    	    			if(tableVente.getItems()!=null) {
    	    			tableVente.getItems().remove(0);
    	    			}
    	    		}
    	    			
    	    			
    	    			
    	    			
    	    		
    	}
    }
    @FXML
    void afficherVentes(MouseEvent event) throws SQLException {
    	
    	ArrayList<venteaffClass> list=new ArrayList<venteaffClass>();;

    	

    	if(Date2.getValue()==null&&Date1.getValue()==null) {
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
    	else {
    		if(Date1.getValue()!=null&&Date2.getValue()!=null) {
    			Statement st = null;
            	ResultSet rst = null;
            	st=ConnectionBDprod.getConnection().createStatement();
            	rst=st.executeQuery("SElECT * FROM stock.ligne_vente WHERE PEREMPTION>='"+java.sql.Date.valueOf(Date2.getValue())+"' AND PEREMPTION<='"+java.sql.Date.valueOf(Date1.getValue())+"';");
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
            	list.clear();
    		}
    	}
    }
}
