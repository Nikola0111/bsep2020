package com.example.bsep.controller;
import java.math.BigInteger;
import java.util.List;

import com.example.bsep.dtos.CertificateCreationDTO;
import com.example.bsep.dtos.CertificateDTO;
import com.example.bsep.service.CertificateDataService;
import com.example.bsep.service.KeyStoreService;
import com.example.bsep.service.RevokeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	RevokeService revokeService;

	
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

	@GetMapping(value="/revoke/{serialNumber}")
	public ResponseEntity<List<CertificateDTO>> revoke(@PathVariable("serialNumber") BigInteger serialNumber){
		
		revokeService.revokeCertificate(serialNumber.toString());
		return new ResponseEntity<>( HttpStatus.OK);
		
	}

	@GetMapping(value="/allCAs")
	public ResponseEntity<List<CertificateDTO>> getAllCas(){
		List<CertificateDTO> certificateDTOs = keyStoreService.getCAs();
		return new ResponseEntity<>(certificateDTOs, HttpStatus.OK);
		
	}




	



	
}
