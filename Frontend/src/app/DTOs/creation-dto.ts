import {CertType} from '../helpers/cer-type.enum';

export class CertificateCreationDTO {

  private requestingID: number;
  private certType: CertType;
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
  // private administratorID: number;
  // private administratorName: string;
  // private administratorSurname: string;
  // private administratorEmail: string;
  // private x500AdminCommonName: string;
  // private x500AdminSurname: string;
  // private x500AdminGivenname: string;
  // private x500AdminOrganizationUnit: string;
  // private x500AdminOrganization: string;
  // private x500AdminLocality: string;
  // private x500AdminState: string;
  // private x500AdminCountry: string;
  // private x500AdminUID: string;
  private serialNumber: number;
  private issuerBI: number;

  constructor() {
  }

  get _requestingID(): number {
    return this.requestingID;
  }

  set _requestingID(value: number) {
    this.requestingID = value;
  }

  get _requestingName(): string {
    return this.requestingName;
  }

  set _requestingName(value: string) {
    this.requestingName = value;
  }

  get _requestingSurname(): string {
    return this.requestingSurname;
  }

  set _requestingSurname(value: string) {
    this.requestingSurname = value;
  }

  get _requestingEmail(): string {
    return this.requestingEmail;
  }

  set _requestingEmail(value: string) {
    this.requestingEmail = value;
  }

  get _x500RequestingState(): string {
    return this.x500RequestingState;
  }

  set _x500RequestingState(value: string) {
    this.x500RequestingState = value;
  }

  get _x500requestingCommonName(): string {
    return this.x500requestingCommonName;
  }

  set _x500requestingCommonName(value: string) {
    this.x500requestingCommonName = value;
  }

  get _x500RequestingSurname(): string {
    return this.x500RequestingSurname;
  }

  set _x500RequestingSurname(value: string) {
    this.x500RequestingSurname = value;
  }

  get _x500RequestingGivenname(): string {
    return this.x500RequestingGivenname;
  }

  set _x500RequestingGivenname(value: string) {
    this.x500RequestingGivenname = value;
  }

  get _x500RequestingOrganizationUnit(): string {
    return this.x500RequestingOrganizationUnit;
  }

  set _x500RequestingOrganizationUnit(value: string) {
    this.x500RequestingOrganizationUnit = value;
  }

  get _x500RequestingOrganization(): string {
    return this.x500RequestingOrganization;
  }

  set _x500RequestingOrganization(value: string) {
    this.x500RequestingOrganization = value;
  }

  get _x500RequestingLocality(): string {
    return this.x500RequestingLocality;
  }

  set _x500RequestingLocality(value: string) {
    this.x500RequestingLocality = value;
  }

  get _x500RequestingCountry(): string {
    return this.x500RequestingCountry;
  }

  set _x500RequestingCountry(value: string) {
    this.x500RequestingCountry = value;
  }

  get _x500RequestingUID(): string {
    return this.x500RequestingUID;
  }

  set _x500RequestingUID(value: string) {
    this.x500RequestingUID = value;
  }

  get _certType(): CertType {
    return this.certType;
  }

  set _certType(value: CertType) {
    this.certType = value;
  }

  get _serialNumber(): number {
    return this.serialNumber;
  }

  set _serialNumber(value: number) {
    this.serialNumber = value;
  }

  get _issuerBi(): number {
    return this.issuerBI;
  }

  set _issuerBi(value: number) {
    this.issuerBI = value;
  }
}
