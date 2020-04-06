package com.example.bsep.controller;
import com.example.bsep.dtos.CertificateCreationDTO;
import com.example.bsep.service.CertificateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "certificate")
public class CertificateDataController {


	@Autowired
	CertificateDataService certificateDataService;

	
	@GetMapping(value="/save")
	public ResponseEntity<CertificateCreationDTO> saveCertificate(@RequestBody CertificateCreationDTO certificateCreationDTO){
		
		
		certificateDataService.save(certificateCreationDTO);
	
		return new ResponseEntity<>(HttpStatus.OK);
		
	}


	



	
}
