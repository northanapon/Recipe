package recipeParser.instControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import recipeParser.dictionary.DictionaryManager;
import recipeParser.dictionary.StemmerX;
import recipeParser.dictionary.VocabType;
import recipeParser.dictionary.Vocabulary;
import recipeParser.recipeModels.Ingredient;
import recipeParser.recipeModels.Instruction;

public class InstructionParser {
	private final DictionaryManager dictMgr;
	private final List<Ingredient> igds;
	
	public InstructionParser(DictionaryManager dict, List<Ingredient> igds) {
		this.igds = igds;
		this.dictMgr = dict;
	}
	
	public Instruction parseInstuction(String line, int lineIndex, int charIndex) {
		Instruction ist = new Instruction(line, lineIndex, charIndex);
		//find actions
		boolean isStem = false;
		boolean isWarning = false;
		List<String> actions = this.findCookingAction(line, lineIndex, charIndex, isStem);
		if(actions.size() > 1) {
			isWarning = true;
			System.out.println("Warning: more than 1 action found - IGNORED the rest");
			System.out.println(" - Full text: \"" + line + "\"");
		}
		if(actions.size() < 1) {
			System.out.println("Error: no action found at char: "+ charIndex +", line: "+lineIndex+"- STOPPED");
			System.out.println(" - Full text: \"" + line + "\"");
			return null;
		}
		int minIndex = 0;
		int runningIndex = 0;
		int minActionIndex = line.length()-1;
		//System.out.println(actions);
		for(String action: actions){
			String tempLine = isStem ? StemmerX.doStem(line) : line;
			String tempAction = isStem ? StemmerX.doStem(action) : action;
			int index = tempLine.indexOf(tempAction);
			if(minActionIndex > index && index != -1){
				minActionIndex = index;
				minIndex = runningIndex;
			}
			runningIndex++;
		}
		ist.setCookingAction(actions.get(minIndex));
		if(isWarning) System.out.println(" - Select " + ist.getCookingAction());
		isStem = true;
		//find ingredients
		ist.setIngredients(this.findIngredients(line, lineIndex, charIndex, isStem));
		
		//find cooking tools
		List<String> tools = this.findCookingTools(line, lineIndex, charIndex, isStem);
		ist.setCookingTools(tools);
		//find temperature
		//find duration
		
		return ist;
	}
	
	public List<String> findCookingTools(String line, int lineIndex, int charIndex, boolean isStem) {
		List<String> actions = new ArrayList<String>();
		for(Vocabulary voc : dictMgr.findMatches(line, isStem)){
			boolean isActionFound = false;
			for(VocabType vct : voc.getCategories()) {
				if(vct.getType().equalsIgnoreCase("tool")){
					isActionFound = true;
				}
			}
			if(isActionFound){
				actions.add(voc.getName());
			}
		}
		return actions;
	}
	
	public List<String> findCookingAction(String line, int lineIndex, int charIndex, boolean isStem) {
		List<String> actions = new ArrayList<String>();
		for(Vocabulary voc : dictMgr.findMatches(line, isStem)){
			boolean isActionFound = false;
			for(VocabType vct : voc.getCategories()) {
				if(vct.getType().equalsIgnoreCase("action")){
					isActionFound = true;
				}
			}
			if(isActionFound){
				actions.add(voc.getName());
			}
		}
		return actions;
	}
	
	public List<Ingredient> findIngredients(String line, int lineIndex, int charIndex, boolean isStem){
		List<Ingredient> igdsFound = new ArrayList<Ingredient>();
		//System.out.println(isStem);
		line = line.replaceAll("\\W", " ");
		if(isStem) line = StemmerX.doStem(line);
		//System.out.println(line);
		for(Ingredient i : this.igds){
			String iName = i.getName();
			if(isStem) iName = StemmerX.doStem(iName);
			String regex = "(^|\\s)";
			regex += iName;
			regex += "(\\s|$)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);
			if(matcher.find())
				igdsFound.add(i);
			
		}
		return igdsFound;
	}
	
	//cookingAction and ingredients are the same
	public Instruction linkPrepAction(Instruction ist, List<Instruction> prevs, InstructionSubstitution iss){
		List<Instruction> preps = iss.getPrepActionList(ist);
		if(preps == null) return null;
		for(Instruction prep : preps) {
			for(Instruction prev : prevs){
				if(!prep.getCookingAction().equalsIgnoreCase(prev.getCookingAction())){
					continue;
				}
				//all match
				boolean allMatch = true;
				//boolean oneMatch = false;
				for(Ingredient iPrep : prep.getIngredients()){
					for(Ingredient iPrev :prev.getIngredients()){
						if(!iPrep.getName().equalsIgnoreCase(iPrev.getName())){
							allMatch = false;
						}
						else {
							//oneMatch = true;
						}
					}
				}
				if(allMatch){
					ist.getPreparations().add(prev);
				}
			}
		}
		return ist;
	}
}
