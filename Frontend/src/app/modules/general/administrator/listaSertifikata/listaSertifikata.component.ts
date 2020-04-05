import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Certificate} from '../../../../model/certificate';
import {CertificateService} from '../../../service/certificate.service';
import {HttpHeaders} from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders( {'Content-Type' : 'application/json'})
};

@Component({
  selector: 'app-lista-sertifikata',
  templateUrl: './listaSertifikata.component.html',
  styleUrls: ['./listaSertifikata.component.css', './../administrator.component.css']
})

export class ListaSertifikataComponent implements OnInit {
  certificates: Certificate[];
  certificateService: CertificateService;
  dialogData: Certificate;
  constructor(private router: Router, public dialog: HTMLDialogElement) {
    this.dialog = new PreviewSertifikataDialogComponent();
  }
  onSubmit() {
  }

  ngOnInit(): void {
    this.certificateService.getCertificates().subscribe(
      data => {
        this.certificates = data;
      }
    );
  }

  preview(id: number): void {
    this.certificateService.getCertificate(id).subscribe(
      data => {
        this.dialogData = data;
      }
    )
    ;
    const dialogRef = this.dialog.open;
  }
  download(id: number) {}
  revoke(id: number) {
    this.certificateService.revoke(id).subscribe();
    this.ngOnInit();
  }

}

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'lista-sertifikata-preview-sertifikata-dialog',
  templateUrl: './previewSertifikataDialog.html',
  styleUrls: ['./previewSertifikataDialog.css']
})
export class PreviewSertifikataDialogComponent extends HTMLDialogElement {
  certificate: Certificate = new Certificate();
  constructor() {
    super();
  }
}
