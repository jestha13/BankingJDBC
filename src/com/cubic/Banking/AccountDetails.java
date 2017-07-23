package com.cubic.Banking;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class AccountDetails {
		
		ArrayList<Account> al= new ArrayList<>();
		ArrayList<Transaction> tr=new ArrayList<>();
		JDBCConnection jc=new JDBCConnection();
		Scanner sc=new Scanner(System.in);
		Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		PreparedStatement pst;
		PreparedStatement ps;
		Statement st;
		ResultSet rs;
		SavingsAccount sacc;
		Account acc;
		Transaction tran;
			
	public void getDisplay() throws ClassNotFoundException, SQLException{
		///prints individual records
		Connection con=jc.getConnection();
		st=con.createStatement();
		System.out.println("\n-----Displaying all Accounts-----");
		rs=st.executeQuery("Select* from Accounts");
		while(rs.next()){
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3));
		}
		con.close();
		}
	
	public void getDetails(int an) throws ClassNotFoundException, SQLException{
		//creating objects of records
		Connection con=jc.getConnection();
		pst=con.prepareStatement("SELECT * FROM Accounts WHERE AccountNumber=?");
		pst.setInt(1, an);
		rs=pst.executeQuery();
		while(rs.next()){
			sacc = new SavingsAccount(rs.getInt(1),rs.getString(2),rs.getDouble(3));
			al.add(sacc);
		}
	}
	
	public void getBalance(int an){
		System.out.println( " ACCOUNT BALANCE : : "+sacc.getBalance());
	}
	
	public void printDetails(int an) throws ClassNotFoundException, SQLException{
		//printing records
		
		Connection con=jc.getConnection();
		pst=con.prepareStatement("SELECT * FROM Accounts WHERE AccountNumber=?");
		pst.setInt(1, an);
		rs=pst.executeQuery();
		while(rs.next()){
			System.out.println("ACCNO: "+rs.getInt(1)+", USERNAME: "+rs.getString(2)+", BALANCE: "+rs.getDouble(3));
		}
	}
  
	public void makeDeposits(int an,double amt) throws ClassNotFoundException, SQLException{
		
		double newBalance=sacc.Deposit(amt);
		Connection con=jc.getConnection();
		String query ="UPDATE ACCOUNTS SET Balance=? WHERE AccountNumber=?";
		pst=con.prepareStatement(query);
		pst.setDouble(1, newBalance);
		pst.setInt(2, an);
		pst.executeUpdate();
		pst.close();
		String q1="INSERT INTO transaction VALUES(?,?,?,?,?)";
		ps=con.prepareStatement(q1);
		ps.setInt(1, an);
		ps.setDouble(2, amt);
		ps.setString(3, "Deposit");
		ps.setTimestamp(4,ourJavaTimestampObject);
		ps.setDouble(5, newBalance);
		ps.execute();
		ps.close();
	}
	
	public void makeWithdraw(int ant,double amt) throws ClassNotFoundException, SQLException{
		double newBalance=sacc.Withdrawal(amt);
		Connection con=jc.getConnection();
		String query ="UPDATE ACCOUNTS SET Balance=? WHERE AccountNumber=?";
		pst=con.prepareStatement(query);
		pst.setDouble(1, newBalance);
		pst.setInt(2, ant);
		pst.executeUpdate();
		pst.close();
		String q1="INSERT INTO transaction VALUES(?,?,?,?,?)";
		ps=con.prepareStatement(q1);
		ps.setInt(1, ant);
		ps.setDouble(2, amt);
		ps.setString(3, "Withdraw");
		ps.setTimestamp(4,ourJavaTimestampObject);
		ps.setDouble(5, newBalance);
		ps.execute();
		ps.close();
	}
	
	public void fixedDeposit(int an, double amt) throws ClassNotFoundException, SQLException{
		sacc.Interest(amt);
		double balance=sacc.balance;
		Connection con=jc.getConnection();
		String q1="INSERT INTO transaction VALUES(?,?,?,?,?)";
		ps=con.prepareStatement(q1);
		ps.setInt(1, an);
		ps.setDouble(2, amt);
		ps.setString(3, "FDeposit");
		ps.setTimestamp(4,ourJavaTimestampObject);
		ps.setDouble(5, balance);
		ps.execute();
		ps.close();
	}
	
	public void viewState(int an) throws ClassNotFoundException, SQLException, IOException{
		
		String filename = "Transaction" + an+".txt";
		FileWriter writer  =new FileWriter("C:\\Users\\tsher_000\\Desktop\\Cubic\\basic java\\" + filename);	
		Connection con=jc.getConnection();
		pst=con.prepareStatement("SELECT * FROM transaction WHERE AccNumber=?");
		pst.setInt(1, an);
		rs=pst.executeQuery();
		int a=1;
		while(rs.next()){
			System.out.println("Transaction no: "+a+", Amount: "+rs.getDouble(2)+", Type: "+rs.getString(3)+", DATE/TIME: "+rs.getTimestamp(4)+", BALANCE: "+rs.getDouble(5));
			tran =new Transaction(rs.getDouble(2),rs.getString(3),rs.getTimestamp(4), rs.getDouble(5));
			tr.add(tran);	
			a++;
		}
		writer.write("---------Statement----------");
		for (Transaction dis:tr){
 			writer.write("\r\n"+dis);		     			 			
		}
		writer.close();
	}
}