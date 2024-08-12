package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.helper.UserFoundException;
import com.exam.modal.User;
import com.exam.modal.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.userRepository.findByUsername(user.getUsername());
		if(local != null) {
			System.out.println("User already exist !!");
			throw new UserFoundException();
		}else {
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserroles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}

	@Override
	public User getUser(String username) {
		User user = this.userRepository.findByUsername(username);
		return user;
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}

}
