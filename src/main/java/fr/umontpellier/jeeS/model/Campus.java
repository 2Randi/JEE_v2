package fr.umontpellier.jeeS.model;

import jakarta.persistence.*;


import java.util.*;
import java.util.*;

@Entity
@Table(name="campus")
public class Campus {
	
	@Id
	@Column(name="nomC", length=16)
	private String nomC;
	
	@Column(name="ville", length=20)
	private String ville;
	
	@OneToMany(mappedBy = "campus", cascade = CascadeType.PERSIST, orphanRemoval = false)
	private List<Batiment> bats = new ArrayList<>();
	
	//constructeurs
	public Campus() {}
	
	public Campus(String nomC, String ville) {
		this.nomC = nomC;
		this.ville = ville;
	
	}

	//getter setter
	public String getNomC() {
		return nomC;
	}

	public void setNomC(String nomC) {
		this.nomC = nomC;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public List<Batiment> getBatiments() {
		return bats;
	}
	
	public void setBatiments(List<Batiment> bat) {
		this.bats = bat;
	}

	@Override
	public String toString() {
		return "Campus [nomC=" + nomC + ", ville=" + ville + "]";
	}

	

}
