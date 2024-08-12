package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.helper.UserFoundException;
import com.exam.modal.JwtRequest;
import com.exam.modal.JwtResponse;
import com.exam.modal.User;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService  userdetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	
	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println("genertae-token");
		try {
			this.authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			throw new Exception("Invalid username or password !!" + e.getMessage());
		}
		
		UserDetails userDetails = this.userdetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	
	
	
	
	private void authenticate(String username,String password) throws Exception {		
		UsernamePasswordAuthenticationToken authenticationToken = new  UsernamePasswordAuthenticationToken(username,password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (DisabledException e) {
			throw new Exception("User Disabled " + e.getMessage());
		}catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials " + e.getMessage());
		} 
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User)userdetailsService.loadUserByUsername(principal.getName()));
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUserFoundException(Exception ex) {
        // You can customize the response as needed
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
