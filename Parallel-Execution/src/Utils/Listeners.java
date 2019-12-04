package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import java.lang.Throwable;
import java.lang.String;


public class Listeners extends BrowserFactory implements WebDriverEventListener{

	public void beforeAlertAccept(WebDriver driver) {
		
	}

	public void afterAlertAccept(WebDriver driver) {
		System.out.println("The alert is accepted");
	}

	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("The alert is dismissed");
	}

	public void beforeAlertDismiss(WebDriver driver) {

	}

	public void beforeNavigateTo(java.lang.String url, WebDriver driver) {

	}

	public void afterNavigateTo(java.lang.String url, WebDriver driver) {
		System.out.println("The browser loaded the URL "+ url);
	}

	public void beforeNavigateBack(WebDriver driver) {

	}

	public void afterNavigateBack(WebDriver driver) {
		System.out.println("The browser has loaded the previous page from the history");
	}

	public void beforeNavigateForward(WebDriver driver) {

	}

	public void afterNavigateForward(WebDriver driver) {
		System.out.println("The browser has loaded the next page from the history");
	}

	public void beforeNavigateRefresh(WebDriver driver) {

	}

	public void afterNavigateRefresh(WebDriver driver) {
		System.out.println("The browser has reloaded successfully");
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying on element-> "+ by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Processed completd on above element");
		//System.out.println("Processe on element-> "+ String.valueOf(by));
		System.out.println("-------------------------------------------*Element Action*------------------------------------------------");
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("-------------------------------------------*Click Action*--------------------------------------------------");
		System.out.println("Trying to click on '"+ element.getText() + "' button");
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on button: " + element.getText());
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, java.lang.CharSequence[] keysToSend) {

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, java.lang.CharSequence[] keysToSend) {

	}

	public void beforeScript(String script, WebDriver driver) {
		System.out.println("Just before beforeScript " + driver);
	}

	public void afterScript(String script, WebDriver driver) {
		System.out.println("Just after afterScript " + driver);
	}

	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("The driver is moved to the window with title "+driver.getTitle());	
	}

	public void beforeSwitchToWindow(String windowName, WebDriver driver) {

	}

	public void onException(Throwable throwable, WebDriver driver) {
		System.out.println("OnExceptio status: "+throwable);
	}

	public void beforeGetText(WebElement element, WebDriver driver) {

	}

	public void afterGetText(WebElement element, WebDriver driver, String text) {

	}

	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
	
	}


	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		
	}
	
	
}