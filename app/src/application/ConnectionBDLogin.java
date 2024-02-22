package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBDLogin {
	public static Connection getConnection()  {
		Connection conn=null;
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "oialide1991");
			
		}
		catch(SQLException ex) {
			
			ex.fillInStackTrace();
			
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		return  conn;
		
	}

}
