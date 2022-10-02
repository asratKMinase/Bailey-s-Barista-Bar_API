package com.revature.bailey.users;

import com.revature.bailey.util.Authable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersServlet {
    private final UsersServices usersServices;
    @Autowired
    public UsersServlet(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @GetMapping("/welcome")
    public @ResponseBody String test(){
        return "Welcome to Bailey Barista Training Academy";
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List> findAllUsers(){
        return new ResponseEntity<>(usersServices.findAll(), HttpStatus.FOUND);
    }
    @GetMapping("/findUsers")
    public ResponseEntity<Users> findUsers(@RequestParam String id){
        Users customer = usersServices.readById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Users> saveUsers(@RequestBody Users users) {
        Users newUsers = usersServices.create(users);
        return new ResponseEntity<>(newUsers, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Users> updateUsers(@RequestBody Users users) {
        Users newUsers = usersServices.update(users);
        return new ResponseEntity<>(newUsers, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public void deleteUsers(@RequestParam String username) {
        boolean newUsers = usersServices.delete(username);
    }
}

