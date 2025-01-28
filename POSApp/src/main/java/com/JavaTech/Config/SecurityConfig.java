package com.JavaTech.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.JavaTech.Entity.UserInfoService;
import com.JavaTech.JwtFilter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableMethodSecurity
public class SecurityConfig {
	@Lazy
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoService();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	
	@Bean
	public SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception {       
		http
        .csrf(csrf -> csrf.disable())  
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/auth").permitAll()  
            .requestMatchers("/user/add/**").authenticated()  
            .requestMatchers("/user/findUser/**").authenticated()  
         )
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and().authenticationProvider(authenticationProvider())
         .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
         return http.build();    
	}
	/*@Bean
	public SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception {       
		http
        .csrf(csrf -> csrf.disable())  
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/auth").permitAll()  
            .requestMatchers("/user/add/**").authenticated()  
            .requestMatchers("/user/findUser/**").authenticated()  
         )
        .formLogin()
        .defaultSuccessUrl("/user/welcome")
        .failureUrl("/login?error=true");     
         return http.build();    
	}*/
	
	/*@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeHttpRequests(auth->auth
		.requestMatchers("/user/**").permitAll());
		return http.build();
		
	}*/

}
