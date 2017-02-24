
import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumScript {
	public static void main(String[] args) throws InterruptedException {
		StringBuffer ss =new StringBuffer();
		ss.append("\\\\192.168.0.241\\");
		ss.append("cjk-ndc\\ganerama\\LOG FILE\\file");
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory",ss.toString());
		
		DesiredCapabilities capabilites = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		capabilites.setCapability(ChromeOptions.CAPABILITY, options);
		String currentPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", currentPath+"//chromedriver.exe");
		
		WebDriver driver = new ChromeDriver(capabilites);
		driver.get("http://192.168.41.222:8080/internal-tool-front/hue/login");
		Thread.sleep(100);
		driver.findElement(By.id("userId")).sendKeys("dhanasekar.c");
		driver.findElement(By.id("password")).sendKeys("Test@123");
		driver.findElement(By.id("login-btn")).click();
		Thread.sleep(100);
		driver.get("http://192.168.41.222:8080/internal-tool-front/hue/conversion/ac/hrtool/attendance/attendancereport/attendancereport/index?sid=ConversionAcattendanceReport");
		Thread.sleep(1000);
		WebElement singleSelectWebElement = driver.findElement(By.id("day-select"));
		singleSelectWebElement.findElement(By.className("wap-icon-angle-down")).click();
		singleSelectWebElement.findElement(By.cssSelector("#day-select li:nth-child(1)")).click();
		Thread.sleep(100);
		driver.findElement(By.id("load-button_target")).click();
		Thread.sleep(1000);
		deleteDir(new File(ss.toString()));
		makeFolder(new File(ss.toString()));
		WebElement excelButton = driver.findElement(By.id("excel-button_target"));
		excelButton.click();
       }

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir (new File(dir, children[i]));

				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
	public static void makeFolder(File file) {

		File f = null;
		boolean bool = false;

		try{      
			bool = file.mkdir();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
