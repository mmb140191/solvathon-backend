package com.lti.solvathon.service;

import com.lti.solvathon.model.BankInformation;
import com.lti.solvathon.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public BankInformation saveData(BankInformation bankInformation){
        return bankRepository.save(bankInformation);
    }

    public BankInformation findByCardNo(String cardNo) {
		return bankRepository.findByCardNo(cardNo);
	}
    public String bankSpecificService(String bankName){
        return "success";
    }

	public BankInformation findByCvvToken(byte[] cvvToken) {
		return bankRepository.findByCvvToken(cvvToken);
		
	}
}
