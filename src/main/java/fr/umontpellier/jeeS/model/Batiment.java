package fr.umontpellier.jeeS.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name="batiment")
public class Batiment {
	
	@Id
	@Column(name="codeB", length=16)
	private String codeB;
	
	@Column(name = "anneeC")
	private int anneeC;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Campus.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "campus", nullable = false)
	private Campus campus;
	
	//dependance existenciellle
	@OneToMany(mappedBy = "batiment")
	private List<Salle> salles = new ArrayList<>();
	
	@ManyToMany(mappedBy = "batiments")
	private List<Composante> composantes = new ArrayList<>();
	
	
	//constructeurs
	public Batiment() {}


	public Batiment(String codeB, int anneeC, Campus campus) {
		super();
		this.codeB = codeB;
		this.anneeC = anneeC;
		this.campus = campus;
	}


	//Accessurs
	public String getCodeB() {
		return codeB;
	}


	public void setCodeB(String codeB) {
		this.codeB = codeB;
	}


	public int getAnneeC() {
		return anneeC;
	}


	public void setAnneeC(int anneC) {
		this.anneeC = anneC;
	}


	public Campus getCampus() {
		return campus;
	}


	public void setCampus(Campus campus) {
		this.campus = campus;
	}


	public List<Salle> getSalles() {
		return salles;
	}


	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}


	public List<Composante> getComposantes() {
		return composantes;
	}


	public void setComposantes(List<Composante> composantes) {
		this.composantes = composantes;
	}

	//toString
	@Override
	public String toString() {
		return "Batiment [codeB=" + codeB + ", anneC=" + anneeC + ", campus=" + campus + "]";
	}
	
	
	//exploite
	public void addComposante(Composante composante) {
        this.composantes.add(composante);
        composante.getBatiments().add(this); 
    }

    public void removeComposante(Composante composante) {
        this.composantes.remove(composante);
        composante.getBatiments().remove(this); 
    }
    
}
