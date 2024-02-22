package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBDprod {
	public static Connection getConnection()  {
		Connection conn=null;
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "oialide1991");
			
		}
		catch(SQLException ex) {
			
			ex.fillInStackTrace();
			
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		return  conn;
		
	}
	public static Connection getConnectionfour()  {
		Connection conn=null;
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "oialide1991");
			
		}
		catch(SQLException ex) {
			
			ex.fillInStackTrace();
			
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		return  conn;
		
	}
	public static Connection getConnectionligne()  {
		Connection conn=null;
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "oialide1991");
			
		}
		catch(SQLException ex) {
			
			ex.fillInStackTrace();
			
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		return  conn;
		
	}
	public static Connection getConnectionCommande()  {
		Connection conn=null;
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", "root", "oialide1991");
			
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
