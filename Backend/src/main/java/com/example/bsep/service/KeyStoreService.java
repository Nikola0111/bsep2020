package com.example.bsep.service;
import com.example.bsep.dtos.CertificateDTO;
import com.example.bsep.model.CertType;
import com.example.bsep.model.IssuerData;

import com.example.bsep.dtos.CertificateCreationDTO;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

        X509Certificate x509Certificate=getCertificate(serialNumber, ("passwordRoot").toCharArray(),"keystoreRoot.jks");

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

    public List<CertificateDTO> getCAs (){
        List<X509Certificate> certificates = new ArrayList<>();
        List<CertificateDTO> certificateDTOs=new ArrayList<>();
        
        certificates.addAll(getKeyStoreCertificates(("passwordRoot").toCharArray(),"keystoreRoot.jks"));
        certificates.addAll(getKeyStoreCertificates(("passwordIntermediate").toCharArray(),"keystoreIntermediate.jks"));

        for (X509Certificate cert : certificates) {

            certificateDTOs.add(new CertificateDTO(cert));
            
        }

        return certificateDTOs;
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
                    
                    if(getCertificate(alias,password,keyStoreName)!=null){
                    certificates.add(getCertificate(alias,password,keyStoreName));
                    }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return certificates;

    }
    
    public X509Certificate getCertificate(String alias,char[] password,String keyStoreName) {
       
		try {
            
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream(keyStoreName), password);
            X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
            
            
            
            if(checkValidity(cert)){
            return cert;
            }
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }
    

    @SuppressWarnings("unchecked")
	private boolean checkValidity(X509Certificate x509Certificate) {
		
		List<X509Certificate> certificates = new ArrayList<>();
		
		File file = new File("ocsp.ocsp");
		
		if (!file.exists()) {
			return true;
		}
		
		try {
			ObjectInputStream iis = new ObjectInputStream(new FileInputStream(file));
			certificates = (List<X509Certificate>) iis.readObject();
			iis.close();
		} catch (Exception e) {
			e.printStackTrace();
        }
        
        for (X509Certificate cert : certificates) {
			if (cert.getSerialNumber().equals(x509Certificate.getSerialNumber())) {
				return false;
			}
		} 
		
		try {
			x509Certificate.checkValidity();
		} catch (Exception e) {
            e.printStackTrace();
            return false;
		}
		
	
		
		return true;
    }
    

    public void download( String serialNumber ) {
        

        
        String fileName = serialNumber+".cert";
       X509Certificate certificate= getOne(serialNumber);
       X509Certificate[] chain= new X509Certificate[]{certificate};
      
        try {
            KeyStore keyStore = KeyStore.getInstance("PEM");
            try {
                keyStore.load(new FileInputStream(fileName),null);
            } catch (IOException e) {
                keyStore.load(null, null);
            }

            keyStore.setKeyEntry(serialNumber, null, serialNumber.toCharArray(),chain);
            keyStore.store(new FileOutputStream(fileName), null);
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
	
    
    




}
