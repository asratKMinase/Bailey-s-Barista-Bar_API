package com.revature.bailey.users;


import com.revature.bailey.classes.Classes;
import com.revature.bailey.exceptions.InvalidRequestException;
import com.revature.bailey.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersServices  {
    private UsersDao usersDao;

    @Autowired
    public UsersServices(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public List<Users> findAll(){
        List<Users> customers = (List<Users>) usersDao.findAll();
        return customers;
    }
    public Users readById(long id) {
        Users customer = usersDao.findById(id).get();
        return customer;
    }
    public Users update(Users updatedCustomer) {
        usersDao.save(updatedCustomer);
        return updatedCustomer;
    }
    public boolean delete(long username) {
        usersDao.deleteById(username);
        return true;
    }

    public boolean validateUsernameNotUsed(long username){
        return usersDao.existsById(username);
    }

    public Users create(Users newUsers){
        if(!validateInput(newUsers)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        if(validateUsernameNotUsed(newUsers.getId())){
            throw new InvalidRequestException("Username is already in use. Please try again with another email or login into previous made account.");
        }

        Users persistedUsers= usersDao.save(newUsers);
        Classes classes = new Classes();
        classes.getUsers().add(persistedUsers);

        if(persistedUsers == null){
            throw new ResourcePersistanceException("Customer was not persisted to the database upon registration");
        }
        return persistedUsers;
    }

    public boolean validateInput(Users newUsers) {
        if(newUsers == null) return false;
        if(newUsers.getUsername()== null || newUsers.getUsername().trim().equals("")) return false;
        if(newUsers.getFname() == null || newUsers.getFname().trim().equals("")) return false;
        if(newUsers.getLname() == null || newUsers.getLname().trim().equals("")) return false;
        if(newUsers.getPassword() == null || newUsers.getPassword().trim().equals("")) return false;
        if(newUsers.getRdate() == null || newUsers.getRdate().trim().equals("")) return false;
        return newUsers.getEmail() != null || !newUsers.getEmail().trim().equals("");
    }

    public Users authenticateUsers(String username, String password) throws AuthenticationException {
        if(password == null || password.trim().equals("") || username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }
        Users authenticatedUsers = usersDao.authenticateUsers(username, password);

        if (authenticatedUsers == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }
        return authenticatedUsers;
    }
}
