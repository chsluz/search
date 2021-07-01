package com.search.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ResourceUtils;

import com.search.entity.Cuisine;
import com.search.entity.Restaurant;

public class FileReader {
	
	public static List<Restaurant> restaurantCousines = listRestaurantCousine();
	
	
	public static List<Restaurant> listRestaurantCousine()  {
		if(restaurantCousines != null) {
			return restaurantCousines;
		}
		try {
			List<Cuisine> readFileCuisines = readFileCuisines();
			List<Restaurant> readFileRestaurants = readFileRestaurants();
			return readFileRestaurants.stream().map(res -> {
				
				Optional<Cuisine> cousine = readFileCuisines
						.stream()
						.filter(edd -> edd.getId().equals(res.getCuisine().getId()))
						.findAny();
				if(cousine.isPresent()) {
					res.setCuisine(cousine.get());
				}
				return res;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static List<Cuisine> readFileCuisines() throws FileNotFoundException {
		InputStream is = FileReader.class.getClassLoader().getResourceAsStream("cuisines.csv");
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		      List<Cuisine> cuisines = new ArrayList<>();
		      for (CSVRecord csvRecord : csvRecords) {
		    	  cuisines.add(new Cuisine(Integer.parseInt(csvRecord.get("id")), csvRecord.get("name")));
		      }
		      return cuisines;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
	}
	
	public static List<Restaurant> readFileRestaurants() throws FileNotFoundException {
		InputStream is = FileReader.class.getClassLoader().getResourceAsStream("restaurants.csv");
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		      List<Restaurant> restaurants = new ArrayList<>();
		      for (CSVRecord csvRecord : csvRecords) {
		    	  String name = csvRecord.get("name");
		    	  Integer customerRating = Integer.parseInt(csvRecord.get("customer_rating"));
		    	  Integer distance = Integer.parseInt(csvRecord.get("distance"));
		    	  Integer price = Integer.parseInt(csvRecord.get("price"));
		    	  Integer cuisineId = Integer.parseInt(csvRecord.get("cuisine_id"));
		    	  restaurants.add(new Restaurant(name, customerRating, distance, price, new Cuisine(cuisineId, "")));
		      }
		      return restaurants;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
	}

}
