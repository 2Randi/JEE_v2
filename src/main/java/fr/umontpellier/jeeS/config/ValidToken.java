package fr.umontpellier.jeeS.config;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
/*
@Entity
@Table(name = "valid_token")
public class ValidToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  

    @Column(length = 100, nullable = false, unique = true)
    private String token;  

    @Column(length = 100, nullable = false)
    private String email;  
    
    @Column(name = "expiration_date")
    private LocalDateTime expiration_date;  

    
    public ValidToken() {}

    public ValidToken(String token, String email, LocalDateTime expirationDate) {
        this.token = token;
        this.email = email;
        this.expiration_date = expirationDate;
    }

    // Getters et setters
    public Long getId() {return id;}
    public void setId(Long id) { this.id = id;}

    public String getToken() { return token;}
    public void setToken(String token) {this.token = token; }

    public String getEmail() { return email;}
    public void setEmail(String nom) {this.email = nom;}

    public LocalDateTime getExpiration_date() {return expiration_date;}
    public void setExpiration_date(LocalDateTime expirationDate) { this.expiration_date = expirationDate;}
}
*/