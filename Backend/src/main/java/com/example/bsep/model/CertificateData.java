package com.example.bsep.model;

import javax.persistence.Inheritance;
import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.InheritanceType;


public class CertificateData {
    
    private Long id;

    private String izdavac;

    private String korisnikIme;

    private Date validanOd;

    private Date validanDo;

    private String keyStoreNaziv;

    private boolean povucen;

    public CertificateData() { }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIzdavac() {
		return this.izdavac;
	}

	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}

	public String getKorisnikIme() {
		return this.korisnikIme;
	}

	public void setKorisnikIme(String korisnikIme) {
		this.korisnikIme = korisnikIme;
	}

	public Date getValidanOd() {
		return this.validanOd;
	}

	public void setValidanOd(Date validanOd) {
		this.validanOd = validanOd;
	}

	public Date getValidanDo() {
		return this.validanDo;
	}

	public void setValidanDo(Date validanDo) {
		this.validanDo = validanDo;
	}

	public boolean isPovucen() {
		return this.povucen;
	}

	public void setPovucen(boolean povucen) {
		this.povucen = povucen;
	} 
}