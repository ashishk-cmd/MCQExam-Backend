package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class MySecurityConfig {

	public static final String[] PUBLIC_URLS= {
			"/generate-token","/user/**"
	};
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		return http .csrf(csrf -> csrf.disable())
			 .cors(cors -> cors.disable())
			 .authorizeHttpRequests((authorize) -> authorize
					 .requestMatchers("/generate-token","/user/**").permitAll()
					 .requestMatchers(HttpMethod.OPTIONS).permitAll()
					 .anyRequest().authenticated())
			 .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			 .exceptionHandling(excep -> excep.authenticationEntryPoint(unauthorizedHandler))
			 .authenticationProvider(authenticationProvider())
			 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			 .build();

	}
}
