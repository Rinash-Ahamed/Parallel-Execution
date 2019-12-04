package Test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utils.BrowserFactory;

public class Basepage extends BrowserFactory {

	BrowserFactory activity = BrowserFactory.getInstance();

	@Parameters("browserName")
	@Test
	public void launch(String browserName) throws Exception {
		try {
			activity.setBrowser(browserName);
			activity.driver().get(activity.readData("url"));
			activity.waitUntil(username, 50, "visibility");
			activity.sendKeys(username, readData("Username"));
			activity.sendKeys(password, readData("Password"));
			activity.click(loginBT);
		} catch (Exception e) {
			System.out.println(this.getClass().getEnclosingMethod().getName() + " funtion failed " + e.toString());
		}
	}

	@Parameters("browserName")
	@AfterTest
	public void tearDown(String browserName) {
		thread.get().quit();
		System.out.println("Executed successfully on: " + browserName);

	}
}
