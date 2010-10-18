package app;


public class Food implements Comparable<Food> {
	static final String referenceValue = "idem ";
	private String name;
	private int popularity;

	public Food(String name) {
		this.name = name.trim();
		this.popularity = 0;
	}
	
	public void vote() {
		this.popularity++;
	}
	
	public void resetPopularity() {
		this.popularity = 0;
	}
	
	public int getPopularity() {
		return popularity;
	}

	@Override
	public String toString() {
		return "Food " + name;
	}

	public String getName() {
			return name;			
	}

	public void setName(String name) {
		this.name = name;
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
		Food other = (Food) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public boolean isFoodReference() {
		return this.getName().contains(referenceValue);
	}
	
	public String getReferece() {
		return this.name.substring(referenceValue.length());
	}

	@Override
	public int compareTo(Food o) {
		return o.popularity - this.popularity;
		
	}
	
}
