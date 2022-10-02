package com.revature.bailey.courses;

import com.revature.bailey.exceptions.InvalidRequestException;
import com.revature.bailey.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CoursesServices {
    private CoursesDao coursesDao;

    @Autowired
    public CoursesServices(CoursesDao coursesDao) {
        this.coursesDao = coursesDao;
    }

    public  List<Courses> findAll(){
        List<Courses> courses = (List<Courses>) coursesDao.findAll();
        return courses;
    }
    public Courses readById(String id) {
        Courses courses= coursesDao.findById(id).get();
        return courses;
    }
    public Courses update(Courses updatedCourses) {
        coursesDao.save(updatedCourses);
        return updatedCourses;
    }
    public boolean delete(String courses) {
        coursesDao.deleteById(courses);
        return true;
    }

    public boolean validateCoursesNotUsed(String courses){
        return coursesDao.existsById(courses);
    }

    public Courses create(Courses newCourses){
        if(!validateInput(newCourses)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        if(validateCoursesNotUsed(newCourses.getCourseid())){
            throw new InvalidRequestException("Courses is already in the list. Please try again");
        }
        Courses persistedCourses = coursesDao.save(newCourses);

        if(persistedCourses == null){
            throw new ResourcePersistanceException("Courses was not persisted to the database.");
        }
        return persistedCourses;
    }

    public boolean validateInput(Courses newCourses) {
        if(newCourses == null) return false;
        if(newCourses.getClassid()== null || newCourses.getCourseid().trim().equals("")) return false;
        if(newCourses.getConame() == null || newCourses.getConame().trim().equals("")) return false;
        return newCourses.getClassid() != null || !newCourses.getClassid().equals("");

    }
}
