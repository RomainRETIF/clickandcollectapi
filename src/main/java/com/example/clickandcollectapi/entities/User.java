package com.example.clickandcollectapi.entities;

import java.lang.reflect.Array;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectSerializer;


@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	private String email;

	private String roles;

	private String password;

    private boolean isVerified;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Commande> commandes;

	@OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Message> messagesVendeur;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Message> messagesClient;

	@OneToMany(mappedBy = "userCreneau", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Creneau> creneaux;


	public List<Message> getMessagesVendeur(){
		return messagesVendeur;
	}

	public List<Message> getMessagesClient(){
		return messagesClient;
	}

	public List<Creneau> getCreneaux(){
		return creneaux;
	}

	public List<Commande> getCommandes(){
		return commandes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isverified) {
		this.isVerified = isverified;
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

	public JSONArray commandesJSON() throws JsonProcessingException {

		JSONArray arrayCommandes = new JSONArray();
		
		for(Integer i = 0; i<commandes.size(); i++){
			JSONObject commande = new JSONObject();
			commande.put("id", commandes.get(i).getId());
			commande.put("dateCommande", commandes.get(i).getDateCommande());
			arrayCommandes.put(commande);
		}
		return arrayCommandes;
	}

	public JSONArray messagesVendeurJSON() throws JsonProcessingException {

		JSONArray arrayMessagesVendeur = new JSONArray();
		
		for(Integer i = 0; i<messagesVendeur.size(); i++){
			JSONObject msg = new JSONObject();
			msg.put("id", messagesVendeur.get(i).getId());
			msg.put("dateMessage", messagesVendeur.get(i).getDateMessage());
			msg.put("idVendeur", messagesVendeur.get(i).getVendeur().getId());
			msg.put("contenu", messagesVendeur.get(i).getContenu());
			msg.put("titre", messagesVendeur.get(i).getTitre());
			arrayMessagesVendeur.put(msg);
		}
		return arrayMessagesVendeur;
	}

	public JSONArray messagesClientJSON() throws JsonProcessingException {

		JSONArray arrayMessagesClient = new JSONArray();
		
		for(Integer i = 0; i<messagesClient.size(); i++){
			JSONObject msg = new JSONObject();
			msg.put("id", messagesClient.get(i).getId());
			msg.put("dateMessage", messagesClient.get(i).getDateMessage());
			msg.put("idVendeur", messagesClient.get(i).getVendeur().getId());
			msg.put("contenu", messagesClient.get(i).getContenu());
			msg.put("titre", messagesClient.get(i).getTitre());
			arrayMessagesClient.put(msg);
		}
		return arrayMessagesClient;
	}
	

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("email", email);
		j.put("roles", roles);
		j.put("password", password);
        j.put("isVerified", isVerified);
		j.put("creneaux", creneauxJSON());
		j.put("commandes", commandesJSON());
		j.put("messagesVendeur", messagesVendeurJSON());
		j.put("messagesClient", messagesClientJSON());
		j.put("update", "/user/update/" + id);
		j.put("delete", "/user/delete/" + id);
		j.put("help", "/swagger-ui.html#/user-controller");

		return (j);
	}
}
