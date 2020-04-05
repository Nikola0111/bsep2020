
package com.example.bsep.dtos;

import com.example.bsep.model.CertType;

public class CertificateCreationDTO {

	private Long requestingID;

	private CertType CertType;

	private String requestingName;

	private String requestingSurname;

	private String requestingEmail;

	private String X500RequestingCommonName;

	private String X500RequestingSurname;

	private String X500RequestingGivenname;

	private String X500RequestingOrganizationUnit;

	private String X500RequestingOrganization;

	private String X500RequestingLocality;

	private String X500RequestingState;

	private String X500RequestingCountry;

	private String X500RequestingUID;

	private Long administratorID;

	private String administratorName;

	private String administratorSurname;

	private String administratorEmail;

	private String X500AdminCommonName;

	private String X500AdminSurname;

	private String X500AdminGivenname;

	private String X500AdminOrganizationUnit;

	private String X500AdminOrganization;

	private String X500AdminLocality;

	private String X500AdminState;

	private String X500AdminCountry;

	private String X500AdminUID;

	private String serialNumber;

	public CertificateCreationDTO() {
		
	}

	public Long getRequestingID() {
		return this.requestingID;
	}

	public void setRequestingID(Long requestingID) {
		this.requestingID = requestingID;
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

	public String getX500RequestingCommonName() {
		return this.X500RequestingCommonName;
	}

	public void setX500RequestingCommonName(String X500RequestingCommonName) {
		this.X500RequestingCommonName = X500RequestingCommonName;
	}

	public String getX500RequestingSurname() {
		return this.X500RequestingSurname;
	}

	public void setX500RequestingSurname(String X500RequestingSurname) {
		this.X500RequestingSurname = X500RequestingSurname;
	}

	public String getX500RequestingGivenname() {
		return this.X500RequestingGivenname;
	}

	public void setX500RequestingGivenname(String X500RequestingGivenname) {
		this.X500RequestingGivenname = X500RequestingGivenname;
	}

	public String getX500RequestingOrganizationUnit() {
		return this.X500RequestingOrganizationUnit;
	}

	public void setX500RequestingOrganizationUnit(String X500RequestingOrganizationUnit) {
		this.X500RequestingOrganizationUnit = X500RequestingOrganizationUnit;
	}

	public String getX500RequestingOrganization() {
		return this.X500RequestingOrganization;
	}

	public void setX500RequestingOrganization(String X500RequestingOrganization) {
		this.X500RequestingOrganization = X500RequestingOrganization;
	}

	public String getX500RequestingLocality() {
		return this.X500RequestingLocality;
	}

	public void setX500RequestingLocality(String X500RequestingLocality) {
		this.X500RequestingLocality = X500RequestingLocality;
	}

	public String getX500RequestingState() {
		return this.X500RequestingState;
	}

	public void setX500RequestingState(String X500RequestingState) {
		this.X500RequestingState = X500RequestingState;
	}

	public String getX500RequestingCountry() {
		return this.X500RequestingCountry;
	}

	public void setX500RequestingCountry(String X500RequestingCountry) {
		this.X500RequestingCountry = X500RequestingCountry;
	}

	public String getX500RequestingUID() {
		return this.X500RequestingUID;
	}

	public void setX500RequestingUID(String X500RequestingUID) {
		this.X500RequestingUID = X500RequestingUID;
	}

	public String getAdministratorName() {
		return this.administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}

	public String getAdministratorSurname() {
		return this.administratorSurname;
	}

	public void setAdministratorSurname(String administratorSurname) {
		this.administratorSurname = administratorSurname;
	}

	public String getAdministratorEmail() {
		return this.administratorEmail;
	}

	public Long getAdministratorID() {
		return this.administratorID;
	}

	public void setAdministratorID(Long administratorID) {
		this.administratorID = administratorID;
	}

	public void setAdministratorEmail(String administratorEmail) {
		this.administratorEmail = administratorEmail;
	}

	public String getX500AdminCommonName() {
		return this.X500AdminCommonName;
	}

	public void setX500AdminCommonName(String X500AdminCommonName) {
		this.X500AdminCommonName = X500AdminCommonName;
	}

	public String getX500AdminSurname() {
		return this.X500AdminSurname;
	}

	public void setX500AdminSurname(String X500AdminSurname) {
		this.X500AdminSurname = X500AdminSurname;
	}

	public String getX500AdminGivenname() {
		return this.X500AdminGivenname;
	}

	public void setX500AdminGivenname(String X500AdminGivenname) {
		this.X500AdminGivenname = X500AdminGivenname;
	}

	public String getX500AdminOrganizationUnit() {
		return this.X500AdminOrganizationUnit;
	}

	public void setX500AdminOrganizationUnit(String X500AdminOrganizationUnit) {
		this.X500AdminOrganizationUnit = X500AdminOrganizationUnit;
	}

	public String getX500AdminOrganization() {
		return this.X500AdminOrganization;
	}

	public void setX500AdminOrganization(String X500AdminOrganization) {
		this.X500AdminOrganization = X500AdminOrganization;
	}

	public String getX500AdminLocality() {
		return this.X500AdminLocality;
	}

	public void setX500AdminLocality(String X500AdminLocality) {
		this.X500AdminLocality = X500AdminLocality;
	}

	public String getX500AdminState() {
		return this.X500AdminState;
	}

	public void setX500AdminState(String X500AdminState) {
		this.X500AdminState = X500AdminState;
	}

	public String getX500AdminCountry() {
		return this.X500AdminCountry;
	}

	public void setX500AdminCountry(String X500AdminCountry) {
		this.X500AdminCountry = X500AdminCountry;
	}

	public String getX500AdminUID() {
		return this.X500AdminUID;
	}

	public void setX500AdminUID(String X500AdminUID) {
		this.X500AdminUID = X500AdminUID;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public CertType getCertType() {
		return this.CertType;
	}

	public void setCertType(CertType CertType) {
		this.CertType = CertType;
	};
}
