package com.example.bsep.service;

import com.example.bsep.model.CertificateData;
import com.example.bsep.model.IssuerData;

import com.example.bsep.dtos.CertificateCreationDTO;

import java.util.List;
import java.util.Calendar;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;



@Service
public class CertificateDataService {


	public void newCertificate(CertificateCreationDTO certificateCreationDTO)
	{
		CertificateData certificateData = generateCertificateData(certificateCreationDTO);
			
		KeyPair keyPairIssuer = generateKeyPair();
		IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate(), certificateCreationDTO);

			
		X509Certificate cert = generateCertificate(certificateData, issuerData);

		

		
	}


	private IssuerData generateIssuerData(PrivateKey issuerKey, CertificateCreationDTO certificateCreationDTO) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    builder.addRDN(BCStyle.CN, certificateCreationDTO.getX500AdminCommonName());
	    builder.addRDN(BCStyle.SURNAME, certificateCreationDTO.getX500AdminSurname());
	    builder.addRDN(BCStyle.GIVENNAME, certificateCreationDTO.getX500AdminGivenname());
	    builder.addRDN(BCStyle.O, certificateCreationDTO.getX500AdminOrganization());
	    builder.addRDN(BCStyle.OU, certificateCreationDTO.getX500AdminOrganizationUnit());
	    builder.addRDN(BCStyle.C, certificateCreationDTO.getX500AdminCountry());
	    builder.addRDN(BCStyle.E, certificateCreationDTO.getAdministratorEmail());
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, certificateCreationDTO.getX500AdminUID());

		//Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
		return new IssuerData(issuerKey, builder.build());
	}

	private CertificateData generateCertificateData(CertificateCreationDTO certificateCreationDTO) {
		try {
			KeyPair keyPairIssuer = generateKeyPair();

			Calendar startDate = Calendar.getInstance();
			Calendar tempDate = Calendar.getInstance();
			tempDate.add(Calendar.YEAR, 3);
			Calendar endDate = tempDate;

			String serialNumber = certificateCreationDTO.getSerialNumber();

			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
			builder.addRDN(BCStyle.CN, certificateCreationDTO.getX500AdminCommonName());
			builder.addRDN(BCStyle.GIVENNAME, certificateCreationDTO.getX500RequestingGivenname());
			builder.addRDN(BCStyle.SURNAME, certificateCreationDTO.getX500RequestingSurname());
			builder.addRDN(BCStyle.O, certificateCreationDTO.getX500RequestingOrganizationUnit());
			builder.addRDN(BCStyle.OU, certificateCreationDTO.getX500RequestingOrganization());
			builder.addRDN(BCStyle.C, certificateCreationDTO.getX500RequestingCountry());
			builder.addRDN(BCStyle.E, certificateCreationDTO.getRequestingEmail());
			builder.addRDN(BCStyle.UID, certificateCreationDTO.getX500AdminUID());

			return new CertificateData(serialNumber, keyPairIssuer, builder.build(), startDate.getTime(), endDate.getTime());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


	public X509Certificate generateCertificate(CertificateData certificateData, IssuerData issuerData) {
		try {
			//Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni kljuc pravi se builder za objekat
			//Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
			//Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifiakta
			JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			//Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle
			builder = builder.setProvider("BC");

			//Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
			ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

			//Postavljaju se podaci za generisanje sertifiakta
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
					new BigInteger(certificateData.getSerialNumber()),
					certificateData.getValidFrom(),
					certificateData.getValidUntil(),
					certificateData.getX500Name(),
					certificateData.getKeyPairIssuer().getPublic());
			//Generise se sertifikat
			X509CertificateHolder certHolder = certGen.build(contentSigner);

			//Builder generise sertifikat kao objekat klase X509CertificateHolder
			//Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider("BC");

			//Konvertuje objekat u sertifikat
			return certConverter.getCertificate(certHolder);
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (OperatorCreationException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
		return null;
	}


	private KeyPair generateKeyPair(){
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
        return null;
	}


	

	public void verify(X509Certificate cert, KeyPair keyPairIssuer){
		try {
			
			//Moguce je proveriti da li je digitalan potpis sertifikata ispravan, upotrebom javnog kljuca izdavaoca
			cert.verify(keyPairIssuer.getPublic());
			System.out.println("\nValidacija uspesna :)");
			
		} catch(CertificateException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			System.out.println("\nValidacija neuspesna :(");
			e.printStackTrace();
		}
	}

	
}
