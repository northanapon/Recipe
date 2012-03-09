package recipeParser.instControllers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import recipeParser.dictionary.DictionaryManager;
import recipeParser.ingrControllers.IngredientParser;
import recipeParser.recipeModels.Ingredient;
import recipeParser.recipeModels.Instruction;

public class InstructionSubstitution {
	private HashMap<String, String> substituions; 
	private final String fileDirectory = "transformationRules/instructions/";
	private final String delimeter = ": ";
	private final String delimeterInner = ", ";
	private static final int MAX = 300;
	private final DictionaryManager dictMgr;
	
	
	public InstructionSubstitution (DictionaryManager dm){
		substituions = new HashMap<String, String>(MAX);
		this.dictMgr = dm;
	}
	
	public void readSubstitutions(){
		try{
			this.substituions.clear();
			String filePath = fileDirectory + "prepAction.txt";
			
			FileInputStream fstream = new FileInputStream(filePath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				if(strLine == "") continue;
				int delimeterIndex = strLine.indexOf(delimeter);
				if(delimeterIndex == -1) continue;
				strLine = strLine.toLowerCase();
				String inst = strLine.substring(0, delimeterIndex);
				inst = inst.substring(inst.indexOf('[') + 1, inst.indexOf(']'));
				//System.out.println("add " + inst);
				
				this.substituions.put(inst, strLine);
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public List<String> getAllInstructionsInRules(){
		ArrayList<String> list = new ArrayList<String>();
		for(String key : this.substituions.keySet()){
			list.add(key);
		}
		return list;
	}
	
	public List<String> getPrepActionStrings(String action, List<String> ingredients) {
		if(this.substituions.size() == 0) {
			this.readSubstitutions();
		}
		
		String sub = this.substituions.get(action);
		if(sub == null) return null;
		int i = 1;
		while(sub.indexOf("@x"+i)>0) {
			if(ingredients.size() <= i)
				sub = sub.replaceAll("@x"+i, ingredients.get(i-1));
			else {
				sub = sub.replaceAll("@x"+i, "");
				System.out.println("Warning: insufficient ingredient for this action " + action);
			}
			i++;
		}
		sub = sub.replaceAll("<>", "");
		System.out.println(sub);
		int delimeterIndex = sub.indexOf(delimeter);
		String prep = sub.substring(delimeterIndex + 2, sub.length());
		prep = prep.replaceAll("[<>\\[\\]]", "");
		String[] preps = prep.split(this.delimeterInner);
		List<String> prepList = new ArrayList<String>();
		for(String p : preps) {
			prepList.add(p);
		}
		return prepList;
	}
	
	public List<Instruction> getPrepActionList(Instruction instruction) {
		if(this.substituions.size() == 0) {
			this.readSubstitutions();
		}
		List<Instruction> prepActions = new ArrayList<Instruction>();
		IngredientParser igdParser = new IngredientParser(dictMgr);
		String action = instruction.getCookingAction();
		String sub = this.substituions.get(action);
		List<Ingredient> ingredients = instruction.getIngredients();
		
		if(sub == null) {
			//System.out.println("ERROR: cannot find action: \""+ action +"\"");
			return null;
		}
		int i = 1;
		while(sub.indexOf("@x"+i)>0) {
			//System.out.println(ingredients.size() + ", " + i);
			if(ingredients.size() >= i){
				sub = sub.replaceAll("@x"+i, ingredients.get(i-1).getName());
			}
			else {
				sub = sub.replaceAll("@x"+i, "");
				System.out.println("Warning: insufficient ingredient for this action " + action);
			}
			i++;
		}
		//only one -> try to make more prep from diff ingredients
		
		if(i==2 && ingredients.size()>1){
			Ingredient temp = ingredients.remove(0);
			List<Instruction> prepActionsTemp = this.getPrepActionList(instruction);
			for(Instruction istTemp : prepActionsTemp){
				prepActions.add(istTemp);
			}
			ingredients.add(0, temp);
		}
		
		String pre = sub.substring(sub.indexOf(':')+1);
		//split actions
		for(String actLine : pre.split(", ")){
			actLine = actLine.trim();
			//System.out.println(actLine);
			String fullText = actLine.replaceAll("[<>\\[\\]]", "");
			//System.out.println(fullText);
			Instruction ist = new Instruction(fullText, -1, -1);
			//get the action
			Pattern pattern = Pattern.compile("\\[.*\\]");
			Matcher matcher = pattern.matcher(actLine);
	        matcher.find();
	        String actWord = matcher.group();
	        actLine = actLine.replaceAll(actWord, "");
	        actWord = actWord.replaceAll("[\\[\\]]", "");
			ist.setCookingAction(actWord);
			
			//get the ingredient(s)
			pattern = Pattern.compile("<.*>");
			matcher = pattern.matcher(actLine);
			while(matcher.find()) {
	            String igdName = matcher.group();
	            igdName = igdName.replaceAll("[<>]", "");
	            boolean isExist = false;
	            for(Ingredient igd : ingredients){
	            	if(igd.getName().equalsIgnoreCase(igdName)){
	            		ist.getIngredients().add(igd);
	            		isExist = true;
	            	}
	            }
	            if(!isExist) {
	            	Ingredient newIgd = igdParser.parseIngredient(fullText.replaceAll(actWord, "").trim(), -1);
	            	ist.getIngredients().add(newIgd);
	            }
	        }
			//System.out.println(ist);
			prepActions.add(ist);
		}
		
		return prepActions;
	}
	
	
	public List<String> getPrepActionString(String action) {
		return this.getPrepActionStrings(action, null);
	}
	
	public List<String> getPrepActionString(String action, String ingredient) {
		List<String> a = new ArrayList<String>();
		a.add(ingredient);
		return this.getPrepActionStrings(action, a);
	}
}
