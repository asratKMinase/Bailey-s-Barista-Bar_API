package com.revature.bailey.challenges;

import com.revature.bailey.exceptions.InvalidRequestException;
import com.revature.bailey.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChallengesServices {
    private ChallengesDao challengesDao;

    @Autowired
    public ChallengesServices(ChallengesDao challengesDao) {
        this.challengesDao = challengesDao;
    }

    public  List<Challenges> findAll(){
        List<Challenges> challenges = (List<Challenges>) challengesDao.findAll();
        return challenges;
    }
    public Challenges readById(String id) {
        Challenges challenges= challengesDao.findById(id).get();
        return challenges;
    }
    public Challenges update(Challenges updatedChallenges) {
        challengesDao.save(updatedChallenges);
        return updatedChallenges;
    }
    public boolean delete(String challenges) {
        challengesDao.deleteById(challenges);
        return true;
    }

    public boolean validateChallengesNotUsed(String challenges){
        return challengesDao.existsById(challenges);
    }

    public Challenges create(Challenges newChallenges){
        if(!validateInput(newChallenges)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        if(validateChallengesNotUsed(newChallenges.getChaid())){
            throw new InvalidRequestException("Challenges is already in the list. Please try again");
        }
        Challenges persistedChallenges = challengesDao.save(newChallenges);

        if(persistedChallenges == null){
            throw new ResourcePersistanceException("Challenges was not persisted to the database.");
        }
        return persistedChallenges;
    }

    public boolean validateInput(Challenges newChallenges) {
        if(newChallenges == null) return false;
        if(newChallenges.getChaid()== null || newChallenges.getChaid().trim().equals("")) return false;
        if(newChallenges.getChname() == null || newChallenges.getChname().trim().equals("")) return false;
        return newChallenges.getId()!= null || !newChallenges.getId().equals("");

    }
}
