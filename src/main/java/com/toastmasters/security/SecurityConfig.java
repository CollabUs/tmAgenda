package com.toastmasters.security;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.toastmasters.service.UserService;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	UserService userService;
	
//    In-memory authentication to authenticate the user i.e. the user credentials are stored in the memory.
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("guest").password("{noop}guest1234").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin1234").roles("ADMIN");
//    }
	
	//Performing authentication via DB
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(getPasswordEncoder());
	}
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
        .antMatchers("/user/register","/user/test").permitAll()
        .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
        .and()
        .formLogin()
        .permitAll();
     
        
        http.cors().disable()
        	.csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return BCrypt.checkpw(rawPassword.toString(),encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return BCrypt.hashpw(rawPassword.toString(),BCrypt.gensalt(4));
			}
		};
    }
 

}