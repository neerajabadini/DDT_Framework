package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import util.AppUtil;
import util.ExcelFileUtil;

public class AdminLoginInvalidData extends AppUtil {
	String inputpath ="./FileInput/TestData.xlsx";
	String outputpath ="./FileOutput/InputLoginData.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	String TCsheet = "InvalidLoginData";
	@Test
	public void startTest() throws Throwable
	{
		//define path to generate extent reports
		reports = new ExtentReports("./target/InvalidData.Html");                                                                                                                                                                                                        
		//create reference object for ExcelFileUtil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in tcsheet
		int rc = xl.rowCount(TCsheet);
		Reporter.log("No of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger = reports.startTest("InvalidLoginData");
			logger.assignAuthor("Ranga");
			String user = xl.getCellData(TCsheet, i, 0);
			String pass = xl.getCellData(TCsheet, i, 1);
			FunctionLibrary lp = new FunctionLibrary();
			lp.adminLogin(user, pass);
			boolean res = lp.isErrorMessageDisplay();
			if (res) 
			{
				
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screen, new File("./target/screenshot/"+i+"  "+"InvalidLoginData.png"));
				xl.setCellData(TCsheet, i, 2,pass,outputpath);
				logger.log(LogStatus.FAIL, "Valid credentials");
				logger.addScreenCapture("./target/screenshot/"+i+"  "+"InvalidLoginData.png");
				
				}
			reports.endTest(logger);
			reports.flush();
			lp.adminlogout();
		}
		
	}

}
