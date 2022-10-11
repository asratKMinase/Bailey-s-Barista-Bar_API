package com.revature.bailey.enroll;


import com.revature.bailey.challenges.ChallengesServices;
import com.revature.bailey.users.Users;
import com.revature.bailey.users.UsersServices;
import com.revature.bailey.util.web.dto.EnrollIntializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/enroll")
public class EnrollServlet {

    private final EnrollServices enrollServices;
    private final UsersServices usersServices;

    public EnrollServlet(EnrollServices enrollServices, UsersServices usersServices) {
        this.enrollServices = enrollServices;
        this.usersServices = usersServices;
    }

    @Autowired

    @GetMapping("/findAllenrollment")
    public ResponseEntity<List> findAllErollment() {
        return new ResponseEntity<>(enrollServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findEnroll")
    public ResponseEntity<Enroll> findEnroll(@RequestParam String enrollid) {
        Enroll enroll = enrollServices.readById(enrollid);
        return new ResponseEntity<>(enroll, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Enroll> CreateEnroll(@RequestBody EnrollIntializer newEnrolli, HttpSession req) {

        Enroll newEnroll = new Enroll();
        Users authEnroll = (Users) req.getAttribute("authUClasses");
        newEnroll.setEnrollid(newEnrolli.getEnrollid());
        newEnroll.setCname(newEnrolli.getCname());
        newEnroll.setEnroll(newEnrolli.getEnroll());
        newEnroll.setId(usersServices.readById(newEnrolli.getId()));

        Enroll enroll = enrollServices.create(newEnroll);
        return new ResponseEntity<>(enroll, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Enroll> updateChallenges(@RequestBody EnrollIntializer newEnrolli, HttpSession req) {
        Enroll newEnroll = new Enroll();
        Users authEnroll = (Users) req.getAttribute("authUClasses");
        newEnroll.setEnroll(newEnrolli.getEnroll());
        newEnroll.setCname(newEnrolli.getCname());
        newEnroll.setEnroll(newEnrolli.getEnroll());
        newEnroll.setId(usersServices.readById(newEnrolli.getId()));

        Enroll enroll = enrollServices.create(newEnroll);
        return new ResponseEntity<>(enroll, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public void deleteEnroll(@RequestParam String enrollid) {
        boolean newChallenge= enrollServices.delete(enrollid);
    }

}
