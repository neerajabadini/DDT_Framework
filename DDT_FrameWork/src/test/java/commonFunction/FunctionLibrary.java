package commonFunction;

import org.openqa.selenium.By;

import util.AppUtil;

public class FunctionLibrary extends AppUtil{
//method for login
	public void adminLogin(String user,String pass)
	{
		driver.findElement(By.xpath(conpro.getProperty("obbjUser"))).sendKeys(user);
driver.findElement(By.xpath(conpro.getProperty("objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("objlogin"))).click();
		}
	//method for verify admin link
	public boolean isAdminDisplayed()
	{
if (driver.findElement(By.xpath(conpro.getProperty("ObjAdmin"))).isDisplayed()) 
{
	return true;		
		}
else
{
		return false;
		}
	}
	//method for verifying error message
	public boolean isErrorMessageDisplay()
	{
String Error_mess = driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText().toLowerCase();
if (Error_mess.contains("invalid")||Error_mess.contains("empty"))
	
{
return true;
	}
else
{
	return false;
}
}
	//method for logout
	public void adminlogout()
	{
		driver.findElement(By.xpath(conpro.getProperty("objwelcome"))).click();
	driver.findElement(By.xpath(conpro.getProperty("objlogout"))).click();	
	}	
	}