package com.example.bsep.service;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.cert.X509Certificate;


@Service
public class RevokeService {

    @Autowired
    KeyStoreService keyStoreService;


@SuppressWarnings("unchecked")
    public void revokeCertificate(String serialNumber) {
        System.out.println(serialNumber);
        System.out.println("Usao u revoke");
        X509Certificate certificate = keyStoreService.getOne(serialNumber);
        List<X509Certificate> certificates = new ArrayList<>();

        try {
            File file = new File("ocsp.ocsp");

            if (!file.exists()) {
                saveOCSP(certificates, file);
            } else {
                ObjectInputStream iis = new ObjectInputStream(new FileInputStream(file));
                certificates = (List<X509Certificate>) iis.readObject();
                iis.close();
            }

            for (X509Certificate cert : certificates) {
                if (cert.getSerialNumber().equals(certificate.getSerialNumber())) {
                    return;
                }
            }

            String issuer = certificate.getSubjectX500Principal().getName();

            List<X509Certificate> allCertificates =keyStoreService.getCertificates();

            List<X509Certificate> revokeList = allCertificates
                .stream()
                .filter(c -> c.getIssuerX500Principal().getName().equals(issuer))
                .collect(Collectors.toList());

            allCertificates.removeAll(revokeList);
            recursion(certificates, revokeList, allCertificates);

            certificates.add(certificate);
            certificates.addAll(revokeList);
            saveOCSP(certificates, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveOCSP(List<X509Certificate> certificates, File file) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(certificates);
		oos.flush();
		oos.close();
	}



    public void recursion(List<X509Certificate> certificates, List<X509Certificate> revokeList, List<X509Certificate> allCertificates) {		
		revokeList.forEach(rc -> {
			List<X509Certificate> childRevokeList = allCertificates
				.stream()
				.filter(c -> c.getIssuerX500Principal().getName().equals(rc.getSubjectX500Principal().getName()))
				.collect(Collectors.toList());
			
			certificates.addAll(childRevokeList);
			allCertificates.removeAll(childRevokeList);
			recursion(certificates, childRevokeList, allCertificates);
		});
    }
    


    

}
