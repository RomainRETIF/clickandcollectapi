package com.example.clickandcollectapi.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class Creneau {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	@Column(name = "etat_creneau")
	private Integer etatCreneau;

	@Column(name = "date_creneau")
	private Date dateCreneau;

	@ManyToOne
	@JoinColumn(name="idMagasinId")
	private Magasin magasinCreneau;

	@ManyToOne
	@JoinColumn(name="idUserId")
	private User userCreneau;

	
	public User getUserCreneau() {
		return userCreneau;
	}

	public void setUserCreneau(User user) {
		this.userCreneau = user;
	}

	public Magasin getMagasin() {
		return magasinCreneau;
	}

	public void setMagasin(Magasin magasin) {
		this.magasinCreneau = magasin;
	}

	public Date getDateCreneau(){
		return dateCreneau;
	}

	public void setDateCreneau(Date d){
		this.dateCreneau = d;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEtatCreneau() {
		return etatCreneau;
	}

	public void setEtatCreneau(Integer etat) {
		this.etatCreneau = etat;
	}

	public JSONObject magasinJSON() throws JsonProcessingException {

		JSONObject magasinJSON = new JSONObject();
		magasinJSON.put("id", magasinCreneau.getId());
		magasinJSON.put("nom", magasinCreneau.getNom());
		magasinJSON.put("codePostal", magasinCreneau.getCodePostal());
		magasinJSON.put("description", magasinCreneau.getDescription());

		return magasinJSON;
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("date", dateCreneau);
		j.put("etat", etatCreneau);
		j.put("magasin", magasinJSON());
		j.put("idUser", userCreneau.getId());
		j.put("delete", "/creneau/update/" + id);
		j.put("update", "/creneau/delete/" + id);

		return (j);
	}
}
