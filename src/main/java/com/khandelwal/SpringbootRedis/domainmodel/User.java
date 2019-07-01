package com.khandelwal.SpringbootRedis.domainmodel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@javax.persistence.Id
	@SequenceGenerator(name = "SEQ_USER_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_GEN")
	private Long Id;
	private String name;
	private String[] roles;

	public User(String name, String[] roles) {
		super();
		this.name = name;
		this.roles = roles;
	}
}
