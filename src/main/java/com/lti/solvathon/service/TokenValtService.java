package com.lti.solvathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.solvathon.model.TokenValt;
import com.lti.solvathon.repository.TokenValtRepository;

@Service
public class TokenValtService {

    @Autowired
    private TokenValtRepository tokenValtRepository;

    public TokenValt saveData(TokenValt tokenValt){
        return tokenValtRepository.save(tokenValt);
    }

	public Boolean isCardAlreadyRegistered(String cardNo) {
		TokenValt tokenValt = tokenValtRepository.findByCardNo(cardNo);
		return tokenValt != null;
	}

	public TokenValt findbyPanToken(byte[] panToken) {
		return tokenValtRepository.findByCardToken(panToken);
		
	}
}
