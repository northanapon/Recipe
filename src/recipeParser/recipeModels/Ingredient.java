package recipeParser.recipeModels;

import java.util.List;

import recipeParser.dictionary.VocabType;

public class Ingredient extends RecipeComponent {

	private String amount;
	private String unit;
	private String name;
	private String prepAction;
	private List<VocabType> categories;
	
	//Constructors
	public Ingredient(String fullText, int lineIndex, int charIndex) {
		super(fullText, lineIndex, charIndex);
	}
	
	public Ingredient(String name, List<VocabType> categories, String unit, 
			String measurement, String prepAction, 
			String fullText, int lineIndex, int charIndex) {
		super(fullText, lineIndex, charIndex);
		this.setName(name);
		this.setCategories(categories);
		this.setUnit(unit);
		this.setAmount(measurement);
		this.setPrepAction(prepAction);
	}
	
	public Ingredient(String name, List<VocabType> categories, 
			String fullText, int lineIndex, int charIndex) {
		this(name, categories, null, null, null, fullText, lineIndex, charIndex);
	}
	
	//Property Synthesizing
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPrepAction() {
		return prepAction;
	}

	public void setPrepAction(String prepAction) {
		this.prepAction = prepAction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VocabType> getCategories() {
		return categories;
	}

	public void setCategories(List<VocabType> categories) {
		this.categories = categories;
	}
	
	@Override
	public String toString() {
		String desc = this.name;
		if(this.amount != null && this.amount != "") {
			desc += ": " + this.amount;
			if(this.unit != null && this.unit != ""){
				desc += " " + this.unit;
			}
		}
		if(this.prepAction != null && this.prepAction != "") {
			desc += "\n  - Preparation: " + this.prepAction;
		}
		else{
			desc += "\n  - Preparation: none";
		}
		return desc;
	}
	
	public String describe(boolean isCatNeeded) {
		String desc = this.toString();
		if(isCatNeeded){
			desc +="\n  - Category(s): ";
			for(VocabType v : this.categories){
				desc += v +", ";
			}
			desc = desc.substring(0, desc.length()-2);
		}
		return desc;
	}
}
