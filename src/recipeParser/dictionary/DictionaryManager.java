package recipeParser.dictionary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DictionaryManager {
	
	private final int INIT_NUM_VOCAB = 800;
	private HashMap<Integer, Vocabulary> dict;
	
	public DictionaryManager() {}
	
	public HashMap<Integer, Vocabulary> buildDictionary(String directory) throws IOException {
		this.dict = new HashMap<Integer, Vocabulary>(INIT_NUM_VOCAB);
		
		File dataDir = new File(directory);
		String[] dataDirStrs = dataDir.list();
		if (dataDirStrs == null) {
            System.out.println("Not a directory");
            return null;
		}
        
		//for each category type: action, ingredient, tool, unit
		for (String dirName : dataDirStrs){
            File subDir = new File(directory + "/" + dirName);
            
            if (!subDir.isDirectory()){
            	continue;
            } 
            
            String categoryType = dirName.toLowerCase();
            //Read files
            String[] fileNames = subDir.list();
            for (String fileName : fileNames){
            	
            	String category = fileName.substring(0, fileName.indexOf(".")).toLowerCase();
            	if(category.length()<=0) continue;
                System.out.println("Read " + category +" in " + categoryType);
                
                VocabType type = new VocabType(categoryType, category);
                
                FileInputStream inFile = new FileInputStream(directory + "/" + dirName + "/" + fileName);
		        DataInputStream inStream = new DataInputStream(inFile);
		        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF8"));

                String line = null;
                while ((line = reader.readLine()) != null){
                	if(line.charAt(0) == '#') continue;
                    line = line.toLowerCase().replaceAll(",","").trim();
                    //line = StemmerX.doStem(line);
                    if (dict.containsKey(Vocabulary.getHashCode(line))) {
                    	//System.out.println("add category "+ type + " to " + line);
                    	dict.get(Vocabulary.getHashCode(line)).getCategories().add(type);
                    }
                    else {
                    	//System.out.println("add new vocab "+ line);
                    	Vocabulary vocab = new Vocabulary(line, type);
                    	dict.put(vocab.hashCode(), vocab);
                    }
                }
                reader.close();
                inStream.close();
                inFile.close();
            }
        }
		System.out.println("total vocabularies read: " + dict.size());
		return dict;
	}
	
	public VocabFindingResult isVocabInCategory(String vocab, String cType, boolean isStem) {
		ArrayList<Vocabulary> vocabs = this.findMatches(vocab, isStem);
		if(vocabs == null) {
			return VocabFindingResult.NOT_FOUND;
		}
		if(vocabs.size() == 0){
			return VocabFindingResult.NOT_FOUND;
		}
		for(Vocabulary v : vocabs){
			for(VocabType type : v.getCategories()) {
				if(type.getCategory().equalsIgnoreCase(cType))
					return VocabFindingResult.FOUND;
			}
		}
		return VocabFindingResult.NOT_IN_CAT;
	}
	
	public VocabFindingResult isVocabInCategoryType(String vocab, String cType, boolean isStem) {
		ArrayList<Vocabulary> vocabs = this.findMatches(vocab, isStem);
		if(vocabs == null) {
			return VocabFindingResult.NOT_FOUND;
		}
		if(vocabs.size() == 0){
			return VocabFindingResult.NOT_FOUND;
		}
		for(Vocabulary v : vocabs){
			for(VocabType type : v.getCategories()) {
				if(type.getType().equalsIgnoreCase(cType))
					return VocabFindingResult.FOUND;
			}
		}
		return VocabFindingResult.NOT_IN_CAT;
	}
	
	public ArrayList<Vocabulary> findMatches(String line, boolean isStem) {
		ArrayList<Vocabulary> vocabs = new ArrayList<Vocabulary>();
		line = line.replaceAll("\\W", " ");
		if(isStem) {
			line = StemmerX.doStem(line);
		}
		for(Vocabulary vocab : this.dict.values()){
			String name = vocab.getName();
			if(isStem) {
				name = StemmerX.doStem(vocab.getName());
			}
			Pattern pattern = Pattern.compile("(^|\\s)"+name+"(\\s|$|\\W)");
			Matcher matcher = pattern.matcher(line);
			//if(line.indexOf(name) != -1){
			if(matcher.find()){
				vocabs.add(vocab);
			}
		}
		Collections.sort(vocabs, Collections.reverseOrder(new VocabularySpaceComparator()));
		return vocabs;
	}
	
	public void printErrorMessage(String vocab, int lineIndex, String expectedType, String action){
		System.out.println("Warning: Unrecoginzed vocab \"" + vocab 
				+ "\" at line " + lineIndex 
				+ ". Expected: " + expectedType + " - " + action.toUpperCase() );
	}
}
