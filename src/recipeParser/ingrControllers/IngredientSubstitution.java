package recipeParser.ingrControllers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import recipeParser.dictionary.StemmerX;

public class IngredientSubstitution {
	
	private HashMap<String, String> substituions; 
	private final String fileDirectory = "transformationRules/ingredients/";
	private final String delimeter = ", ";
	private static final int MAX = 300;
	private SubstitutionType currentType;
	
	
	public IngredientSubstitution (){
		substituions = new HashMap<String, String>(MAX);
		currentType = null;
	}
	
	public  String getSubFileName(SubstitutionType type) {
		String str = "";
		switch (type){
		case CHINESE_TO_INDIAN:
		case INDIAN_TO_CHINESE:
			str = "ChineseToIndian.txt";
			break;
		case CHINESE_TO_ITALIAN:
		case ITALIAN_TO_CHINESE:
			str = "ChineseToItalian.txt";
			break;
		case EASTERNEUROPEAN_TO_CHINESE:
		case CHINESE_TO_EASTERNEUROPEAN:
			str = "EasternEuropeanToChinese.txt";
			break;
		case EASTERNEUROPEAN_TO_ITALIAN:
		case ITALIAN_TO_EASTERNEUROPEAN:
			str = "EasternEuropeanToIndian.txt";
			break;
		case EASTERNEUROPEAN_TO_INDIAN:
		case INDIAN_TO_EASTERNEUROPEAN:
			str = "EasternEuropeanToItalian.txt";
			break;
		case INDIAN_TO_ITALIAN:
		case ITALIAN_TO_INDIAN:
			str = "IndianToItalian.txt";
			break;
		default:
			str = "default_substitution.txt";
		}
		return str;
	}
	
	public boolean isSubstitutionNeededSwap(SubstitutionType type) {
		boolean need = false;
		switch (type){
		case CHINESE_TO_INDIAN:
		case CHINESE_TO_ITALIAN:
		case EASTERNEUROPEAN_TO_CHINESE:
		case EASTERNEUROPEAN_TO_ITALIAN:
		case EASTERNEUROPEAN_TO_INDIAN:
		case INDIAN_TO_ITALIAN:
			need = false;
			break;
		case INDIAN_TO_CHINESE:
		case ITALIAN_TO_CHINESE:
		case CHINESE_TO_EASTERNEUROPEAN:
		case ITALIAN_TO_EASTERNEUROPEAN:
		case INDIAN_TO_EASTERNEUROPEAN:
		case ITALIAN_TO_INDIAN:
			need = true;
			break;
		default:
			need = false;
		}
		return need;
	}
	
	public static ArrayList<String> getTypeList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Chinese To Indian");
		list.add("Indian To Chinese");
		list.add("Chinese To Italian");
		list.add("Italian To Chinese");
		list.add("Eastern European To Chinese");
		list.add("Chinese To Eastern European");
		list.add("Eastern European To Indian");
		list.add("Indian To Eastern European");
		list.add("Eastern European To Italian");
		list.add("Italian To Eastern European");
		list.add("Indian To Italian");
		list.add("Italian To Indian");
		return list;
	}
	
	public static SubstitutionType getTypeFromString(String type){
		if(type.equalsIgnoreCase("Chinese To Indian")){
			return SubstitutionType.CHINESE_TO_INDIAN;
		}
		if(type.equalsIgnoreCase("Indian To Chinese")){
			return SubstitutionType.INDIAN_TO_CHINESE;
		}
		if(type.equalsIgnoreCase("Chinese To Italian")){
			return SubstitutionType.CHINESE_TO_ITALIAN;
		}
		if(type.equalsIgnoreCase("Italian To Chinese")){
			return SubstitutionType.ITALIAN_TO_CHINESE;
		}
		if(type.equalsIgnoreCase("Eastern European To Chinese")){
			return SubstitutionType.EASTERNEUROPEAN_TO_CHINESE;
		}
		if(type.equalsIgnoreCase("Chinese To Eastern European")){
			return SubstitutionType.CHINESE_TO_EASTERNEUROPEAN;
		}
		if(type.equalsIgnoreCase("Eastern European To Indian")){
			return SubstitutionType.EASTERNEUROPEAN_TO_INDIAN;
		}
		if(type.equalsIgnoreCase("Indian To Eastern European")){
			return SubstitutionType.INDIAN_TO_EASTERNEUROPEAN;
		}
		if(type.equalsIgnoreCase("Eastern European To Italian")){
			return SubstitutionType.EASTERNEUROPEAN_TO_ITALIAN;
		}
		if(type.equalsIgnoreCase("Italian To Eastern European")){
			return SubstitutionType.ITALIAN_TO_EASTERNEUROPEAN;
		}
		if(type.equalsIgnoreCase("Indian To Italian")){
			return SubstitutionType.INDIAN_TO_ITALIAN;
		}
		if(type.equalsIgnoreCase("Italian To Indian")){
			return SubstitutionType.ITALIAN_TO_INDIAN;
		}
		return null;
	}
	
	public void readSubstitutions(SubstitutionType type){
		try{
			this.substituions.clear();
			String filePath = fileDirectory + this.getSubFileName(type);
			
			FileInputStream fstream = new FileInputStream(filePath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				if(strLine == "") continue;
				int delimeterIndex = strLine.indexOf(delimeter);
				if(delimeterIndex == -1) continue;
				strLine = strLine.toLowerCase();
				String first = strLine.substring(0, delimeterIndex);
				String second = strLine.substring(delimeterIndex + 2, strLine.length());
				if (this.isSubstitutionNeededSwap(type)){
					//ex us, chi
					//input chi
					//output us
					substituions.put(StemmerX.doStem(second), first);
				}
				else {
					substituions.put(StemmerX.doStem(first), second);
				}
			}
		  
			in.close();
			currentType = type;
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public String getSubstitution(SubstitutionType type, String ingredient) {
		String sub = "";
		if(currentType != type) {
			this.readSubstitutions(type);
		}
		sub = this.substituions.get(StemmerX.doStem(ingredient));
		return sub;
	}

	public HashMap<String, String> getSubstituions() {
		return substituions;
	}

	public void setSubstituions(HashMap<String, String> substituions) {
		this.substituions = substituions;
	}
	
}
