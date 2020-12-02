package CommonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;
	public static WebDriver startbrowser()throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			Reporter.log("Executing on chrome browser",true);
			System.setProperty("webdriver.chrome.driver", "D:\\OJTFrameWorks\\StockAccounting_Maven\\CommonDrivers\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			Reporter.log("Executing on firefox browser",true);
			System.setProperty("webdriver.gecko.driver", "D:\\OJTFrameWorks\\StockAccounting_Maven\\CommonDrivers\\geckodriver.exe");
			driver= new FirefoxDriver();	
		}
		else
		{
			Reporter.log("Browser value is not matching",true);
		}
		return driver;
	}
	//method for launch url
	public static void openApplication(WebDriver driver)throws Throwable
	{
		Reporter.log("executing openApplication method",true);	
		driver.get(PropertyFileUtil.getValueForKey("Url"));
		driver.manage().window().maximize();
	}
	//method for wait for element
	public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String waititme)
	{
		Reporter.log("executing waitfor element method",true);	
		WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waititme));
		if(locatortype.equalsIgnoreCase("name"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));	
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}
		else
		{
			Reporter.log("Unable to execute waitfor element method",true);	
		}
	}
	//method for type action
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata)
	{
		Reporter.log("Executing typeAction method");
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);	
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}
		else
		{
			Reporter.log("Unable to exeute typeaction method",true);
		}
	}
	//method for click action
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue)
	{
		Reporter.log("Execuring clickaction Method",true);
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}
		else
		{
			Reporter.log("Unable to Execute ClickAction method",true);
		}
	}
	//method for close browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	//method date generate
		public static String generateDate()
		{
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd hh_mm_ss");
			return sdf.format(d);
		}
	//method for capturedata into note pad
public static void captureData(WebDriver driver,String locatortype,String locatorvalue)		
throws Throwable{
	String expsnumber="";
	if(locatortype.equalsIgnoreCase("name"))
	{
	expsnumber=driver.findElement(By.name(locatorvalue)).getAttribute("value");	
	}
	else if(locatortype.equalsIgnoreCase("id"))
	{
	expsnumber=driver.findElement(By.id(locatorvalue)).getAttribute("value");	
	}
	else if(locatortype.equalsIgnoreCase("xpath"))
	{
	expsnumber=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
	}
FileWriter fw= new FileWriter("D:\\OJTFrameWorks\\StockAccounting_Maven\\CaptureData\\snumber.txt");
BufferedWriter bw= new BufferedWriter(fw);
bw.write(expsnumber);
bw.flush();
bw.close();
}
//method supplier table validation
public static void stableValidation(WebDriver driver,String testdata)throws Throwable
{
FileReader fr= new FileReader("D:\\\\OJTFrameWorks\\StockAccounting_Maven\\CaptureData\\snumber.txt");
BufferedReader br= new BufferedReader(fr);
String supnumber=br.readLine();
////convert testdata into integer
int column=Integer.parseInt(testdata);
//if search textbox already exist then enter expdata into search textbox
if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtextbox"))).isDisplayed())
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("serachpanel"))).click();
Thread.sleep(5000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtextbox"))).clear();
Thread.sleep(5000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtextbox"))).sendKeys(supnumber);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbtn"))).click();
Thread.sleep(5000);
WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("stable")));
List<WebElement> rows=table.findElements(By.tagName("tr"));
for(int i=1;i<=rows.size(); i++)
{
String actsnumber=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+column+"]/div/span/span")).getText();
Assert.assertEquals(actsnumber, supnumber);
Reporter.log(actsnumber+"    "+supnumber,true);
break;
	}
}
//method for stockCategories
public static void stockCategories(WebDriver driver)throws Throwable
{
	Actions ac= new Actions(driver);
ac.moveToElement(driver.findElement(By.xpath("//a[text()='Stock Items ']"))).perform();
Thread.sleep(4000);
ac.moveToElement(driver.findElement(By.xpath("(//a[text()='Stock Categories'])[2]"))).click().perform();
Thread.sleep(4000);
}
//method for stockitems
public static void sttableValidation(WebDriver driver,String testdata)throws Throwable
{
if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtextbox1"))).isDisplayed())
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("serachpanel1"))).click();
Thread.sleep(4000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtextbox1"))).clear();
Thread.sleep(4000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtextbox1"))).sendKeys(testdata);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbtn1"))).click();
Thread.sleep(4000);
String tabledata=driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr/td[4]/div/span/span")).getText();
Assert.assertEquals(testdata, tabledata,"Categories name is not matching with table");
Reporter.log(testdata+"    "+tabledata,true);

}
}









