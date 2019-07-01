package com.khandelwal.SpringbootRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.khandelwal.SpringbootRedis.domainmodel.User;
import com.khandelwal.SpringbootRedis.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableCaching
public class SpringbootRedisApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	@Autowired
	public SpringbootRedisApplication(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("Current count of users in H2 database is {} ", userRepository.count());

		User abhishek = new User("Khandelwal", new String[] { "Admin", "Architect" });
		User kapil = new User("Gupta", new String[] { "user", "QA" });

		userRepository.save(abhishek);
		userRepository.save(kapil);

		log.info("Saved users are : {}", userRepository.findAll());
	}

}
