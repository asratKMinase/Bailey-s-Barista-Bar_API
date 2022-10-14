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
        Courses persistedCourses = coursesDao.save(newCourses);

        if(persistedCourses == null){
            throw new ResourcePersistanceException("Courses was not persisted to the database.");
        }
        return persistedCourses;
    }

}
