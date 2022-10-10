package com.revature.bailey.challenges;

import com.revature.bailey.classes.ClassesServices;
import com.revature.bailey.users.Users;
import com.revature.bailey.util.web.dto.ChallengesInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/challenges")
public class ChallengesServlet {
    private final ChallengesServices challengesServices;
    private final ClassesServices classesServices;

    @Autowired
    public ChallengesServlet(ChallengesServices challengesServices, ClassesServices classesServices) {
        this.challengesServices = challengesServices;
        this.classesServices = classesServices;
    }

    @GetMapping("/findAllChallenges")
    public ResponseEntity<List> findAllChallenges() {
        return new ResponseEntity<>(challengesServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findChallenges")
    public ResponseEntity<Challenges> findCourses(@RequestParam String challengesName) {
        Challenges challenges = challengesServices.readById(challengesName);
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Challenges> CreateChallenges(@RequestBody ChallengesInitializer newChallengesi, HttpSession req) {

        Challenges newChallenges = new Challenges();
        Users authChallenges = (Users) req.getAttribute("authUClasses");
        newChallenges.setChaid(newChallengesi.getChaid());
        newChallenges.setChname(newChallengesi.getChname());
        newChallenges.setId(classesServices.readById(newChallengesi.getId()));

        Challenges challenges = challengesServices.create(newChallenges);
        return new ResponseEntity<>(challenges, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Challenges> updateChallenges(@RequestBody ChallengesInitializer newChallengesi, HttpSession req) {
        Challenges newChallenges = new Challenges();
        Users authChallenges = (Users) req.getAttribute("authUClasses");
        newChallenges.setChaid(newChallengesi.getChaid());
        newChallenges.setChname(newChallengesi.getChname());
        newChallenges.setId(classesServices.readById(newChallengesi.getId()));

        Challenges challenges = challengesServices.create(newChallenges);
        return new ResponseEntity<>(challenges, HttpStatus.CREATED);


    }
    @DeleteMapping("/delete")
    public void deleteChallenge(@RequestParam String chaid) {
        boolean newChallenge= challengesServices.delete(chaid);
    }

}
