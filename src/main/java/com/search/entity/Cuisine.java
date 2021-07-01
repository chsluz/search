package com.search.entity;

import java.util.Objects;

public class Cuisine {
	private Integer id;
	private String name;
	
	public Cuisine(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuisine other = (Cuisine) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
	
	
}
