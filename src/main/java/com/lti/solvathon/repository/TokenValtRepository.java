package com.lti.solvathon.repository;

import com.lti.solvathon.model.TokenValt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenValtRepository extends CrudRepository<TokenValt, Integer> {

	TokenValt findByCardNo(String cardNo);

	TokenValt findByCardToken(byte[] cardToken);
}
