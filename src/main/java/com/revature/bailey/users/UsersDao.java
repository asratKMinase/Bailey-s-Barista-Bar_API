package com.revature.bailey.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends CrudRepository<Users, String> {
    @Query(value = "FROM Users WHERE username= :username AND password = :password")
    Users authenticateUsers(String username, String password);
}