import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CertificateCreationDTO} from '../../../../DTOs/creation-dto';
import {FormBuilder, FormGroup} from '@angular/forms';
import {CertificateService} from '../../../service/certificate.service';
import {CertType} from '../../../../helpers/cer-type.enum';

@Component({
  selector: 'app-forma',
  templateUrl: './forma.component.html',
  styleUrls: ['./forma.component.css', './../administrator.component.css']
})
export class FormaComponent  implements OnInit {
  certificateCreationDTO: CertificateCreationDTO = new CertificateCreationDTO();
  forma: FormGroup;
  type: string;

  constructor(private router: Router,
              private certificateService: CertificateService,
              private formBuilder: FormBuilder) {
    this.certificateCreationDTO = new CertificateCreationDTO();
  }
  onSubmit(): void {
    alert('poslo sam');
    console.log(this.type);

    if(this.type === 'Root'){
      this.certificateCreationDTO.certType = CertType.ROOT;
    } else if (this.type === 'Intermediate') {
      this.certificateCreationDTO.certType = CertType.INTERMEDIATE;
    } else if (this.type === 'End_entity') {
      this.certificateCreationDTO.certType = CertType.ENDENTITY;
    }

    console.log(this.certificateCreationDTO);
    this.certificateCreationDTO.serialNumber = 1;
    this.certificateService.save(this.certificateCreationDTO).subscribe();

    this.ngOnInit();

  }

  ngOnInit(): void {
    this.forma = this.formBuilder.group( {
      requestingID: [''],
      requestingName: [''],
      requestingSurname: [''],
      requestingEmail: [''],
      x500requestingCommonName: [''],
      x500RequestingSurname: [''],
      x500RequestingGivenName: [''],
      x500RequestingOrganizationUnit: [''],
      x500RequestingOrganization: [''],
      x500RequestingLocality: [''],
      x500RequestingState: [''],
      x500RequestingCountry: [''],
      x500RequestingUID: [''],
      certType: [''],
      // serialNumber: [''],
      issuerBI: ['']
    });
  }

}
