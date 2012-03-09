package recipeParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import recipeParser.dictionary.DictionaryManager;
import recipeParser.ingrControllers.IngredientParser;
import recipeParser.instControllers.InstructionParser;
import recipeParser.instControllers.InstructionSubstitution;
import recipeParser.recipeModels.Ingredient;
import recipeParser.recipeModels.Instruction;
import recipeParser.recipeModels.Recipe;

public class RecipeParser {
	public Recipe parseRecipe(String address, RecipeResourceType type ) throws IOException {
		switch(type){
		case URL:
			return this.parseRecipeFromURL(address);
		default:
			return this.parseRecipeFromFile(address);
		}
	}
	
	public Recipe parseRecipeFromForm(String metadata, String ingredients, String instructions) throws IOException {
		//write to file
		String fileName = "recipes/"+ (new Date()).getTime() +".txt";
		FileWriter fstream = new FileWriter(fileName);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("MetaData:\n");
		out.write(metadata + "\n");
		out.write("Ingredients:\n");
		out.write(ingredients + "\n");
		out.write("Instructions:\n");
		out.write(instructions);
		//Close the output stream
		out.close();
		
		//parse
		Recipe r = this.parseRecipeFromFile(fileName);
		
		//delete
		(new File(fileName)).delete();
		
		//return
		return r;
	}
	
	public Recipe parseRecipeFromFile(String filePath) throws IOException {
		Recipe recipe = new Recipe();
		
		FileInputStream inFile = new FileInputStream(filePath);
        DataInputStream inStream = new DataInputStream(inFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF8"));
        
        DictionaryManager dm = new DictionaryManager();
		dm.buildDictionary("data");
		
        IngredientParser igdParser = new IngredientParser(dm);
        List<Ingredient> igds = new ArrayList<Ingredient>();
        
        InstructionParser istParser = new InstructionParser(dm, igds);
        InstructionSubstitution istSubst = new InstructionSubstitution(dm);
        List<Instruction> ists = new ArrayList<Instruction>();
        
        String line = null;
        int lineIndex = 0;
        int charIndex = 0;
        String sentence = "";
        RecipePartType state = RecipePartType.PREDATA;
        
        while ((line = reader.readLine()) != null){
        	recipe.setFullText(recipe.getFullText() + "\n" + line);
        	line=line.toLowerCase();
        	lineIndex++;
        	if(line.length() == 0) {
        		continue;
        	}
        	if(state == RecipePartType.PREDATA && line.equalsIgnoreCase("MetaData:")) {
        		state = RecipePartType.METADATA;
        		System.out.println("\nStart Parsing Metadata...");
        		continue;
        	}
        	if(state == RecipePartType.METADATA && line.equalsIgnoreCase("Ingredients:")) {
        		state = RecipePartType.INGREDIENTS;
        		System.out.println("\nStart Parsing Ingredients...");
        		continue;
        	}
        	if(state == RecipePartType.INGREDIENTS && line.equalsIgnoreCase("Instructions:")) {
        		state = RecipePartType.INSTRUCTIONS;
        		System.out.println("\nStart Parsing Instructions...");
        		continue;
        	}
        	
        	switch(state){
        	case METADATA:
        		break;
        	case INGREDIENTS:
        		Ingredient igd = igdParser.parseIngredient(line, lineIndex);
        		if(igd != null){
        			igds.add(igd);
        		}
        		break;
        	case INSTRUCTIONS:
        		//sentence by sentence (period = '.' followed a space or new line)
        		int periodIndex = -1;
        		int charIndexInst = -1;
        		while((periodIndex = line.indexOf('.', charIndexInst))!=-1){
        			if(periodIndex < line.length()-1){//not the end
            			if(line.charAt(periodIndex+1)!=' '&&//not a period
            					line.charAt(periodIndex+1)!='\t'){
            				charIndexInst = periodIndex+1;
            				continue;
            			}
            		}
        			//got a sentence
        			sentence += line.substring(charIndex, periodIndex+1);
        			charIndexInst = periodIndex+1;
        			//System.out.println(sentence);
        			//parse
        			Instruction ist = istParser.parseInstuction(sentence, lineIndex, charIndexInst);
        			if(ist != null) {
        				istParser.linkPrepAction(ist, ists, istSubst);
        				ists.add(ist);
        			}
        			//start new one
        			charIndex = charIndexInst;
        			sentence = "";
        		}
        		if(charIndex < line.length()-1){
        			sentence = line.substring(charIndex);
        		}
        		charIndex=0;
        		break;
        	default:
        		continue;
        	}
        	
        }
        recipe.setIngredients(igds);
        recipe.setInstructions(ists);
        //igds.get(5).setName("NOR");
        reader.close();
        inStream.close();
        inFile.close();
		return recipe;
	}
	
	public Recipe parseRecipeFromURL(String url) {
		return null;
	}
}
