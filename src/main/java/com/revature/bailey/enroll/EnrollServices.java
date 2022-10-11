package com.revature.bailey.enroll;

import com.revature.bailey.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EnrollServices {
    private EnrollDao enrollDao;

    @Autowired
    public EnrollServices(EnrollDao enrollDao) {
        this.enrollDao = enrollDao;
    }

    public List<Enroll> findAll(){
        List<Enroll> enroll = (List<Enroll>) enrollDao.findAll();
        return enroll;
    }
    public Enroll readById(String id) {
        Enroll enroll= enrollDao.findById(id).get();
        return enroll;
    }
    public Enroll update(Enroll updatedEnroll) {
        enrollDao.save(updatedEnroll);
        return updatedEnroll;
    }
    public boolean delete(String enroll) {
        enrollDao.deleteById(enroll);
        return true;
    }

    public Enroll create(Enroll newEnroll){

        Enroll persistedEnroll = enrollDao.save(newEnroll);

        if(persistedEnroll == null){
            throw new ResourcePersistanceException("You are not enrolled in a class.");
        }
        return persistedEnroll;
    }

}
