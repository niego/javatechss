package service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import model.Transactions;

@Service
public class TransactionsService {
	ArrayList<Transactions> trans = new ArrayList<Transactions>();
	ArrayList<Transactions> transType = new ArrayList<Transactions>();
	public TransactionsService() {}
	
	public String save(Transactions transactions) {
		trans.add(transactions);
		return "{\"code\":0,\"description\":\"insert success\"}";
	}
	
	
	public Transactions getTrans(String id) {
		for(Transactions transaction:trans) {
			if(String.valueOf(transaction.getId()).equalsIgnoreCase(id)) return transaction;
		}
		return null;
	}
	
	public ArrayList<Transactions> getType(String type) {
		transType.clear();
		for(Transactions transaction:trans) {
			if(transaction.getType().equalsIgnoreCase(type)) 
				transType.add(transaction);
		}
		return transType;
	}
	
	public String sum(String id) {
		Double total = 0.0;
		Long parentId= null;
		for(Transactions transaction:trans) {
			if(String.valueOf(transaction.getId()).equalsIgnoreCase(id)) {
				parentId = transaction.getParent_id();
			}
		}
		
		for(Transactions transaction:trans) {
			if(String.valueOf(transaction.getParent_id()).equalsIgnoreCase(String.valueOf(parentId))) {
				total = total + transaction.getAmount();
			}
		}
		
		return "{\"sum\":"+total+"}";
	}
	
	public ArrayList<Transactions> getAll(){
		return trans;
	}
}
