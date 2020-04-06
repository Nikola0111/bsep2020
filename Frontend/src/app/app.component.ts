import { Component } from '@angular/core';
import {CertType} from './helpers/cer-type.enum';
import {CertificateService} from './modules/service/certificate.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
}

