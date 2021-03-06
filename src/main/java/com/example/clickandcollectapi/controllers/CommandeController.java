package com.example.clickandcollectapi.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.example.clickandcollectapi.entities.Commande;
import com.example.clickandcollectapi.entities.Magasin;
import com.example.clickandcollectapi.entities.User;
import com.example.clickandcollectapi.exceptions.RessourceBadRequestException;
import com.example.clickandcollectapi.exceptions.RessourceIntrouvableException;
import com.example.clickandcollectapi.repositories.CommandeRepository;
import com.example.clickandcollectapi.repositories.MagasinRepository;
import com.example.clickandcollectapi.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/commande") // This means URL's start with /demo (after Application path)
public class CommandeController {
    @Autowired // This means to get the bean called userRepository
				// Which is auto-generated by Spring, we will use it to handle the data
	private CommandeRepository commandeRepository;
	@Autowired
	private MagasinRepository magasinRepository;
	@Autowired
    private UserRepository userRepository;

	@PostMapping(path = "") // Map ONLY POST Requests
	@RequestMapping(value = { "/", "/add" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addNewCommande(@RequestParam Integer etatCommande,
    @RequestParam Integer idMagasin,@RequestParam Integer idUser) throws JsonProcessingException {
		
		Commande n = new Commande();
        Magasin m = new Magasin();
        User u = new User();
        n.setDateCommande(new Date());
		n.setEtatCommande(etatCommande);
        m.setId(idMagasin);
        n.setMagasin(m);
        u.setId(idUser);
        n.setUser(u);
		commandeRepository.save(n);
		return n.toJSON().toString();
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Commande> getAllCommande() {
		// This returns a JSON or XML with the users
		return commandeRepository.findAll();
	}

	@RequestMapping(value = { "/", "/{commandeId}" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findCommandeById(@PathVariable Integer commandeId) throws JsonProcessingException, RessourceIntrouvableException {
		
		Optional<Commande> n = commandeRepository.findById(commandeId);
		if(n.isPresent()){
			Commande commande = n.get();
			return commande.toJSON().toString();
		}
		else{
			// JSONObject JSONErreur = new JSONObject();
			// JSONErreur.put("message", "Error");
			// JSONErreur.put("help", "/swagger-ui.html#/commande-controller");
			// return JSONErreur.toString();
			throw new RessourceIntrouvableException(Integer.toString(commandeId)+";/swagger-ui.html#/commande-controller");
		}
		
	}

	
	@DeleteMapping("/delete/{commandeId}")  
	private void deleteCommande(@PathVariable("commandeId") Integer commandeId)   
	{  
		if(commandeRepository.findById(commandeId).isPresent())
		{
			commandeRepository.deleteById(commandeId);
		}
		else
		{
			throw new RessourceIntrouvableException(Integer.toString(commandeId)+";/swagger-ui.html#/commande-controller");
		}
		
	}

	@RequestMapping(value = { "/", "/update/{commandeId}" }, method = RequestMethod.PUT, produces = "application/json") 
	private @ResponseBody String update(@PathVariable("commandeId") Integer commandeId,
	@RequestParam(required = false) Integer etatCommande,@RequestParam(required = false) Integer idMagasin,
	@RequestParam(required = false) Integer idUser) throws JsonProcessingException,RessourceBadRequestException
	{  
		Optional<Commande> n = commandeRepository.findById(commandeId);
		if(n.isPresent()){
			Commande commande = n.get();
			if(idMagasin != null)
			{
			 	Magasin m = new Magasin();
				m.setId(idMagasin);
				commande.setMagasin(m);
			}
			if(idUser != null)
			{
				User u = new User();
				u.setId(idUser);
				commande.setUser(u);
			}
			if(etatCommande != null)
			{
				commande.setEtatCommande(etatCommande);
			}
        	if(idMagasin != null || idUser !=null || etatCommande != null)
			{
				commande.setDateCommande(new Date());
				commandeRepository.save(commande);
				return commande.toJSON().toString();
			}
			else
			{
				// JSONObject JSONInfo = new JSONObject();
				// JSONInfo.put("message", "Aucune modification nécéssaire");
				// JSONInfo.put("commande", commande.toJSON());
				// return JSONInfo.toString();
				throw new RessourceBadRequestException(Integer.toString(commandeId)+";/swagger-ui.html#/commande-controller");
			}
			
		}
		else{
			// JSONObject JSONErreur = new JSONObject();
			// JSONErreur.put("message", "Error");
			// JSONErreur.put("help", "/swagger-ui.html#/commande-controller");
			// return JSONErreur.toString();
			throw new RessourceIntrouvableException(Integer.toString(commandeId)+";/swagger-ui.html#/commande-controller");
		}
		
	}  
}
