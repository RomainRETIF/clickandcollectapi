package com.example.clickandcollectapi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Magasin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nom;

	private String description;

	private String code_postal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodePostal() {
		return code_postal;
	}

	public void setCodePostal(String code_postal) {
		this.code_postal = code_postal;
	}
}
