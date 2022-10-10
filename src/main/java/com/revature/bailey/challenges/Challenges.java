package com.revature.bailey.challenges;


import com.revature.bailey.classes.Classes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "challenges")
public class Challenges {
    @Id
    private String chaid;
    private String chname;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Classes id;
}
