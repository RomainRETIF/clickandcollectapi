package com.example.clickandcollectapi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class Magasin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	private String nom;

	private String description;

	@OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Commande> commandes;
	
	public List<Commande> getCommandes(){
		return commandes;
	}

	@OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Stock> stocks;

	public List<Stock> getStocks(){
		return stocks;
	}

	@Column(name = "code_postal")
	private String codePostal;

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
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("nom", nom);
		j.put("description", description);
		j.put("codePostal", codePostal);
		j.put("update", "/magasin/update/" + id);
		j.put("delete", "/magasin/delete/" + id);

		return (j);
	}
}
