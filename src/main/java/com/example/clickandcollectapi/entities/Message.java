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
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	private String titre;

	private String contenu;

	@Column(name = "date_message")
	private Date dateMessage;

	@ManyToOne
	@JoinColumn(name="id_vendeur_id")
	private User vendeur;

	@ManyToOne
	@JoinColumn(name="id_client_id")
	private User client;

	
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public User getVendeur() {
		return vendeur;
	}

	public void setVendeur(User vendeur) {
		this.vendeur = vendeur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("titre", titre);
		j.put("contenu", contenu);
		j.put("vendeur", vendeur.toJSON());
		j.put("client", client.toJSON());
		j.put("dateMessage", dateMessage.toString());
		j.put("update", "/message/update/" + id);
		j.put("delete", "/message/delete/" + id);

		return (j);
	}
}
