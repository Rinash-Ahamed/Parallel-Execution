package Utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;

import io.restassured.RestAssured;

public class Action {

	public static WebDriver driver;
	public static EventFiringWebDriver edriver;
	public static String value = "";
	public static Properties properties;
	public static WebDriverWait wait;
	
	private final static String url = "http://automationpractice.com/index.php";

	protected static void chromeInit() throws Exception {
		try {
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			getStatusCode(url);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for driver Initialization");
		}
	}

	public static String readData(String key) throws IOException {
		properties = new Properties();
		File file = new File(System.getProperty("user.dir"), "src\\Utils\\File.properties");
		FileReader read = new FileReader(file);
		properties.load(read);
		String data = properties.getProperty(key);
		return data;
	}

	public static WebElement FindElement(By element) {
		try {
			return edriver.findElement(element);
		} catch (Exception e) {
			System.out.println("ExceptionFindElement" + e.toString());
		}
		return null;
	}

	public static String configurePath(String UserDirPath) {
		Path path = Paths.get(UserDirPath);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				System.out.println("Created new Filepath");
			} catch (IOException e) {
				System.out.println("Failed to create new Filepath");
				e.printStackTrace();
			}
		} else {
			System.out.println("Filepath is already available");
		}
		return UserDirPath;
	}

	public static void ChooseFromHeader(List<WebElement> element, String headerName) {
		for (int i = 0; i < element.size(); i++) {
			String Text = element.get(i).getText();
			if (Text.equalsIgnoreCase(headerName)) {
				click(element.get(i));
			}
		}
	}
	
	public static void selectRandomValue(List<WebElement> element) {
		//List<WebElement> se = new Select(driver.findElement(By.xpath("//select[@id='country']"))).getOptions();
		List<WebElement> selects = element;
		Random rand = new Random();
		for(WebElement select : selects){
		    List<WebElement> options = element;
		    int all = rand.nextInt(options.size());
		    options.get(all).click();
		} 
	}
	
	public static void getListofElements(List<WebElement> element) {
		List<WebElement> list = element; // driver.findElement(By.xpath(".//*[@id='__dialog1-footer']/button[1]/div"));
		for (WebElement resultItem : list) {
			String print = resultItem.getText();
			System.out.println(print);
		}
	}

	public static void sendKeys(WebElement element, String key) {
		try {
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
			element.sendKeys(key);
		} catch (Exception e) {
			throw new TestException(String.format("Error in sending to the following element: ", key, e.toString()));
		}
	}

	public static boolean click(WebElement element) {
		try {
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
			element.click();
			return true;
		} catch (Exception c) {
			System.out.println("element not clickable - " + c);
		}
		return false;
	}

	public static void hoverToElement(WebElement hoverFrom, WebElement hoverTo) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(hoverFrom).moveToElement(hoverTo).click().build().perform();
		} catch (Exception e) {
			System.out.println("element not visible - " + e);
		}
	}

	public static String generateRandomEmailID(String username) {
		String email = null;
		Random rad = new Random();
		for (int j = 1; j <= 1; j++) {
			email = username + rad.nextInt(100) + "@gmail.com";
		}
		return email;
	}
	
	public void zoom(double factor) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("document.body.style.transform = 'scale(' + arguments[0] + ')';" +
	        "document.body.style.transformOrigin = '0 0';",factor);
	  }

	public static void PageScroll(String value) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + value + ")"); // "window.scrollBy(0,-250)"
		// pass negative value for scrolling up
	}

	public static void selectByValue(WebElement element, int Index) {
		try {
			Select dropdown = new Select(element);
			dropdown.selectByIndex(Index);
		} catch (Exception e) {
			System.out.println("Exception occured" + e.toString());
		}
	}

	// For selecting visible from dropdown
	public static void selectByVisibleText(WebElement element, String value) {
		try {
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(value);
		} catch (Exception e) {
			System.out.println("Exception occured" + e.toString());
		}
	}
	
	public static void Refresh() {
		edriver.navigate().refresh();
	}

	public static void removeReadOnly() {
		List<WebElement> inputs = driver.findElements(By.tagName("input"));
		for (WebElement input : inputs) {
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", input);
		}
	}

	public static void SendCurrentsDate(WebElement element) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		System.out.println("Selected Date:" + date1);
		element.sendKeys(date1);
	}

	public static void sendDesiredDate(WebElement element) {
		// Create object of SimpleDateFormat class and decide the format
		SimpleDateFormat currentdate = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		// Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, +5); // Negative value for prevous / and positive for past date
		// Date after adding the days to the given date
		String newDate = currentdate.format(c.getTime());
		// Displaying the new Date after addition of Days
		System.out.println("Date after Addition: " + newDate);
		element.sendKeys(newDate);
	}

	public static void sendValueByJs(WebElement element, String value) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('value','" + value + "');", element);
		} catch (Exception e) {
			System.out.println("Exception occured" + e.toString());
		}
	}

	public static void selectJQueryDate(String fromDate, String toDate) {
		// For From or StartDate box
		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('start_leave_date').removeAttribute('readonly',0);");
		WebElement fromDateBox = driver.findElement(By.name("from_date"));
		fromDateBox.clear();
		fromDateBox.sendKeys(fromDate); // Enter date in required format

		// For To or EndDate box
		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('end_leave_date').removeAttribute('readonly',0);");
		WebElement toDateBox = driver.findElement(By.name("to_date"));
		toDateBox.clear();
		toDateBox.sendKeys(toDate); // Enter date in required format
	}

	public static void Datedifference() throws ParseException {
		String date1 = "07/15/2019";
		String date2 = "07/20/2019";
		String format = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dateObj1 = sdf.parse(date1);
		Date dateObj2 = sdf.parse(date2);
		System.out.println(dateObj1);
		System.out.println(dateObj2 + "\n");
		long diff = dateObj2.getTime() - dateObj1.getTime();
		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		System.out.println("difference between days: " + diffDays);
	}

	public static void jsForceClick(WebElement element) {
		try {
			WebElement data = element;
			String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
			((JavascriptExecutor) driver).executeScript(js, data);
			data.click();
		} catch (Exception e) {
			System.out.println("Exception occured" + e.toString());
		}
	}

	public static void setWindowSize(int Dimension1, int dimension2) {
		driver.manage().window().setSize(new Dimension(Dimension1, dimension2));
	}

	public static void keyboardEvents(WebElement webelement, Keys key, String alphabet) {
		webelement.sendKeys(Keys.chord(key, alphabet));
	}

	public static void PressEnter() {
		Actions press = new Actions(driver);
		press.sendKeys(Keys.ENTER).build().perform();
	}

	public static void clickMultipleElements(WebElement someElement, WebElement someOtherElement) {
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(someElement).click(someOtherElement).keyUp(Keys.CONTROL).build().perform();
	}

	public static void doubleClickWebelement(WebElement doubleclickonWebElement) throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.doubleClick(doubleclickonWebElement).perform();
		Thread.sleep(2000);
	}

	public static void highlightelement(WebElement element) {
		for (int i = 0; i < 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: solid red; border: 6px solid yellow;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

	public static void clickCheckboxFromList(WebElement element) {
		WebElement elementToClick = element;
		elementToClick.click();
	}

	public static void clear(WebElement element) {
		element.clear();
	}

	public static void navigateToURL(String URL) {
		System.out.println("Navigating to: " + URL);
		System.out.println("Thread id = " + Thread.currentThread().getId());
		try {
			driver.navigate().to(URL);
		} catch (Exception e) {
			System.out.println("URL did not load: " + URL);
			throw new TestException("URL did not load");
		}
	}

	public static String getPageTitle() {
		try {
			System.out.print(String.format("The title of the page is: ", driver.getTitle()));
			return driver.getTitle();
		} catch (Exception e) {
			throw new TestException(String.format("Current page title is: ", driver.getTitle()));
		}
	}

	// For Capturing screenshot
	public static String captureScreenshot(String testname) throws IOException {
		// String date = new SimpleDateFormat("hh:mm:ss a").format(new Date());
		TakesScreenshot shot = (TakesScreenshot) driver;
		File source = shot.getScreenshotAs(OutputType.FILE);
		String Dest = System.getProperty("user.dir") + "/Screenshots/" + testname + currentdate() + ".png";
		File Destination = new File(Dest);
		FileHandler.copy(source, Destination);
		return Dest;
	}

	public static void dragNDrop(WebElement element1, WebElement element2) {
		Actions action = new Actions(driver);
		WebElement SorceLocator = element1;
		WebElement DestinationLocator = element2;
		action.dragAndDrop(SorceLocator, DestinationLocator);
	}

	public static void SubMenuselector(WebElement element) {
		Actions makevisible = new Actions(driver);
		makevisible.moveToElement(element).build().perform();
		;
	}

	public static void uploadFiles(WebElement element, String filePath) throws Exception {
		try {
			Robot robot = new Robot();
			element.click();
			robot.setAutoDelay(2000);
			StringSelection select = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			System.out.println("File Upload using Robot class Failed");
			throw (e);
		}
	}

	public static Object getResponsCode() throws Exception {
		String currenturl = driver.getCurrentUrl();
		URL url = new URL(currenturl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		int code = connection.getResponseCode();
		// System.out.println("Response code of the currenturl is " + code);
		if (code == 200) {
			System.out.println("Response code of the currenturl is " + code);
		} else {
			System.out.println("ResponseCode not matched");
		}
		return code;
	}

	public static void getStatusCode(String url) {
		int code = RestAssured.get(url).statusCode();
		Assert.assertEquals(code, 200);
		System.out.println("Retured status code = '" + code + "'");
	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean isAlertPresent() {
		boolean foundAlert = false;
		wait = new WebDriverWait(driver, 5);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}
	
	public static void waitUntil(String pageTitle, int waitsec) throws InterruptedException {
		wait = new WebDriverWait(driver, waitsec);
		wait.until(ExpectedConditions.titleContains(pageTitle));
	}

	public static void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}
	
	// Method to open new tab
	public static void OpenNewtab(String url, int tabcount) {
		try {
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabcount));
			driver.get(url);
		} catch (Exception e) {
			System.out.println("Exception occurred in select dropdown" + e.toString());
		}
	}

	// Method to open previous tab
	public static void previoustab(int prevtabcount) {
		try {
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			// Thread.sleep(2000);
			driver.switchTo().window(tabs.get(prevtabcount));
		} catch (Exception e) {
			System.out.println("Exception occurred in select dropdown" + e.toString());
		}
	}

	// get tooltip text
	public static String getToolTip(WebElement toolTipofWebElement) throws InterruptedException {
		String tooltip = toolTipofWebElement.getAttribute("title");
		System.out.println("Tool text : " + tooltip);
		return tooltip;
	}

	// navigate to all link in page
	public static void navigateToEveryLinkInPage(List<WebElement> element) throws InterruptedException {
		List<WebElement> linksize = element;
		int linksCount = linksize.size();
		System.out.println("Total no of links Available: " + linksCount);
		String[] links = new String[linksCount];
		System.out.println("List of links Available: ");
		// print all the links from webpage
		for (int i = 0; i < linksCount; i++) {
			links[i] = linksize.get(i).getAttribute("href");
			System.out.println(linksize.get(i).getAttribute("href"));
		}
		// navigate to each Link on the webpage
		for (int i = 0; i < linksCount; i++) {
			driver.navigate().to(links[i]);
			Thread.sleep(3000);
			System.out.println(driver.getTitle());
		}
	}

	public static String currentdate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date(System.currentTimeMillis());
		String newDate = formatter.format(date);
		// System.out.println(newDate);
		return newDate;
	}

	/* ..........................Random Methods........................ */

	public static String generateRandomNumber() {
		int aNumber = (int) ((Math.random() * 900000000) + 100000000);
		int bNumber = (int) ((Math.random() * 1) + 9);
		return " " + (Integer.toString(aNumber) + Integer.toString(bNumber));
	}

	public static String randomString() {
		int n = 3;
		String AlphaNumericString = "0123456789"; // +0123456789+abcdefghijklmnopqrstuvxyz
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static String randomPan() {
		int n = 3, c = 4, d = 1;
		String Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Number = "0123456789";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (Char.length() * Math.random());
			sb.append(Char.charAt(index));
		}
		StringBuilder sd = new StringBuilder(c);
		for (int i = 0; i < c; i++) {
			int index1 = (int) (Number.length() * Math.random());
			sd.append(Number.charAt(index1));
		}
		StringBuilder sc = new StringBuilder(d);
		for (int i = 0; i < d; i++) {
			int index2 = (int) (Char.length() * Math.random());
			sc.append(Char.charAt(index2));
		}
		return sb.toString() +"PF"+ sd.toString() + sc.toString();
	}
}
