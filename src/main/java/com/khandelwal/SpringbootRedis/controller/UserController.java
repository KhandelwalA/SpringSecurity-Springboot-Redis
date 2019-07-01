package com.khandelwal.SpringbootRedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khandelwal.SpringbootRedis.domainmodel.User;
import com.khandelwal.SpringbootRedis.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	/**
	 * This method fetch the user from the redis cache if user is available else it
	 * fetch the user from database and add it in redis cache
	 * 
	 * @param userId
	 * @return
	 */
	@Cacheable(value = "users", key = "#userId")
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getUser/{userId}")
	public Object getUser(@PathVariable String userId) {

		log.info("Fetching user with id {}", userId);
		return userRepository.findById(Long.valueOf(userId));
	}

	/**
	 * This method update the user in database and also updates the redis cache
	 * 
	 * @param user
	 * @return
	 */
	@CachePut(value = "users", key = "#userId")
	@SuppressWarnings("unchecked")
	@PutMapping(value = "/updateUser")
	public Object updateUser(@RequestBody User user) {

		log.info("Updating user with id {}", user.getId());
		return userRepository.save(user);
	}

	/**
	 * This method delete the user from database and also clears the redis cache
	 * 
	 * @param id
	 */
	@CacheEvict(value = "users", allEntries = true)
	@SuppressWarnings("unchecked")
	@PutMapping(value = "/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id) {

		log.info("deleting user with id {}", id);
		userRepository.deleteById(id);
	}
}
