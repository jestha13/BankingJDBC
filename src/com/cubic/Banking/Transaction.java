package com.cubic.Banking;

import java.sql.Timestamp;

public class Transaction {
	int AccountNo;
	String type;
	Timestamp datetime;
	double totalbalance;
	double amount;

	public Transaction(double amount,String type, Timestamp datetime, double totalbalance) {
		this.type = type;
		this.datetime = datetime;
		this.totalbalance = totalbalance;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "[type=" + type + ", amount="+ amount +", datetime=" + datetime + ", totalbalance=" + totalbalance + "]";
	}
	
}
