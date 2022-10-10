package com.revature.bailey;

import com.revature.bailey.classes.Classes;
import com.revature.bailey.classes.ClassesDao;
import com.revature.bailey.users.Users;
import com.revature.bailey.users.UsersDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

import static org.springframework.context.ConfigurableApplicationContext.*;

@SpringBootApplication
public class BaileyApplication {

	public static void main(String[] args) {

		SpringApplication.run(BaileyApplication.class, args);

	}



}
