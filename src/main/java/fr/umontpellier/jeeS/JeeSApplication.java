package fr.umontpellier.jeeS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JeeSApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeeSApplication.class, args);
		System.err.println("*****************");
		System.err.println("Server ready ...");
		System.err.println("*****************");
		
		//test mdp crypté
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "12345";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);

	}

}
