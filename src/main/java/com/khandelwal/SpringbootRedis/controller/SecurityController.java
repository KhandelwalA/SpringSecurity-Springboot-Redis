package com.khandelwal.SpringbootRedis.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SecurityController {

	/**
	 * Getting login user information from Principal object injected by Spring boot
	 * @param user
	 */
	@GetMapping (value="/getUserByPrincipal")
	public String getUserDetailsByPrincipal (Principal user) {
		
		log.info ("User name is: {}", user.getName());
		return user.getName();
	}
	
	/**
	 * Getting logged in user details through Threadlocal SecurityContextHolder
	 */
	@GetMapping (value="/getUserBySecurityContext")
	public String getUserDetailsBySecurityContext () {
		
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		log.info ("User name is: {} & details are {}", user.getName(), user.getDetails().toString());
		return user.getDetails().toString();
	}
}
