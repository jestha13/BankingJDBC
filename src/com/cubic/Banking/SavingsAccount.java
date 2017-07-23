package com.cubic.Banking;

public class SavingsAccount extends Account{
	 int AccountNo;
	 String User;
	
	public SavingsAccount(int AccountNo, String User, double balance) {
		super(balance);
		this.AccountNo=AccountNo;
		this.User=User;
	}

	@Override
	public double Interest(double amount) {
		interest =amount*0.05;
		System.out.println("Interest acquired on: "+amount+" is "+interest);
		return interest;
	}

	@Override
	public String toString() {
		return "SavingsAccount [AccountNo=" + AccountNo + ", Username="+User+", Balance="+balance+ "]";
	}

}
