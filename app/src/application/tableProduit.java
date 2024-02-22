package application;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class tableProduit {
                String Nom;Double PPV;String Type,Forme;int Stock;
                LocalDate peremption;
                public tableProduit(String Nom,LocalDate localDate,double ppv,String type,String form,int stock){
                	this.Nom=Nom;
                	this.peremption=localDate;
                	this.PPV=ppv;
                	this.Type=type;
                	this.Forme=form;
                	this.Stock=stock;
                }
                public String getForme() {
					return Forme;
				}
                public String getNom() {
					return Nom;
				}
                public LocalDate getPeremption() {
					return peremption;
				}
                public double getPPV() {
					return PPV;
				}
                public int getStock() {
					return Stock;
				}
                public String getType() {
					return Type;
				}
                public void setForme(String forme) {
					Forme = forme;
				}
                public void setNom(String nom) {
					Nom = nom;
				}
                public void setPeremption(LocalDate peremption) {
					this.peremption = peremption;
				}
                public void setPPV(Double pPV) {
					PPV = pPV;
				}
                public void setStock(int stock) {
					Stock = stock;
				}
                public void setType(String type) {
					Type = type;
				}
}
