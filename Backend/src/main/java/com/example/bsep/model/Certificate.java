package com.example.bsep.model;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

public class Certificate extends X509Certificate {

	private Long id;

	private CertificateData certificateData;

	public Certificate() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CertificateData getCertificateData() {
		return this.certificateData;
	}

	public void setCertificateData(CertificateData certificateData) {
		this.certificateData = certificateData;
	}

	@Override
	public boolean hasUnsupportedCriticalExtension() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getCriticalExtensionOIDs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getNonCriticalExtensionOIDs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getExtensionValue(String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigInteger getSerialNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Principal getIssuerDN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Principal getSubjectDN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getNotBefore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getNotAfter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getTBSCertificate() throws CertificateEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getSignature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSigAlgName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSigAlgOID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getSigAlgParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] getIssuerUniqueID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] getSubjectUniqueID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] getKeyUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBasicConstraints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getEncoded() throws CertificateEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verify(PublicKey key) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException,
			NoSuchProviderException, SignatureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void verify(PublicKey key, String sigProvider) throws CertificateException, NoSuchAlgorithmException,
			InvalidKeyException, NoSuchProviderException, SignatureException {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicKey getPublicKey() {
		// TODO Auto-generated method stub
		return null;
	}

	
}