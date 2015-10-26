import java.io.IOException;
import java.util.List;

public class MainClass {

	static String os_name;
	static String configfilename = "config.json";
	static String console_charset;
	
	public static void main(String[] args) {
		
		
		
		os_name = getOSname();
		JsonParser jsp = new JsonParser(configfilename, os_name);
		console_charset=jsp.getConsoleCharset();
		List<String> command = jsp.getCommandString();

		CommandLineProcess clp = new CommandLineProcess(command);
		
		List<String> response=clp.start(console_charset);
		
		response.forEach((a) -> System.out.println(a));
		

	}

	private static String getOSname() {
		return System.getProperty("os.name");
	}

}
