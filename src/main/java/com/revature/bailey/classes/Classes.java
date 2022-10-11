package com.revature.bailey.classes;

import com.revature.bailey.users.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity

@Table(name = "classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(name = "cname", length = 50, nullable = false, unique = true)
    private String cname;
    @Column(name = "sdate", length = 15, nullable = false)
    private String sdate;
    @Column(name = "edate", length = 15, nullable = false)
    private String edate;
    @ManyToMany( cascade = CascadeType.PERSIST)
    @JoinTable(name = "usersClasses",
            joinColumns = {
                    @JoinColumn(name = "C_ID", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "U_ID", referencedColumnName = "id")})
    private Set<Users> users = new HashSet<>();

    public Classes() {
    }

    @Autowired
    public Classes(String cname, String sdate, String edate, String enroll) {
        this.cname = cname;
        this.sdate = sdate;
        this.edate = edate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }
    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", sdate='" + sdate + '\'' +
                ", edate='" + edate + '\'' +
                ", users=" + users +
                '}';
    }
}
