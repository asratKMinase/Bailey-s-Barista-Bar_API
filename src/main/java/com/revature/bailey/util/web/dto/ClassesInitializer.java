package com.revature.bailey.util.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesInitializer {
    private String classid;
    private String cname;
    private String sdate;
    private String edate;
    private String username;
}
