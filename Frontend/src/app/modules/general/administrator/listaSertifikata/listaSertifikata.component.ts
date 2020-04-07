import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Certificate} from '../../../../model/certificate';
import {CertificateService} from '../../../service/certificate.service';
import {HttpHeaders} from '@angular/common/http';
import {PreviewCertificateDTO} from '../../../../DTOs/preview-certificate-dto';


const httpOptions = {
  headers: new HttpHeaders( {'Content-Type' : 'application/json'})
};

@Component({
  selector: 'app-lista-sertifikata',
  templateUrl: './listaSertifikata.component.html',
  styleUrls: ['./listaSertifikata.component.css', './../administrator.component.css']
})

export class ListaSertifikataComponent implements OnInit {
  certificates: PreviewCertificateDTO[];
  certificateService: CertificateService;
  dialogData: Certificate;
  selected: boolean;
  selectedCertificate: PreviewCertificateDTO;
  constructor(private router: Router, certificateService: CertificateService) {
    certificateService.getCertificates().subscribe( data => {
      console.log(data);
      this.certificates = data;
    });
  }
  onSubmit() {
  }

  ngOnInit(): void {

  }

  preview(certificate: PreviewCertificateDTO): void {
    this.selectedCertificate = certificate;
    this.selected = true;
  }

  download(serialNumber: number) {}

  revoke(serialNumber: number) {
    this.certificateService.revoke(serialNumber).subscribe();
  }

}
