export class CertificateData {
  id: number;
  serialNumber: string;
  keyPairIssuer: CryptoKeyPair;  // da li string ili ovo?
  x500Name: string;
  validFrom: string;
  validUntil: string;
  withdrawn: boolean;
}
