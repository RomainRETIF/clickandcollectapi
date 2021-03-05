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

import org.json.JSONArray;
import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class Magasin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	private String nom;

	private String description;

	@Column(name = "code_postal")
	private String codePostal;

	@OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Creneau> creneaux;

	@OneToMany(mappedBy = "magasinStock", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Stock> stocks;

	public List<Stock> getStocks(){
		return stocks;
	}

	public List<Creneau> getCreneaux(){
		return creneaux;
	}

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

	public JSONArray creneauxJSON() throws JsonProcessingException {

		JSONArray arrayCreneaux = new JSONArray();
		
		for(Integer i = 0; i<creneaux.size(); i++){
			JSONObject creneau = new JSONObject();
			creneau.put("id", creneaux.get(i).getId());
			creneau.put("dateCreneau", creneaux.get(i).getDateCreneau());
			creneau.put("etatCreneau", creneaux.get(i).getEtatCreneau());
			creneau.put("idUser", creneaux.get(i).getUserCreneau().getId());
			arrayCreneaux.put(creneau);
		}
		return arrayCreneaux;
	}

	public JSONArray stocksJSON() throws JsonProcessingException {

		JSONArray arrayStocks = new JSONArray();
		
		for(Integer i = 0; i<stocks.size(); i++){
			JSONObject stock = new JSONObject();
			stock.put("id", stocks.get(i).getId());
			stock.put("quantite", stocks.get(i).getQuantite());
			stock.put("article", stocks.get(i).getArticle().toJSON());
			arrayStocks.put(stock);
		}
		return arrayStocks;
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("nom", nom);
		j.put("description", description);
		j.put("codePostal", codePostal);
		j.put("creneaux", creneauxJSON());
		j.put("stock", stocksJSON());
		j.put("update", "/magasin/update/" + id);
		j.put("delete", "/magasin/delete/" + id);
		
		return (j);
	}
}
