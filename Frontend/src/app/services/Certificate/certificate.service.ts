import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable()

export class CertificateService {

  constructor(private http: HttpClient) { }

  public save() {
    return this.http.get('/server/certificates/save');
  }
}
