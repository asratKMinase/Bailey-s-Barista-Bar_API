package com.revature.bailey.enroll;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollDao extends CrudRepository<Enroll, String> {

}
