package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(name="username")
	WebElement enterusername;
	@FindBy(name="password")
	WebElement enterpassword;
	@FindBy(name="btnsubmit")
	WebElement Clicklogin;
	@FindBy(name="btnreset")
	WebElement Clickreset;
	@FindBy(id="mi_logout")
	WebElement clicklogout;
	public String verifyLogin(String username,String password)
	{
		String res=null;
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(Clickreset));
		this.Clickreset.click();
		wait.until(ExpectedConditions.visibilityOf(enterusername));
		this.enterusername.sendKeys(username);
		wait.until(ExpectedConditions.visibilityOf(enterpassword));
		this.enterpassword.sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(Clicklogin));
		this.Clicklogin.click();
		wait.until(ExpectedConditions.visibilityOf(clicklogout));
		if(this.clicklogout.isDisplayed())
		{
			res="Login Success";
		}
		else
		{
			res="Login Fail";
		}
		return res;
	}
}









