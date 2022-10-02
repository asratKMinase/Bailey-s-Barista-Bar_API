package com.revature.bailey.users;


import com.revature.bailey.exceptions.InvalidRequestException;
import com.revature.bailey.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersServices {
    private UsersDao usersDao;

    @Autowired
    public UsersServices(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public List<Users> findAll(){
        List<Users> customers = (List<Users>) usersDao.findAll();
        return customers;
    }
    public Users readById(String id) {
        Users customer = usersDao.findById(id).get();
        return customer;
    }
    public Users update(Users updatedCustomer) {
        usersDao.save(updatedCustomer);
        return updatedCustomer;
    }
    public boolean delete(String username) {
        usersDao.deleteById(username);
        return true;
    }

    public boolean validateUsernameNotUsed(String username){
        return usersDao.existsById(username);
    }

    public Users create(Users newCustomer){
        if(!validateInput(newCustomer)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        if(validateUsernameNotUsed(newCustomer.getUsername())){
            throw new InvalidRequestException("Username is already in use. Please try again with another email or login into previous made account.");
        }
        Users persistedTrainer = usersDao.save(newCustomer);

        if(persistedTrainer == null){
            throw new ResourcePersistanceException("Customer was not persisted to the database upon registration");
        }
        return persistedTrainer;
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
            throw new InvalidRequestException("Either email or password is an invalid entry. Please try logging in again");
        }
        Users authenticatedUsers = usersDao.authenticateCustomer(username, password);

        if (authenticatedUsers == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }
        return authenticatedUsers;
    }
}
