package com.example.bsep.model;

import javax.persistence.Entity;
import org.bouncycastle.asn1.x500.X500Name;

//@Entity
public class Administrator {

	private Long id;

	private String username;

	private String password;

	public Administrator(){


	}

	public String getUsername(){

		return this.username;
	}

	public Long getId(){

		return id;
	}



}
