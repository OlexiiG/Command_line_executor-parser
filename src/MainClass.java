import java.util.List;

public class MainClass {

	static String os_name;
	static String configfilename = "config.json";
	static String console_charset;
	
	public static void main(String[] args) {
		
		
		
		os_name = getOSname();//getting current OS name
		JsonParser jsp = new JsonParser(configfilename, os_name);//reading configuration JSON file
		console_charset=jsp.getConsoleCharset();//getting charset for standart output console (STDOUT)
		List<String> executeParameters = jsp.getExecuteParameters();//getting application name and command line parameters

		CommandLineProcess clp = new CommandLineProcess(executeParameters);//preparing application to start
		
		List<String> response=clp.start(console_charset);//starting command line application response is STDOUT
		
		response.forEach((a) -> System.out.println(a));//printing application response
		
		
		//parsing application response (STDOUT)
		StringParser sp=new StringParser(response,jsp);

	}

	private static String getOSname() {
		return System.getProperty("os.name");
	}

}
