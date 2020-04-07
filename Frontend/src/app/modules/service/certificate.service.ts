import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Certificate} from '../../model/certificate';
import {Injectable} from '@angular/core';
import {CertificateCreationDTO} from '../../DTOs/creation-dto';
import {PreviewCertificateDTO} from '../../DTOs/preview-certificate-dto';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type' : 'application/json'})
};

@Injectable()

export class CertificateService {
  private requestUrl: string;
  constructor(private http: HttpClient) {}

  public getCertificates() {
    this.requestUrl = '/server/certificate/all';
    return this.http.get<Array<PreviewCertificateDTO>>(this.requestUrl, httpOptions);
  }

  public revoke(serialNumber: number) {
    this.requestUrl = '/server/certificate/revoke';
    return this.http.post(this.requestUrl, serialNumber, httpOptions);
  }

  public getCertificate(serialNumber: number) {
    this.requestUrl = '/server/certificate/getCertificate';
    return this.http.post<Certificate>(this.requestUrl, serialNumber, httpOptions);
  }

  public save(creationDTO: CertificateCreationDTO) {
    console.log('usao je ovde');
    const body = JSON.stringify(creationDTO);
    return this.http.post<CertificateCreationDTO>('/server/certificate/save', body, httpOptions);
  }

}
