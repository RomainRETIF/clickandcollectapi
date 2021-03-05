package com.example.clickandcollectapi.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.example.clickandcollectapi.entities.User;
import com.example.clickandcollectapi.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;
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
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)

public class UserController {
	@Autowired // This means to get the bean called userRepository
				// Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser(@RequestParam String email, @RequestParam String roles,
			@RequestParam String password, @RequestParam boolean isVerified) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setEmail(email);
		n.setRoles(roles);
		n.setPassword(password);
		n.setVerified(isVerified);
		userRepository.save(n);
		return "Saved";
	}

	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String loginUser(@RequestParam(required = true) String email, @RequestParam(required = true) String password) throws JsonProcessingException {
		Optional<User> n = userRepository.findByEmailAndPassword(email, password);
		if(n.isPresent()){
			User user = n.get();
			return user.toJSON().toString();
		}
		else{

			return new JSONObject().put("error", "user not found").put("help", "/swagger-ui.html#/user-controller").toString();
		}
	}

	@RequestMapping(value = { "/", "/{userId}" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findUserById(@PathVariable Integer userId) throws JsonProcessingException {

		Optional<User> n = userRepository.findById(userId);
		if (n.isPresent()) {
			User user = n.get();
			return user.toJSON().toString();
		} else {
			JSONObject JSONErreur = new JSONObject();
			JSONErreur.put("message", "Error");
			JSONErreur.put("help", "/swagger-ui.html#/user-controller");
			return JSONErreur.toString();
		}

	}

	@DeleteMapping("/delete/{userId}")
	private void deleteUser(@PathVariable("userId") Integer userId) {
		userRepository.deleteById(userId);
	}

	// @PutMapping("/update/{userId}")
	@RequestMapping(value = { "/", "/update/{userId}" }, method = RequestMethod.PUT, produces = "application/json")
	private @ResponseBody String update(@PathVariable("userId") Integer userId,
			@RequestParam(required = false) String email, @RequestParam(required = false) String roles,
			@RequestParam(required = false) String password, @RequestParam boolean isVerified)
			throws JSONException, JsonProcessingException 
	{  
		Optional<User> n = userRepository.findById(userId);
		if(n.isPresent()){
			User user = n.get();
			if(email != null)
			{
				user.setEmail(email);
			}
			if(roles != null)
			{
				user.setRoles(roles);
			}
            if(password != null)
			{
				user.setPassword(password);
			}
            user.setVerified(isVerified);
            userRepository.save(user);
			JSONObject JSONInfo = new JSONObject();
			JSONInfo.put("user", user.toJSON());
			return JSONInfo.toString();
		}
		else{
			JSONObject JSONErreur = new JSONObject();
			JSONErreur.put("message", "Error");
			JSONErreur.put("help", "/swagger-ui.html#/user-controller");
			return JSONErreur.toString();
		}
		
	}  
}
