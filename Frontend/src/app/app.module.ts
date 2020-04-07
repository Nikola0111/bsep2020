import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {AdministratorModule} from './modules/general/administrator/administrator.module';
import {HttpClientModule} from '@angular/common/http';
import {FormaModule} from './modules/general/administrator/forma/forma.module';
import {ListaSertifikataModule} from './modules/general/administrator/listaSertifikata/listaSertifikata.module';
import {CertificateService} from './modules/service/certificate.service';
import {CertificateCreationDTO} from './DTOs/creation-dto';
import {CertType} from './helpers/cer-type.enum';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AdministratorModule,
    HttpClientModule,
    FormaModule,
    ListaSertifikataModule
  ],
  providers: [CertificateService],
  bootstrap: [AppComponent]
})
export class AppModule {
  creationDTO: CertificateCreationDTO;
  constructor(certificateService: CertificateService) {
    this.creationDTO = new CertificateCreationDTO();

    this.creationDTO._requestingID = 1;
    this.creationDTO._certType = CertType.ROOT;
    this.creationDTO._requestingEmail = 'root_email@gmail.com';
    this.creationDTO._requestingName = 'Root';
    this.creationDTO._requestingSurname = 'Root';
    this.creationDTO._x500RequestingState = 'Server';
    this.creationDTO._x500requestingCommonName = 'Root';
    this.creationDTO._x500RequestingSurname = 'Root';
    this.creationDTO._x500RequestingGivenname = 'Root';
    this.creationDTO._x500RequestingOrganization = 'Server';
    this.creationDTO._x500RequestingOrganizationUnit = 'Server';
    this.creationDTO._x500RequestingLocality = 'Server';
    this.creationDTO._x500RequestingCountry = 'Server';
    this.creationDTO._x500RequestingUID = '123';
    this.creationDTO._serialNumber = 1;
    this.creationDTO._issuerBi = 1;
    // this.creationDummyDTO = new CertificateDummy(1, CertType.ROOT, 'Root', 'Root',
    //   'root_gmail.com', 'comname', 'comsur','givname',
    //   'org', 'org');
    console.log(this.creationDTO);
    certificateService.save(this.creationDTO).subscribe();
  }
}
