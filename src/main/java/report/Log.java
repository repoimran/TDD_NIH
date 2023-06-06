package report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.testng.Reporter;

public class Log {
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//	This line declares a Logger object named logger and initializes it with the global logger name.

	private static final Path logsPath = Paths.get("test-output", "logs");
//	This line creates a Path object named logsPath that represents the path to the "logs" directory. The directory will be created in the test-output directory.

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//	This line creates a DateTimeFormatter object named formatter that is used to format the date and time information in the log file name.

	private static FileHandler fileHandler;
//	This line declares a FileHandler object named fileHandler. 

//	The object will be initialized in the static block below.
	static {
		try {
			Files.createDirectories(logsPath);
			String fileName = "log_" + LocalDateTime.now().format(formatter) + ".txt";
			Path logFilePath = logsPath.resolve(fileName);
			fileHandler = new FileHandler(logFilePath.toString(), true);
			fileHandler.setLevel(Level.ALL);
			fileHandler.setFormatter(new Formatter() {
				public String format(LogRecord record) {
					return record.getMessage() + "\n";
				}
			});
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * This is the static block that initializes the fileHandler object and performs
	 * other initialization tasks.
	 * 
	 * Files.createDirectories(logsPath); creates the "logs" directory if it does
	 * not already exist. String fileName = "log_" +
	 * 
	 * LocalDateTime.now().format(formatter) + ".txt"; creates a file name for the
	 * log file. The file name includes a timestamp formatted using the formatter
	 * object.
	 * 
	 * Path logFilePath = logsPath.resolve(fileName); creates a Path object named
	 * logFilePath that represents the path to the log file. The path is created by
	 * combining the logsPath and fileName variables.
	 * 
	 * fileHandler = new FileHandler(logFilePath.toString(), true); initializes the
	 * fileHandler object with the path to the log file and the option to append to
	 * the file if it already exists.
	 * 
	 * fileHandler.setLevel(Level.ALL); sets the logging level for the fileHandler
	 * object to ALL, which means it will log all messages.
	 * 
	 * fileHandler.setFormatter(new Formatter() {...}); sets a custom formatter for
	 * the fileHandler object that formats each log message.
	 * 
	 * logger.addHandler(fileHandler); adds the fileHandler object to the logger
	 * object's list of handlers.
	 */
	public static void log(String actionMethods, String msg) {
		removeConsole();

		Reporter.log(msg + "<br>"); // for html report
		logger.log(Level.INFO, actionMethods + " - " + msg);// for console report
	}

//	remove console logs
	public static void removeConsole() {
		Logger rootLogger = Logger.getLogger("");
		for (Handler handler : rootLogger.getHandlers()) {
			if (handler instanceof ConsoleHandler) {
				rootLogger.removeHandler(handler);
			}
		}
	}
}
