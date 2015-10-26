import java.io.IOException;
import java.util.List;

public class MainClass {

	static String os_name;
	static String configfilename = "config.json";

	public static void main(String[] args) {
		
		
		
		os_name = getOSname();
		JsonParser jsp = new JsonParser(configfilename, os_name);
		List<String> command = jsp.getCommandString();

		CommandLineProcess clp = new CommandLineProcess(command);
		
		//List<String> response=clp.start(System.getProperty("file.encoding"));
		//Cp866
		List<String> response=clp.start("Cp866");
		
		response.forEach((a) -> System.out.println(a));
		

	}

	private static String getOSname() {
		return System.getProperty("os.name");
	}

}
