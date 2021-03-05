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

	public JSONObject toJSON() throws JsonProcessingException {
		
	    JSONObject j = new JSONObject();
		j.put("id", id);
		j.put("email", email);
		j.put("roles", roles);
		j.put("password", password);
        j.put("isVerified", isVerified);
		j.put("update", "/user/update/" + id);
		j.put("delete", "/user/delete/" + id);

		return (j);
	}
}
