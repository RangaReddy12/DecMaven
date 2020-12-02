package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.LoginPage;
import CommonFunLibrary.SupplierPage;
import Utilities.ExcelFileUtil;

public class DataDrivenFramework {
	WebDriver driver;
	String inputpath="D:\\OJTFrameWorks\\ERP_StockAccounting\\TestInput\\TestData.xlsx";
	String outputpath="D:\\OJTFrameWorks\\ERP_StockAccounting\\TestOutput\\DatadrivenResults.xlsx";
	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\OJTFrameWorks\\ERP_StockAccounting\\CommonDrivers\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("http://projects.qedgetech.com/webapp/login.php");
		driver.manage().window().maximize();
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		String loginres=login.verifyLogin("admin", "master");
		Reporter.log(loginres,true);
	}
	@Test
	public void suppliercreation()throws Throwable
	{
		//create object for excelfileutil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		SupplierPage supplier=PageFactory.initElements(driver, SupplierPage.class);
		//count no of rows in a sheet
		int rc=xl.rowCount("Supplier");
		//count no of cells in a row
		int cc=xl.cellCount("Supplier");
		Reporter.log("No of rows in a sheet::"+rc+"  "+"No of cells in a row::"+cc,true);
		for(int i=1;i<=rc;i++)
		{
			String sname=xl.getCellData("Supplier", i, 0);
			String address=xl.getCellData("Supplier", i, 1);
			String city=xl.getCellData("Supplier", i, 2);
			String country=xl.getCellData("Supplier", i, 3);
			String cperson=xl.getCellData("Supplier", i, 4);
			String pnumber=xl.getCellData("Supplier", i, 5);
			String email=xl.getCellData("Supplier", i, 6);
			String mnumber=xl.getCellData("Supplier", i, 7);
			String notes=xl.getCellData("Supplier", i, 8);
			String results=supplier.verifySupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
			xl.setCellData("Supplier", i, 9, results, outputpath);
		}
	}
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
}
