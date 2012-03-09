package recipeParser.view;

import java.util.ArrayList;
import java.util.List;

public class RecipeLine {
    private final List<String> theTags = new ArrayList<String>();
    private final String theLine;

    public RecipeLine(String aLine) {
        theLine = aLine;
    }

    public List<String> getTags() {
        return theTags;
    }

    public String getLine() {
        return theLine;
    }

    public void tag(String aTag){
        theTags.add(aTag);
    }

    @Override
    public String toString() {
        StringBuilder mySB = new StringBuilder(theLine);

        for (String myTag : theTags){
            mySB.append("\n" + myTag);
        }

        return mySB.toString();
    }
}
