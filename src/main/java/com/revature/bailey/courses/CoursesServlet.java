package com.revature.bailey.courses;


import com.revature.bailey.classes.ClassesServices;
import com.revature.bailey.users.Users;
import com.revature.bailey.util.web.dto.CoursesInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CoursesServlet {
    private final CoursesServices coursesServices;
    private final ClassesServices classesServices;

    @Autowired
    public CoursesServlet(CoursesServices coursesServices, ClassesServices classesServices) {
        this.coursesServices = coursesServices;
        this.classesServices = classesServices;
    }

    @GetMapping("/findAllCourses")
    public ResponseEntity<List> findAllCourses() {
        return new ResponseEntity<>(coursesServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findCourses")
    public ResponseEntity<Courses> findCourses(@RequestParam String coursesName) {
        Courses courses = coursesServices.readById(coursesName);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Courses> CreateClasses(@RequestBody CoursesInitializer newCoursesi, HttpSession req) {

        Courses newCourses = new Courses();
        Users authClasses = (Users) req.getAttribute("authUClasses");
        newCourses.setCourseid(newCoursesi.getCourseid());
        newCourses.setConame(newCoursesi.getConame());
        newCourses.setClassid(classesServices.readById(newCoursesi.getClassid()));

        Courses courses = coursesServices.create(newCourses);
        return new ResponseEntity<>(courses, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Courses> updateCourses(@RequestBody CoursesInitializer newCoursesi, HttpSession req) {

        Courses newCourses = new Courses();
        Users authClasses = (Users) req.getAttribute("authUClasses");
        newCourses.setCourseid(newCoursesi.getCourseid());
        newCourses.setConame(newCoursesi.getConame());
        newCourses.setClassid(classesServices.readById(newCoursesi.getClassid()));

        Courses courses = coursesServices.create(newCourses);
        return new ResponseEntity<>(courses, HttpStatus.CREATED);

    }
    @DeleteMapping("/delete")
    public void deleteCourses(String courseid){
        boolean newCourses = coursesServices.delete(courseid);
    }
}
