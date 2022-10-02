package com.revature.bailey.skills;

import com.revature.bailey.courses.CoursesServices;
import com.revature.bailey.users.Users;
import com.revature.bailey.util.web.dto.SkillsInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/skills")
public class SkillsServlet {
    private final SkillsServices skillsServices;

    private final CoursesServices coursesServices;

    @Autowired
    public SkillsServlet(SkillsServices skillsServices, CoursesServices coursesServices) {
        this.skillsServices = skillsServices;
        this.coursesServices = coursesServices;
    }

    @GetMapping("/findAllSkills")
    public ResponseEntity<List> findAllSkills() {
        return new ResponseEntity<>(skillsServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findSkills")
    public ResponseEntity<Skills> findSkills(@RequestParam String skillsName) {
        Skills skills = skillsServices.readById(skillsName);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Skills> CreateSkills(@RequestBody SkillsInitializer newSkillsi, HttpSession req) {

        Skills newSkills = new Skills();
        Users authUsers = (Users) req.getAttribute("authUClasses");
        newSkills.setSkillid(newSkillsi.getSkillid());
        newSkills.setSname(newSkillsi.getSname());
        newSkills.setCourseid(coursesServices.readById(newSkillsi.getCourseid()));

        Skills skills = skillsServices.create(newSkills);
        return new ResponseEntity<>(skills, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Skills> updateChallenges(@RequestBody SkillsInitializer newSkillsi, HttpSession req) {

        Skills newSkills = new Skills();
        Users authUsers = (Users) req.getAttribute("authUClasses");
        newSkills.setSkillid(newSkillsi.getSkillid());
        newSkills.setSname(newSkillsi.getSname());
        newSkills.setCourseid(coursesServices.readById(newSkillsi.getCourseid()));

        Skills skills = skillsServices.create(newSkills);
        return new ResponseEntity<>(skills, HttpStatus.CREATED);


    }
    @DeleteMapping("/delete")
    public void deleteSkills(String skillid){
        boolean newSkills = skillsServices.delete(skillid);
    }
}
