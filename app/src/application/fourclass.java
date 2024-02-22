package application;

public class fourclass {
     String Raison_s,Adress,numero,email;
     public fourclass(String a,String b,String c,String d) {
    	 this.Raison_s=a;
    	 this.Adress=b;
    	 this.numero=c;
    	 this.email=d;
     }
     public String getAdress() {
		return Adress;
	}
     public String getEmail() {
		return email;
	}
     public String getNumero() {
		return numero;
	}
     public String getRaison_s() {
		return Raison_s;
	}
     public void setAdress(String adress) {
		Adress = adress;
	}
     public void setEmail(String email) {
		this.email = email;
	}
     public void setNumero(String numero) {
		this.numero = numero;
	}
     public void setRaison_s(String raison_s) {
		Raison_s = raison_s;
	}
}
