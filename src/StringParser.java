import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringParser {
	List<String> response;

	// regexes
	// http://www.regxlib.com/DisplayPatterns.aspx?cattabindex=2&categoryId=3&AspxAutoDetectCookieSupport=1
	final static String IPregex = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

	final static String DecimalFloatregex = "[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?";
	final static String DecimalIntregex = "[+-]?([0-9]+)";

	final static String HexNumberLikeregex = "[+-]|[0-9]|[e]|[E]|[A-F]|[a-f]";

	final static String DecimalFloatNumberLikeregex = "[+-]|[0-9]|[e]|[E]";
	final static String DecimalIntNumberLikeregex = "[+-]|[0-9]|[e]|[E]";

	public StringParser(List<String> response) {
		this.response = response;
	}

	public List<String> gettable(List<String> tablesignatures,
			String endtablesignature) 
			{//retyrns strings 
		boolean istable = false;
		List<String> table = new ArrayList<String>();
		for (String line : response) {
			if (!istable) {
				if (hasallelements(line, tablesignatures))
					istable = true;
			}

			if (istable & line.contains(endtablesignature))
				return table;
			if (istable)
				table.add(line);
		}

		return table;
	}

	public static boolean hasallelements(String line, List<String> tablesignatures) {
		for (String s : tablesignatures) {
			if (!line.contains(s))
				return false;
		}
		return true;
	}
	
	public static boolean hasoneofelements(String line, List<String> tablesignatures) {
		for (String s : tablesignatures) {
			if (line.contains(s))
				return true;
		}
		return false;
	}
	
	
	

	public static String removetabs(String s) {
		s.replaceAll("\t", " ");
		return s;
	}

	public static List<String> split(String s) {
		String[] el = s.split(" ");
		List<String> elements = new ArrayList<String>();
		for (String st : el) {
			if (st.isEmpty() | st.contains(" "))
				continue;
			elements.add(st);
		}
		return elements;
	}




	
	
}
