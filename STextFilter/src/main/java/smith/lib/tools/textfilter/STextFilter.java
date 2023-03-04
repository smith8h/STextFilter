package smith.lib.tools.textfilter;

import android.app.Activity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class STextFilter {
    
    private Activity activity;
    private int start = -1;
    private int end = -1;
    private List<String> bannedWords = new ArrayList<>();
    
    public STextFilter(Activity activity) {
        this.activity = activity;
        addDataFromRawResource(R.raw.arbanned);
        addDataFromRawResource(R.raw.enbanned);
    }
    
    // filter banned words
    public String censorBannedWords(String text) {
        String pattern = "[،-٩؟چه‍ڢڤڥڨگکإأٱآءلإلالألآپئىڜژؤـa-zA-Z0-9àáâäæãåāç₱£$¢€íîïīìñóôöòœøōõßúùüūû.@¥&:_-]*";
        Matcher m = Pattern.compile(pattern).matcher(text);
        for (int i = 0; i < 2; i++) while (m.find()) {
            String word = text.substring(m.start(), m.end());
            for (String bannedWord: bannedWords) {
                if (word.contains(bannedWord)) text = text.replaceAll(bannedWord, censorWord(bannedWord));
            }
        }
        return text;
    }
    
    // remove by regex
    public static String removeWithRegEx(String pattern, String text) {
        Matcher m = Pattern.compile(pattern).matcher(text);
        while (m.find()) {
            String find = text.substring(m.start(), m.end());
            text = text.replaceAll(find, "");
        }
        return text;
    }
    
    // is it email
    public static boolean isEmail(String text) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_.]*@[a-zA-Z]*\\.[a-z]*");
        Matcher m = p.matcher(text);
        if (m.matches()) return true;
        else return false;
    }
    
    // contains email within text
    public boolean containsEmail(String text) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_.]*@[a-zA-Z]*\\.[a-z]*");
        Matcher m = p.matcher(text);
        if (m.find()) {
            start = m.start();
            end = m.end();
            return true;
        } else return false;
    }
    
    // check availability of some texts
    public boolean contains(String toBeFound, String targetText) {
        Matcher m = Pattern.compile(toBeFound).matcher(targetText);
        if (m.find()) {
            start = m.start();
            end = m.end();
            return true;
        } else return false;
    }
    
    public int start() {
        return start;
    }
    
    public int end() {
        return end;
    }
	
	// keep words only
	public static String keepLetters(String text) {
		StringBuffer finalText = new StringBuffer();
		for (char chars : text.toCharArray()) {
			if (Character.isLetter(chars)) finalText.append(chars);
		}
		return finalText.toString();
	}
	
	// keep Words with spaces
	public static String keepSentences(String text) {
		StringBuffer finalText = new StringBuffer();
		for (char chars : text.toCharArray()) {
			if (Character.isLetter(chars) || Character.isSpaceChar(chars)) finalText.append(chars);
		}
		return finalText.toString();
	}
	
	// keep numbers only
	public static String keepNumbers(String text) {
		StringBuffer finalText = new StringBuffer();
		for (char chars : text.toCharArray()) {
			if (Character.isDigit(chars)) finalText.append(chars);
		}
		return finalText.toString();
	}
	
	// keep symbols only
	public static String keepSymbols(String text) {
		StringBuffer finalText = new StringBuffer();
		for (char chars : text.toCharArray()) {
			if (!Character.isLetter(chars) && !Character.isSpaceChar(chars) && !Character.isDigit(chars)) 
                finalText.append(chars);
		}
		return finalText.toString();
	}
	
	// remove spaces 
	public static String removeSpaces(String text) {
		String finalText = text.replaceAll(" ", "");
		return finalText;
	}
    
    // add from raw resources
    public void addDataFromRawResource(int rawResourceId) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(activity.getResources().openRawResource(rawResourceId)))) {
            String line;
            while ((line = reader.readLine()) != null) bannedWords.add(line);
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    // add from assets
    public void addDataFromAssets(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(activity.getAssets().open(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) bannedWords.add(line);
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    private String censorWord(String word) {
        StringBuffer censored = new StringBuffer();
        for (char letter : word.toCharArray()) censored.append("*");
        return censored.toString();
    }
}
