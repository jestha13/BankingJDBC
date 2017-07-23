package com.cubic.Banking;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Banking {
//savepoint:
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		AccountDetails ad=new AccountDetails();
		
		Scanner sc = new Scanner(System.in);
	    while(true){
	    	System.out.println("Enter account no:");
	    	int an=sc.nextInt();
	    	System.out.println("\n-----Getting details from the Bank-----");
	    	ad.getDetails(an);//create object of individual records from table
	    	ad.printDetails(an);//printing from table
	    	do{
			
	    		System.out.println("\nPlease Enter a Choice (1-5):");
	    		System.out.println("1. Deposit 2. Withdrawal 3.Checkbalance 4. Fixed Deposit 5.Transfer 6.View Statement");
	    		int c=sc.nextInt();
			
	    		switch(c)
	    			{
	    			case 1:
	    			{
	    				System.out.println("Enter an amount to Deposit:");
	    				double amt=sc.nextDouble();
	    				ad.makeDeposits(an,amt);
	    				System.out.println("\n-----Updating Account-----");
	    				ad.printDetails(an);
	    				break;
	    			}
	    			case 2:
	    			{
	    				System.out.println("Enter an amount to Withdraw:");
	    				double amt=sc.nextDouble();
	    				ad.makeWithdraw(an, amt);
	    				System.out.println("\n-----Updating Account-----");
	    				ad.printDetails(an);
	    				break;
	    			}
	    			case 3:
	    			{   
	    				ad.getBalance(an);
	    				break;
	    			}
	    			case 4:
	    			{   
	    				System.out.println("Enter amount for Fixed Deposit.");
	    				double amt=sc.nextDouble();
	    				ad.fixedDeposit(an, amt);
					
	    				System.out.println("\n---Calculating Interest----");
	    				System.out.println(" ");
					
	    				ad.makeDeposits(an,ad.sacc.interest);
	    				System.out.println("\n-----Updating Account-----");
	    				ad.printDetails(an);
	    				break;
	    			}
	    			case 5:
	    			{
	    				System.out.println("Enter amount to Transfer:");
	    				double amt =sc.nextDouble();
	    				System.out.println("Enter Account No. to Transfer:");
	    				int an1=sc.nextInt();
	    				try {
	    					if( an==an1){
	    						System.out.println("Enter Different Account no.");
	    						continue;
	    					}
	    				} 
	    				catch (Exception e) {
	    					e.printStackTrace();
	    				}
					
	    				ad.makeWithdraw(an, amt);
	    				ad.getDetails(an1);
	    				ad.makeDeposits(an1, amt);
	    				System.out.println("\n---Transfering Amount---");
	    				System.out.println(" ");
	    				ad.printDetails(an);
	    				ad.printDetails(an1);
	    				break;
	    			}
	    			case 6:
	    			{
	    				System.out.println("****STATEMENT of****"+an);
	    				ad.viewState(an);
	    			}
	    		}
	    		System.out.println("\nDo you want to continue: Y/N");
	    		String cd=sc.next();
	    		if(cd.equalsIgnoreCase("n")){		        	 
	    			System.out.println("\n---Printing SavingAccounts objects---");
	    			for(Account s :ad.al){
	    				System.out.println(s);
	    			}
	    			break;
	    		} 		 
	     		
	     	}
	    	while(true);
	    	System.out.println("\nDo you want to access different account: Y/N");
	    	String cd=sc.next();
	    	if(cd.equalsIgnoreCase("n")){		        	 
	    		System.out.println("---------ALL Accounts Updated----------");
	    		ad.getDisplay();
	    		break;
	    	}
	    }
	}
}


