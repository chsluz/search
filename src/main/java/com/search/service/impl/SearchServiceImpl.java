package com.search.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.search.comparator.RestaurantComparator;
import com.search.entity.Restaurant;
import com.search.reader.FileReader;
import com.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Override
	public List<Restaurant> searchByCriteria(String restaurantName,
			Integer customerRating,
			Integer distance,
			Integer price,
			String cuisineName) {
		List<Restaurant> restaurants = FileReader.listRestaurantCousine();
		List<Restaurant> filteredRestaurant = filter(restaurantName, customerRating, distance, price,cuisineName, restaurants);
		if(filteredRestaurant.size() > 0) {
			Collections.sort(filteredRestaurant, new RestaurantComparator());
			return filteredRestaurant.size() < 6 ? filteredRestaurant : filteredRestaurant.subList(0, 5);	
		}
		else {
			return searchByMoreMatches(restaurantName, customerRating, distance, price, cuisineName, restaurants);	
		}
	}

	private List<Restaurant> searchByMoreMatches(String restaurantName, Integer customerRating, Integer distance,
			Integer price, String cuisineName, List<Restaurant> restaurants) {
		List<Restaurant> byRestaurantName = restaurantName != null ? filter(restaurantName, null, null, null, null, restaurants): new ArrayList<>();
		List<Restaurant> byCustomerRating = customerRating != null ? filter(null, customerRating, null, null, null, restaurants) : new ArrayList<>();
		List<Restaurant> byDistance = distance != null ? filter(null, null, distance, null, null, restaurants) : new ArrayList<>();
		List<Restaurant> byPrice =  price != null ? filter(null, null, null, price, null, restaurants) : new ArrayList<>();
		List<Restaurant> byCuisineName = cuisineName != null ? filter(null, null, null, null, cuisineName, restaurants) : new ArrayList<>();
		
		restaurants.stream().forEach(restaurant -> {
			var matches = restaurant.matches;
			if(byRestaurantName.contains(restaurant)) {
				matches = matches+1;
			}
			if(byCustomerRating.contains(restaurant)) {
				matches = matches+1;
			}
			if(byDistance.contains(restaurant)) {
				matches = matches+1;
			}
			if(byPrice.contains(restaurant)) {
				matches = matches+1;
			}
			if(byCuisineName.contains(restaurant)) {
				matches = matches+1;
			}
			restaurant.matches = matches;
		});
		restaurants.sort(new RestaurantComparator().reversed());
		return restaurants.size() < 6 ? restaurants : restaurants.subList(0, 5);
	}

	private ArrayList<Restaurant> filter(String restaurantName, Integer customerRating, Integer distance, Integer price,
			String cuisineName,List<Restaurant> restaurants) {
		List<Restaurant> list = restaurants
					.stream()
					.filter(res -> 
							{ 
								boolean sholdReturn = true;
								if(restaurantName != null) {
									sholdReturn = res.getName().contains(restaurantName) ? sholdReturn : false;
								}
								if(customerRating != null) {
									sholdReturn = res.getCustomerRating() >= customerRating ? sholdReturn : false;
								}
								if(distance != null) {
									sholdReturn = res.getDistance() <= distance ? sholdReturn : false;
								}
								if(price != null) {
									sholdReturn = res.getPrice() <= price ? sholdReturn : false;
								}
								if(cuisineName != null) {
									sholdReturn = res.getCuisine().getName().contains(cuisineName) ? sholdReturn : false;
								}
								return sholdReturn;
							}
					 )
					.collect(Collectors.toList());
		return new ArrayList<>(list);
	}

}
