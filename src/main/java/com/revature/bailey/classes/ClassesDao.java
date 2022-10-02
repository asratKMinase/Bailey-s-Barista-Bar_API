package com.revature.bailey.classes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesDao extends CrudRepository<Classes, String> {

}