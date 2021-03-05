package com.example.clickandcollectapi.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class Commande {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	@Column(name = "date_commande")
	private Date dateCommande;

    @Column(name= "etat_commande")
    private Integer etatCommande;

    @ManyToOne
	@JoinColumn(name="idMagasinId")
    private Magasin magasin;

    @ManyToOne
	@JoinColumn(name="idUserId")
    private User user;

	@OneToMany(mappedBy = "commandeContenir", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Contenir> contenirs;

	public List<Contenir> getContenirs(){
		return contenirs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Integer getEtatCommande() {
		return etatCommande;
	}

	public void setEtatCommande(Integer etatCommande) {
		this.etatCommande = etatCommande;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JSONObject magasinJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("nom", magasin.getNom());
		j.put("description", magasin.getDescription());
		j.put("codePostale", magasin.getCodePostal());
		return (j);
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("dateCommande", dateCommande);
		j.put("etatCommande", etatCommande);
		j.put("magasin", magasinJSON());
        j.put("idUser", user.getId());
		j.put("update", "/commande/update/" + id);
		j.put("delete", "/commande/delete/" + id);
		j.put("help", "/swagger-ui.html#/commande-controller");
		return (j);
	}
}
