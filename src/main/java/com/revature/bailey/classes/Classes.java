package com.revature.bailey.classes;

import com.revature.bailey.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "classes")
public class Classes {
    @Id
    private String classid;
    @Column(name = "cname", length = 50, nullable = false)
    private String cname;
    @Column(name = "sdate", length = 15, nullable = false)
    private String sdate;
    @Column(name = "edate", length = 15, nullable = false)
    private String edate;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Users username;

}
