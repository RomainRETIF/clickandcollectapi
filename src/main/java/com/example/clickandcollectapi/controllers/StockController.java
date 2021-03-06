package com.example.clickandcollectapi.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.example.clickandcollectapi.entities.Article;
import com.example.clickandcollectapi.entities.Magasin;
import com.example.clickandcollectapi.entities.Stock;
import com.example.clickandcollectapi.exceptions.RessourceBadRequestException;
import com.example.clickandcollectapi.exceptions.RessourceIntrouvableException;
import com.example.clickandcollectapi.repositories.ArticleRepository;
import com.example.clickandcollectapi.repositories.MagasinRepository;
import com.example.clickandcollectapi.repositories.StockRepository;
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
@RequestMapping(path = "/stock") // This means URL's start with /demo (after Application path)

public class StockController {
    @Autowired // This means to get the bean called userRepository
				// Which is auto-generated by Spring, we will use it to handle the data
	private StockRepository stockRepository;
	private MagasinRepository magasinRepository;
    private ArticleRepository articleRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	@RequestMapping(value = { "/", "/add" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addNewStock(@RequestParam Integer quantite,
    @RequestParam Integer idMagasin,@RequestParam Integer idArticle) throws JsonProcessingException {
		
		Stock n = new Stock();
        Article a = new Article();
        Magasin m = new Magasin();
        a.setId(idArticle);
        m.setId(idMagasin);
        n.setQuantite(quantite);
		n.setMagasin(m);
        n.setArticle(a);
		stockRepository.save(n);
		return n.stockAjoutJSON().toString();
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Stock> getAllStock() {
		//This returns a JSON or XML with the users
		return stockRepository.findAll();
	}

	@RequestMapping(value = { "/", "/{stockId}" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findStockById(@PathVariable Integer stockId) throws JsonProcessingException, RessourceIntrouvableException {
		Optional<Stock> n = stockRepository.findById(stockId);
		if(n.isPresent()){
			Stock stock = n.get();
			return stock.toJSON().toString();
		}
		else{
			throw new RessourceIntrouvableException(Integer.toString(stockId)+";/swagger-ui.html#/stock-controller");
		}
		
	}

	
	@DeleteMapping("/delete/{stockId}")  
	private void deleteStock(@PathVariable("stockId") Integer stockId)
	{  
		if(stockRepository.findById(stockId).isPresent()){
			stockRepository.deleteById(stockId);
		}
		else{
			throw new RessourceIntrouvableException(Integer.toString(stockId)+";/swagger-ui.html#/stock-controller");
		}
	}

	@RequestMapping(value = { "/", "/update/{stockId}" }, method = RequestMethod.PUT, produces = "application/json")
	private @ResponseBody String update(@PathVariable("stockId") Integer stockId,
	@RequestParam(required = false) Integer quantite,@RequestParam(required = false) Integer idMagasin,
    @RequestParam(required = false) Integer idArticle) throws JsonProcessingException 
	{  
		Optional<Stock> n = stockRepository.findById(stockId);
		if(n.isPresent()){
			Stock stock = n.get();
			if(idMagasin != null)
			{
				Magasin m = new Magasin();
				m.setId(idMagasin);
				stock.setMagasin(m);
			}
			if(idArticle != null)
			{
				Article a = new Article();
				a.setId(idArticle);
				stock.setArticle(a);
			}
			if(quantite != null)
			{
				stock.setQuantite(quantite);
			}
            if(idMagasin != null || idArticle != null || quantite != null)
			{
				stockRepository.save(stock);
				return stock.stockAjoutJSON().toString();
			}
			else
			{
				throw new RessourceBadRequestException(Integer.toString(stockId)+";/swagger-ui.html#/stock-controller");
			}
			
		}
		else{
			throw new RessourceIntrouvableException(Integer.toString(stockId)+";/swagger-ui.html#/stock-controller");
		}
		
	}  
}
