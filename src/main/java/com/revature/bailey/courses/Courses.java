package com.revature.bailey.courses;

import com.revature.bailey.classes.Classes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "courses")
public class Courses {
    @Id
    private String courseid;
    @Column(unique = true)
    private String coname;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Classes id;
}
