package com.revature.bailey.skills;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsDao extends CrudRepository<Skills, String> {

}