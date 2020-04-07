package com.example.bsep.service;
import com.example.bsep.dtos.CertificateDTO;
import com.example.bsep.model.CertType;
import com.example.bsep.model.IssuerData;
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
    

    public X509Certificate getOne(String serialNumber) {
		return getCertificate(serialNumber)
				.orElseThrow(NotFoundException::new);
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
        char[] password = ("password").toCharArray();
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream("keystore.jks"), password);

            Key key = keyStore.getKey(serialNumber, serialNumber.toCharArray());
            if (key instanceof PrivateKey) {
                X509Certificate cert = (X509Certificate) keyStore.getCertificate(serialNumber);
                return new IssuerData((PrivateKey) key, new JcaX509CertificateHolder(cert).getSubject(),
                        cert.getPublicKey(), cert.getSerialNumber());
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<X509Certificate> getCertificates() {	
		List<X509Certificate> certificates = new ArrayList<>();
		char[] password = ("password").toCharArray();
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream("keystore.jks"), password);
			Enumeration<String> aliases = keyStore.aliases();
			
			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();
				
				if (keyStore.isKeyEntry(alias)) {		
					certificates.add(getCertificate(alias).get());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return certificates;
    }
    
    public Optional<X509Certificate> getCertificate(String alias) {
        char[] password = ("password").toCharArray();
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream("keystore.jks"), password);
			X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
			return Optional.ofNullable(cert);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
    
    




}