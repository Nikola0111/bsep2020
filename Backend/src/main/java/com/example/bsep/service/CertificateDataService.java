package com.example.bsep.service;

import com.example.bsep.model.CertType;
import com.example.bsep.model.CertificateData;
import com.example.bsep.model.IssuerData;
import com.example.bsep.dtos.CertificateCreationDTO;
import java.util.Calendar;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Service
public class CertificateDataService {


	@Autowired
	KeyStoreService keyStoreService;

	
	

	public void save(CertificateCreationDTO  certificateCreationDTO) {
		BigInteger issuerBI=certificateCreationDTO.getIssuerBI();
		String issuerSN=null;
		CertType type=certificateCreationDTO.getCertType();
		//ako je BI null proce i upasce u root
		if(issuerBI!=null){
			issuerSN=issuerBI.toString();

		}

		if(type!=CertType.INTERMEDIATE && type!=CertType.ENDENTITY){

			type=CertType.ROOT;
		}
		
		KeyPair keyPair;
		type=certificateCreationDTO.getCertType();
        CertificateData subject;
        IssuerData issuer;
        X509Certificate certificate;
	

        if (issuerSN == null || type == CertType.ROOT) {
			System.out.print("Usao u root ");
            keyPair = generateKeyPair();
            subject = generateCertificateData(keyPair.getPublic(), certificateCreationDTO, true);
            issuer =createRootIssuerData(keyPair, certificateCreationDTO, subject.getSerialNumber());
            certificate = generateCertificate(subject, issuer, true);
        } else if (type == CertType.INTERMEDIATE) {
            keyPair = generateKeyPair();
            subject = generateCertificateData(keyPair.getPublic(), certificateCreationDTO, true);
            issuer = keyStoreService.findCAbySerialNumber(issuerSN);
            certificate = generateCertificate(subject, issuer, true);
        }
        else {
            keyPair = generateKeyPair();
            issuer = keyStoreService.findCAbySerialNumber(issuerSN);
            subject = generateCertificateData(keyPair.getPublic(), certificateCreationDTO, false);
            certificate = generateCertificate(subject, issuer, false);
        }

        try {
			
			keyStoreService.store(new X509Certificate[]{certificate}, keyPair.getPrivate(), type);
			
           
        } catch(Exception e){
			e.printStackTrace();
		}
    }


	
	private CertificateData generateCertificateData(PublicKey publicKey,CertificateCreationDTO certificateCreationDTO, boolean isCA) {
		try {
		
			Calendar startDate = Calendar.getInstance();
			Calendar tempDate = Calendar.getInstance();
			tempDate.add(Calendar.YEAR, 5);
			Calendar endDate = tempDate;

			long now = System.currentTimeMillis();
			BigInteger serialNumber =  new BigInteger(Long.toString(now));

			//FALI PROVERA VALIDNOSTI DATU

			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
			builder.addRDN(BCStyle.CN, certificateCreationDTO.getX500RequestingCommonName());
			builder.addRDN(BCStyle.GIVENNAME, certificateCreationDTO.getX500RequestingGivenname());
			builder.addRDN(BCStyle.SURNAME, certificateCreationDTO.getX500RequestingSurname());
			builder.addRDN(BCStyle.O, certificateCreationDTO.getX500RequestingOrganizationUnit());
			builder.addRDN(BCStyle.OU, certificateCreationDTO.getX500RequestingOrganization());
			builder.addRDN(BCStyle.C, certificateCreationDTO.getX500RequestingCountry());
			builder.addRDN(BCStyle.E, certificateCreationDTO.getRequestingEmail());
			builder.addRDN(BCStyle.UID, certificateCreationDTO.getX500RequestingUID());

			System.out.print("pROSLO SVE ");
			return new CertificateData(serialNumber, publicKey, builder.build(), startDate.getTime(),endDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("VRATIO JE NULL PUKO JE ");
		return null;
		
	}


	private IssuerData createRootIssuerData(KeyPair keyPair,CertificateCreationDTO certificateCreationDTO,BigInteger serialNumber ){


		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
			builder.addRDN(BCStyle.CN, certificateCreationDTO.getX500RequestingCommonName());
			builder.addRDN(BCStyle.GIVENNAME, certificateCreationDTO.getX500RequestingGivenname());
			builder.addRDN(BCStyle.SURNAME, certificateCreationDTO.getX500RequestingSurname());
			builder.addRDN(BCStyle.O, certificateCreationDTO.getX500RequestingOrganizationUnit());
			builder.addRDN(BCStyle.OU, certificateCreationDTO.getX500RequestingOrganization());
			builder.addRDN(BCStyle.C, certificateCreationDTO.getX500RequestingCountry());
			builder.addRDN(BCStyle.E, certificateCreationDTO.getRequestingEmail());
			builder.addRDN(BCStyle.UID, certificateCreationDTO.getX500RequestingUID());

	return	new IssuerData(keyPair.getPrivate(),builder.build(), keyPair.getPublic(), serialNumber);

		
	}

	public X509Certificate generateCertificate(CertificateData certificateData, IssuerData issuerData, boolean Ca ) {
		try {
			// Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni
			// kljuc pravi se builder za objekat
			// Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za
			// potpisivanje sertifikata
			// Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje
			// sertifiakta
			JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			// Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle
			builder = builder.setProvider("BC");

			// Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za
			// potpisivanje sertifikata
			ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

			// Postavljaju se podaci za generisanje sertifiakta
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
				issuerData.getX500name(),
				certificateData.getSerialNumber(),
				certificateData.getValidFrom(),
				certificateData.getValidUntil(),
				certificateData.getX500Name(),
				certificateData.getPublicKey());
				
				//org.bouncycastle.asn1.x509.SubjectPublicKeyInfo
			//AuthorityKeyIdentifier auth = new AuthorityKeyIdentifier(certificateData.getKeyPairIssuer().getPublic());
		
			//SubjectKeyIdentifier ski = new SubjectKeyIdentifier(certificateData.getKeyPairIssuer().getPublic());

			JcaX509ExtensionUtils extensionUtils=new JcaX509ExtensionUtils();
	
			BasicConstraints basicConstraints = new BasicConstraints(true);
			SubjectKeyIdentifier ski = extensionUtils.createSubjectKeyIdentifier(certificateData.getPublicKey());
			AuthorityKeyIdentifier aki = extensionUtils.createAuthorityKeyIdentifier(issuerData.getPublicKey());
			
				certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.19"), true, basicConstraints);
				certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.15"), true, new KeyUsage(KeyUsage.digitalSignature));
				certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.14"), false, ski);
				certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.35"), false, aki);

			
			
			//Generise se sertifikat
			X509CertificateHolder certHolder = certGen.build(contentSigner);

			//Builder generise sertifikat kao objekat klase X509CertificateHolder
			//Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider("BC");

			//Konvertuje objekat u sertifikat
			return certConverter.getCertificate(certHolder);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

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
			
			System.out.println("\nValidacija uspesna :)");
			
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








	/*
		void revokeAllChildren(Certificate sertifikat, List<Certifikate)
	*/
}
