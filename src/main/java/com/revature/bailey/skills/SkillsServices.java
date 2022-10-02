package com.revature.bailey.skills;

import com.revature.bailey.exceptions.InvalidRequestException;
import com.revature.bailey.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SkillsServices {
    private SkillsDao skillsDao;

    @Autowired
    public SkillsServices(SkillsDao skillsDao) {
        this.skillsDao = skillsDao;
    }

    public  List<Skills> findAll(){
        List<Skills> skills = (List<Skills>) skillsDao.findAll();
        return skills;
    }
    public Skills readById(String id) {
        Skills skills= skillsDao.findById(id).get();
        return skills;
    }
    public Skills update(Skills updatedSkills) {
        skillsDao.save(updatedSkills);
        return updatedSkills;
    }
    public boolean delete(String skills) {
        skillsDao.deleteById(skills);
        return true;
    }

    public boolean validateSkillsNotUsed(String skills){
        return skillsDao.existsById(skills);
    }

    public Skills create(Skills newSkills){
        if(!validateInput(newSkills)){
            throw new InvalidRequestException("Skills input was not validated, either empty String or null values");
        }
        if(validateSkillsNotUsed(newSkills.getSkillid())){
            throw new InvalidRequestException("Skills is already in the list. Please try again");
        }
        Skills persistedSkills = skillsDao.save(newSkills);

        if(persistedSkills == null){
            throw new ResourcePersistanceException("Skills was not persisted to the database.");
        }
        return persistedSkills;
    }

    public boolean validateInput(Skills newSkills) {
        if(newSkills == null) return false;
        if(newSkills.getSkillid()== null || newSkills.getSkillid().trim().equals("")) return false;
        if(newSkills.getSname() == null || newSkills.getSname().trim().equals("")) return false;
        return newSkills.getCourseid() != null || !newSkills.getCourseid().equals("");

    }
}
