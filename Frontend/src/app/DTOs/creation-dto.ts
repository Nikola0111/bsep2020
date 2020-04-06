import {CertType} from '../helpers/cer-type.enum';

export class CertificateCreationDTO {

  private requestingID: number;
  private CertType: CertType;
  private requestingName: string;
  private requestingSurname: string;
  private requestingEmail: string;
  private x500requestingCommonName: string;
  private x500RequestingSurname: string;
  private x500RequestingGivenname: string;
  private x500RequestingOrganizationUnit: string;
  private x500RequestingOrganization: string;
  private x500RequestingLocality: string;
  private x500RequestingState: string;
  private x500RequestingCountry: string;
  private x500RequestingUID: string;
  private administratorID: number;
  private administratorName: string;
  private administratorSurname: string;
  private administratorEmail: string;
  private x500AdminCommonName: string;
  private x500AdminSurname: string;
  private x500AdminGivenname: string;
  private x500AdminOrganizationUnit: string;
  private x500AdminOrganization: string;
  private x500AdminLocality: string;
  private x500AdminState: string;
  private x500AdminCountry: string;
  private x500AdminUID: string;
  private serialNumber: number;
  private issuerBI: number;


  constructor(requestingID: number, certType: CertType, requestingName: string, requestingSurname: string, requestingEmail: string,
              requestingCommonName: string, X500RequestingSurname: string, X500RequestingGivenname: string, X500RequestingOrganizationUnit: string,
              X500RequestingOrganization: string, X500RequestingLocality: string, X500RequestingState: string, X500RequestingCountry: string,
              X500RequestingUID: string, administratorID: number, administratorName: string, administratorSurname: string, administratorEmail: string,
              X500AdminCommonName: string, X500AdminSurname: string, X500AdminGivenname: string, X500AdminOrganizationUnit: string,
              X500AdminOrganization: string, X500AdminLocality: string, X500AdminState: string, X500AdminCountry: string, X500AdminUID: string,
              serialNumber: number, issuerBI: number) {
    this.requestingID = requestingID;
    this.CertType = certType;
    this.requestingName = requestingName;
    this.requestingSurname = requestingSurname;
    this.requestingEmail = requestingEmail;
    this.x500requestingCommonName = requestingCommonName;
    this.x500RequestingSurname = X500RequestingSurname;
    this.x500RequestingGivenname = X500RequestingGivenname;
    this.x500RequestingOrganizationUnit = X500RequestingOrganizationUnit;
    this.x500RequestingOrganization = X500RequestingOrganization;
    this.x500RequestingLocality = X500RequestingLocality;
    this.x500RequestingState = X500RequestingState;
    this.x500RequestingCountry = X500RequestingCountry;
    this.x500RequestingUID = X500RequestingUID;
    this.administratorID = administratorID;
    this.administratorName = administratorName;
    this.administratorSurname = administratorSurname;
    this.administratorEmail = administratorEmail;
    this.x500AdminCommonName = X500AdminCommonName;
    this.x500AdminSurname = X500AdminSurname;
    this.x500AdminGivenname = X500AdminGivenname;
    this.x500AdminOrganizationUnit = X500AdminOrganizationUnit;
    this.x500AdminOrganization = X500AdminOrganization;
    this.x500AdminLocality = X500AdminLocality;
    this.x500AdminState = X500AdminState;
    this.x500AdminCountry = X500AdminCountry;
    this.x500AdminUID = X500AdminUID;
    this.serialNumber = serialNumber;
    this.issuerBI = issuerBI;
  }
}
