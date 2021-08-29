package com.lti.solvathon.repository;

import org.springframework.data.repository.CrudRepository;

import com.lti.solvathon.model.TokenKeyPair;

public interface TokenKeyRepository extends CrudRepository<TokenKeyPair, Integer> {

}
