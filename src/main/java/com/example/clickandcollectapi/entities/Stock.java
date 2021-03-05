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
	private Magasin magasinStock;

	@ManyToOne
	@JoinColumn(name="idArticleId")
    private Article articleStock;

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
		return magasinStock;
	}

	public void setMagasin(Magasin magasin) {
		this.magasinStock = magasin;
	}

    public Article getArticle() {
		return articleStock;
	}

	public void setArticle(Article article) {
		this.articleStock = article;
	}

	public JSONObject stockAjoutJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("quantite", quantite);
		j.put("idMagasin", magasinStock.getId());
        j.put("idArticle", articleStock.getId());
		j.put("update", "/stock/update/" + id);
		j.put("delete", "/stock/delete/" + id);
		return (j);
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("quantite", quantite);
		j.put("magasin", magasinStock.toJSON());
        j.put("article", articleStock.toJSON());
		j.put("update", "/stock/update/" + id);
		j.put("delete", "/stock/delete/" + id);
		return (j);
	}

}
