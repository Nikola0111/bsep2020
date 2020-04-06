
package com.example.bsep.DTOs;


import java.math.BigInteger;

import com.example.bsep.model.CertType;

public class CertificateCreationDTO {

	private Long requestingID;

	private CertType CertType;

	private String requestingName;

	private String requestingSurname;

	private String requestingEmail;

	private String x500requestingCommonName;

	private String x500RequestingSurname;

	private String x500RequestingGivenname;

	private String x500RequestingOrganizationUnit;

	private String x500RequestingOrganization;

	private String x500RequestingLocality;

	private String x500RequestingState;

	private String x500RequestingCountry;

	private String x500RequestingUID;

	private BigInteger serialNumber;

	private BigInteger issuerBI;
  
	// private Long administratorID;

	// private String administratorName;

	// private String administratorSurname;

	// private String administratorEmail;

	// private String X500AdminCommonName;

	// private String X500AdminSurname;

	// private String X500AdminGivenname;

	// private String X500AdminOrganizationUnit;

	// private String X500AdminOrganization;

	// private String X500AdminLocality;

	// private String X500AdminState;

	// private String X500AdminCountry;

	// private String X500AdminUID;

	public CertificateCreationDTO(Long requestingID, String requestingName, String requestingSurname, String requestingEmail, String x500requestingCommonName, String x500RequestingSurname, String x500RequestingGivenname, String x500RequestingOrganizationUnit, String x500RequestingOrganization, String x500RequestingLocality, String x500RequestingState, String x500RequestingCountry, String x500RequestingUID, Long administratorID, String administratorName, String administratorSurname, String administratorEmail, String x500AdminCommonName, String x500AdminSurname, String x500AdminGivenname, String x500AdminOrganizationUnit, String x500AdminOrganization, String x500AdminLocality, String x500AdminState, String x500AdminCountry, String x500AdminUID, BigInteger serialNumber, BigInteger issuerBI) {
		this.requestingID = requestingID;
		this.requestingName = requestingName;
		this.requestingSurname = requestingSurname;
		this.requestingEmail = requestingEmail;
		this.x500requestingCommonName = x500requestingCommonName;
		this.x500RequestingSurname = x500RequestingSurname;
		this.x500RequestingGivenname = x500RequestingGivenname;
		this.x500RequestingOrganizationUnit = x500RequestingOrganizationUnit;
		this.x500RequestingOrganization = x500RequestingOrganization;
		this.x500RequestingLocality = x500RequestingLocality;
		this.x500RequestingState = x500RequestingState;
		this.x500RequestingCountry = x500RequestingCountry;
		this.x500RequestingUID = x500RequestingUID;
		this.serialNumber = serialNumber;
		this.issuerBI = issuerBI;
	}

	public CertificateCreationDTO() {

	
	}

	public Long getRequestingID() {
		return this.requestingID;
	}

	public void setRequestingID(Long requestingID) {
		this.requestingID = requestingID;
	}

	public BigInteger getIssuerBI() {
		return this.issuerBI;
	}

	public void setIssuerBI(BigInteger issuerBI) {
		this.issuerBI = issuerBI;
	}

	public String getRequestingName() {
		return this.requestingName;
	}

	public void setRequestingName(String requestingName) {
		this.requestingName = requestingName;
	}

	public String getRequestingSurname() {
		return this.requestingSurname;
	}

	public void setRequestingSurname(String requestingSurname) {
		this.requestingSurname = requestingSurname;
	}

	public String getRequestingEmail() {
		return this.requestingEmail;
	}

	public void setRequestingEmail(String requestingEmail) {
		this.requestingEmail = requestingEmail;
	}

	public String getX500requestingCommonName() {
		return x500requestingCommonName;
	}

	public void setX500requestingCommonName(String x500requestingCommonName) {
		this.x500requestingCommonName = x500requestingCommonName;
	}

	public String getX500RequestingSurname() {
		return x500RequestingSurname;
	}

	public void setX500RequestingSurname(String x500RequestingSurname) {
		this.x500RequestingSurname = x500RequestingSurname;
	}

	public String getX500RequestingGivenname() {
		return x500RequestingGivenname;
	}

	public void setX500RequestingGivenname(String x500RequestingGivenname) {
		this.x500RequestingGivenname = x500RequestingGivenname;
	}

	public String getX500RequestingOrganizationUnit() {
		return x500RequestingOrganizationUnit;
	}

	public void setX500RequestingOrganizationUnit(String x500RequestingOrganizationUnit) {
		this.x500RequestingOrganizationUnit = x500RequestingOrganizationUnit;
	}

	public String getX500RequestingOrganization() {
		return x500RequestingOrganization;
	}

	public void setX500RequestingOrganization(String x500RequestingOrganization) {
		this.x500RequestingOrganization = x500RequestingOrganization;
	}

	public String getX500RequestingLocality() {
		return x500RequestingLocality;
	}

	public void setX500RequestingLocality(String x500RequestingLocality) {
		this.x500RequestingLocality = x500RequestingLocality;
	}

