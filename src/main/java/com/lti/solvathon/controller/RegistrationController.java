package com.lti.solvathon.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lti.solvathon.model.BankInformation;
import com.lti.solvathon.model.PaymentInformation;
import com.lti.solvathon.model.TokenKeyPair;
import com.lti.solvathon.model.TokenValt;
import com.lti.solvathon.model.Transactions;
import com.lti.solvathon.model.UserInformation;
import com.lti.solvathon.repository.BankRepository;
import com.lti.solvathon.repository.TokenKeyRepository;
import com.lti.solvathon.repository.TransactionsRepository;
import com.lti.solvathon.service.BankService;
import com.lti.solvathon.service.TokenValtService;
import com.lti.solvathon.service.UserService;
import com.lti.solvathon.service.UtillService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private BankService bankService;
    @Autowired
    private TokenValtService tokenValtService;
    @Autowired
    private UtillService service;
    
    @Autowired
    private TokenKeyRepository tokenKeyRepository;
    
    @Autowired
    private TransactionsRepository transactionsRepository;
    
    @Autowired
    private BankRepository bankRepository;

    @RequestMapping("/hello")
    public String getData(){
        return "Hello";
    }

    @PostMapping("/registrationFlow")
    public UserInformation registrationFlow(@RequestBody UserInformation userInformation){
    	
    	UserInformation info = new UserInformation();
    	KeyPair keyPair = null;
    	PublicKey publicKey;
    	PrivateKey privateKey;
    	TokenKeyPair tokenKeyPair = new TokenKeyPair();
    	try {
        String bankName = service.getBankName(userInformation.getCardNo());
		if (bankName != null) {

        //adding the data in tsp table
        TokenValt tokenValt = new TokenValt();
        Boolean alreadyRegistered = tokenValtService.isCardAlreadyRegistered(userInformation.getCardNo());
        if(alreadyRegistered) {
        	info.setMessage("card already registered");
        	return info;
        }else {
        	List<TokenKeyPair> findAll = (List<TokenKeyPair>) tokenKeyRepository.findAll();
        	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        	
        	if(findAll.isEmpty()) {
        		keyPair = service.keyGenrator();
            	tokenKeyPair.setPrivateKey(keyPair.getPrivate().getEncoded());
            	tokenKeyPair.setPublicKey(keyPair.getPublic().getEncoded());
            	tokenKeyRepository.save(tokenKeyPair);
        		publicKey = keyPair.getPublic();
        		privateKey=keyPair.getPrivate();
        	} else {
        		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(findAll.get(0).getPublicKey());
        		publicKey = keyFactory.generatePublic(publicKeySpec);
        		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(findAll.get(0).getPrivateKey());
        		privateKey = keyFactory.generatePrivate(privateKeySpec);
        	}
        	
        	
        }
        byte[] valtToken = service.encryptionData(userInformation.getCardNo(), publicKey);
        tokenValt.setCardToken(valtToken);
        tokenValt.setCardNo(userInformation.getCardNo());
        tokenValt.setPrivateKey(privateKey.getEncoded());
        tokenValtService.saveData(tokenValt);

        //adding the data in bank table
        BankInformation bankInformation = new BankInformation();
        bankInformation.setCardNo(userInformation.getCardNo());
        bankInformation.setExpiryMonth(userInformation.getExpiryMonth());
        bankInformation.setExpiryYear(userInformation.getExpiryYear());
        bankInformation.setMobileNo(userInformation.getMobileNo());
        bankInformation.setAccountNumber(Long.toString((long)(Math.random()*(99999999-10000000+1)+1000)));
        bankInformation.setBalance(50000);
        Integer rand = (int)(Math.random()*(9999-1000+1)+1000);
        byte[] rendomEncypt = service.dynamicCVVEncyptor(rand);
        bankInformation.setCvvToken(rendomEncypt);
        bankService.saveData(bankInformation);

       // String valtTokenReturn = service.decryptionData(valtToken, keyPair.getPrivate());
       // System.out.println("valtTokenReturn --> "+valtTokenReturn);

       // Integer randomDycript = service.dynamicCVVDecryptor(rendomEncypt);
       // System.out.println("randomDycript --> "+randomDycript);

        //adding the data in user table
        userInformation.setCvvToken(rendomEncypt);
        userInformation.setPanToken(valtToken);
        userInformation.setPublicToken(publicKey.getEncoded());

        userService.saveData(userInformation);
        
        info.setCvvToken(rendomEncypt);
        info.setPanToken(valtToken);
        info.setPublicToken(publicKey.getEncoded());
        } else {
        	info.setMessage("cannot add card to the wallet. incorrect card number or issuer bank not partnered with the pay(local) server ");
        	return info;
        }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return info;
    }
    
    @PostMapping("/payment")
    public String payment(@RequestBody PaymentInformation paymentInformation){
    	try {
    	
    	List<TokenKeyPair> findAll = (List<TokenKeyPair>) tokenKeyRepository.findAll();
    	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    	PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(findAll.get(0).getPrivateKey());
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
    	
    	String cardno = service.decryptionData(paymentInformation.getPanToken(), privateKey);
    	
    	TokenValt tokenVault = tokenValtService.findbyPanToken(paymentInformation.getPanToken()); 
    	
		if (cardno.equals(tokenVault.getCardNo())) {
			
			Integer cvv = service.dynamicCVVDecryptor(paymentInformation.getCvvToken());
			
			if(cvv!=null) {
			
			BankInformation bankInfo = bankService.findByCvvToken(paymentInformation.getCvvToken());
			
			if(cardno.equals(bankInfo.getCardNo())) {
				if(!bankInfo.isCardLossOrStolen()) {
		    		//check acct balance
					if (bankInfo.getBalance() > paymentInformation.getTransactionAmount()) {
						//do transaction
						Transactions tx = new Transactions();
						tx.setAmount(paymentInformation.getTransactionAmount());
						tx.setTransactionType("debit");
						tx.setBank(bankInfo);
						bankInfo.setBalance(bankInfo.getBalance() - paymentInformation.getTransactionAmount());
						bankInfo.getTransactions().add(tx);
						BankInformation save = bankRepository.save(bankInfo);

						return "Success";
					} else {
						return "insufficient balance";
					}
					
		    	} else {
		    		return "failure";
		    	}
			} else {
				return "failure";
			}
			
	    	
		} else {
			return "failure";
		}
    	
    	
    	
    	
    	
		} else {
			return "failure";
			
		}
    	
    	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
}
