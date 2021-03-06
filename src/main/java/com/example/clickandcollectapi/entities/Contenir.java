package com.example.clickandcollectapi.entities;

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
public class Contenir {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	@Column(name ="quantite_commandee")
	private Integer quantite;

	@ManyToOne
	@JoinColumn(name="idCommandeId")
	private Commande commandeContenir;

	@ManyToOne
	@JoinColumn(name="idArticleId")
	private Article articleContenir;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Article getArticle() {
		return articleContenir;
	}

	public void setArticle(Article article) {
		this.articleContenir = article;
	}

	public Commande getCommande() {
		return commandeContenir;
	}

	public void setCommande(Commande commande) {
		this.commandeContenir = commande;
	}

	public JSONObject ajoutToJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("quantite", quantite.toString());
		j.put("idArticle", articleContenir.getId());
		j.put("idCommande", commandeContenir.getId());
		j.put("update", "/contenir/update/" + id);
		j.put("delete", "/contenir/delete/" + id);
		j.put("help", "/swagger-ui.html#/contenir-controller");
		return (j);
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("quantite", quantite.toString());
		if(articleContenir.toJSON() != null)
		{
			j.put("article", articleContenir.toJSON());
		}
		if(commandeContenir != null)
		{
			j.put("commande", commandeContenir.toJSON());
		}
		j.put("update", "/contenir/update/" + id);
		j.put("delete", "/contenir/delete/" + id);
		j.put("help", "/swagger-ui.html#/contenir-controller");
		return (j);
	}
}
