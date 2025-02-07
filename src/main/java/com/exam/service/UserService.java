package com.exam.service;

import java.util.Set;

import com.exam.modal.User;
import com.exam.modal.UserRole;

public interface UserService {

	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	public User getUser(String username);

	public void deleteUser(Long userId);
}