	public String getX500RequestingState() {
		return x500RequestingState;
	}

	public void setX500RequestingState(String x500RequestingState) {
		this.x500RequestingState = x500RequestingState;
	}

	public String getX500RequestingCountry() {
		return x500RequestingCountry;
	}

	public void setX500RequestingCountry(String x500RequestingCountry) {
		this.x500RequestingCountry = x500RequestingCountry;
	}

	public String getX500RequestingUID() {
		return x500RequestingUID;
	}

	public void setX500RequestingUID(String x500RequestingUID) {
		this.x500RequestingUID = x500RequestingUID;
	}

//	public Long getAdministratorID() {
//		return administratorID;
//	}
//
//	public void setAdministratorID(Long administratorID) {
//		this.administratorID = administratorID;
//	}


	// public String getAdministratorName() {
	// 	return this.administratorName;
	// }


	// public void setAdministratorName(String administratorName) {
	// 	this.administratorName = administratorName;
	// }

	// public String getAdministratorSurname() {
	// 	return this.administratorSurname;
	// }

	// public void setAdministratorSurname(String administratorSurname) {
	// 	this.administratorSurname = administratorSurname;
	// }

	// public String getAdministratorEmail() {
	// 	return this.administratorEmail;
	// }

	// public Long getAdministratorID() {
	// 	return this.administratorID;
	// }

	// public void setAdministratorID(Long administratorID) {
	// 	this.administratorID = administratorID;
	// }

	// public void setAdministratorEmail(String administratorEmail) {
	// 	this.administratorEmail = administratorEmail;
	// }

	// public String getX500AdminCommonName() {
	// 	return this.X500AdminCommonName;
	// }

	// public void setX500AdminCommonName(String X500AdminCommonName) {
	// 	this.X500AdminCommonName = X500AdminCommonName;
	// }

	// public String getX500AdminSurname() {
	// 	return this.X500AdminSurname;
	// }

	// public void setX500AdminSurname(String X500AdminSurname) {
	// 	this.X500AdminSurname = X500AdminSurname;
	// }

	// public String getX500AdminGivenname() {
	// 	return this.X500AdminGivenname;
	// }

	// public void setX500AdminGivenname(String X500AdminGivenname) {
	// 	this.X500AdminGivenname = X500AdminGivenname;
	// }

	// public String getX500AdminOrganizationUnit() {
	// 	return this.X500AdminOrganizationUnit;
	// }

	// public void setX500AdminOrganizationUnit(String X500AdminOrganizationUnit) {
	// 	this.X500AdminOrganizationUnit = X500AdminOrganizationUnit;
	// }

	// public String getX500AdminOrganization() {
	// 	return this.X500AdminOrganization;
	// }

	// public void setX500AdminOrganization(String X500AdminOrganization) {
	// 	this.X500AdminOrganization = X500AdminOrganization;
	// }

	// public String getX500AdminLocality() {
	// 	return this.X500AdminLocality;
	// }

	// public void setX500AdminLocality(String X500AdminLocality) {
	// 	this.X500AdminLocality = X500AdminLocality;
	// }

	// public String getX500AdminState() {
	// 	return this.X500AdminState;
	// }

	// public void setX500AdminState(String X500AdminState) {
	// 	this.X500AdminState = X500AdminState;
	// }

	// public String getX500AdminCountry() {
	// 	return this.X500AdminCountry;
	// }

	// public void setX500AdminCountry(String X500AdminCountry) {
	// 	this.X500AdminCountry = X500AdminCountry;
	// }

	// public String getX500AdminUID() {
	// 	return this.X500AdminUID;
	// }

	// public void setX500AdminUID(String X500AdminUID) {
	// 	this.X500AdminUID = X500AdminUID;
	// }

	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}

	public CertType getCertType() {
		return this.CertType;
	}

	public void setCertType(CertType CertType) {
		this.CertType = CertType;
	};

	@Override
	public String toString() {
		return "CertificateCreationDTO{" +
				"requestingID=" + requestingID +
				", CertType=" + CertType +
				", requestingName='" + requestingName + '\'' +
				", requestingSurname='" + requestingSurname + '\'' +
				", requestingEmail='" + requestingEmail + '\'' +
				", x500requestingCommonName='" + x500requestingCommonName + '\'' +
				", x500RequestingSurname='" + x500RequestingSurname + '\'' +
				", x500RequestingGivenname='" + x500RequestingGivenname + '\'' +
				", x500RequestingOrganizationUnit='" + x500RequestingOrganizationUnit + '\'' +
				", x500RequestingOrganization='" + x500RequestingOrganization + '\'' +
				", x500RequestingLocality='" + x500RequestingLocality + '\'' +
				", x500RequestingState='" + x500RequestingState + '\'' +
				", x500RequestingCountry='" + x500RequestingCountry + '\'' +
				", x500RequestingUID='" + x500RequestingUID + '\'' +
				", serialNumber=" + serialNumber +
				", issuerBI=" + issuerBI +
				'}';
	}
}
