package fr.umontpellier.jeeS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

	@Bean(name = "customPasswordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return (BCryptPasswordEncoder) NoOpPasswordEncoder.getInstance();

    }
}
