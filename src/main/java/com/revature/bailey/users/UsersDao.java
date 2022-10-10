package com.revature.bailey.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao extends CrudRepository<Users, Long> {
//    List<Users> findByNameContaining(String username);
    @Query(value = "FROM Users WHERE username= :username AND password = :password")
    Users authenticateUsers(String username, String password);
}