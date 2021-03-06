package com.springwebapp.config;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SecureConfig extends WebSecurityConfigurerAdapter {
	
	/*@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.
		     inMemoryAuthentication()
		     .withUser("frodo")
		     .password("faszom")
		     .roles("USER")
		     .and()
		     .withUser("admin")
		     .password("tokom")
		     .roles("ADMIN");
	} */

	@Override
	protected void configure(HttpSecurity httpSec) throws Exception
	{
		httpSec
		        .authorizeRequests()
		          //.antMatchers(HttpMethod.GET,"/").permitAll()  csak ezekhez kér jelszót
		          //.antMatchers("/stories").hasAnyRole("USER,ADMIN")
		          // .antMatchers("/story").hasRole("ADMIN")
		          .antMatchers("/admin/**").hasRole("ADMIN")
		          .antMatchers("/registration").permitAll()
		          .antMatchers("/reg").permitAll()
		          .antMatchers("/activation/*").permitAll()
		          .antMatchers("/joke").permitAll()
		          .anyRequest().authenticated()  //mindenhez kér jelszót
		        .and()
		          .formLogin()
		          .loginPage("/login")
		          .permitAll()
		         .and()
		          .logout()
		            .logoutSuccessUrl("/login?logout")
		            .permitAll();
	}
}
