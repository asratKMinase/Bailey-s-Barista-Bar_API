package com.revature.bailey.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    private String username;
    @Column(name = "fname", length = 50, nullable = false)
    private String fname;
    @Column(name = "lname", length = 50, nullable = false)
    private String lname;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String  rdate;
    @Column(name = "is_teacher")
    private boolean isTeacher;
    @Column(name = "is_admin")
    private boolean isAdmin;
    private String email;
}