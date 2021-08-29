package com.lti.solvathon.repository;

import org.springframework.data.repository.CrudRepository;

import com.lti.solvathon.model.Transactions;

public interface TransactionsRepository extends CrudRepository<Transactions, Integer> {
	
	

}
