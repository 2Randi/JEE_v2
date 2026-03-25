package fr.umontpellier.jeeS.model;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 30, nullable = false)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String nom;
    
    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING) // Stocke la valeur en texte dans la BDD
    private Role role;

    @Column(length = 100, nullable = false)
    private String mdp;

    // Constructeurs
    public Utilisateur() {}

    public Utilisateur(String email, String nom, Role role, String mdp) {
        this.email = email;
        this.nom = nom;
        this.role = role;
        this.mdp = mdp;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
               "id=" + id +
               ", email='" + email + '\'' +
               ", nom='" + nom + '\'' +
               ", role=" + role +
               ", mdp='" + mdp + '\'' +
               '}';
    }
}
