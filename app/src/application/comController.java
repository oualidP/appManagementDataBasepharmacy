package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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

public class comController {
	 

	@FXML
    private TableView<commandeclass> tablecommande;
  @FXML
    private TableColumn<commandeclass, Double> ColPPV;

    @FXML
    private TableColumn<commandeclass, LocalDate> ColPeremption;

    @FXML
    private TableColumn<commandeclass,String> ColProduit;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnSauvegarder;

    @FXML
    private Button btnmodifier;
    @FXML
    private ComboBox<String> combRaison;

    @FXML
    private TableColumn<commandeclass, LocalDate> colDate;

    @FXML
    private TableColumn<commandeclass, String> colForme;

    @FXML
    private TableColumn<commandeclass, String> colRaison;

    @FXML
    private TableColumn<commandeclass, Integer> colStock;

    @FXML
    private TableColumn<commandeclass, String> colType;

    @FXML
    private ComboBox<String> combForme;

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
	private Statement st1;
    @FXML
     private void initialize() {
    	
    	 combForme.getItems().addAll("Comprime","Sirop","Hypnotique","Cpantibiotique","Spantibiotique");
    	 combType.getItems().addAll("Médicaments","Dietétique","Préparations","Vétérinaire","Homéopathie");
    	 combRaison.getItems().addAll("Sociate1","Sociate2","Sociate3","Sociate4");
    	 
    	 ColProduit.setCellValueFactory(new PropertyValueFactory<commandeclass,String>("Nom"));
    	 ColPeremption.setCellValueFactory(new PropertyValueFactory<commandeclass,LocalDate>("peremption"));
    	 colType.setCellValueFactory(new PropertyValueFactory<commandeclass,String>("Type"));
    	 ColPPV.setCellValueFactory(new PropertyValueFactory<commandeclass,Double>("PPV"));
    	 colForme.setCellValueFactory(new PropertyValueFactory<commandeclass,String>("Forme"));
    	 colStock.setCellValueFactory(new PropertyValueFactory<commandeclass,Integer>("stock"));
    	 colRaison.setCellValueFactory(new PropertyValueFactory<commandeclass,String>("Raison_S"));
    	 colDate.setCellValueFactory(new PropertyValueFactory<commandeclass,LocalDate>("Date_commande"));
    	
    	 
    	
     }

