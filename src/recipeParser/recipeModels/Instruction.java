package recipeParser.recipeModels;

import java.util.ArrayList;
import java.util.List;

public class Instruction extends RecipeComponent {
	private List<Instruction> preparations;
	private String cookingAction;
	private List<Ingredient> ingredients;
	private String duration;
	private String temperature;
	private List<String> cookingTools; 
	
	public Instruction(String cookingAction, String fullText, int lineIndex, int charIndex) {
		super(fullText, lineIndex, charIndex);
		this.setCookingAction(cookingAction);
		this.setPreparations(new ArrayList<Instruction>());
		this.setIngredients(new ArrayList<Ingredient>());
		this.setCookingTools(new ArrayList<String>());
	}
	
	public Instruction(String fullText, int lineIndex, int charIndex) {
		super(fullText, lineIndex, charIndex);
		this.setPreparations(new ArrayList<Instruction>());
		this.setIngredients(new ArrayList<Ingredient>());
		this.setCookingTools(new ArrayList<String>());
	}
	
	public List<Instruction> getPreparations() {
		return preparations;
	}

	public void setPreparations(List<Instruction> preparations) {
		this.preparations = preparations;
	}

	public String getCookingAction() {
		return cookingAction;
	}

	public void setCookingAction(String cookingAction) {
		this.cookingAction = cookingAction;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getCookingTools() {
		return cookingTools;
	}

	public void setCookingTools(List<String> cookingTools) {
		this.cookingTools = cookingTools;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public String toString() {
		String desc = this.cookingAction;
		if(!this.ingredients.isEmpty()) {
			desc += "\n\t- Ingredient: " + this.ingredients;
		}
		if(!this.cookingTools.isEmpty()){
			desc += "\n\t- Tools: " + this.cookingTools;
		}
		if(this.temperature != null && this.temperature != "") {
			desc += "\n\t- Temperature: " + this.temperature;
		}
		if(this.duration != null && this.duration != "") {
			desc += "\n\t- Duration: " + this.duration;
		}
		if(!this.preparations.isEmpty()) {
			desc += "\n\t- Preparation: \n";
			int i = 1;
			for(Instruction is : this.preparations){
				desc += "\n\t\t- " + i + ". " + is.toString(false);
				i++;
			}
		}
		return desc;
	}
	
	public String toString(boolean isPrepShown) {
		String desc = this.cookingAction;
		if(!this.ingredients.isEmpty()) {
			desc += "\n\t- Ingredient: " + this.ingredients;
		}
		if(!this.cookingTools.isEmpty()){
			desc += "\n\t- Tools: " + this.cookingTools;
		}
		if(this.temperature != null && this.temperature != "") {
			desc += "\n\t- Temperature: " + this.temperature;
		}
		if(this.duration != null && this.duration != "") {
			desc += "\n\t- Duration: " + this.duration;
		}
		if(!isPrepShown) return desc;
		if(!this.preparations.isEmpty()) {
			desc += "\n\t- Preparation: \n";
			int i = 1;
			for(Instruction is : this.preparations){
				desc += "\n\t\t- " + i + ". " + is.toString(false);
				i++;
			}
		}
		return desc;
	}
	
	public String describe(){
		String desc = this.cookingAction;
		if(!this.ingredients.isEmpty()) {
			desc += "\n  - Ingredient(s): ";
			for(Ingredient ig : this.ingredients){
				desc += ig.getName() + ", ";
			}
			desc = desc.substring(0, desc.length()-2);
		}
		if(!this.cookingTools.isEmpty()){
			desc += "\n  - Tool(s): ";
			for(String tool : this.cookingTools){
				desc += tool + ", ";
			}
			desc = desc.substring(0, desc.length()-2);
		}
		if(this.temperature != null && this.temperature != "") {
			desc += "\n  - Temperature: " + this.temperature;
		}
		if(this.duration != null && this.duration != "") {
			desc += "\n  - Duration: " + this.duration;
		}
		return desc;
	}
	
	public String describeShort(){
		String desc = this.cookingAction;
		if(!this.ingredients.isEmpty()) {
			desc += " ";
			for(Ingredient ig : this.ingredients){
				if(ig == null) continue;
				desc += ig.getName() + ", ";
			}
			desc = desc.substring(0, desc.length()-2);
		}
		if(!this.cookingTools.isEmpty()){
			desc += " using "; 
			for(String tool : this.cookingTools){
				desc += tool + ", ";
			}
			desc = desc.substring(0, desc.length()-2);
		}
		if(this.temperature != null && this.temperature != "") {
			desc += "\n  - Temperature: " + this.temperature;
		}
		if(this.duration != null && this.duration != "") {
			desc += "\n  - Duration: " + this.duration;
		}
		return desc;
	}
}
