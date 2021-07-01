package com.search.comparator;

import java.util.Comparator;

import com.search.entity.Restaurant;

public class RestaurantComparator implements Comparator<Restaurant>{
	
	@Override
	public int compare(Restaurant r1, Restaurant r2) {
		int matches = r1.matches.compareTo(r2.matches);
		if(matches != 0)
			return matches;
		int distance = r1.getDistance().compareTo(r2.getDistance());
        if (distance != 0)
            return distance;
        int rating = r1.getCustomerRating().compareTo(r2.getCustomerRating());
        if (rating != 0)
            return rating;
        return r1.getPrice().compareTo(r2.getPrice());
	}

}
