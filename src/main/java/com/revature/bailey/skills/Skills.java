package com.revature.bailey.skills;

import com.revature.bailey.courses.Courses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "skills")
public class Skills {
    @Id
    private String skillid;
    private String sname;
    @ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "courseid")
    private Courses courseid;
}
