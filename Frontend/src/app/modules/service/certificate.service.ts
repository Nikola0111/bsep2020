import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Certificate} from '../../model/certificate';
import {Injectable} from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type' : 'application/json'})
};

@Injectable()
export class CertificateService {
  private requestUrl: string;
  constructor(private http: HttpClient) {

  }

  public getCertificates() {
    this.requestUrl = '/server/api/certificate/all';
    return this.http.get<Array<Certificate>>(this.requestUrl, httpOptions);
  }

  public revoke(id: number) {
    this.requestUrl = '/server/api/certificate/revoke/' + id;
    return this.http.post(this.requestUrl, httpOptions);
  }

  public getCertificate(id: number) {
    this.requestUrl = '/server/api/certificate/getCertificate/' + id;
    return this.http.get<Certificate>(this.requestUrl, httpOptions);
  }

}
