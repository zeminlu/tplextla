package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Farm {
	private HashMap<String,Animal> animalPreferences;
	private HashSet<Food> availableFood;
	private Animal currentAnimal;
	
	public Farm() {
		this.animalPreferences = new HashMap<String, Animal>();
		this.availableFood = new HashSet<Food>();
		
	}
	
	public void animalsNotEat() {
		for(Animal currAnimal : animalPreferences.values()){
			System.out.println(currAnimal.getName());
			System.out.println(notEatBy(currAnimal));
		}
	}
	
	public Set<String> notEatBy(Animal animal) {
		Set<String> ans = new HashSet<String>();
		for(Food currentFood : availableFood) {
			if(!animal.getDietPreferences().contains(currentFood)) {
				ans.add(currentFood.getName());
			}
		}
		return ans;
	}
	
	public List<String> animalsHaventFood() {
		List<String> ans = new LinkedList<String>();
		for(Animal currAnimal : animalPreferences.values()){
			if(!availableFood.containsAll(currAnimal.getDietPreferences())) {
				ans.add(currAnimal.getName());
			}
		
		}
		return ans;
	}
	
	public Set<String> absentPlants() {
		Set<String> ansFoods = new HashSet<String>(); 
		
		for(Animal curAnimal : animalPreferences.values()){
			for(Food animalFood : curAnimal.getDietPreferences()){
				if(!availableFood.contains(animalFood) && !animalFood.isFoodReference()) {
					ansFoods.add(animalFood.getName());
				}
			}	
		}
		return ansFoods; 
	}
	
	public Set<String> absentDiet() {
		Set<String> ansFoods = new HashSet<String>(); 
		
		for(Animal curAnimal : animalPreferences.values()){
			for(Food animalFood : curAnimal.getDietPreferences()){
				if(animalFood.isFoodReference()) {
					ansFoods.add(curAnimal.getName());
				}
			}	
		}
		return ansFoods; 
	}
	
	public void addFood(Food food) {
		this.availableFood.add(food);
	}
	
	public void addAnimal(Animal animal) {
		
		this.animalPreferences.put(animal.getName(), animal);
	}


	public HashSet<Food> getAvailablePlants() {
		return availableFood;
	}

	public void setAvailablePlants(HashSet<Food> availablePlants) {
		this.availableFood = availablePlants;
	}

	public Animal getCurrentAnimal() {
		return currentAnimal;
	}

	public void setCurrentAnimal(Animal currentAnimal) {
		this.currentAnimal = currentAnimal;
	}
	
	public void printAnimals() {

		for(Animal current : this.animalPreferences.values()) {
			System.out.println("Nombre del animal :" + current.toString() + " Y su dieta: ");
			for(Food food : current.getDietPreferences()) {
				System.out.println(food);
			}
		}
	}
	
	public void printFood() {
		System.out.println("Comida disponible en la graja");
		for(Food food : this.availableFood) {
			System.out.println(food);
		}
	}
	
	public void updateDietReferences() {
		 HashSet<Food> aux;
		 clearMarks();
		for(Animal animal : this.animalPreferences.values()) {
			aux = upDietReferences(animal);
			if(aux != null) {
				animal.setFoodPreferences(aux);				
			}
		}
	}
	
	private HashSet<Food> upDietReferences(Animal animal) {
		HashSet<Food> response = new HashSet<Food>();
		if(animal.getVisited()){
			return null;
		}
		for(Food food : animal.getDietPreferences()){
			
			if(food.isFoodReference()){
				animal.setVisited(true);
				Animal auxAnimal = this.animalPreferences.get(food.getReferece());
				if( auxAnimal != null) {
					HashSet<Food> auxResp = upDietReferences(auxAnimal);
					if(auxResp != null) {
						response.addAll(auxResp);						
					}
				}else{
					response.add(food);
				}


				animal.setVisited(false);
			}else{
				response.add(food);				
			}
			
		}
		
		return response;
	}
	
	private void clearMarks() {
		for(Animal animal : this.animalPreferences.values()) {
			animal.setVisited(false);
		}
	}
	
}
