package application;

import java.sql.Date;
import java.sql.Time;

public class venteaffClass {
    int n;String nom;Date date;int quentite;Date dateV;Time heure;String type;
    public venteaffClass(int a,String b,Date c,int q,Date k,Time t,String ty) {
    	this.n=a;
    	this.nom=b;
    	this.date=c;
    	this.quentite=q;
    	this.dateV=k;
    	this.heure=t;
    	this.type=ty;
    	
    	
    }
    public String getType() {
		return type;
	}
    public void setType(String type) {
		this.type = type;
	}
    public Date getDateV() {
		return dateV;
	}public Time getHeure() {
		return heure;
	}
	public void setDateV(Date dateV) {
		this.dateV = dateV;
	}
	public void setHeure(Time heure) {
		this.heure = heure;
	}
    public Date getDate() {
		return date;
	}
    public int getN() {
		return n;
	}
    public String getNom() {
		return nom;
	}
    public int getQuentite() {
		return quentite;
	}
    public void setDate(Date date) {
		this.date = date;
	}
    public void setN(int n) {
		this.n = n;
	}
    public void setNom(String nom) {
		this.nom = nom;
	}
    public void setQuentite(int quentite) {
		this.quentite = quentite;
	}
}
