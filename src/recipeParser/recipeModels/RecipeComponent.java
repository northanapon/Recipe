package recipeParser.recipeModels;

public class RecipeComponent {
	private String fullText;
	private int lineIndex;
	private int charIndex;
	
	public RecipeComponent(String fullText, int lineIndex, int charIndex){
		this.setFullText(fullText);
		this.setLineIndex(lineIndex);
		this.setCharIndex(charIndex);
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public int getLineIndex() {
		return lineIndex;
	}

	public void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;
	}

	public int getCharIndex() {
		return charIndex;
	}

	public void setCharIndex(int charIndex) {
		this.charIndex = charIndex;
	}
	
	public String describeComponent(){
		return "\'" +this.fullText+ "\' " + "Line: " + this.lineIndex + " Char: "+ this.charIndex;
	}
	
	@Override
	public String toString() {
		return this.describeComponent();
	}
}
