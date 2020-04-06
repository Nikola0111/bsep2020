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
    this.creationDTO = new CertificateCreationDTO(1, CertType.ROOT, 'Root', 'Root', 'root_email@gmail.com',
      'Root', 'Root', 'Root', 'System', 'SubSystem',
      'Server', 'Server', 'Server', '123',  232, 'Admin',
      'Admin', 'admin@gmail.com', 'Admin', 'Admin', 'Admin',
      'System', 'System', 'Server', 'Server', 'Server', '123',
      123321, null);
    // this.creationDummyDTO = new CertificateDummy(1, CertType.ROOT, 'Root', 'Root',
    //   'root_gmail.com', 'comname', 'comsur','givname',
    //   'org', 'org');
    console.log(this.creationDTO);
    certificateService.save(this.creationDTO).subscribe();
  }
}
