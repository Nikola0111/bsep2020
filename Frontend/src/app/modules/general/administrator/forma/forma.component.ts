import {Component, OnInit} from '@angular/core';
import { Router} from '@angular/router';
import {Administrator} from '../../../../model/administrator';
import {UserData} from '../../../../model/userData';
import {CertificateAuthority} from '../../../../model/certificateAuthority';
import {CertificateData} from '../../../../model/certificateData';
import {IssuerData} from '../../../../model/issuerData';

@Component({
  selector: 'app-forma',
  templateUrl: './forma.component.html',
  styleUrls: ['./forma.component.css', './../administrator.component.css']
})
export class FormaComponent  implements OnInit {
  administrator: Administrator = new Administrator();
  userData: UserData = new UserData();
  issuerData: IssuerData = new IssuerData();
  certificateAuthority: CertificateAuthority = new CertificateAuthority();
  certificateData: CertificateData = new CertificateData();

  constructor(private router: Router) {

  }
  onSubmit() {
  }

  ngOnInit(): void {
  }

}
