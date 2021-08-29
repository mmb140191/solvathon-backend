package com.lti.solvathon.repository;

import com.lti.solvathon.model.BankInformation;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<BankInformation, Integer> {

	BankInformation findByCardNo(String cardNo);

	BankInformation findByCvvToken(byte[] cvvToken);
}
