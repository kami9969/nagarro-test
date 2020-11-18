package com.nagarro.test.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

	public static boolean hasRole(String roleName) {
		return SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getAuthorities()
				.stream()
				.anyMatch(
						grantedAuthority -> grantedAuthority.getAuthority()
								.equals(roleName));
	}
}
