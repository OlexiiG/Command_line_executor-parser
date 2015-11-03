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
		
		//response.forEach((a) -> System.out.println(a));//printing application response
		
		
		//parsing application response (STDOUT)
		StringParser sp=new StringParser(response);
	//getting table parsing param	
		List<String> tablesignatures=jsp.gettableheader("table1");
		String endtablesignature=jsp.endtablesignature("table1");
		
		List<String> table=sp.gettable (tablesignatures,endtablesignature);
		
		table.forEach((a) -> System.out.println(a));
		
		TableParser tp=new TableParser(table, tablesignatures);
		
		
		for (int t=0;t<1000;t++){
		System.out.println(tp.getvalue(tp.getrow(t), "Имя")+"\t\t"+tp.getvalue(tp.getrow(t), "Состояние"));
		}
		
		
		
		
		//List<String> tableheader=tp.gettablesignatures();
		//sp.split(sp.removetabs(table.get(1))).forEach((a) -> System.out.println(a));
		
		//List <String> column=tp.getcolumn (table,tableheader,"Имя");
		
		
		/*for (String s:column){
			
			System.out.println(s);
		}*/
	}

	private static String getOSname() {
		return System.getProperty("os.name");
	}

}
