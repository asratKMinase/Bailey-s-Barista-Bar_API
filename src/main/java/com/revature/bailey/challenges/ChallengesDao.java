package com.revature.bailey.challenges;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengesDao extends CrudRepository<Challenges, String> {

}