package com.revature.bailey.enroll;

import com.revature.bailey.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "enroll")
public class Enroll {
    @Id
    private String enrollid;
    @Column(unique = true)
    private String cname;
    private String enroll;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Users id;
}
