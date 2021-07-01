package com.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.entity.Restaurant;
import com.search.service.SearchService;

@RestController
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@GetMapping("/status")
	public String getStatus() {
		return "Status OK";
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Restaurant>> search(
			@RequestParam(value="restaurantName", required = false) String restaurantName,
			@RequestParam(value="customerRating", required = false) Integer customerRating,
			@RequestParam(value="distance", required = false) Integer distance,
			@RequestParam(value="price", required = false) Integer price,
			@RequestParam(value="cuisineName", required = false) String cuisineName) {
		List<Restaurant> searchByCriteria = searchService.searchByCriteria(restaurantName,customerRating,distance,price,cuisineName);
		return new ResponseEntity<>(searchByCriteria, HttpStatus.OK);
	}

}
