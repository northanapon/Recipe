package recipeParser;

import java.io.IOException;
/*
import java.util.ArrayList;
import java.util.List;


import recipeParser.dictionary.DictionaryManager;
import recipeParser.ingrControllers.IngredientParser;
import recipeParser.ingrControllers.IngredientSubstitution;
import recipeParser.ingrControllers.SubstitutionType;
import recipeParser.instControllers.InstructionParser;
import recipeParser.instControllers.InstructionSubstitution;
import recipeParser.recipeModels.Ingredient;
import recipeParser.recipeModels.Instruction;
*/

public class TestRun {
	public static void main(String argvs[]) throws IOException{
		//DictionaryManager dm = new DictionaryManager();
		//dm.buildDictionary("data");
		
		/*
		IngredientSubstitution is = new IngredientSubstitution();
		System.out.println(is.getSubstitution(SubstitutionType.CHINESE_TO_INDIAN, "chinese"));
		//expect indian
		System.out.println(is.getSubstitution(SubstitutionType.INDIAN_TO_CHINESE, "indian"));
		//expect chinese
		*/ 
		/*
		DictionaryManager dm = new DictionaryManager();
		dm.buildDictionary("data");
		//System.out.println(dm.findMatches("low fat minced beef", true));
		
		IngredientParser igp = new IngredientParser(dm);
		//System.out.println(igp.parseIngredient("2 tbsp. dark soy sauce", 0));
		//System.out.println(igp.parseIngredient("8 1/2 1 - 2 oz (225g) of low fat minced beef, mince", 0));
		//System.out.println(igp.parseIngredient("low fat beef: 2 oz, minced and cut", 0).toString(true));
		Ingredient i = igp.parseIngredient("low fat pork: 2 oz, minced and cut", 0);
		Ingredient i2 = igp.parseIngredient("1 beef", 0);
		List<Ingredient> i12 = new ArrayList<Ingredient>();
		i12.add(i);
		i12.add(i2);
		
		Instruction is = new Instruction("",0,0);
		is.setCookingAction("stir-fry");
		is.getIngredients().add(i);
		InstructionSubstitution ist = new InstructionSubstitution(dm);
		//System.out.print(ist.getPrepActionList(is));
		
		InstructionParser p = new InstructionParser(dm, i12);
		
		Instruction ist1 = p.parseInstuction("chop beef", 0, 0);
		List<Instruction> istList = new ArrayList<Instruction>();
		istList.add(ist1);
		Instruction ist2 = p.parseInstuction("stir-fry beef and pork on the pan", 0, 0);
		System.out.println(ist2);
		System.out.println(p.linkPrepAction(ist2, istList, ist).getPreparations());
		*/
		
		
		
		RecipeParser parser = new RecipeParser();
		System.out.println(parser.parseRecipeFromFile("recipes/Hot and Sour Soup.txt").describe());
	}
}
