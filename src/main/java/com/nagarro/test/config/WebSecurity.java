package com.nagarro.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

@Configuration
public class WebSecurity {
	public boolean checkUserHasAccessToProjectId(Authentication authentication) {
		if(authentication.isAuthenticated()) {
			if(authentication.getAuthorities().contains("ROLE_ADMIN")) {
				return true;
			}
		}
		return false;
	}
}
