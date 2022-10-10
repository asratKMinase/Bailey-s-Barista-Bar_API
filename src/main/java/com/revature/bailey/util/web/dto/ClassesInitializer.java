package com.revature.bailey.util.web.dto;


import com.revature.bailey.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesInitializer {
    private long id;
    private String cname;
    private String sdate;
    private String edate;
    private String enroll;
    private String users;
}
