import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandLineProcess {

	ProcessBuilder procBuilder = null;

	public CommandLineProcess(List<String> command_params) {

		procBuilder = new ProcessBuilder(command_params);
	}

	public List<String> start(String console_encoding) {

		List<String> response = new ArrayList<String>();
		Process process;
		try {
			process = procBuilder.start();

			InputStream stdout = process.getInputStream();
			InputStreamReader isrStdout = new InputStreamReader(stdout,
					console_encoding);
			BufferedReader brStdout = new BufferedReader(isrStdout);

			String line;

			while ((line = brStdout.readLine()) != null) {
				response.add(line);
			}
			
			response.add(new String ("ERRORCODE="+Integer.toString(process.waitFor())));
			return response;

		} catch (IOException | InterruptedException e) {
			System.out.println("Error running programm");
			//e.printStackTrace();
			return null;
		}

	}
}
