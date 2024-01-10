package StepDefinition;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Generic.WebElements;
import Generic.genericFunction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class ReadsoftToNetsuite extends genericFunction {

	public static String defaultItem;

	@Given("User is logged into the Readsoft Application with AP Manager role")
	public void user_logs_into_readsoft() throws InterruptedException, IOException {
		Thread.sleep(2000);
if(driver==null) {
	System.out.println("driver is null");
		driver=InitializeDriver();
		LoginToReadsoft();
		Thread.sleep(2000);
		clickOnElement(WebElements.readsoftCookies);
}
else {
	System.out.println("driver is not null");
	switchWindow("Kofax");
	clickOnElement(WebElements.verify);
	Thread.sleep(1000);
}


	}

	@When("^User creates the \"([^\"]*)\"$")
	public void user_creates_the(String documenttype) throws InterruptedException {

		Thread.sleep(10000);
		clickOnElement(WebElements.pdfDocument);
		Thread.sleep(1000);
		clickOnElement(WebElements.startBtn);
		Thread.sleep(3000);

		String[] trackIdText=getText(WebElements.trackID).split(":");
		String trackId=trackIdText[1].trim();
		Thread.sleep(1000);

		WebElement documentType=findElement(WebElements.documentType);
		Select docType=new Select(documentType);
		if(documenttype.contains("invoice"))
		{
			docType.selectByValue("British expense invoices");
		}else if(documenttype.contains("credit")) {
			docType.selectByValue("British credit notes");
		}
		documentType.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		clickOnElement(WebElements.supplierName);
		Thread.sleep(2000);
		clickOnElement(WebElements.supplierReset);
		sendKeys(WebElements.supplierSearchBox,Hooks.TestDataInMap.get("supplier"));
		Thread.sleep(1000);
		findElement(WebElements.supplierSearchBox).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		clickOnElement(WebElements.supplierSearchResult);
		Thread.sleep(1000);
		clickOnElement(WebElements.supplierOkButton);

		Thread.sleep(4000);
		WebElement cc=driver.findElement(WebElements.readsoftCostCentre);
		cc.click();
		Thread.sleep(2000);
		sendKeys(WebElements.readsoftCostCentre1,Hooks.TestDataInMap.get("costCentre"));
		Thread.sleep(2000);
		findElement(WebElements.readsoftCostCentre1).sendKeys(Keys.ENTER);

		enterValue(WebElements.documentNumber,Hooks.TestDataInMap.get("invoiceNumber"));
		enterValue(WebElements.documentDate,Hooks.TestDataInMap.get("invoiceDate"));
		enterValue(WebElements.totalNetAmount,Hooks.TestDataInMap.get("totalNetAmount"));
		enterValue(WebElements.totalVatRate,Hooks.TestDataInMap.get("totalVatRate"));
		enterValue(WebElements.totalVatAmount,Hooks.TestDataInMap.get("totalVatAmount"));
		enterValue(WebElements.deliveryCost,"0.00");
		enterValue(WebElements.totalGrossAmount,Hooks.TestDataInMap.get("totalGrossAmount"));

		clickOnElement(WebElements.allLinesCheckBox);
		clickOnElement(WebElements.deleteLineItems);
		clickOnElement(WebElements.deleteOkBtn);
		
		
		int numberOfines=Integer.parseInt(Hooks.TestDataInMap.get("numberOfLines"));
			for(int i=0;i<numberOfines;i++)
			{
		        clickOnElement(WebElements.addLineItems);
		    }
		Thread.sleep(2000);
		List<WebElement> itemLines=findElements(WebElements.LineItemsRows);
		for(int i=0;i<itemLines.size();i++)
		{

			WebElement itemLine=itemLines.get(i);
			int lineNumber=i+1;
			enterLineValue(itemLine,WebElements.lineItemDescription,Hooks.TestDataInMap.get("itemDescription"+ lineNumber));
			enterLineValue(itemLine,WebElements.lineItemQuantity,Hooks.TestDataInMap.get("itemQuantity"+ lineNumber));
			enterLineValue(itemLine,WebElements.lineItemUnitPrice,Hooks.TestDataInMap.get("itemRate"+ lineNumber));
			enterLineValue(itemLine,WebElements.lineItemNetAmount,Hooks.TestDataInMap.get("itemNetAmount"+ lineNumber));
			enterLineValue(itemLine,WebElements.lineItemSupplierProductCode,Hooks.TestDataInMap.get("supplierProdCode"+ lineNumber));
			enterLineValue(itemLine,WebElements.lineItemWickesProductCode,Hooks.TestDataInMap.get("wickesProdCode"+ lineNumber));
		}
		WebElement currencyType=findElement(WebElements.currencyType);
		Select Currency=new Select(currencyType);
		Currency.selectByValue(Hooks.TestDataInMap.get("currency"));
		currencyType.sendKeys(Keys.ENTER);
		clickOnElement(WebElements.readsoftOkBtn);
		Thread.sleep(10000);
		clickOnElement(WebElements.processed);
		Thread.sleep(4000);
		clickOnElement(WebElements.trackIdTextBox);
		driver.findElement(WebElements.trackIdTextBox).sendKeys(trackId);
		findElement(WebElements.trackIdTextBox).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		clickOnElement(WebElements.readsoftRefreshBtn);
		boolean elementFound=false;
		int n=0;
		while(!elementFound)
		{ 
			if(n<15)
			{
				System.out.println(n);
				n++;

			try {	
				
				WebElement searchResultMsg=findElement(WebElements.DocSearchResults);
				System.out.println(searchResultMsg.isDisplayed());
				if(searchResultMsg.isDisplayed())
				{
					System.out.println("closed");
					elementFound=true;
				}
			}catch(Exception e)
			{
				clickOnElement(WebElements.readsoftRefreshBtn);
			}
		}else
		{
			Assert.fail("Document is not processed to Netsuite");
		}
	}	
}

	@When("User logs into Netsuite Application")
	public void user_logs_into_netsuite() throws InterruptedException, IOException {


		switchWindow("NetSuite");
		if(!driver.getTitle().contains("NetSuite"))
		{
			openNewTab();
			LaunchNetsuite();
			LoginToNetsuite();
			driver.navigate().refresh();
		}
		else
		{
			driver.navigate().refresh();
			if(driver.getTitle().contains("NetSuite Login"))
			{
				LoginToNetsuite();
			}
		}

	}

	@Then("User verifies the newly created {string} is present in Netsuite")
	public void user_opens_the_transaction(String docType) {

		sendKeys(WebElements.globalSearchBox,Hooks.TestDataInMap.get("invoiceNumber"));
		if(docType.contains("invoice")) {
			clickOnElement(WebElements.billsearchResult);
		}
		else if(docType.contains("credit")){
			clickOnElement(WebElements.billCreditsearchResult);
		}
	}

	@Given("User is in the readsoft HomePage")
	public void user_is_in_the_readsoft_home_page() throws InterruptedException {
		switchWindow("Kofax");
		clickOnElement(WebElements.verify);
		Thread.sleep(1000);
	}
	@Given("User logs into Readsoft application with Admin role")
	public void user_logs_into_readsoft_application_with_admin_role() throws InterruptedException, IOException {
		openNewTab();
		LoginToReadsoftAdmin();
		Thread.sleep(2000);
		clickOnElement(WebElements.readsoftCookies);
	}
	@When("User switches to the Readsoft application with Admin role")
	public void user_switches_to_readsoft_application_with_admin_role() throws InterruptedException, IOException {

		switchWindow("Kofax");
	}
	@When("User clears the filter criteria")
	public void user_clears_filter_criteria() throws InterruptedException, IOException {

		clickOnElement(WebElements.resetAllFilters);
	}
	@When("User updates the {string} vendor")
	public void user_updates_the_vendor(String string) throws InterruptedException {

		openVendorRecord();
		findElement(WebElements.companyName).clear();
		sendKeys(WebElements.companyName,Hooks.TestDataInMap.get("newCompanyName"));
		Thread.sleep(1000);
		clickOnElement(WebElements.saveBtn);
	}
	@When("User updates the {string} vendor to inactive")
	public void user_updates_the_vendor_to_inactive(String string) throws InterruptedException {

		openVendorRecord();
		Thread.sleep(5000);
		clickOnElement(WebElements.systemInformationSubtab);
		clickOnElement(WebElements.inactiveCheckBox);
		Thread.sleep(2000);
		clickOnElement(WebElements.saveBtn);

	}
	@Then("User verifies the inactive supplier is not present in Readsoft")
	public void user_verifies_the_inactive_supplier_is_not_present_in_readsoft() throws InterruptedException {

		searchSupplier(Hooks.TestDataInMap.get("CompanyName"),WebElements.InactiveSearchResult,"Inactive supplier is present in Readsoft");

	}

	@When("User navigates to the supplier master data")
	public void user_navigates_to_the_supplier_master_data() throws InterruptedException {

		clickOnElement(WebElements.explore);
		clickOnElement(WebElements.masterData);
		clickOnElement(WebElements.readsoftSuppliersPage);
		Thread.sleep(3000);


	}

	@Then("User verifies the {string} supplier created in Netsuite is sent to Readsoft")
	public void user_verifies_the_supplier_created_in_netsuite_is_sent_to_readsoft(String string) throws InterruptedException {

		searchSupplier(Hooks.TestDataInMap.get("CompanyName"),WebElements.readsoftSearchResult,"Newly created supplier is not available in Readsoft");
	}

	@Then("User verifies the {string} supplier updated in Netsuite is sent to Readsoft")
	public void user_verifies_the_supplier_updated_in_netsuite_is_sent_to_readsoft(String string) throws InterruptedException {

		searchSupplier(Hooks.TestDataInMap.get("newCompanyName"),WebElements.readsoftSearchResult,"Updated supplier is not available in Readsoft");

	}

	@When("User creates a new cost centre")
	public void user_creates_a_new_cost_centre() throws InterruptedException, IOException {

		sendKeys(WebElements.globalSearchBox,"cost centres > new");
		clickOnElement(WebElements.newCostCentre);

		sendKeys(WebElements.costCentreName,Hooks.TestDataInMap.get("costCentreName"));
		clickOnElement(WebElements.subsidiaryDropdwn);
		Thread.sleep(1000);
		clickOnElement(WebElements.wickesSubsidiary);

		clickOnElement(WebElements.costGroupDropDown);
		Thread.sleep(1000);
		clickOnElement(WebElements.selectCostGroup);
		clickOnElement(WebElements.businessFunctionDropDown);
		Thread.sleep(1000);
		clickOnElement(WebElements.selectBusinessFunction);
		Thread.sleep(1000);
		clickOnElement(WebElements.saveBtn);

		//	createCSVFile("C:\\Users\\902303\\eclipse-workspace\\FinanceAutomation\\src\\test\\resources\\TestData\\CostCentretestdata.CSV");
	}

	@When("User navigates to the location master data")
	public void user_navigates_to_the_location_master_data() throws InterruptedException {

		clickOnElement(WebElements.explore);
		clickOnElement(WebElements.masterData);
		clickOnElement(WebElements.readsoftlocationsPage);
		Thread.sleep(3000);

	}

	@Then("User verifies the newly created cost centre created in Netsuite is sent to Readsoft")
	public void user_verifies_the_newly_created_cost_centre_created_in_netsuite_is_sent_to_readsoft() throws InterruptedException {

		searchCostCentre(Hooks.TestDataInMap.get("costCentreName"),WebElements.readsoftSearchResult,"Newly created cost centre is not available in Readsoft");
	}

	@When("User updates the cost centre")
	public void user_updates_the_cost_centre() throws InterruptedException {

		openCostCentreRecord();
		findElement(WebElements.costCentreName).clear();
		sendKeys(WebElements.costCentreName,Hooks.TestDataInMap.get("newCostCentreName"));
		Thread.sleep(1000);
		clickOnElement(WebElements.saveBtn);		
	}

	@Then("User verifies the cost centre updated in Netsuite is sent to Readsoft")
	public void user_verifies_the_cost_centre_updated_in_netsuite_is_sent_to_readsoft() throws InterruptedException {
		searchCostCentre(Hooks.TestDataInMap.get("newCostCentreName"),WebElements.readsoftSearchResult,"Updated cost centre is not available in Readsoft");
	}

	@When("User updates the cost centre to inactive")
	public void user_updates_the_cost_centre_to_inactive() throws InterruptedException {

		openCostCentreRecord();
		clickOnElement(WebElements.inactiveCheckBox);		
		clickOnElement(WebElements.saveBtn);
	}

	@Then("User verifies the inactive cost centre is not present in Readsoft")
	public void user_verifies_the_inactive_cost_centre_is_not_present_in_readsoft() throws InterruptedException {
		searchCostCentre(Hooks.TestDataInMap.get("newCostCentreName"),WebElements.InactiveSearchResult,"Inactive cost centre is present in Readsoft");
	}

	@Given("User is in the GFR invoice record")
	public void user_is_in_the_gfr_invoice_record() throws InterruptedException, IOException {
	}

	@Given("User is in the GFR credit record")
	public void user_is_in_the_gfr_credit_record() {

	}

	@Given("User is in the GNFR invoice record")
	public void user_is_in_the_gnfr_invoice_record() {

	}

	@Given("User is in the GFR import invoice record")
	public void user_is_in_the_gfr_import_invoice_record() {

	}

	@Given("User is in the GNFR credit record")
	public void user_is_in_the_gnfr_credit_record() {

	}


	@When("User gets the default item from the supplier record")
	public void user_gets_the_default_item_from_the_supplier_record() {
		defaultItem=getDefaultItem();
	}

	@When("User navigates to the system information subtab")
	public void user_navigates_to_the_system_information_subtab() throws InterruptedException {
		
		Thread.sleep(1000);
		clickOnElement(WebElements.systemInformationSubtab);
	}

	@Then("User verifies the item replacement is triggered")
	public void user_verifies_the_item_replacement_is_triggered() {

		moveToElement(findElement(WebElements.itemReplacementCheckBox));
		boolean itemReplacementTriggeredcheckBox=findElement(WebElements.itemReplacementCheckBox).getAttribute("alt").equalsIgnoreCase("checked");
		if(!itemReplacementTriggeredcheckBox)
		{
			Assert.fail("AP coding solution script to replace the item is not triggered");
		}
	}

	@Then("User verifies the item is replaced by the default item in the expenses and items subtab")
	public void user_verifies_the_item_is_repalaced_by_the_default_item_in_the_expenses_and_items_subtab() throws InterruptedException {


		Thread.sleep(5000);
		clickOnElement(WebElements.expenseAndItemsSubtab);
		Thread.sleep(2000);
		scrollToElement(WebElements.clickItem);
		List<WebElement> lineItems=driver.findElements(WebElements.invoiceItemsList);

		boolean isMatching= lineItems.stream().skip(1).map(WebElement::getText).allMatch(text-> text.equals(defaultItem));

		if(!isMatching)
		{
			Assert.fail("Item is not replaced by the default item from the supplier record");
		}
	}
	@Then("User verifies the item is replaced by the default item in the items subtab for the credit")
	public void user_verifies_the_item_is_repalaced_by_the_default_item_in_the_items_subtab() throws InterruptedException {


		Thread.sleep(2000);
		clickOnElement(WebElements.expenseAndItemsSubtab);

		List<WebElement> lineItems=driver.findElements(WebElements.creditItemsList);

		boolean isMatching= lineItems.stream().skip(1).map(WebElement::getText).allMatch(text-> text.equals(defaultItem));

		if(!isMatching)
		{
			Assert.fail("Item is not replaced by the default item from the supplier record");
		}
	}
	
	@When("^User opens the \"([^\"]*)\" record$")
	public void User_opens_the_GFR_invoice_record(String docType) throws InterruptedException {
		
		sendKeys(WebElements.globalSearchBox,Hooks.TestDataInMap.get("invoiceNumber"));
		if(docType.contains("invoice")) {
			clickOnElement(WebElements.billsearchResult);
		}
		else if(docType.contains("credit")){
			clickOnElement(WebElements.billCreditsearchResult);
		}
		
	}
	
	@Then("User verifies the Line level data of {string} is stored in line object field in json format")
	public void user_verifies_the_line_level_data_of_is_stored_in_line_object_field_in_json_format(String string)
	{
		
		String OriginalLineData=createLineJson();
		System.out.println(OriginalLineData);
		clickOnElement(WebElements.systemInformationSubtab);
		String lineObject=getText(WebElements.originalLineObject);
		System.out.println(lineObject);
		if(!lineObject.equalsIgnoreCase(OriginalLineData))
		{
			Assert.fail("Reasoft Original line level data is not store in line object field correctly");
		}
		
	}
	
	
	@When("User opens the record in edit mode")
	public void user_opens_the_record_in_edit_mode() throws InterruptedException {
		
		driver.get("https://6087331-sb3.app.netsuite.com/app/accounting/transactions/vendbill.nl?id=4337248&whence=");
		clickOnElement(WebElements.editBtn);
		clickOnElement(WebElements.vendor);
		waitForElementToBeDisabled(WebElements.postingPeriod);
	   
	}
	@When("User navigates to the expense and item subtab")
	public void user_navigates_to_the_expense_and_item_subtab() {
		
		clickOnElement(WebElements.expenseAndItemsSubtab);
	  
	}

	@When("User updates the item and saves the record")
	public void user_updates_the_item_and_saves_the_record() throws InterruptedException {
		
		clickOnElement(WebElements.clickItem);
		sendKeys(WebElements.enterItem,Hooks.TestDataInMap.get("updateItem"));
		clickOnElement(WebElements.billAddBtn);
		clickOnElement(WebElements.clickRate);
		clickOnElement(WebElements.enterRate);
		findElement(WebElements.enterRate).clear();
		sendKeys(WebElements.enterRate,Hooks.TestDataInMap.get("itemRate1"));
		clickOnElement(WebElements.saveBtn);
	   
	}

	@Then("User verifies the {string} got saved with the updated item")
	public void user_verifies_the_got_saved_with_the_updated_item(String string) {
		
		clickOnElement(WebElements.expenseAndItemsSubtab);
	    scrollToElement(WebElements.clickItem);
		String item=getText(WebElements.clickItem);
		System.out.println(item);
		if(!item.equalsIgnoreCase("GOODS IN TRANSIT"))
		{
			Assert.fail("The item is not updated correctly");
		}
		
	}



	public void searchSupplier(String supplier,By searchResultElement,String errorMsg) throws InterruptedException
	{
		clickOnElement(WebElements.synchronizeBtn);
		Thread.sleep(2000);
		sendKeys(WebElements.readsoftSupplierSearch,supplier);
		findElement(WebElements.readsoftSupplierSearch).sendKeys(Keys.ENTER);

		boolean elementFound=false;
		int n=0;
		while(!elementFound)
		{ 
			if(n<20)
			{
				System.out.println(n);
				n++;
				try {	
					WebElement supplierResult=driver.findElement(searchResultElement);
					System.out.println(supplierResult.isDisplayed());
					if(supplierResult.isDisplayed())
					{
						elementFound=true;
					}
				}catch(Exception e)
				{
					Thread.sleep(3000);
					System.out.println("refresh");
					findElement(WebElements.readsoftSupplierSearch).sendKeys(Keys.ENTER);
				}

			}
			else
			{
				Assert.fail(errorMsg);
			}
		}	
	}

	public void searchCostCentre(String costCentre,By searchResultElement,String errorMsg) throws InterruptedException
	{
		clickOnElement(WebElements.synchronizeBtn);
		Thread.sleep(2000);
		sendKeys(WebElements.readsoftLocationSearch,costCentre);
		findElement(WebElements.readsoftLocationSearch).sendKeys(Keys.ENTER);

		boolean elementFound=false;
		int n=0;
		while(!elementFound)
		{ 
			if(n<20)
			{
				System.out.println(n);
				n++;
				try {	
					WebElement supplierResult=driver.findElement(searchResultElement);
					System.out.println(supplierResult.isDisplayed());
					if(supplierResult.isDisplayed())
					{
						elementFound=true;
					}
				}catch(Exception e)
				{
					Thread.sleep(3000);
					System.out.println("refresh");
					findElement(WebElements.readsoftLocationSearch).sendKeys(Keys.ENTER);
				}

			}
			else
			{
				Assert.fail(errorMsg);
			}
		}	
	}

	public void enterValue(By element,String value) throws InterruptedException
	{

		findElement(element).sendKeys(Keys.CONTROL+"a");
		findElement(element).sendKeys(Keys.DELETE);
		sendKeys(element,value);
		findElement(element).sendKeys(Keys.ENTER);
	}

	public void enterLineValue(WebElement itemLine,By element,String value)
	{
		itemLine.findElement(element).sendKeys(Keys.CONTROL+"a");
		itemLine.findElement(element).sendKeys(Keys.DELETE);
		itemLine.findElement(element).sendKeys(value);
		itemLine.findElement(element).sendKeys(Keys.ENTER);
	}

	public void openNewTab()
	{
		driver.switchTo().newWindow(WindowType.TAB);
	}

	public void openVendorRecord()
	{
		sendKeys(WebElements.globalSearchBox,Hooks.TestDataInMap.get("CompanyName"));
		clickOnElement(WebElements.vendorsearchResult);
		clickOnElement(WebElements.editBtn);
	}
	public void openCostCentreRecord() throws InterruptedException
	{
		sendKeys(WebElements.globalSearchBox,"cost centres > new");
		clickOnElement(WebElements.newCostCentre);
		clickOnElement(WebElements.listButton);
		List<WebElement> costCentres=driver.findElements(WebElements.costCentresList);
		costCentres.stream().
		filter(costCentre -> costCentre.findElement(WebElements.costCentreNames).getText().equals(Hooks.TestDataInMap.get("costCentreName")))
		.findFirst()
		.ifPresent(costCentre -> {WebElement editButton = costCentre.findElement(WebElements.costCentreEditBtn);
		editButton.click();
		});

	}
	public void switchWindow(String title)
	{
		Set<String> windowHandles=driver.getWindowHandles();
		//System.out.println("1");
		for(String windowHandle:windowHandles ) {
			//System.out.println("2");
			driver.switchTo().window(windowHandle);
			//System.out.println(driver.getTitle());
			if(driver.getTitle().contains(title))
			{
				//System.out.println("3");
				break;
			}
		}
	}

	public String getDefaultItem()
	{

		clickOnElement(WebElements.clickVendorId);
		clickOnElement(WebElements.financialSubtab);
		findElement(WebElements.getDefaultItem);
		String cisLevel=getText(WebElements.getDefaultItem);
		driver.navigate().back();
		return cisLevel;

	}
	
	public String createLineJson()
	{
		 	
		 	 String json = "{\"Line 1\":{"
		 	            + "\"Item Name\":\"" + "Default DO NOT USE" + "\","
		 	            + "\"Description\":\"" + Hooks.TestDataInMap.get("itemDescription1") + "\","
		 	            + "\"Quantity\":" + Hooks.TestDataInMap.get("itemQuantity1") + ","
		 	            + "\"Rate\":" + Hooks.TestDataInMap.get("itemRate1") + ","
		 	            + "\"Amount\":" + Hooks.TestDataInMap.get("itemNetAmount1") + ","
		 	            + "\"TaxCode Name\":\"" + "VAT:S-GB" + "\","
		 	            + "\"Wickes ProductCode\":\""+ Hooks.TestDataInMap.get("wickesProdCode1") + "\","
		 	            + "\"Product Code\":\"" + Hooks.TestDataInMap.get("supplierProdCode1") + "\","
		 	            + "\"locationName\":\"" + "9000 General Cost Centre"+ "\""
		 	            + "}}";
		 	 
		 	 
		 	    return json;
		 }
		
		
		
	}


