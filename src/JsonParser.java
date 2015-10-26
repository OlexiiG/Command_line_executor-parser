import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {

	JSONObject configuration = null;// current configuration depending current
									// OS

	
	public JsonParser(String configfilename, String os_version) {
		// constructor reads config file, getting configuration depending current OS
		// as JSON object
		try {

			FileReader reader = new FileReader(configfilename);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			configuration = (JSONObject) jsonObject.get(os_version);

			reader.close();
		}

		catch (ParseException e) {//error parsing
			System.out.println("Error parsing JSON config file-probaly, invalid file format. check "+configfilename+" on http://jsonviewer.stack.hu/"
					+ configfilename);
			//e.printStackTrace();
		}

		catch (IOException e) {//error reading config file
			System.out.println("Error reading config file " + configfilename);
			//e.printStackTrace();
		}
	}

	public List<String> getExecuteParameters() {
		//getting parameters 4 start application command -param1 -param2 etc...
		String command = (String) configuration.get("command");
		List<String> command_params = new ArrayList<String>();
		command_params.add(command);
		command_params.addAll(getCommandParams());
		return command_params;
	}

	public List<String> getCommandParams() {
		//returns array of parameters as List <String>
		JSONArray param = (JSONArray) configuration.get("params");
		return JSONArrayToStringList(param);
	}

	public static List<String> JSONArrayToStringList(JSONArray jsonArray) {
		//transforms JSON Array to List <String>
		List<String> list = new ArrayList<String>();
		for (Object o : jsonArray) {
			list.add(o.toString());
		}
		return list;
	}

	public String getConsoleCharset() {
		String charset = (String) configuration.get("charset");
		return charset;
	}

}
