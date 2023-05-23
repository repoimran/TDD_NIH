package common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import report.Log;
import static common.CommonWait.waitUntilClickable;
import static common.CommonWait.waitUntilVisible;
import util.Configuration;
import util.Misc;

public class CommonAction {
	static Configuration config = new Configuration();

	static String test_output_path = config.readProp("test_output_path");
	static String screenshotPath = config.readProp("screenshotPath");
	static String tessarectTextPath = config.readProp("tessarectTextPath");

	public static String getInnerHTML(WebElement element) {
		waitUntilVisible(element);
		String innerText = element.getAttribute("innerHTML");
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< Inner Text : " + innerText);
		return innerText;
	}

	public static String getText(WebElement element) {
		waitUntilVisible(element);
		String innerText = element.getText();
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< Inner Text : " + innerText);
		return innerText;
	}

	public static void click(WebElement element) {
		waitUntilClickable(element);
		element.click();
		Log.log(Misc.getClassName(Thread.currentThread()), element + " Has been clicked");
	}

	public static void jsClick(WebElement element, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " Has been clicked with JavascriptExec");
	}

	public static void insert(WebElement element, String text) {
		element.sendKeys(text);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< value inserted : " + text);
	}

	public static void insert(WebElement element, char text) {
		String temp = String.valueOf(text);
		element.sendKeys(temp);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< value inserted : " + text);
	}

	public static void dropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< selected : " + value);
	}

	public static void switchWindow(String root, Set<String> allWindows, WebDriver driver) {
		for (String handle : allWindows) {
			if (!handle.equals(root)) {
				driver.switchTo().window(handle);
			}
		}
	}

	public static void screenshotElement(WebDriver driver, String name, WebElement element) throws Exception {
		waitUntilVisible(element);
		File src = element.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File(screenshotPath + name + ".png"));

	}

	public static File screenshotPage(WebDriver driver, String name) throws IOException {
		File screenShotTarget;
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dir = createDirectory(screenshotPath);
		screenShotTarget = new File(dir + name + ".png");
		FileUtils.copyFile(source, screenShotTarget);
		return screenShotTarget;
	}

	public static void mouseHover(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		waitUntilVisible(element);
		actions.moveToElement(element).build().perform();
	}

	public static void roboKey(String key) {
		String k = key.toLowerCase();
		try {
			Robot robot = new Robot();
			if (k.equals("enter")) {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

			} else if (k.equals("tab")) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}
		} catch (AWTException e) {
			System.out.println(e.getMessage());
		}

	}

	public static String saveOcrImage(File screenshot) {
		ITesseract image = new Tesseract();
		String ocrString = "";
		try {
			ocrString = image.doOCR(screenshot);
			System.out.println("******************** OCR TEXT START ********************");
			System.out.println(ocrString);
			System.out.println("******************** OCR TEXT END ********************");
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return ocrString;
	}

	public static String getTimestamp() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String timestamp = format.format(date);
		return timestamp;
	}

	public static void textToFile(String text, String name) throws IOException {
		String dir = createDirectory(tessarectTextPath);
		// Create the empty text file name ocr.txt
		String filePath = dir + File.separator + name + ".txt"; // Build the file path

		text = getTimestamp() + "\n" + text; // adding timestamp and new line
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(text);// write the text to the file, not yet saved
		fileWriter.close();// save the file

	}

	public static String createDirectory(String dirName) {
		String dir = test_output_path + dirName;
		// check if directory exists
		File directory = new File(dir);
		if (!directory.exists()) {
			directory.mkdirs();// if not create directory
		}
		return dir;
	}

}
