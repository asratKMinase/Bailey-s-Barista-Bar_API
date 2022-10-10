package com.revature.bailey.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.bailey.classes.Classes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(unique = true)
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
    @ManyToMany(mappedBy = "users")
    private Set<Classes> classes = new HashSet<>();
    public Users() {
    }

    public Users(String username, String fname, String lname, String password, String rdate, boolean isTeacher, boolean isAdmin, String email) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.rdate = rdate;
        this.isTeacher = isTeacher;
        this.isAdmin = isAdmin;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Classes> getClasses() {
        return classes;
    }

    public void setClasses(Set<Classes> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", rdate='" + rdate + '\'' +
                ", isTeacher=" + isTeacher +
                ", isAdmin=" + isAdmin +
                ", email='" + email + '\'' +
                ", classes=" + classes +
                '}';
    }
}