package recipeParser.dictionary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Vocabulary {
	private List<VocabType> categories;
	private String name;
	
	public Vocabulary(String name, VocabType category){
		this.name = name;
		this.categories = new ArrayList<VocabType>();
		this.categories.add(category);
	}
	
	public Vocabulary(String name, List<VocabType> categories){
		this.name = name;
		this.categories = categories;
	}
	
	public List<VocabType> getCategories() {
		return categories;
	}
	public void setCategories(List<VocabType> categories) {
		this.categories = categories;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString(){
		return name + ": " + this.categories;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	public static int getHashCode(String name){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}

class VocabularySpaceComparator implements Comparator<Vocabulary> {
	@Override
	public int compare(Vocabulary v1, Vocabulary v2) {
        return v1.getName().split(" ").length - v2.getName().split(" ").length;
    }
}
