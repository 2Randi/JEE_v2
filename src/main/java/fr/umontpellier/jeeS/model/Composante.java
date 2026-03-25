package fr.umontpellier.jeeS.model;

import jakarta.persistence.*;
import java.util.*;


@Entity
@Table(name="composante")
public class Composante {
	
	@Id
	@Column(name="acronyme", length=8)
	private String acronyme;
	
	@Column(name="nom", length=50)
	private String nom;
	
	@Column(name="responsable", length=30)
	private String responsable;
	
	@ManyToMany
    @JoinTable(
        name = "exploite", 
        joinColumns = @JoinColumn(name = "team"), 
        inverseJoinColumns = @JoinColumn(name = "building"))
	private List<Batiment> batiments = new ArrayList<>();
	
	
	//constructeurs
	public Composante() {}
	
	public Composante(String acronyme, String nom, String responsable) {
		super();
		this.acronyme = acronyme;
		this.nom = nom;
		this.responsable = responsable;
	}

	//getter setter
	public String getAcronyme() {
		return acronyme;
	}

	public void setAcronyme(String acronyme) {
		this.acronyme = acronyme;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Batiment> getBatiments() {
		return batiments;
	}

	public void setBatiments(List<Batiment> batiments) {
		this.batiments = batiments;
	}

	//toString
	@Override
	public String toString() {
		return "Composante [acronyme=" + acronyme + ", nom=" + nom + ", responsable=" + responsable + "]";
	}

	
	//exploite
	public void addBatiment(Batiment batiment) {
        this.batiments.add(batiment);
        batiment.getComposantes().add(this); 
    }

    public void removeBatiment(Batiment batiment) {
        this.batiments.remove(batiment);
        batiment.getComposantes().remove(this); 
    }
    
}

