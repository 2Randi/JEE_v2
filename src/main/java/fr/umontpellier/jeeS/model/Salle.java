package fr.umontpellier.jeeS.model;


import jakarta.persistence.*;


@Entity
@Table(name="salle")
public class Salle {
	
	@Id
	@Column(name="numS", length=16)
	private String numS;
	
	@Column(name="capacite")
	private int capacite;
	
	@Column(name="typeS", length=12)
	private String typeS;
	
	@Column(name="acces", length=3)
	private String acces;
	
	@Column(name="etage", length=3)
	private String etage;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Batiment.class)
	@JoinColumn(name="batiment")
	private Batiment batiment;
	
	// constructeurs
	public Salle() {}
	
	public Salle(String numS, int capacite, String typeS,
			String acces, String etage, Batiment bat) {
		this.numS = numS;
		this.capacite = capacite;
		this.typeS = typeS;
		this.acces = acces;
		this.etage = etage;
		this.batiment = bat;
	}

	//getter setter
	public String getNumS() {
		return numS;
	}

	public void setNumS(String numS) {
		this.numS = numS;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public String getTypeS() {
		return typeS;
	}

	public void setTypeS(String typeS) {
		this.typeS = typeS;
	}

	public String getAcces() {
		return acces;
	}

	public void setAcces(String acces) {
		this.acces = acces;
	}

	public String getEtage() {
		return etage;
	}

	public void setEtage(String etage) {
		this.etage = etage;
	}
	
	public Batiment getBatiment() {
		return this.batiment;
	}

	public void setBatiment(Batiment bat) {
		this.batiment = bat;
	}

	
}
