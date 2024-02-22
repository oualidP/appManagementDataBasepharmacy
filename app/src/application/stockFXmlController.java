package application;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class stockFXmlController {
	@FXML
    private Button btnStock;

    @FXML
    private TableColumn<tableProduit, Date> colDate;

    @FXML
    private TableColumn<tableProduit, String> colForme;

    @FXML
    private TableColumn<tableProduit, String> colNom;

    @FXML
    private TableColumn<tableProduit, Double> colPPV;

    @FXML
    private TableColumn<tableProduit, Integer> colStock;

    @FXML
    private TableColumn<tableProduit,String> colType;

    @FXML
    private TableView<tableProduit> tableStock;
    @FXML
    private AnchorPane PaneBack;

    @FXML
    private void initialize() {
   	
   	 
   	 
   	 colNom.setCellValueFactory(new PropertyValueFactory<tableProduit,String>("Nom"));
   	 colDate.setCellValueFactory(new PropertyValueFactory<tableProduit,Date>("peremption"));
   	 colType.setCellValueFactory(new PropertyValueFactory<tableProduit,String>("Type"));
   	 colPPV.setCellValueFactory(new PropertyValueFactory<tableProduit,Double>("PPV"));
   	 colForme.setCellValueFactory(new PropertyValueFactory<tableProduit,String>("Forme"));
   	 colStock.setCellValueFactory(new PropertyValueFactory<tableProduit,Integer>("stock"));
   	 
   	
    }

    @FXML
    void affichStock(MouseEvent event) {
    	
    		Statement st = null;
        	ResultSet rst = null;
        	int i=0;
        	
        	try {
    			st=ConnectionBDLogin.getConnection().createStatement();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	try {
    			rst=st.executeQuery("SElECT * FROM stock.produit;");
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	ArrayList <tableProduit>list=new ArrayList<tableProduit>();
        	try {
    			while(rst.next()) {
    				String a=rst.getString("NOM_PRODUIT");
    				java.sql.Date b=rst.getDate("PEREMPTION");
    				int l=rst.getInt("STOCK");
    				
    				 LocalDate k=b.toLocalDate();
    				String r=rst.getString("FORME");
    				String d=rst.getString("TYPE_PRODUIT");
    				Double nmb=rst.getDouble("PPV");
    				
    				
    				
    				
    				int stock=0;
    				Statement st1 = null;
    	        	ResultSet rst1= null;
    	        	st1=ConnectionBDLogin.getConnection().createStatement();
    	        	rst1=st1.executeQuery("SELECT * FROM stock.ligne_vente WHERE NOM_PRODUIT='"+a+"' AND PEREMPTION='"+b+"';");
    	        	while(rst1.next()) {
    	        		stock=rst1.getInt("QUENTITE");
    	        		i+=stock;
    	        	}
    	        	if(l>stock) {
    	        		int h=l-stock;
    	        		tableProduit m=new tableProduit(a,k,nmb,d,r,h);
    	        		list.add(m);
    	        	}
    	        	
    				
    				
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			 System.out.println(e.getMessage());
    			e.printStackTrace();
    		}
        	
        	
        	for(int j=0;j<list.size();j++) {
        		tableStock.getItems().add(list.get(j));
        	}
        	btnStock.setDisable(true);
    	

    }
	
	
}
