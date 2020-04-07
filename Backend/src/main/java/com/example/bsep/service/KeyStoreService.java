package com.example.bsep.service;
import com.example.bsep.dtos.CertificateDTO;
import com.example.bsep.model.CertType;
import com.example.bsep.model.IssuerData;

import com.example.bsep.DTOs.CertificateCreationDTO;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import javax.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;


@Service
public class KeyStoreService {


    public List<CertificateDTO> getAll() {
		return getCertificates()
			.stream()
			.map(CertificateDTO::new)
			.collect(Collectors.toList());
    }
    

    public Optional<X509Certificate> getOne(String serialNumber) {

        Optional<X509Certificate> x509Certificate=getCertificate(serialNumber, ("passwordRoot").toCharArray(),"keystoreRoot.jks");

        if(x509Certificate==null){
           x509Certificate=getCertificate(serialNumber, ("passwordIntermediate").toCharArray(),"keystoreIntermediate.jks");
        
        }
        else {

            x509Certificate=getCertificate(serialNumber, ("passwordEND").toCharArray(),"keystoreEND.jks");
        }

       


		return x509Certificate;
	}


    public void store(X509Certificate[] chain, PrivateKey privateKey,CertType type) {
        char[] password = ("password").toCharArray();
        String fileName = "keystore.jks";
        if(type==CertType.ROOT){
        password = ("passwordRoot").toCharArray();   
        fileName="keystoreRoot.jks";
        }
        else if(type==CertType.INTERMEDIATE){
        password = ("passwordIntermediate").toCharArray();
        fileName = "keystoreIntermediate.jks";

        }
        else{
        password = ("passwordEND").toCharArray();  
        fileName = "keystoreEND.jks";   
        }

        String serialNumber = chain[0].getSerialNumber().toString();
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try {
                keyStore.load(new FileInputStream(fileName), password);
            } catch (IOException e) {
                keyStore.load(null, null);
            }

            keyStore.setKeyEntry(serialNumber, privateKey, serialNumber.toCharArray(), chain);
            keyStore.store(new FileOutputStream(fileName), password);
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

  



    public IssuerData findCAbySerialNumber(String serialNumber) {
        
        try {
            char[] password = ("passwordRoot").toCharArray();
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream("keystoreRoot.jks"), password);

            Key key = keyStore.getKey(serialNumber, serialNumber.toCharArray());
            if (key instanceof PrivateKey) {
                X509Certificate cert = (X509Certificate) keyStore.getCertificate(serialNumber);
                return new IssuerData((PrivateKey) key, new JcaX509CertificateHolder(cert).getSubject(),
                        cert.getPublicKey(), cert.getSerialNumber());
            } else {
                password = ("passwordIntermediate").toCharArray();
                KeyStore keyStore2 = KeyStore.getInstance("PKCS12");
                keyStore2.load(new FileInputStream("keystoreIntermediate.jks"), password);
    
                Key key2 = keyStore2.getKey(serialNumber, serialNumber.toCharArray());
                if (key2 instanceof PrivateKey) {
                    X509Certificate cert = (X509Certificate) keyStore2.getCertificate(serialNumber);
                    return new IssuerData((PrivateKey) key2, new JcaX509CertificateHolder(cert).getSubject(),
                            cert.getPublicKey(), cert.getSerialNumber());
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<X509Certificate> getCertificates() {	
		List<X509Certificate> certificates = new ArrayList<>();
     
        
        certificates.addAll(getKeyStoreCertificates(("passwordRoot").toCharArray(),"keystoreRoot.jks"));
        certificates.addAll(getKeyStoreCertificates(("passwordIntermediate").toCharArray(),"keystoreIntermediate.jks"));
		certificates.addAll(getKeyStoreCertificates(("passwordEND").toCharArray(),"keystoreEND.jks"));
		return certificates;
    }



    public List<X509Certificate> getKeyStoreCertificates(char[] password,String keyStoreName){

        List<X509Certificate> certificates = new ArrayList<>();
		
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream(keyStoreName), password);
			Enumeration<String> aliases = keyStore.aliases();
			
			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();
				
				if (keyStore.isKeyEntry(alias)) {		
					certificates.add(getCertificate(alias,password,keyStoreName).get());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return certificates;

    }
    
    public Optional<X509Certificate> getCertificate(String alias,char[] password,String keyStoreName) {
       
		try {
            
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream(keyStoreName), password);
			X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
			return Optional.ofNullable(cert);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
    
    




}
