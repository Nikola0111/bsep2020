package com.example.bsep.service;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;


import com.example.bsep.model.CertificateData;

import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Service;


@Service
public class SignatureService {




	public byte[] createSignature(PublicKey publicKey,PrivateKey privateKey, CertificateData certificateData)
	{


		byte[] dataToSign= SerializationUtils.serialize(certificateData);
	
		byte[] signature = sign(dataToSign, privateKey);
		
	
		return signature;
	}


	private byte[] sign(byte[] data, PrivateKey privateKey) {
		try {
			//Kreiranje objekta koji nudi funkcionalnost digitalnog potpisivanja
			//Prilikom getInstance poziva prosledjujemo algoritam koji cemo koristiti
			//U ovom slucaju cemo generisati SHA-1 hes kod koji cemo potpisati upotrebom RSA asimetricne sifre
			Signature sig = Signature.getInstance("SHA1withRSA");
			//Navodimo kljuc kojim potpisujemo 
			sig.initSign(privateKey);
			//Postavljamo podatke koje potpisujemo
			sig.update(data);
			
			//Vrsimo potpisivanje
			return sig.sign();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean verify(byte[] data, byte[] signature, PublicKey publicKey) {
		try {
			//Kreiranje objekta koji nudi funkcionalnost digitalnog potpisivanja
			//Prilikom getInstance poziva prosledjujemo algoritam koji cemo koristiti
			//U ovom slucaju cemo generisati SHA-1 hes kod koji cemo potpisati upotrebom RSA asimetricne sifre
			Signature sig = Signature.getInstance("SHA1withRSA");
			//Navodimo kljuc sa kojim proveravamo potpis 
			sig.initVerify(publicKey);
			//Postavljamo podatke koje potpisujemo
			sig.update(data);
			
			//Vrsimo proveru digitalnog potpisa
			return sig.verify(signature);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
		private void revokeCertificates(Certificate  sertifikatKojiSePovlaci, List<Certificates> sertifikatiZaPovlacenje, List<Certificates> sviSertifikati){
			for(int i = 0;i < sviSertifikati.size(); i++){
				if(sviSertifikati.get(i).parametar == sertifikatKojiSePovlaci.parametar) {
					Certificate zaPovlacenje = sviSertifikati.get(i);
					revokeCertificates(zaPovlacenje, sertifikatiZaPovlacenje, sviSertifikati);

					zaPovlacenje.setWithdrawn(true);
					sertifikatiZaPovlacenje.add(zaPovlacenje);
				}
			}
			sertifikatKojiSePovlaci.setWithdrawn(true);
			sertifikatiZaPovlacenje.add(sertifikatKojiSePovlaci);
		}
	*/
	
}
