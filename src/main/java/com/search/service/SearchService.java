package com.search.service;

import java.util.List;

import com.search.entity.Restaurant;

public interface SearchService {
	
	public List<Restaurant> searchByCriteria(String restaurantName,
			Integer customerRating,
			Integer distance,
			Integer price,
			String cuisineName) ;

}
