package com.example.clickandcollectapi.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.example.clickandcollectapi.entities.Magasin;
import com.example.clickandcollectapi.repositories.MagasinRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.classreading.MethodMetadataReadingVisitor;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/magasin") // This means URL's start with /demo (after Application path)
public class MagasinController {
	@Autowired // This means to get the bean called userRepository
				// Which is auto-generated by Spring, we will use it to handle the data
	private MagasinRepository magasinRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewMagasin(@RequestParam(required = false) String name, @RequestParam(required = false) String description,
			@RequestParam(required = false) String codePostal) throws JsonProcessingException {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Magasin n = new Magasin();
		n.setNom(name);
		n.setDescription(description);
		n.setCodePostal(codePostal);
		magasinRepository.save(n);
		return n.toJSON().toString();
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Magasin> getAllMagasins() {
		// This returns a JSON or XML with the users
		return magasinRepository.findAll();
	}

	@GetMapping("/search/{magasinCodePostal}")  
	private @ResponseBody Iterable<Magasin> searchMagasins(@PathVariable("magasinCodePostal") String magasinCodePostal)   
	{  
		return magasinRepository.findByCodePostalIs(magasinCodePostal);
	}

	@RequestMapping(value = { "/", "/{magasinId}" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findMagasinById(@PathVariable Integer magasinId) throws JsonProcessingException {
		
		
		Optional<Magasin> n = magasinRepository.findById(magasinId);
		if(n.isPresent()){
			Magasin magasin = n.get();
			return magasin.toJSON().toString();
		}
		else{
			JSONObject JSONErreur = new JSONObject();
			JSONErreur.put("message", "Error");
			JSONErreur.put("help", "/swagger-ui.html#/magasin-controller");
			return JSONErreur.toString();
		}
		
	}

	@DeleteMapping("/delete/{magasinId}")  
	private void deleteMagasin(@PathVariable("magasinId") Integer magasinId)   
	{  
		magasinRepository.deleteById(magasinId);
	}

	@RequestMapping(value = { "/", "/update/{magasinId}" }, method = RequestMethod.PUT, produces = "application/json")
	private @ResponseBody String update(@PathVariable("magasinId") Integer magasinId, @RequestParam(required = false) String nom, @RequestParam(required = false) String description, @RequestParam(required = false) String codePostal)
			throws JsonProcessingException 
	{  
		Optional<Magasin> n = magasinRepository.findById(magasinId);
		if(n.isPresent()){
			Magasin magasin = n.get();
			if(nom != null){
				magasin.setNom(nom);
			}
			if(description != null){
				magasin.setDescription(description);
			}
			if(codePostal != null){
				magasin.setCodePostal(codePostal);
			}
			if(nom != null || description != null || codePostal != null)
			{
				magasinRepository.save(magasin);
				return magasin.toJSON().toString();
			}
			else
			{
				JSONObject JSONInfo = new JSONObject();
				JSONInfo.put("message", "Aucune modification nécéssaire");
				JSONInfo.put("stock", magasin.toJSON());
				return JSONInfo.toString();
			}
			
		}
		else{
			JSONObject JSONErreur = new JSONObject();
			JSONErreur.put("message", "Error");
			JSONErreur.put("help", "/swagger-ui.html#/magasin-controller");
			return JSONErreur.toString();
		}
		
	}  
}
