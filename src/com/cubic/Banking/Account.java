package com.cubic.Banking;

public abstract class Account {
	 double balance;
	 double interest;

	public Account(double balance) {
		this.balance = balance;
	}
	public double Deposit(double amount){
		
		balance = balance +  amount;
		System.out.println("\nThe balance after the deposit of "+amount+" will be "+balance+".");
		return balance;
	
	}
	public double Withdrawal(double amount){
		
		balance = balance -  amount;
		System.out.println("\nThe balance after the withdraw of "+amount+" will be "+balance+".");
		return balance;
	}
	double getBalance() {
		return balance;
	}
	void setBalance(double balance) {
		this.balance = balance;
	}
	double getInterest() {
		return interest;
	}
	void setInterest(double interest) {
		this.interest = interest;
	}
	
	public abstract double Interest (double amount);
	
}

