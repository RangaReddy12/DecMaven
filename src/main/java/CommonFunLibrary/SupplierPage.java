package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SupplierPage {
WebDriver driver;
WebDriverWait wait;
public SupplierPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(linkText="Suppliers")
WebElement clicksupplier;
@FindBy(xpath="//body/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")
WebElement clickaddiconbtn;
@FindBy(xpath="//input[@id='x_Supplier_Number']")
WebElement snumber;
@FindBy(xpath="//input[@id='x_Supplier_Name']")
WebElement entersname;
@FindBy(xpath="//textarea[@id='x_Address']")
WebElement enteraddress;
@FindBy(xpath="//input[@id='x_City']")
WebElement entercity;
@FindBy(xpath="//input[@id='x_Country']")
WebElement entercountry;
@FindBy(xpath="//input[@id='x_Contact_Person']")
WebElement entercperson;
@FindBy(xpath="//input[@id='x_Phone_Number']")
WebElement enterpnumber;
@FindBy(xpath="//input[@id='x__Email']")
WebElement enteremail;
@FindBy(xpath="//input[@id='x_Mobile_Number']")
WebElement entermnumber;
@FindBy(xpath="//textarea[@id='x_Notes']")
WebElement enternotes;
@FindBy(xpath="//button[@id='btnAction']")
WebElement Clickaddbtn;
@FindBy(xpath="//button[contains(text(),'OK!')]")
WebElement Clickconfirmokbtn;
@FindBy(xpath="(//button[text()='OK'])[6]")
WebElement Clickalertonbtn;
@FindBy(xpath="//body/div[2]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/button[1]/span[1]")
WebElement clickserachpanelbtn;
@FindBy(name="psearch")
WebElement enterserchtextbox;
@FindBy(name="btnsubmit")
WebElement clicksearchbtn;
@FindBy(xpath="//table[@id='tbl_a_supplierslist']")
WebElement table;
public String verifySupplier(String suppliername,String address,String city,
String country,String contactperson,String phonenumber,String email,
String mobilenumber,String notes)
{
	String res=null;
	wait= new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.elementToBeClickable(clicksupplier));
	this.clicksupplier.click();
	wait.until(ExpectedConditions.elementToBeClickable(clickaddiconbtn));
	this.clickaddiconbtn.click();
	wait.until(ExpectedConditions.visibilityOf(snumber));
	String expectedsnumber=this.snumber.getAttribute("value");
	this.entersname.sendKeys(suppliername);
	this.enteraddress.sendKeys(address);
	this.entercity.sendKeys(city);
	this.entercountry.sendKeys(country);
	this.entercperson.sendKeys(contactperson);
	this.enterpnumber.sendKeys(phonenumber);
	this.enteremail.sendKeys(email);
	this.entermnumber.sendKeys(mobilenumber);
	this.enternotes.sendKeys(notes);
	this.Clickaddbtn.click();
	wait.until(ExpectedConditions.elementToBeClickable(Clickconfirmokbtn));
	this.Clickconfirmokbtn.click();
	wait.until(ExpectedConditions.elementToBeClickable(Clickalertonbtn));
	this.Clickalertonbtn.click();
	if(!this.enterserchtextbox.isDisplayed())
		//click on search panel
    this.clickserachpanelbtn.click();
	this.enterserchtextbox.clear();
	this.enterserchtextbox.sendKeys(expectedsnumber);
	this.clicksearchbtn.click();
	wait.until(ExpectedConditions.visibilityOf(table));
String actualsnumber=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr/td[6]/div/span/span")).getText();
if(actualsnumber.equals(expectedsnumber))
{
	Reporter.log(actualsnumber+"   "+expectedsnumber,true);
	res="Pass";
}
else
{
	Reporter.log(actualsnumber+"   "+expectedsnumber,true);
	res="Fail";
}
return res;
}








}







