package com.revature.bailey.classes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesDao extends CrudRepository<Classes, Long> {
//    List<Classes> findByTitleContaining(String cname);

}