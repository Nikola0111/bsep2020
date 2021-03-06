
package com.example.bsep.dtos;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;

import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

public class CertificateDTO {

	private String commonName;
	private String givenname;
	private String surname;
	private String organization;
	private String organizationalUnitName;
	private String countryCode;
	private String email;
	private BigInteger serialNumber;
	private String validFrom;
	private String validUntil;
	private String status;
	
	public CertificateDTO(X509Certificate certificate) {
		try {
			X500Name name = new JcaX509CertificateHolder(certificate).getSubject();
			RDN[] rnds = name.getRDNs();
		

			for (RDN rdn: rnds) {
				AttributeTypeAndValue[] values = rdn.getTypesAndValues();
				for (AttributeTypeAndValue val : values) {
					if (val.getType().equals(BCStyle.CN)) {
						commonName = val.getValue().toString();
					} else if (val.getType().equals(BCStyle.GIVENNAME)) {
						givenname = val.getValue().toString();
					} else if (val.getType().equals(BCStyle.SURNAME)) {
						surname = val.getValue().toString();
					} else if (val.getType().equals(BCStyle.O)) {
						organization = val.getValue().toString();
					} else if (val.getType().equals(BCStyle.OU)) {
						organizationalUnitName = val.getValue().toString();
					} else if (val.getType().equals(BCStyle.C)) {
						countryCode = val.getValue().toString();
					} else if (val.getType().equals(BCStyle.E)) {
						email = val.getValue().toString();
					}
				}
			}

			serialNumber = certificate.getSerialNumber();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			
			//EVO NIKOLICE STRING JE 
			validFrom = formatter.format(certificate.getNotBefore());
			validUntil=formatter.format(certificate.getNotAfter());

		}  catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	public String getCommonName() {
		return commonName;
	}

	public String getGivenname() {
		return givenname;
	}

	public String getSurname() {
		return surname;
	}

	public String getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidUntil() {
		return this.validUntil;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}


	public String getOrganization() {
		return organization;
	}

	public String getOrganizationalUnitName() {
		return organizationalUnitName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getEmail() {
		return email;
	}
	
	public String getStatus() {
		return status;
	}
}