package application;

import java.time.LocalDate;

public class venteClass {
	String nom;String type;
	int Quentite;
	LocalDate Date;
	public venteClass(String a,String b,int c,LocalDate d) {
		this.nom=a;
		this.type=b;
		this.Quentite=c;
		this.Date=d;
	}
	public LocalDate getDate() {
		return Date;
	}
	public String getNom() {
		return nom;
	}
	public int getQuentite() {
		return Quentite;
	}
	public String getType() {
		return type;
	}
	public void setDate(LocalDate date) {
		Date = date;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setQuentite(int quentite) {
		Quentite = quentite;
	}
	public void setType(String type) {
		this.type = type;
	}

}
