package recipeParser.ingrControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import recipeParser.dictionary.*;
import recipeParser.recipeModels.Ingredient;

public class IngredientParser {
	private final int AMOUNT_IDX = 0;
	private final int UNIT_IDX = 1;
	private final DictionaryManager dictMgr;
	public IngredientParser(DictionaryManager dict){
		this.dictMgr = dict;
	}
	
	public Ingredient parseIngredient(String line, int lineIndex){

		boolean isStem = true;
		Ingredient igd = new Ingredient(line, 0, lineIndex);
	
		//set measurement
		List<String[]> measurements = this.findMeasurements(line, lineIndex, isStem);
		if(measurements != null)  {
			String[] measurement = measurements.get(0); 
			igd.setAmount(measurement[this.AMOUNT_IDX]);
			igd.setUnit(measurement[this.UNIT_IDX]);
			for(String[] meas : measurements) {
				if(!meas[this.UNIT_IDX].equalsIgnoreCase("")){
					igd.setAmount(meas[this.AMOUNT_IDX]);
					igd.setUnit(meas[this.UNIT_IDX]);
				}
				line=line.replaceAll("\\s?" + meas[this.AMOUNT_IDX] + "\\s?"+meas[this.UNIT_IDX], "");
			}
		}
		//line = line.replaceAll("\\W", " ").trim();
		line = line.replaceAll(" +", " ");
		//line = line.replaceAll("^of", "").trim();
		
		if(isStem) line = StemmerX.doStem(line);
		
		//find prepAction
		igd.setPrepAction("");
		List<String> actions = this.findPrepAction(line, lineIndex, isStem);
		//remove action out of the line
		for(String action : actions) {
			String tempAction = action;
			if(isStem) action = StemmerX.doStem(action);
			if(line.indexOf(action)!=0){
				line = line.replaceAll(action, "");
				igd.setPrepAction(igd.getPrepAction()+", "+tempAction);
			}
		}
		if(!igd.getPrepAction().equalsIgnoreCase("")){
			if(igd.getPrepAction().charAt(0) == ','){
				igd.setPrepAction(igd.getPrepAction().substring(2));
			}
		}
		line = line.replaceAll(" +", " ");
		//System.out.println(igd.getPrepAction());
		
		//find ingredient
		List<Vocabulary> igdVocabs = this.findIngredient(line, lineIndex, isStem);
		//hopefully there's only one.
		if(igdVocabs.size() == 0) {
			System.out.println("Error: No ingredient found for: \"" + line + "\" at line "+lineIndex);
			System.out.println(" - Full Text: \"" + igd.getFullText() +"\"");
			return null;
		}
		else {
			igd.setCategories(igdVocabs.get(0).getCategories());
			igd.setName(igdVocabs.get(0).getName());
		}
		//System.out.println(igd.getName() + ": " + igd.getCategories());
		return igd;
	}
	
	public List<String[]> findMeasurements(String line, int lineIndex, boolean isStem) {
		List<String> amounts = findAmounts(line, lineIndex);
		if(amounts.size() <= 0) {
			return null;
		}
		
		List<String[]> measurements = new ArrayList<String[]>();
		//expect next word to be unit, otherwise ignore
		for(String amount : amounts){
			String[] meas = new String[2];
			amount = amount.trim();
			meas[this.AMOUNT_IDX] = amount;
			String unit = line.substring(line.indexOf(amount)+amount.length(),line.length());
			unit = unit.trim();
			if(unit.indexOf(' ') == -1)
				unit = unit.substring(0,unit.length());
			else
				unit = unit.substring(0,unit.indexOf(' '));
			//delete common symbol behind unit
			if(unit.charAt(unit.length()-1)==')'||unit.charAt(unit.length()-1)==',') {
				unit = unit.substring(0, unit.length() - 1);
			}
			//System.out.println(unit);
			//check if it is unit
			switch (dictMgr.isVocabInCategory(unit, "unit", isStem)) {
			case FOUND:
				meas[this.UNIT_IDX] = unit;
				break;
			case NOT_IN_CAT:
				meas[this.UNIT_IDX] = "";
				break;
			case NOT_FOUND:
				dictMgr.printErrorMessage(unit, lineIndex, "unit", "IGNORED");
				meas[this.UNIT_IDX] = "";
			}
			measurements.add(meas);
			//System.out.println(meas[0] + " " + meas[1]);
		}
		return measurements;
	}
	
	public List<String> findAmounts(String line,  int lineIndex) {
		Pattern pattern = Pattern.compile("([0-9]+(((\\s?-\\s?)|([\\./]))[0-9]+)?\\s?)+");
		Matcher matcher = pattern.matcher(line);

        List<String> listMatches = new ArrayList<String>();

        while(matcher.find()) {
            listMatches.add(matcher.group());
        }
        /*
        for(String s : listMatches) {
            System.out.println(s);
        }
        */
        return listMatches;
	}
	
	public List<String> findPrepAction(String line, int lineIndex, boolean isStem) {
		List<String> actions = new ArrayList<String>();
		for(Vocabulary voc : dictMgr.findMatches(line, isStem)){
			boolean isActionFound = false;
			for(VocabType vct : voc.getCategories()) {
				if(vct.getType().equalsIgnoreCase("action")){
					isActionFound = true;
				}
				else if(vct.getType().equalsIgnoreCase("ingredient")){
					//ingredient priority
					isActionFound = false;
					break;
				}
			}
			if(isActionFound){
				actions.add(voc.getName());
			}
		}
		
		return actions;
	}
	
	public List<Vocabulary> findIngredient(String line, int lineIndex, boolean isStem) {
		List<Vocabulary> igds = new ArrayList<Vocabulary>();
		for(Vocabulary voc : dictMgr.findMatches(line, isStem)){
			boolean isIgdFound = false;
			for(VocabType vct : voc.getCategories()) {
				if(vct.getType().equalsIgnoreCase("ingredient")){
					isIgdFound = true;
				}
			}
			if(isIgdFound){
				igds.add(voc);
			}
		}
		
		return igds;
	}
	
}
