package com.example.clickandcollectapi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

@Entity // This tells Hibernate to make a table out of this class
public class TypeArticle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	private String libelle;

	private String description;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getlibelle() {
		return libelle;
	}

	public void setlibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("libelle", libelle);
		j.put("description", description);
		j.put("update", "/typearticle/update/" + id);
		j.put("delete", "/typearticle/delete/" + id);

		return (j.toString());
	}
}
