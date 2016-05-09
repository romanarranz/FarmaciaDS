package util;

public class TextParser {
	public static String parseLatinToHTML(String text){
		String result = text;
		result = result.replace("Ã±", "&ntilde;");
		result = result.replace(">", "&gt;");
		result = result.replace("<", "&lt;");
		
		return result;
	}
}
