package Enel.ADOPdemo;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class createDealerInSalesCloud {
	
	
	
	WebDriver driver;
	
	
	@Test
	public  void updateDealer() throws Exception {
		
		System.setProperty("webdriver.chrome.driver","resources\\chromedriver.exe");
		driver=new ChromeDriver();
		login("https://test.salesforce.com","karan.k.kansagra@accenture.com.fsdfdev","salesforce1");
		searchDealer("Honda");
		editDealer("210073");
		
	}
	
	
	public void login(String uRL, String userName, String passWord) throws Exception {
		
		driver.get(uRL);
		driver.manage().window().maximize();
		takeSnapShot(driver,"screenshots\\001.loginPage.png");
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		takeSnapShot(driver,"screenshots\\002.enterUsername.png");
		
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(passWord);
		takeSnapShot(driver,"screenshots\\003.enterPassword.png");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		takeSnapShot(driver,"screenshots\\004.homPage.png");
	}
	
	
	public void searchDealer(String dealer)throws Exception {
		
		driver.findElement(By.xpath("//input[@id='phSearchInput']")).clear();
		driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(dealer);
		takeSnapShot(driver,"screenshots\\005.searchDealer.png");
		
		driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
		takeSnapShot(driver,"screenshots\\006.dealerSearchResult.png");
		driver.findElement(By.xpath("//div[@class='listRelatedObject Custom58Block']//a[text()='"+dealer+"']")).click();
		
	}
	
	
	public void editDealer(String zip) throws Exception {
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='edit']")).click();
		takeSnapShot(driver,"screenshots\\007.clickOnEdit.png");
		
		driver.findElement(By.xpath("//input[@id='00N4B000000pAKb']")).clear();
		takeSnapShot(driver,"screenshots\\008.clearExistingPinCode.png");
		driver.findElement(By.xpath("//input[@id='00N4B000000pAKb']")).sendKeys(zip);
		takeSnapShot(driver,"screenshots\\009.enterNewPinCode.png");
		driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']")).click();
		takeSnapShot(driver,"screenshots\\010.updatedDealer.png");
	}
	
	
	public void takeSnapShot(WebDriver webdriver, String filePath) throws Exception {
		
		TakesScreenshot scrShot=((TakesScreenshot)webdriver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(filePath);
		FileUtils.copyFile(SrcFile, DestFile);
	}

}
