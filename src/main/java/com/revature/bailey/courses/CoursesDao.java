package com.revature.bailey.courses;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesDao extends CrudRepository<Courses, String> {

}