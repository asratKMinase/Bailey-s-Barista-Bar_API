package com.revature.bailey.util.web.servlet;

import com.revature.bailey.users.Users;
import com.revature.bailey.users.UsersServices;
import com.revature.bailey.util.web.dto.LoginCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/auth")
public class AuthServlet {

    private final UsersServices usersServices;

    @Autowired
    public AuthServlet(UsersServices usersServices){
        this.usersServices = usersServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authorizeUsers(@RequestBody LoginCreds loginCreds, HttpSession httpSession) throws AuthenticationException {
        Users authUsers = usersServices.authenticateUsers(loginCreds.getUsername(), loginCreds.getPassword());
        httpSession.setAttribute("authUsers", authUsers);
        System.out.println("Successfully logged in.");
    }

    @DeleteMapping
    public void logout(HttpSession session){
        session.invalidate();
        System.out.println("Successfully logged out");
    }

}
