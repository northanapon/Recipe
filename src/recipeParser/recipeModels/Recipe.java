package recipeParser.recipeModels;

import java.util.List;

public class Recipe {
	private String name;
	private String cookingTime;
	private String servingSize;
	private String fullText;
	private String url;
	private List<Ingredient> ingredients;
	private List<Instruction> instructions;
	
	public Recipe(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(String cookingTime) {
		this.cookingTime = cookingTime;
	}

	public String getServingSize() {
		return servingSize;
	}

	public void setServingSize(String servingSize) {
		this.servingSize = servingSize;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
	@Override
	public String toString(){
		String desc = "Name: " + this.name;
		desc += "\n\nCooking Time: " + this.cookingTime + ", Serving Size: " + this.servingSize;
		desc += "\n\nIngredients: ";
		int i = 1;
		for(Ingredient ig : this.ingredients){
			desc += "\n \u2022 " + ig.describe(true); 
			i++;
		}
		i = 1;
		desc += "\n\nInstructions: ";
		for(Instruction is : this.instructions){
			desc += "\n " + i + ". " + is;
			i++;
		}
		return desc;
	}
	
	public String describe(){
		String desc = "";
		
		if(this.getName() != null && this.getName() != ""){
			desc += "Name: " + this.getName();
		}
		
		if(this.getCookingTime() != null && this.getCookingTime() != ""){
			desc += "\nCooking Time: " + this.getCookingTime();
		}
		
		if(this.getServingSize() != null && this.getServingSize() != ""){
			desc += "\nServing Size: " + this.getServingSize();
		}
		
		desc += "\n\nIngredients: ";
		for(Ingredient ig : this.ingredients){
			desc += "\n \u2022 " + ig.describe(true); 
		}
		desc += "\n\nInstructions: ";
		int i = 1;
		for(Instruction is : this.instructions){
			desc += "\n "+i+". "+is.describe();
			String prep = "";
			for(Instruction isP : is.getPreparations()){
				int ii = 1;
				for(Instruction isL : this.instructions){
					if(isP == isL) {
						prep = "("+ii+"), ";
					}
					ii++;
				}
			}
			i++;
			if(!prep.equalsIgnoreCase("")) {
				desc += "\n  - Prerequirsite(s): " + prep;
			}
		}
		return desc;
	}
}
