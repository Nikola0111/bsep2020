package com.example.bsep.model;

import javax.persistence.Inheritance;

import java.security.KeyPair;
import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.InheritanceType;


public class CertificateData {
    
	private Long id;
	
	private String serialNumber;

	private KeyPair keyPairIssuer;

	private  IssuerData issuerData;

	private UserData userData;
        
    private Date validFrom;

    private Date validUntil;
        
     
    private boolean withdrawn;

    public CertificateData() { }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	



}