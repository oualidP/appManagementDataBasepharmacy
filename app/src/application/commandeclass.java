package application;

import java.time.LocalDate;

public class commandeclass {
	 String Nom;Double PPV;String Type,Forme;int Stock;
     LocalDate peremption;String Raison_S;LocalDate Date_commande;
     public commandeclass(String a,Double b,String c,String d,int e,LocalDate f,String g,LocalDate h) {
    	 this.Nom=a;
    	 this.PPV=b;
    	 this.Type=c;
    	 this.Forme=d;
    	 this.Stock=e;
    	 this.peremption=f;
    	 this.Raison_S=g;
    	 this.Date_commande=h;
    	 
     }
     public LocalDate getDate_commande() {
		return Date_commande;
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
     public Double getPPV() {
		return PPV;
	}
     public String getRaison_S() {
		return Raison_S;
	}
     public int getStock() {
		return Stock;
	}
     public String getType() {
		return Type;
	}
     public void setDate_commande(LocalDate date_commande) {
		Date_commande = date_commande;
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
     public void setRaison_S(String raison_S) {
		Raison_S = raison_S;
	}
     public void setStock(int stock) {
		Stock = stock;
	}
     public void setType(String type) {
		Type = type;
	}

}
