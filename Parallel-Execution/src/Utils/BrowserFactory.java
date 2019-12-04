package Utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;
import Objects.pageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;
import Utils.Listeners;

public class BrowserFactory implements pageObjects{

	private static BrowserFactory instance = null;
	protected ThreadLocal<WebDriver> thread = new ThreadLocal<WebDriver>();
	public WebDriverWait wait;
	public EventFiringWebDriver edriver;

	public static BrowserFactory getInstance() {
		if (instance == null) {
			instance = new BrowserFactory();
		}
		return instance;
	}

	@SuppressWarnings("deprecation")
	public void setBrowser(String browserName) throws Exception {
		try {
			String str = browserName + " is up and running on thread : " + Thread.currentThread().getId();
			switch (browserName) {
 
			case "Chrome":
				System.out.println(str);
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				//options.addArguments("--start-maximized");
				options.addArguments("enable-automation");
				options.addArguments("--disable-notifications");
				options.addArguments("--disable-infobars");
				thread.set(new ChromeDriver(options));
				break;
				
			case "ChromeIG":
				System.out.println(str);
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				ChromeOptions options1 = new ChromeOptions();
				options1.addArguments("--incognito");
				options1.addArguments("enable-automation");
				options1.addArguments("--disable-notifications");
				options1.addArguments("--disable-infobars");
				thread.set(new ChromeDriver(options1));
				break;
				
			case "IE":
				System.out.println(str);
				WebDriverManager.iedriver().setup();
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("ignoreZoomSetting", true);
				capabilities.setCapability("requireWindowFocus", true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				//capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
				InternetExplorerOptions ieOption = new InternetExplorerOptions();
				ieOption.getVersion();
				ieOption.requireWindowFocus();
				thread.set(new InternetExplorerDriver(capabilities));
				break;
				
			case "Firefox":
				System.out.println(str);	
				System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
//				FirefoxProfile profile = new FirefoxProfile();
//				profile.setPreference("browser.privatebrowsing.autostart", true);
				FirefoxOptions fOption = new FirefoxOptions();
				fOption.setBinary("C:\\Program Files (x86)\\Firefox\\firefox.exe");
				thread.set(new FirefoxDriver(fOption));
				break;
				
			case "Edge":
				try {
				System.out.println(str);			
				System.setProperty("webdriver.chrome.driver", "./driver/MicrosoftWebDriver.exe");
				DesiredCapabilities edgeCapabilities = DesiredCapabilities.chrome();
				thread.set(new ChromeDriver(edgeCapabilities));
				//driver().manage().window().maximize();
				break;
				}catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		} catch (Exception e) {
			//System.out.println("Browser Failed");
		}
	}

	public WebDriver driver() {
		return thread.get();
	}
	
	public void eventListener() throws InterruptedException {
		edriver = new EventFiringWebDriver(driver());
		Listeners listen = new Listeners();
		edriver.register(listen);
		wait(2000);
		driver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver().manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);
	}
	
	public void selectDropdown(By obj, String value) {
		Select selectListBox = new Select(getElement(obj));
		selectListBox.selectByValue(value);
	}

	public String readData(String key) throws IOException {
		Properties properties = new Properties();
		File file = new File(System.getProperty("user.dir"), "src\\Resources\\file.properties");
		FileReader read = new FileReader(file);
		properties.load(read);
		String data = properties.getProperty(key);
		return data;
	}

	public WebElement getElement(By obj) {
		WebElement element = null;
		try {
			edriver = new EventFiringWebDriver(driver());
			Listeners listen = new Listeners();
			edriver.register(listen);
		element = edriver.findElement(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  element;
	}
	
	public void PressEnter() {
		try {
			Actions press = new Actions(driver());
			press.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception e) {
			e.toString();
		}
	}
	
	public void click(By obj) {
		wait = new WebDriverWait(driver(), 15);
		wait.until(ExpectedConditions.elementToBeClickable(obj));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(obj));
		getElement(obj).click();
	}
	
	public void uploadFiles(By obj, String fileNameWithFormat) throws Exception {
		try {
			Robot robot = new Robot();
			click(obj);
			robot.setAutoDelay(2000);
			String fileLocation = System.getProperty("user.dir") + "\\files\\" + fileNameWithFormat;
			StringSelection select = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.toString();
		}
	}
	
	public void createNewTab(String URL) throws Exception {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver();
		String jsOpenNewWindow = "window.open('" + URL + "');";
		jsExecutor.executeScript(jsOpenNewWindow);
	}

	public void sendKeys(By obj, String key) {
		try {
			wait = new WebDriverWait(driver(), 5);
			wait.until(ExpectedConditions.visibilityOfAllElements(getElement(obj)));
			getElement(obj).sendKeys(key);
		} catch (Exception e) {
			throw new TestException(String.format("Error in sending to the following element: ", key, e.toString()));
		}
	}

	public void waitUntil(By element, int time, String strConditionMode) throws Exception {
		try {

			String mode = strConditionMode.toUpperCase();
			switch (mode) {
			case "VISIBILITY":
				(new WebDriverWait(driver(), time)).until(ExpectedConditions.visibilityOfElementLocated(element));
				break;
			case "INVISIBILITY":
				(new WebDriverWait(driver(), time)).until(ExpectedConditions.invisibilityOfElementLocated(element));
				break;
			case "PRESENCE":
				(new WebDriverWait(driver(), time)).until(ExpectedConditions.presenceOfElementLocated(element));
				break;
			case "FRAME":
				(new WebDriverWait(driver(), time))
						.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
				break;
			case "CLICKABLE":
				(new WebDriverWait(driver(), time)).until(ExpectedConditions.elementToBeClickable(element));
				break;
			default:
				Assert.fail("wait for UI failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchToNewWindow() {
		try {
//			String parentWinHandle = driver().getWindowHandle();
			String lastWindow = null;
			Set<String> winHandles = driver().getWindowHandles();
			// Loop through all handles
			for (String handle : winHandles) {
				lastWindow = handle;
			}
			driver().switchTo().window(lastWindow);
			System.out.println("Switched to new window");
		} catch (Exception e) {
			e.toString();
		}
	}
}
