package com.example.bsep.controller;
import java.util.List;

import com.example.bsep.dtos.CertificateCreationDTO;
import com.example.bsep.dtos.CertificateDTO;
import com.example.bsep.service.CertificateDataService;
import com.example.bsep.service.KeyStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "certificate")
public class CertificateDataController {


	@Autowired
	CertificateDataService certificateDataService;

	@Autowired
	KeyStoreService keyStoreService;

	
	@PostMapping(value="/save", consumes = "application/json")
	public ResponseEntity<CertificateCreationDTO> saveCertificate(@RequestBody CertificateCreationDTO certificateCreationDTO){
		
		
		certificateDataService.save(certificateCreationDTO);
	
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	
	@GetMapping(value = "/all")
	public ResponseEntity<List<CertificateDTO>> getFreeAdmins(){
		List<CertificateDTO> certificateDTOs = keyStoreService.getAll();
		return new ResponseEntity<>(certificateDTOs, HttpStatus.OK);
		
	}

	



	
}
