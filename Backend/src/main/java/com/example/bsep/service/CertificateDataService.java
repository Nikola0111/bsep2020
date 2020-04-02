package com.example.bsep.service;

import com.example.bsep.model.IssuerData;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Service
public class CertificateDataService {

private IssuerData generateIssuerData(PrivateKey issuerKey ) {
		// X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    // builder.addRDN(BCStyle.CN, "Nikola Luburic");
	    // builder.addRDN(BCStyle.SURNAME, "Luburic");
	    // builder.addRDN(BCStyle.GIVENNAME, "Nikola");
	    // builder.addRDN(BCStyle.O, "UNS-FTN");
	    // builder.addRDN(BCStyle.OU, "Katedra za informatiku");
	    // builder.addRDN(BCStyle.C, "RS");
	    // builder.addRDN(BCStyle.E, "nikola.luburic@uns.ac.rs");
	    // //UID (USER ID) je ID korisnika
	    // builder.addRDN(BCStyle.UID, "654321");

		//Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
		return new IssuerData();
	}


}