    @FXML
    void Ajouter(MouseEvent event) {
    	if(!textproduit.getText().equals("")&&combType.getValue()!=null&&combType.getValue()!=null&&!textPPV.equals(null)&&dateUtilisation.getValue()!=null&&combRaison.getValue()!=null&&dateCommande.getValue()!=null) {
    		tablecommande.getItems().add(new commandeclass(
        			 textproduit.getText(),
        			Double.parseDouble(textPPV.getText()),
        			combType.getValue(),
        			 combForme.getValue(),
        			 Integer.parseInt(textStock.getText()),
        			dateUtilisation.getValue(),
        			 combRaison.getValue(),
 		    				dateCommande.getValue()
 		    				)
    				
    				);
        	 textproduit.setText("");
        	 dateUtilisation.setValue(null);
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
    	dateCommande.setDisable(true);
    	combRaison.setDisable(true);
    }

    @FXML
    void Modifier(MouseEvent event) {
    	 int i=tablecommande.getSelectionModel().getSelectedIndex();
         if(i>=0) {
        	 textproduit.setText(ColProduit.getCellData(i).toString());
       	  textPPV.setText(String.valueOf(ColPPV.getCellData(i).doubleValue()));
       	  combType.setValue(colType.getCellData(i).toString());
       	  combForme.setValue(colForme.getCellData(i).toString());
       	  textStock.setText(String.valueOf(colStock.getCellData(i).intValue()));
       	 tablecommande.getItems().remove(tablecommande.getSelectionModel().getSelectedIndex());
       	combRaison.setValue(colRaison.getCellData(i).toString());
         }
         if(i<=-1) {
         	Alert erreur=new Alert(Alert.AlertType.ERROR);
				erreur.setTitle("Les données");
				erreur.setContentText("Saisi le produit dans  le table qui modifier");
				erreur.show();
         }
    }

    @FXML
    void Sauvegarder(MouseEvent event) {
    	 Date d;int g=0;
    	 int gl[]=new int[2];
    	 PreparedStatement ps11=null;
    	 if(tablecommande.getItems().size()>=1) {
        	 commandeclass l[]=new commandeclass [tablecommande.getItems().size()];
             for(int i=0;i<tablecommande.getItems().size();i++) {
           	  l[i]=tablecommande.getItems().get(i);
           	  }
             int i=0;
             if(i==0) {
            	 for(i=0;i<tablecommande.getItems().size();i++) {
            	 String query11="INSERT INTO stock.produit(NOM_PRODUIT,PEREMPTION,FORME,TYPE_PRODUIT,PPV,STOCK)VALUES(?,?,?,?,?,?);";
            		try {
            			
            			ps11=ConnectionBDprod.getConnection().prepareStatement(query11);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
            		try {
            			
            			d=java.sql.Date.valueOf(l[i].getPeremption());
						ps11.setString(1,l[i].getNom());
						ps11.setDate(2,(java.sql.Date) d);
                   		ps11.setString(3,l[i].getForme());
                   		ps11.setString(4,l[i].getType());
                   		ps11.setDouble(5, l[i].getPPV());
                   		ps11.setInt(6,l[i].getStock());
                   		ps11.executeUpdate();
                   		
					} catch (SQLException e) {
						e.printStackTrace();
					}
            	 }
         			PreparedStatement pss=null;
               		
               		String query1="INSERT INTO stock.commande(REASEAU_SOCIAL,DATE_COMMANDE)VALUES(?,?)";;
               		try {
						pss=ConnectionBDprod.getConnectionCommande().prepareStatement(query1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
               		try {
               			
               			d=java.sql.Date.valueOf(l[0].getDate_commande());
						pss.setString(1, l[0].getRaison_S());
						pss.setDate(2, (java.sql.Date) d);
						pss.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
               	 Date ll=java.sql.Date.valueOf(l[0].getDate_commande());
          		String query2="SElECT * FROM stock.commande WHERE REASEAU_SOCIAL="+"'"+l[0].getRaison_S()+"'"+" AND DATE_COMMANDE="+"'"+(java.sql.Date) ll+"'"+" ;";
          		
          		st1 = null;
                    	ResultSet rst1 = null;
                    	try {
							st1=ConnectionBDLogin.getConnection().createStatement();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
      			try {
							rst1=st1.executeQuery(query2);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
      			
      			try {
							if(rst1.next()) {
							gl[0]=rst1.getInt("NUMERO_COMMANDE");
  }
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
            		
             }
             PreparedStatement ps=null;
				
            	  for(int j=0;j<tablecommande.getItems().size();j++) {
	                   
	                    	try {
	                			String query1="INSERT INTO stock.ligne_commande(NUMERO_COMMANDE,NOM_PRODUIT,PEREMPTION)VALUES(?,?,?)";
	                			PreparedStatement ps1=ConnectionBDprod.getConnectionCommande().prepareStatement(query1);
	                			 ps1.setInt(1, gl[0]);
	                			 ps1.setString(2, l[j].getNom());
	                			 Date lm=java.sql.Date.valueOf(l[j].getPeremption());
	                			 ps1.setDate(3,(java.sql.Date) lm);
	                			 ps1.executeUpdate();
	                			}
	         
	                		 catch (SQLException e) {
	                			// TODO Auto-generated catch block
	                			e.printStackTrace();
	                		}
	                   		
	                   	}

	                     
	                   
            	  
             
             
    	 }
    	 btnSauvegarder.getScene().getWindow().hide();
    	 page l=new page();
    	 l.start(new Stage());
    	 
    }
	    }
