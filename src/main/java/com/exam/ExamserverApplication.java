package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.modal.Role;
import com.exam.modal.User;
import com.exam.modal.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Code");
		
		/*
		 * User user = new User();
		 * 
		 * user.setFirstName("Ashish"); user.setLastName("Kumar");
		 * user.setUsername("ashish123"); user.setPassword("abc");
		 * 
		 * Role role1 = new Role(); role1.setRoleId(44L); role1.setRoleName("ADMIN");
		 * 
		 * Set<UserRole> userRoleSet = new HashSet<>(); UserRole userRole = new
		 * UserRole(); userRole.setRole(role1); userRole.setUser(user);
		 * userRoleSet.add(userRole);
		 * 
		 * User user1 = this.userService.createUser(user,userRoleSet);
		 * System.out.println(user1.getUsername());
		 */
		
	}
	
	

}
