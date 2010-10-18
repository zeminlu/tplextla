package app;

import java.util.HashSet;

public class Animal {
	private String name;
	private boolean visited;
	private HashSet<Food> dietPreferences;
	
	public Animal(String name) {
		this.name = name.trim();
		this.visited = false;
		this.dietPreferences = new HashSet<Food>();
	}
	
	public Animal(String name, HashSet<Food> dietPreferences) {
		this.name = name.trim();
		this.visited = false;
		this.dietPreferences = dietPreferences;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	public Boolean getVisited() {
		return visited;
	}
	public void setVisited(Boolean visited) {
		this.visited = visited;
	}
	public HashSet<Food> getDietPreferences() {
		return dietPreferences;
	}
	public void setFoodPreferences(HashSet<Food> dietPreferences) {
		this.dietPreferences = dietPreferences;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public void addFoodToDiet(Food food) {
		this.dietPreferences.add(food);
	}

	@Override
	public String toString() {
		return "Animal " + name;
	}
	
}
