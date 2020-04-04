package com.example.bsep.model;


import java.security.KeyPair;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x500.X500Name;



public class CertificateData {
    
	private Long id;
	
	private String serialNumber;

	private KeyPair keyPairIssuer;

	private X500Name x500Name;

	private Date validFrom;

	private Date validUntil;

	private boolean withdrawn;

	

	public CertificateData() { }
	
	public CertificateData(String serialNumber, KeyPair keyPairIssuer, X500Name x500Name, Date validFrom, Date validUntil){
		this.serialNumber = serialNumber;
		this.keyPairIssuer = keyPairIssuer;
		this.x500Name = x500Name;
		this.validFrom=validFrom;
		this.validUntil=validUntil;
		this.withdrawn=false;


	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public KeyPair getKeyPairIssuer() {
		return this.keyPairIssuer;
	}

	public void setKeyPairIssuer(KeyPair keyPairIssuer) {
		this.keyPairIssuer = keyPairIssuer;
	}


	public X500Name getX500Name() {
		return this.x500Name;
	}

	public void setX500Name(X500Name X500Name) {
		this.x500Name = X500Name;
	}

	

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidUntil() {
		return this.validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}



	public boolean isWithdrawn() {
		return this.withdrawn;
	}

	public void setWithdrawn(boolean withdrawn) {
		this.withdrawn = withdrawn;
	}
	
	

}