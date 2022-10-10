package com.revature.bailey.classes;


import com.revature.bailey.users.Users;
import com.revature.bailey.users.UsersServices;
import com.revature.bailey.util.Authable;
import com.revature.bailey.util.web.dto.ClassesInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/classes")
public class ClassesServlet {
    private final ClassesServices classesServices;
    private final UsersServices userServices;

    @Autowired
    public ClassesServlet(ClassesServices classesServices, UsersServices userServices) {
        this.classesServices = classesServices;
        this.userServices = userServices;
    }

    @GetMapping("/findAllClasses")
    public ResponseEntity<List> findAllclasses() {
        return new ResponseEntity<>(classesServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findClasses")
    public ResponseEntity<Classes> findClasses(@RequestParam long classesName) {
        Classes classes = classesServices.readById(classesName);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Classes> saveUsers(@RequestBody Classes classes) {
        Classes newClasses = classesServices.create(classes);
        return new ResponseEntity<>(newClasses, HttpStatus.CREATED);
    }
//    @PostMapping("/register")
//    public ResponseEntity<Classes> CreateClasses(@RequestBody ClassesInitializer newClassesi, HttpSession req)  {
//
//        Classes newClasses = new Classes();
//        Users newUsers = new Users();
//        Users authClasses = (Users) req.getAttribute("authUClasses");
//        newClasses.setid(newClassesi.getid());
//        newClasses.setCname(newClassesi.getCname());
//        newClasses.setSdate(newClassesi.getSdate());
//        newClasses.setEdate(newClassesi.getEdate());
//        newClasses.setEnroll(newClassesi.getEnroll());
//        newClasses.getEnrolledUsers().add(newUsers);
//        //newClasses.setUsers(userServices.findAll(newClassesi.getUsers()));
//
//
//        Classes classes = classesServices.create(newClasses);
//        return new ResponseEntity<>(classes, HttpStatus.CREATED);
//    }

    @PutMapping("/update")
    public ResponseEntity<Classes> updateClasses(@RequestBody ClassesInitializer newClassesi, HttpSession req) {

        Classes newClasses = new Classes();
        Users authClasses = (Users) req.getAttribute("authUClasses");
        newClasses.setId(newClassesi.getId());
        newClasses.setCname(newClassesi.getCname());
        newClasses.setSdate(newClassesi.getSdate());
        newClasses.setEdate(newClassesi.getEdate());
        newClasses.setEnroll(newClassesi.getEnroll());

        //newClasses.setUsername( userServices.readById(newClassesi.getUsername()));


        Classes classes = classesServices.create(newClasses);
        return new ResponseEntity<>(classes, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public void deleteClass(@RequestParam long id) {
        boolean newClass= classesServices.delete(id);
    }

}
