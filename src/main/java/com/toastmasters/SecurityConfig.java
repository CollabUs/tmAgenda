package com.toastmasters;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    // Securing the urls and allowing role-based access to these urls.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
        .antMatchers("/toastmasters").hasRole("ADMIN")
//        .antMatchers("/security/admin").hasRole("ADMIN")
        .and().csrf().disable();
    }
 
    // In-memory authentication to authenticate the user i.e. the user credentials are stored in the memory.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("guest").password("{noop}guest1234").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin1234").roles("ADMIN");
    }
}