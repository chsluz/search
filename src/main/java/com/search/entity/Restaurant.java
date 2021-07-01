package com.search.entity;

import java.util.Comparator;
import java.util.Objects;

public class Restaurant {
	private String name;
	private Integer customerRating;
	private Integer distance;
	private Integer price;
	private Cuisine cuisine;
	public Integer matches = 0;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String name, Integer customerRating, Integer distance, Integer price, Cuisine cuisine) {
		super();
		this.name = name;
		this.customerRating = customerRating;
		this.distance = distance;
		this.price = price;
		this.cuisine = cuisine;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCustomerRating() {
		return customerRating;
	}
	public void setCustomerRating(Integer customerRating) {
		this.customerRating = customerRating;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Cuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cuisine, customerRating, distance, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		return Objects.equals(cuisine, other.cuisine) && Objects.equals(customerRating, other.customerRating)
				&& Objects.equals(distance, other.distance) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price);
	}
	
	
	
	

}
