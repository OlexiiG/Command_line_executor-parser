import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {

	JSONObject configuration = null;

	public JsonParser(String configfilename, String os_version) {
		FileReader reader;
		try {

			reader = new FileReader(configfilename);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			configuration = (JSONObject) jsonObject.get(os_version);

			reader.close();

		} catch (IOException | ParseException e) {

			System.out.println("Error reading config file " + configfilename);
			e.printStackTrace();
		}
	}

	public List<String> getCommandString() {
		String command = (String) configuration.get("command");
		List<String> command_params = new ArrayList<String>();
		command_params.add(command);
		command_params.addAll(getCommandParams());
		return command_params;
	}

	public List<String> getCommandParams() {
		JSONArray param = (JSONArray) configuration.get("params");
		return JSONArrayToStringList(param);
	}

	public static List<String> JSONArrayToStringList(JSONArray jsonArray) {
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
