package com.example.bsep.controller;
import com.example.bsep.DTOs.CertificateCreationDTO;
import com.example.bsep.service.CertificateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "certificate")
public class CertificateDataController {


	@Autowired
	CertificateDataService certificateDataService;

	
	@PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody CertificateCreationDTO certificateCreationDTO){
		System.out.println(certificateCreationDTO);
		certificateDataService.save(certificateCreationDTO);
	
		return new ResponseEntity<String>("Its ok", HttpStatus.OK);
		
	}

}
