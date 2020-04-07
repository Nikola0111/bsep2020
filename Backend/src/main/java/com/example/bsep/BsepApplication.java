package com.example.bsep;
import java.security.Security;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BsepApplication {




	public static void main(String[] args) {
		SpringApplication.run(BsepApplication.class, args);
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	
		
	}

	//	komentar
}
