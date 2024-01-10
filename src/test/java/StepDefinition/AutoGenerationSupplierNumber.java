package StepDefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.WebElements;
import Generic.genericFunction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class AutoGenerationSupplierNumber extends genericFunction {

	public static String vendorUrl;
	public static String vendorId;
	public static String secondVendorId;



	@Given("User is logged into the Netsuite Application with Wickes developer role")
	public void user_is_logged_into_the_netsuite_application_with_wickes_developer_role() throws Exception {
		driver=InitializeDriver();
		LaunchNetsuite();
		LoginToNetsuite();
	}


	@When("^User creates the \"([^\"]*)\" vendor$")
	public void user_creates_the_vendors(String vendorCategory) throws Exception {

		vendorUrl = createVendor(vendorCategory);

	}

	@When("User logs in with AP manager role")
	public void user_logs_in_with_a_p_manager_role() throws InterruptedException, IOException {
		tempdriver=LoginAsAPManager(tempdriver);
	}

	@When("User approves the vendor")
	public void user_approves_the_vendor() throws Exception {

		tempdriver.get(vendorUrl);
		tempdriver.findElement(WebElements.approveBtn).click();
		Thread.sleep(2000);
		//tempdriver.quit();

	}
	@When("User logs back into the Wickes developer role")
	public void user_logs_back_into_the_wickes_developer_role() throws InterruptedException, IOException {

		switchToDriver(driver);
		Thread.sleep(2000);
		driver.navigate().refresh();
		LoginToNetsuite();
	}

	@When("User gets the vendor ID")
	public void user_gets_the_vendor_id() {

		driver.get(vendorUrl);
		vendorId =getText(WebElements.supplierId);

	}

	@Then("^Vendor ID should start with \"([^\"]*)\".$")
	public void vendor_id_should_start_with(String supplierPrefix) {


		boolean checkBox=findElement(WebElements.supplierIdUpdatedCheckBox).getAttribute("alt").equalsIgnoreCase("checked");

		if(checkBox)
		{
			if((!vendorId.startsWith(supplierPrefix)) ) {

				Assert.fail("The newly created vendor does not have the correct prefix in supplier ID");
			}
		}
		else
		{
			Assert.fail("Supplier ID is not updated");
		}

	}

	@Given("User is in the Netsuite HomePage")
	public void user_is_in_the_netsuite_home_page() throws InterruptedException {
		Thread.sleep(1000);
		clickOnElement(WebElements.home);
	}

	@Given("User is in the Netsuite Home Page")
	public void user_is_in_the_netsuite_homepage() throws InterruptedException {
		Thread.sleep(5000);
		clickOnElement(WebElements.home);
	}

	@When("User creates the second {string} vendor")
	public void user_creates_the_second_vendor(String vendorCategory) throws InterruptedException {

		vendorUrl = createVendor(vendorCategory);

	}

	@When("User gets the second vendor ID")
	public void user_gets_the_second_vendor_id() {

		driver.get(vendorUrl);
		secondVendorId = getText(WebElements.supplierId);

	}


	@Then("Two newly created {string} vendors should have sequential numbers prefixed with {string}.")
	public void two_newly_created_stock_suppliers_should_have_sequential_numbers_prefixed_with(String category,String firstLetter) {

		if((!vendorId.startsWith(firstLetter)) || (!secondVendorId.startsWith(firstLetter))) {

			Assert.fail("Vendor ID doesn't have the correct Prefix");
		}

		int num1 = Integer.parseInt(vendorId.substring(1));
		int num2 = Integer.parseInt(secondVendorId.substring(1));


		if(num2-num1 != 1)
		{
			Assert.fail("Two newly created" + category+" vendors are not sequential");
		}

	}
	@When("User rejects the vendor with the reason")
	public void user_rejects_the_vendor_with_the_reason() {

		tempdriver.get(vendorUrl);
		tempdriver.findElement(WebElements.rejectBtn).click();
		tempdriver.findElement(WebElements.rejectionReason).sendKeys(Hooks.TestDataInMap.get("rejectReason"));
		tempdriver.findElement(WebElements.rejectionSaveBtn).click();

	}
	@Then("User verifies the rejected vendor record has the rejection reason")
	public void user_verifies_the_rejected_vendor_record_has_the_rejection_reason() throws InterruptedException {

		driver.navigate().refresh();
		clickOnElement(WebElements.approvalSubtab);
		Thread.sleep(2000);
		moveToElement(findElement(WebElements.rejectionReasonTxt));
		String rejectionReason=getText(WebElements.rejectionReasonTxt);
		if(!rejectionReason.equalsIgnoreCase(Hooks.TestDataInMap.get("rejectReason")))
		{
			Assert.fail("Rejection Reason is not displayed correctly");
		}

	}
	@When("User switches to the AP manager role")
	public void user_switches_to_the_ap_manager_role() {
		switchToDriver(tempdriver);
		tempdriver.navigate().refresh();
		if(tempdriver.getTitle().contains("Login access has been disabled for this role."))
		{

			tempdriver.findElement(WebElements.APManagerBtn).click();
		}

	}
	@Given("User is on the rejected stock vendor record")
	public void user_is_on_the_rejected_stock_vendor_record() {

	}
	@When("User updates the vendor record with correct details")
	public void user_updates_the_vendor_record_with_correct_details() throws InterruptedException {

		clickOnElement(WebElements.editBtn);
		findElement(WebElements.companyName).clear();
		sendKeys(WebElements.companyName,Hooks.TestDataInMap.get("newCompanyName"));
		Thread.sleep(1000);
		clickOnElement(WebElements.saveBtn);

	}
	@When("User resubmits the vendor record for approval")
	public void user_resubmits_the_vendor_record_for_approval() {
		clickOnElement(WebElements.vendorSubmitBtn);
	}

	@When("^User changes the vendor category to (.+)$")
	public void user_changes_the_vendor_category_to_stock(String vendorCategory) throws InterruptedException {

		clickOnElement(WebElements.editBtn);
		findElement(WebElements.categoryDropdwn).clear();
		Thread.sleep(2000);
		clickOnElement(WebElements.categoryDropdwn);
		Thread.sleep(2000);

		if(vendorCategory.equalsIgnoreCase("stock"))
		{
			clickOnElement(WebElements.stockCategory);
		}
		else if(vendorCategory.equalsIgnoreCase("Non stock"))
		{
			clickOnElement(WebElements.nonStockCategory);
		}
		else if(vendorCategory.equalsIgnoreCase("Installer")) {
			clickOnElement(WebElements.installerCategory);
		}
		Thread.sleep(1000);
		clickOnElement(WebElements.saveBtn);
	}
	@When("User creates the csv file with {string} vendor details")
	public void user_creates_the_csv_file_with_vendor_details(String vendorCategory) throws IOException {
		
		createCSVFile(vendorCategory,getCsvFilePath("supplierCSVFilePath"));
	}
	@When("User imports the csv file")
	public void user_imports_the_csv_file() throws InterruptedException, AWTException, IOException {
		Thread.sleep(1000); 
		hoverOnElement(WebElements.Setup);
		hoverOnElement(WebElements.importExport);
		clickOnElement(WebElements.savedCsvImports);
		clickOnElement(WebElements.vendorCreationSavedImport);
		Thread.sleep(1000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		WebElement selectBtn= driver.findElement(WebElements.selectBtn);
		js.executeScript("arguments[0].scrollIntoView(true);", selectBtn);
		Actions action = new Actions(driver);
		action.moveToElement(selectBtn).click().perform();

		uploadFile(getCsvFilePath("supplierCSVFilePath"));
		Thread.sleep(1000);
		clickOnElement(WebElements.nextBtn);
		Thread.sleep(1000);
		clickOnElement(WebElements.nextBtn);
		Thread.sleep(1000);
		clickOnElement(WebElements.fieldMappingnextBtn);
		Thread.sleep(1000);
		hoverOnElement(WebElements.saveAndRunBtn);
		clickOnElement(WebElements.RunBtn);
		driver.switchTo().alert().accept();

	}
	@When("User opens the {string} vendor record")
	public void user_opens_the_vendor_record(String string) throws InterruptedException {
		
		Thread.sleep(3000);
		clickOnElement(WebElements.home);
		Thread.sleep(2000);
		hoverOnElement(WebElements.Lists);
		hoverOnElement(WebElements.relationships);
		clickOnElement(WebElements.vendors);
		Thread.sleep(1000);
		List<WebElement> vendors=driver.findElements(WebElements.vendorsList);
		vendors.stream().
		filter(vendor -> vendor.findElement(WebElements.vendorNamesList).getText().equals(Hooks.TestDataInMap.get("CompanyName")))
		.findFirst()
		.ifPresent(vendor -> {WebElement viewButton = vendor.findElement(WebElements.vendorViewBtn);
		viewButton.click();
		});
		clickOnElement(WebElements.vendorSubmitBtn);
		Thread.sleep(2000);
		vendorUrl = driver.getCurrentUrl();

	}

	public String createVendor(String vendorCategory) throws InterruptedException
	{
		Thread.sleep(2000);
		
		hoverOnElement(WebElements.Lists);
		hoverOnElement(WebElements.relationships);
		clickOnElement(WebElements.vendors);
		clickOnElement(WebElements.newVendor);

		sendKeys(WebElements.companyName,Hooks.TestDataInMap.get("CompanyName"));
		Thread.sleep(2000);
		clickOnElement(WebElements.categoryDropdwn);
		Thread.sleep(2000);
		if(vendorCategory.equalsIgnoreCase("stock"))
		{

			clickOnElement(WebElements.stockCategory);
		}
		else if(vendorCategory.equalsIgnoreCase("Non stock"))
		{
			clickOnElement(WebElements.nonStockCategory);
		}
		else if(vendorCategory.equalsIgnoreCase("Installer")) {
			clickOnElement(WebElements.installerCategory);
		}
		sendKeys(WebElements.email,Hooks.TestDataInMap.get("email"));
		sendKeys(WebElements.phoneNo,Hooks.TestDataInMap.get("phoneNo"));
		clickOnElement(WebElements.subsidiaryDropdwn);
		Thread.sleep(2000);
		clickOnElement(WebElements.wickesSubsidiary);
		Thread.sleep(1000);

		if(!Hooks.TestDataInMap.get("defaultItem").equalsIgnoreCase(""))
		{
			clickOnElement(WebElements.financialSubtab);
			Thread.sleep(3000);
			sendKeys(WebElements.defaultItem,Hooks.TestDataInMap.get("defaultItem"));
			findElement(WebElements.defaultItem).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
		}

		clickOnElement(WebElements.saveBtn);
		clickOnElement(WebElements.vendorSubmitBtn);
		Thread.sleep(2000);
		String VendorUrl = driver.getCurrentUrl();
		return VendorUrl;
	}

	
	
	public String getValue(String field) throws IOException
	{
		return Hooks.TestDataInMap.get(field);
	}

	public void createCSVFile(String vendorCategory, String filePath) throws IOException
	{
		FileWriter writer = new FileWriter(filePath);
		writer.write("Name,Category,Individual,email,Phone,Subsidiary,Default item\n");
		writer.write(getValue("CompanyName")+","+vendorCategory+",F,"+getValue("email")+","+getValue("phoneNo")+",Wickes Group PLC : Wickes Group Holdings Limited : Wickes Building Supplies Limited,"+getValue("defaultItem"));
	/*	if(vendorCategory.equalsIgnoreCase("stock"))
		{
			writer.write(getValue("CompanyName")+",Stock,F,"+getValue("email")+","+getValue("phoneNo")+",Wickes Group PLC : Wickes Group Holdings Limited : Wickes Building Supplies Limited,"+getValue("defaultItem"));
		}
		else if(vendorCategory.equalsIgnoreCase("Non stock"))
		{
			writer.write(getValue("CompanyName")+",Non-Stock,F,"+getValue("email")+","+getValue("phoneNo")+",Wickes Group PLC : Wickes Group Holdings Limited : Wickes Building Supplies Limited,"+getValue("defaultItem"));
		}
		else if(vendorCategory.equalsIgnoreCase("Installer")) {
			writer.write(getValue("CompanyName")+",Installer,F,"+getValue("email")+","+getValue("phoneNo")+",Wickes Group PLC : Wickes Group Holdings Limited : Wickes Building Supplies Limited,"+getValue("defaultItem"));
		}*/
		writer.close();
	}

	
	public static void switchToDriver(WebDriver driver)
	{
		for(String WindowHandle:driver.getWindowHandles())
		{
			driver.switchTo().window(WindowHandle);
		}
	}

}
