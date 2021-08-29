package com.lti.solvathon.repository;

import com.lti.solvathon.model.UserInformation;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInformation, Integer> {
}
