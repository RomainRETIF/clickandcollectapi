package com.example.clickandcollectapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class Stock {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer quantite;

	@ManyToOne
	@JoinColumn(name="idMagasinId")
	private Magasin magasin;

	@ManyToOne
	@JoinColumn(name="idArticleId")
    private Article article;

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

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

    public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("quantite", quantite);
		j.put("magasin", magasin.toJSON());
        j.put("article", article.toJSON());
		j.put("update", "/commande/update/" + id);
		j.put("delete", "/commande/delete/" + id);
		return (j);
	}

}
