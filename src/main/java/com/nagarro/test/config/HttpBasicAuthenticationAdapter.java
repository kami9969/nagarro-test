package com.nagarro.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HttpBasicAuthenticationAdapter extends
		WebSecurityConfigurerAdapter {
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("testadmin")
				.password(passwordEncoder().encode("adminpassword"))
				.authorities(ROLE_ADMIN).and().withUser("testUser")
				.password(passwordEncoder().encode("userpassword"))
				.authorities(ROLE_USER);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//		.antMatchers("/rest/api/v1/statement")
//		.access("@webSecurity.checkUserHasAccessToProjectId(authentication)")
		.anyRequest().authenticated().
		and().formLogin()
		.defaultSuccessUrl("/", false)
				.successHandler(new MyAuthenticationSuccessHandler()).and()
				.httpBasic().and().csrf().disable();
		http.sessionManagement().maximumSessions(1)
				.expiredUrl("/login?expired");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}