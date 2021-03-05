package com.example.clickandcollectapi.entities;

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
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	private Double prix;

	@ManyToOne
	@JoinColumn(name="idTypeArticleId")
	private TypeArticle typeArticle;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Contenir> contenirs;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Stock> stocks;
	
	public List<Contenir> getContenirs(){
		return contenirs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public TypeArticle getTypeArticle() {
		return typeArticle;
	}

	public void setTypeArticle(TypeArticle typeArticle) {
		this.typeArticle = typeArticle;
	}

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("prix", prix.toString());
		j.put("typeArticle", typeArticle.toJSON());
		j.put("update", "/article/update/" + id);
		j.put("delete", "/article/delete/" + id);

		return (j);
	}
}
