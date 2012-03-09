package recipeParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import recipeParser.dictionary.DictionaryManager;
import recipeParser.dictionary.StemmerX;
import recipeParser.ingrControllers.IngredientSubstitution;
import recipeParser.ingrControllers.SubstitutionType;
import recipeParser.instControllers.InstructionSubstitution;
import recipeParser.recipeModels.Ingredient;
import recipeParser.recipeModels.Instruction;
import recipeParser.recipeModels.Recipe;
import recipeParser.view.RecipeGUI;

public class RunParserGUI {
	
	final private RecipeGUI theGUI;
	private Recipe currentRecipe;
	private IngredientSubstitution igdSub;
	private InstructionSubstitution istSub;
	public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("hi");
        new RunParserGUI();
    }
	
	
	public RunParserGUI() throws IOException {
        this.igdSub = new IngredientSubstitution();
        DictionaryManager dm = new DictionaryManager();
		dm.buildDictionary("data");
        this.istSub = new InstructionSubstitution(dm);
        istSub.readSubstitutions();
        theGUI = new RecipeGUI();
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        theGUI.getButtonParse().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					parseRecipe(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });
        
        theGUI.getBtnReplace().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
          		replaceIngredient(e);
        	}
        });
        
        theGUI.getBtnChange().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		changeCuisine(e);
        	}
        });
        
        theGUI.getBtnSubstitute().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		subStituteAction(e);
        	}
        });
        
        
        for(String str : IngredientSubstitution.getTypeList()){
        	theGUI.getDdlCuisine().addItem(str);
        }
        theGUI.pack();
        theGUI.setVisible(true);
    }
	
	private void subStituteAction(ActionEvent e) {
		int i = theGUI.getDdlActionFrom().getSelectedIndex();
		Instruction ist = this.currentRecipe.getInstructions().get(i);
		String temp = ist.getCookingAction();
		ist.setCookingAction((String)theGUI.getDdlActionTo().getSelectedItem());
		List<Instruction> preNews = this.istSub.getPrepActionList(ist);
		System.out.println(preNews);
		//remove old
		for(Instruction preI : ist.getPreparations()){
			this.currentRecipe.getInstructions().remove(preI);
		}
		//put new one
		for(Instruction preI : preNews){
			this.currentRecipe.getInstructions().add(i,preI);
		}
		this.initDdlActionTo();
	}
	
	private void changeCuisine(ActionEvent e) {
		
		String cuisineStr = (String)theGUI.getDdlCuisine().getSelectedItem();
		SubstitutionType type = IngredientSubstitution.getTypeFromString(cuisineStr);
		this.igdSub.readSubstitutions(type);
		for(Ingredient i : this.currentRecipe.getIngredients()){
			String subStr = this.igdSub.getSubstitution(type, i.getName());
			System.out.println(cuisineStr + ", " + i.getName() + ", " + subStr);
			if(subStr != null) {
				i.setName(subStr);
			}
		}
		theGUI.getOutputText().setText(this.currentRecipe.describe());
	}
	
	private void replaceIngredient(ActionEvent e) {
		String from = theGUI.getTxtIngredientFrom().getText();
		String to = theGUI.getTxtIngredientTo().getText();
		for(Ingredient i : this.currentRecipe.getIngredients()){
			//System.out.println(i.getName() + ", " + from);
			if(StemmerX.doStem(i.getName()).equalsIgnoreCase(StemmerX.doStem(from))){
				i.setName(to);
				theGUI.getTxtIngredientFrom().setText("");
				theGUI.getTxtIngredientTo().setText("");
				theGUI.getOutputText().setText(this.currentRecipe.describe());
				return;
			}
		}
		theGUI.getOutputText().setText(this.currentRecipe.describe() + "\n\nOops. \nCannot find your the ingredient in the recipe! \n");
	}
    private void parseRecipe(ActionEvent e) throws IOException {
        System.out.println("Parsing Recipe..");
        RecipeParser parser = new RecipeParser();
        this.currentRecipe = parser.parseRecipeFromForm(//theGUI.getRecipeNameInput().getText(),
                theGUI.getRecipeMetaInput().getText(),
                theGUI.getRecipeIngInput().getText(),
                theGUI.getRecipeDirInput().getText());

        theGUI.getOutputText().setText(this.currentRecipe.describe());
        for(String str : this.istSub.getAllInstructionsInRules()){
        	//System.out.println(str);
        	theGUI.getDdlActionTo().addItem(str);
        }
        this.initDdlActionTo();
    }
    private void initDdlActionTo(){
    	int i = 1;
        for(Instruction ist : this.currentRecipe.getInstructions()){
        	theGUI.getDdlActionFrom().addItem(i+". "+ist.describeShort());
        	i++;
        }
    }
}
