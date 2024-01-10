package Generic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import StepDefinition.Hooks;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class genericFunction {

	public static WebDriver driver;
	public static WebDriver tempdriver;
	public static String originalWindow;

	public WebDriver InitializeDriver() throws InterruptedException	
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}
	public void LaunchNetsuite() throws InterruptedException, IOException

	{

		driver.get("https://system.netsuite.com/pages/customerlogin.jsp");

	}
	public void LoginToNetsuite() throws InterruptedException, IOException

	{

		sendKeys(WebElements.email,getValueFrom_Properties("NSUserName"));
		sendKeys(WebElements.password,getValueFrom_Properties("NSPassword"));
		clickOnElement(WebElements.submitBtn);

		if(driver.getTitle().contains("The system was not able to select a login role for you based on your usual NetSuite usage. Choose an item from the list below."))
		{
			Thread.sleep(3000);
			clickOnElement(WebElements.chooseSB3AccountBtn);
			Thread.sleep(7000);
			if(getText(WebElements.verifySandbox).contains("SB3"))
			{
				clickOnElement(WebElements.trustDevice);
				Thread.sleep(5000);
				WebElement txt_EnteringOTP= findElement(WebElements.verificationCode);
				txt_EnteringOTP.sendKeys(new Totp(getValueFrom_Properties("netsuiteAuthKey")).now().toString());

				clickOnElement(WebElements.submitBtn2);
				Thread.sleep(5000);
				driver.navigate().refresh();
			}
		}
		if(driver.getTitle().contains("Login access has been disabled for this role."))
		{
			//System.out.println("yes");
			driver.findElement(WebElements.wickesDevBtn).click();
		}

	}

	public WebDriver LoginAsAPManager(WebDriver driver) throws InterruptedException, IOException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(getValueFrom_Properties("NetsuiteUrl"));
		driver.findElement(By.id("identifierId"))
		.sendKeys(getValueFrom_Properties("username_" + System.getProperty("user.name")));
		driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("password"))
		.sendKeys(DecryptPassword(getValueFrom_Properties("password_" + System.getProperty("user.name"))));
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("passwordNext")).click();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Thread.sleep(3000);
		if (!driver.findElements(By.xpath("//span[contains(text(), 'Try another way')]")).isEmpty()) {
			driver.findElement(By.xpath("//span[contains(text(), 'Try another way')]")).click();
		}

		Thread.sleep(3000);
		WebElement AuthenticatorAppElement = driver
				.findElement(By.xpath("//div[contains(text(), 'Get a verification code from the')]"));
		if (AuthenticatorAppElement.isDisplayed()) {
			AuthenticatorAppElement.click();
		}
		Thread.sleep(5000);
		WebElement txt_EnteringOTP = driver.findElement(By.xpath("//*[@id='totpPin']"));
		txt_EnteringOTP.sendKeys(new Totp(getValueFrom_Properties("googleAuthKey")).now().toString());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='totpNext']")).click();
		Thread.sleep(5000);
		// System.out.println(driver.getTitle());
		if(driver.getTitle().contains("Login access has been disabled for this role."))
		{
			//System.out.println("yes");
			driver.findElement(WebElements.APManagerBtn).click();
		}
		return driver;

	}

	public WebDriver LoginToReadsoft() throws InterruptedException, IOException
	{

		driver.get(getValueFrom_Properties("ReadsoftUrl"));
		/*driver.findElement(WebElements.loginSSO).click();
		driver.findElement(By.id("identifierId"))
		.sendKeys(getValueFrom_Properties("username_" + System.getProperty("user.name")));
		driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("password"))
		.sendKeys(DecryptPassword(getValueFrom_Properties("password_" + System.getProperty("user.name"))));
		driver.findElement(By.id("passwordNext")).click();

		Thread.sleep(3000);
		if (!driver.findElements(By.xpath("//span[contains(text(), 'Try another way')]")).isEmpty()) {
			driver.findElement(By.xpath("//span[contains(text(), 'Try another way')]")).click();
		}

		Thread.sleep(3000);
		WebElement AuthenticatorAppElement = driver
				.findElement(By.xpath("//div[contains(text(), 'Get a verification code from the')]"));
		if (AuthenticatorAppElement.isDisplayed()) {
			AuthenticatorAppElement.click();
		}
		Thread.sleep(5000);
		WebElement txt_EnteringOTP = driver.findElement(By.xpath("//*[@id='totpPin']"));
		txt_EnteringOTP.sendKeys(new Totp(getValueFrom_Properties("googleAuthKey")).now().toString());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='totpNext']")).click();
		Thread.sleep(5000);*/
		sendKeys(WebElements.readsoftUserName,getValueFrom_Properties("RSUsername"));
		sendKeys(WebElements.readsoftPassword,getValueFrom_Properties("RSPassword"));
		clickOnElement(WebElements.readsoftLoginBtn);
		return driver;

	}

	public void LoginToReadsoftAdmin() throws InterruptedException, IOException
	{
		driver.get(getValueFrom_Properties("ReadsoftUrl"));
		sendKeys(WebElements.readsoftUserName,getValueFrom_Properties("RSUsernameAdmin"));
		sendKeys(WebElements.readsoftPassword,getValueFrom_Properties("RSPassword"));
		clickOnElement(WebElements.readsoftLoginBtn);
	}
	public String DecryptPassword(String Password) {

		byte[] decodeBytes = Base64.getDecoder().decode(Password.getBytes());
		return new String(decodeBytes);
	}

	public String getValueFrom_Properties(String PropertyName) throws IOException {
		Properties cofig = new Properties();
		FileInputStream fis = new FileInputStream("src/test/resources/extent.properties");
		cofig.load(fis);
		return cofig.getProperty(PropertyName);
	}


	public void SwitchRole(String role) throws InterruptedException
	{
		clickOnElement(WebElements.myRoles);
		Thread.sleep(5000);
		String currentRole=getText(WebElements.currentRole);
		if(currentRole.equalsIgnoreCase(role)) {

		}
		else
		{
			if(role.equalsIgnoreCase("A/P manager")) {
				clickOnElement(WebElements.APTeamLead);
			}
			else if(role.equalsIgnoreCase("Wickes developer"))
			{
				clickOnElement(WebElements.wickesDeveloper);
			}
		}
		Thread.sleep(5000);


		String PageTitle=driver.getTitle();
		if(PageTitle.equalsIgnoreCase("Additional Authentication Required"))
		{
			String question =getText(WebElements.authenticationQn);

			if(question.contains("sixth grade"))
			{

				sendKeys(WebElements.authenticationAnswer,"Bharath");
			}

			clickOnElement(WebElements.authenticationSubmit);
			Thread.sleep(5000);

		}

	}
	
	public void switchToSandbox2()
	{
		hoverOnElement(WebElements.myRoles);
		clickOnElement(WebElements.SB2Account);
	}

	public WebElement findElement(By byLocator) {
		WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10)))
				.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		return element;
	}

	public void clickOnElement(By byLocator) {

		try {

			WebElement element = findElement(byLocator);
			waitForElements(element);
			element.click();

		} catch (Exception e) {

			Assert.fail("Element not found");

		}
	}

	public List<WebElement> findElements(By bylocator) {

		List<WebElement> element = null;
		try {
			element = driver.findElements(bylocator);
			Assert.assertTrue(!element.isEmpty());

		} catch (Exception e) {
			Assert.fail("Element not found");
		}
		return element;
	}

	public void waitForElements(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeDisabled(By byLocator)
	{

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(byLocator)));
	}

	public boolean checkElementPresent(By byLocator) {

		try {
			findElement(byLocator);
			Assert.assertEquals("The element " + byLocator + " " + "is displayed", true,
					findElement(byLocator).isDisplayed());
			return true;
		} catch (Exception e) {
			Assert.assertEquals("Element" + byLocator + " " + "is not visible", true, false);
			return false;
		}
	}

	public boolean checkElementEnable(By byLocator) {

		try {
			findElement(byLocator);
			// Assert.assertEquals( "The element " + byLocator +" "+ "is
			// displayed", true, findElement(byLocator).isEnabled());
			return true;
		} catch (Exception e) {
			// Assert.assertEquals("Element" + byLocator +" "+ "is not
			// visible",true, false);
			return false;
		}
	}

	public void sendKeys(By byLocator, CharSequence keysToSend) {

		try {

			WebElement element = findElement(byLocator);
			element.sendKeys(keysToSend);
		} catch (Exception e) {

			Assert.fail("Element not found");
		}
	}

	public String getText(By byLocator) {

		String text = null;
		try {

			WebElement element = findElement(byLocator);
			text = element.getText();
		} catch (Exception e) {

			Assert.fail("Element not found");

		}
		return text;
	}

	public void hoverOnElement(By byLocator)
	{
		try {
			Actions action = new Actions(driver);
			WebElement element = findElement(byLocator);
			action.moveToElement(element).perform();

		} catch (Exception e) {

			Assert.fail("Element not found");

		}
	}

	public void moveToElement(WebElement element )
	{
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();

		} catch (Exception e) {

			Assert.fail("Element not found");

		}
	}
	public void scrollToElement(By byLocator)
	{
		WebElement element=findElement(byLocator);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void uploadFile(String filePath) throws AWTException, InterruptedException
	{
		Thread.sleep(2000);
		Robot robot= new Robot();
		StringSelection FilePath=new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(FilePath, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public String getCsvFilePath(String filePath) throws IOException
	{
		String csvFilePath=getValueFrom_Properties(filePath);
		return csvFilePath;
	}


	public static Map<String,String> getTestDataInMap(String testDataFile,String sheetName,String testCaseId) throws Exception
	{
		Map<String,String> TestDataInMap=new TreeMap<String,String>();  
		String query=null;
		query=String.format("SELECT * FROM %s WHERE TestCaseId='%s'",sheetName,testCaseId);
		Fillo fillo=new Fillo();
		Connection conn=null;
		Recordset recordset=null;
		try
		{
			conn=fillo.getConnection(testDataFile);
			recordset=conn.executeQuery(query);
			while(recordset.next())
			{
				for(String field:recordset.getFieldNames())
				{
					TestDataInMap.put(field, recordset.getField(field));
				}
			}
		}
		catch(FilloException e)
		{
			e.printStackTrace();
			throw new Exception("Test data cannot be found");   
		}
		conn.close();
		return TestDataInMap;  
	}

	public void createCsvFile(String fileName) throws CsvException, IOException
	{
		String csvFilePath = getCsvFilePath(fileName);
		List<String[]> lines = null;
		String getfieldsToUpdate=Hooks.TestDataInMap.get("fieldsToUpdate");
		String[] fieldsToUpdate = getfieldsToUpdate.split(",");
		//  String[] fieldsToUpdate = {"Field1", "Field2"}; // Add the names of fields you want to update

		try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {

			lines = reader.readAll();

			// Assuming the first line contains the headers
			String[] headers = lines.get(0);

			// Find the indices of fields to update
			int[] fieldIndices = new int[fieldsToUpdate.length];
			for (int i = 0; i < fieldsToUpdate.length; i++) {
				for (int j = 0; j < headers.length; j++) {
					if (headers[j].equals(fieldsToUpdate[i])) {
						fieldIndices[i] = j;
						break;
					}
				}
			}
			System.out.println(fieldIndices);
			// Update values in each line
			for (int i = 1; i < lines.size(); i++) {
				String[] line = lines.get(i);
				for (int index : fieldIndices) {
					// Perform your update logic here
					String fieldName =headers[index];
					System.out.println(headers[index]);

					line[index] = Hooks.TestDataInMap.get(fieldName); // Replace "NewValue" with the updated value
				}
			}



		} catch (IOException e) {
			e.printStackTrace();
		}

		try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath),CSVWriter.DEFAULT_SEPARATOR,CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END)) {
			// Write the changes back to the CSV file
			writer.writeAll(lines);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void writeTestData(String sheetName, String testCaseId, Map<String, String> testData) throws Exception {
		String updateQuery = null;
		Fillo fillo = new Fillo();
		Connection conn = null;

		try {
			conn = fillo.getConnection("./src/test/resources/Test data.xlsx");

			// Create an update query based on the TestCaseId
			updateQuery = String.format("UPDATE %s SET ", sheetName);

			for (Map.Entry<String, String> entry : testData.entrySet()) {
				updateQuery += entry.getKey() + "='" + entry.getValue() + "', ";
			}

			// Remove the trailing comma and space
			updateQuery = updateQuery.substring(0, updateQuery.length() - 2);

			// Add the WHERE clause
			updateQuery += String.format(" WHERE TestCaseId='%s'", testCaseId);

			// Execute the update query
			conn.executeUpdate(updateQuery);

		} catch (FilloException e) {
			e.printStackTrace();
			throw new Exception("Error writing data to Excel");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}	

	   public File getLastModifiedFile(String directoryPath) {
	        File dir = new File(directoryPath);
	        File[] files = dir.listFiles();
	 
	        if (files != null && files.length > 0) {
	            File lastModifiedFile = files[0];
	            for (File file : files) {
	                if (file.lastModified() > lastModifiedFile.lastModified()) {
	                    lastModifiedFile = file;
	                }
	            }
	            return lastModifiedFile;
	        }
	        return null;
	    }
	   
	   public  Map<String, String> readCsvData(String filePath) throws Exception {

	        // Initialize a map to store field and value pairs
	        Map<String, String> dataMap = new HashMap<>();

	        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
	            // Read all records from the CSV file
	            List<String[]> records = csvReader.readAll();

	            // Assuming the first row contains field names
	            String[] fieldNames = records.get(0);

	            // Iterate through the records starting from the second row
	            for (int i = 1; i < records.size(); i++) {
	                String[] values = records.get(i);

	                // Iterate through field names and get corresponding values
	                for (int j = 0; j < fieldNames.length; j++) {
	                    String fieldName = fieldNames[j];
	                    String value = values[j];
	                    dataMap.put(fieldName, value);
	                }
	            }

	        } catch (IOException | CsvException e) {
	            e.printStackTrace();
	        }
			return dataMap;
	    }
	}
