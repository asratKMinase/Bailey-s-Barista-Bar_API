package com.revature.bailey.classes;

import com.revature.bailey.exceptions.InvalidRequestException;
import com.revature.bailey.exceptions.ResourcePersistanceException;
import com.revature.bailey.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClassesServices {
    private ClassesDao classesDao;

    @Autowired
    public ClassesServices(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    public  List<Classes> findAll(){
        List<Classes> classes = (List<Classes>) classesDao.findAll();
        return classes;
    }
    public Classes readById(long id) {
        Classes classes= classesDao.findById(id).get();
        return classes;
    }
    public Classes update(Classes updatedClasses) {
        classesDao.save(updatedClasses);
        return updatedClasses;
    }
    public boolean delete(long classes) {
        classesDao.deleteById(classes);
        return true;
    }

    public boolean validateClassesNotUsed(long classes){
        return classesDao.existsById(classes);
    }

    public Classes create(Classes newClasses){
        if(!validateInput(newClasses)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        if(validateClassesNotUsed(newClasses.getId())){
            throw new InvalidRequestException("Classes is already in the list. Please try again");
        }
        Classes persistedClasses = classesDao.save(newClasses);
        Users users = new Users();
        users.getClasses().add(persistedClasses);

        if(persistedClasses == null){
            throw new ResourcePersistanceException("Classes was not persisted to the database upon registration");
        }
        return persistedClasses;
    }

    public boolean validateInput(Classes newClasses) {
        if(newClasses == null) return false;
        if(newClasses.getCname() == null || newClasses.getCname().trim().equals("")) return false;
        if(newClasses.getSdate() == null || newClasses.getSdate().trim().equals("")) return false;
        return newClasses.getEdate() != null || !newClasses.getEdate().equals("");

    }
}
