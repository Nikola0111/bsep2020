import {CertificateData} from './certificateData';
import {Certificate} from './certificate';
import {CertificateType} from './certificateType';

export class CertificateAuthority {
  parent: CertificateAuthority;
  data: CertificateData;
  children: Certificate[];
  type: CertificateType;
  basicConstraints: string;
  keyUsage: KeyUsage;
}
