package StepDefinition;

import java.awt.AWTException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.opencsv.exceptions.CsvException;

import Generic.WebElements;
import Generic.genericFunction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CSVImport extends genericFunction {
	
	
	public static String internalId;
	public static String stagingTableRecordId;
	
	@Given("User is logged into the Netsuite Sandbox2 with Wickes developer role")
	public void user_is_logged_into_the_netsuite_sandbox2_with_wickes_developer_role() throws Exception {
		driver=InitializeDriver();
		LaunchNetsuite();
		LoginToNetsuite();
		switchToSandbox2();
	}
	@When("User navigates to the CSV Imports folder in the File cabinet")
	public void user_navigates_to_the_csv_imports_folder_in_the_file_cabinet() throws InterruptedException {
		

		Thread.sleep(1000);
		hoverOnElement(WebElements.documents);
		hoverOnElement(WebElements.files);
		clickOnElement(WebElements.fileCabinet);
		clickOnElement(WebElements.csvImportsFolder);
		
	}

	@When("User opens the {string} folder")
	public void user_opens_the_folder(String FolderName) {
		
	    findElement(By.xpath("//a[contains(text(),'"+FolderName+"')]")).click();
	}


	@When("User uploads the {string} file")
	public void user_uploads_the_file(String fileName) throws AWTException, InterruptedException, IOException {
	
		
		Thread.sleep(2000);
		driver.findElement(WebElements.addFileBtn).sendKeys(getCsvFilePath(fileName));
		//uploadFile(getCsvFilePath("TCVendorBillCSVFilePath"));
		
		
	    
	}
	
	
	@When("User executes the {string} script to import the files")
	public void user_executes_the_script_to_import_the_files(String script) throws InterruptedException {
		Thread.sleep(2000);
		sendKeys(WebElements.globalSearchBox,"NS | MR | Import Files");
		clickOnElement(WebElements.scriptsearchResult);
		clickOnElement(WebElements.deploymentsSubtab);  
		  
	    findElement(By.xpath("//a[contains(text(),'"+script+"')]")).click();
	    clickOnElement(WebElements.editBtn);
		  hoverOnElement(WebElements.saveDropdown);
		  clickOnElement(WebElements.saveAndExecute);
	}


	@When("User waits for the script run to complete")
	public void user_waits_for_the_script_run_to_complete() {
		

		
		String isFilterOpen=findElement(WebElements.expandFilters).getAttribute("aria-expanded");
		if(isFilterOpen.equals("false"))
		{
		clickOnElement(WebElements.expandFilters);
		}
		findElement(WebElements.scriptType).clear();
		sendKeys(WebElements.scriptType,"- All -");
		
		findElement(WebElements.deploymentId).clear();
		sendKeys(WebElements.deploymentId,"- All -");
		findElement(WebElements.deploymentId).sendKeys(Keys.ENTER);
		
		Boolean scriptStatusPending=true;
		  while(scriptStatusPending) {
			  String scriptStatus= getText(WebElements.MapReduceScriptStatus);
			  if(scriptStatus.equalsIgnoreCase("Complete"))
			  {
				  scriptStatusPending=false;
			  }
			  else
			  {
				  clickOnElement(WebElements.refreshBtn);
			  }
		  } 
	    
	}


	@Then("User opens the {string}")
	public void user_opens_the(String TransactionRecord) throws InterruptedException {
		
		
		if(TransactionRecord.contains("bill")) {
			sendKeys(WebElements.globalSearchBox,Hooks.TestDataInMap.get("tranId"));
			clickOnElement(WebElements.billsearchResult);
		}
		else if(TransactionRecord.contains("credit")){
			sendKeys(WebElements.globalSearchBox,Hooks.TestDataInMap.get("tranId"));
			clickOnElement(WebElements.billCreditsearchResult);
		}
		else if(TransactionRecord.contains("Journal")){
			Thread.sleep(2000);
			clickOnElement(WebElements.Transactions);
			clickOnElement(WebElements.expandAll);
			clickOnElement(WebElements.makeJournalEntries);
			clickOnElement(WebElements.listButton);
			Thread.sleep(2000);
			try {
			driver.switchTo().alert().accept();
			}
			catch(NoAlertPresentException e) {
				
			}
			clickOnElement(WebElements.lastCreatedJournal);
			
			System.out.println(getText(WebElements.JournalExternalId));
			if(!getText(WebElements.JournalExternalId).equals(Hooks.TestDataInMap.get("External ID")))
			{
				Assert.fail("Journal Entery is not created for the data sent from didos");
			}
			
			
		}
	    
	}
	@When("User creates the {string} file")
	public void user_creates_the_tc_vendor_bill_files(String fileName) throws CsvException, IOException {
	 
		createCsvFile(fileName);
	}
	
	@When("User creates the {string} file with the internal ID of the stock bill")
	public void user_creates_the_didos_vendor_bill_files(String fileName) throws CsvException, IOException {
	 
		FileWriter writer = new FileWriter(getCsvFilePath(fileName));
		writer.write("Internal ID,DIDOS Verified\n");
		writer.write(internalId+",TRUE");
		writer.close();
	}
	
	@When("User creates the {string} file with the invalid internal ID of the stock bill")
	public void user_creates_the_didos_vendor_bill_files_invalid(String fileName) throws CsvException, IOException {
	 
		FileWriter writer = new FileWriter(getCsvFilePath(fileName));
		writer.write("Internal ID,DIDOS Verified\n");
		writer.write(Hooks.TestDataInMap.get("invalidInternalId")+",TRUE");
		writer.close();
	}
	
	@When("User gets the internal ID of the bill")
	public void user_gets_the_internal_id_of_the_bill() {
	    
		String billUrl=driver.getCurrentUrl();
		String pattern="id=(\\d+)";
    	java.util.regex.Pattern r=java.util.regex.Pattern.compile(pattern);
    	java.util.regex.Matcher m=r.matcher(billUrl);
    	
    	if(m.find()) {
    		internalId=m.group(1);
        	System.out.println(internalId);
    	}
    	
	}
	
	@Then("User verifies the bill status is updated to approved")
	public void user_verifies_the_bill_status_is_updated_to_approved() {
	   
		String approvalStatus=getText(WebElements.approvalStatus);
		if(!approvalStatus.equalsIgnoreCase("Approved"))
		{
			Assert.fail("The vendor bill is not approved");
		}
		
		clickOnElement(WebElements.gfrDataSubtab);
		scrollToElement(WebElements.didosVerifiedCheckBox);
		boolean didosVerifiedCheckBox=findElement(WebElements.didosVerifiedCheckBox).getAttribute("alt").equalsIgnoreCase("checked");
		if(!didosVerifiedCheckBox)
		{
			Assert.fail("Didos verified checkbox is not True");
		}
		
	}
	@Then("User verifies the didos verified is changed to true in Credit")
	public void user_verifies_the_didosVerified() {
	  
		
		clickOnElement(WebElements.gfrDataSubtab);
		scrollToElement(WebElements.didosVerifiedCheckBox);
		boolean didosVerifiedCheckBox=findElement(WebElements.didosVerifiedCheckBox).getAttribute("alt").equalsIgnoreCase("checked");
		if(!didosVerifiedCheckBox)
		{
			Assert.fail("Didos verified checkbox is not True");
		}
		
	}
	
	@When("^User opens the (.+) list$")
	public void user_opens_the_vendor_bill_credit_staging_table_main_list(String stagingTableName) throws InterruptedException {
		sendKeys(WebElements.globalSearchBox,stagingTableName);
		findElement(By.xpath("//span[text()='"+stagingTableName+"']/preceding-sibling::span[contains(text(),'Page')]")).click();
	}
	@When("^User opens (.+) new record$")
	public void user_opens_vendor_bill_credit_staging_table_main_record(String stagingTableName ) {
	    
		clickOnElement(WebElements.newStagingTableRecordBtn);
	}
	
	
	@When("^User enters the necessary details in the (.+) record$")
	public void user_enters_the_necessary_details_in_the_staging_table_record(String stagingTableName) {
	    
		
		if(stagingTableName.equalsIgnoreCase("Vendor Bill/Credit Staging Table (Main)")) {
		sendKeys(WebElements.externalIdStagingTable,Hooks.TestDataInMap.get("externalId"));
		sendKeys(WebElements.tranIDStagingTable,Hooks.TestDataInMap.get("tranId"));
		sendKeys(WebElements.vendorStagingTable,Hooks.TestDataInMap.get("vendor"));
		sendKeys(WebElements.sourceStagingTable,Hooks.TestDataInMap.get("source"));
		sendKeys(WebElements.locationStagingTable,Hooks.TestDataInMap.get("location"));
		sendKeys(WebElements.storeRefdStagingTable,Hooks.TestDataInMap.get("storeRef"));
		sendKeys(WebElements.subsidiaryStagingTable,Hooks.TestDataInMap.get("subsidiary"));
		sendKeys(WebElements.currencyStagingTable,Hooks.TestDataInMap.get("currency"));
		sendKeys(WebElements.exchangeRateStagingTable,Hooks.TestDataInMap.get("exchangeRate"));
		sendKeys(WebElements.poRefStagingTable,Hooks.TestDataInMap.get("poRef"));
		sendKeys(WebElements.tranDateStagingTable,Hooks.TestDataInMap.get("tranDate"));
		sendKeys(WebElements.dueDateStagingTable,Hooks.TestDataInMap.get("dueDate"));
		sendKeys(WebElements.memoStagingTable,Hooks.TestDataInMap.get("memo"));
		sendKeys(WebElements.grossValueStagingTable,Hooks.TestDataInMap.get("grossValue"));
		sendKeys(WebElements.tranTypeStagingTable,Hooks.TestDataInMap.get("tranType"));
		}
		else if(stagingTableName.equalsIgnoreCase("Vendor Bill/Credit Staging Table (Line)"))
		{
			sendKeys(WebElements.itemStagingTable,Hooks.TestDataInMap.get("item"));
			sendKeys(WebElements.productCodeStagingTable,Hooks.TestDataInMap.get("productCode"));
			sendKeys(WebElements.descriptionStagingTable,Hooks.TestDataInMap.get("description"));
			sendKeys(WebElements.quantityStagingTable,Hooks.TestDataInMap.get("quantity"));
			sendKeys(WebElements.rateStagingTable,Hooks.TestDataInMap.get("rate"));
			sendKeys(WebElements.taxCodeStagingTable,Hooks.TestDataInMap.get("taxCode"));
		}
		
		else if(stagingTableName.equalsIgnoreCase("Vendor Bill/Credit Staging Table (Edit)"))
		{
			sendKeys(WebElements.internalIdStagingTable,Hooks.TestDataInMap.get("internalId"));
			sendKeys(WebElements.didosVerifiedStagingTable,Hooks.TestDataInMap.get("didosVerified"));
		}
		else if(stagingTableName.equalsIgnoreCase("Journal CSV Staging Table (Main)"))
		{
			sendKeys(WebElements.journalexternalIdStagingTable,Hooks.TestDataInMap.get("externalId"));
			sendKeys(WebElements.journalsourceStagingTable,Hooks.TestDataInMap.get("source"));
			sendKeys(WebElements.journalstoreRefdStagingTable,Hooks.TestDataInMap.get("storeRef"));
			sendKeys(WebElements.journalsubsidiaryStagingTable,Hooks.TestDataInMap.get("subsidiary"));
			sendKeys(WebElements.journalcurrencyStagingTable,Hooks.TestDataInMap.get("currency"));
			sendKeys(WebElements.journalmemoStagingTable,Hooks.TestDataInMap.get("memo"));
			sendKeys(WebElements.journaldelNoteStagingTable,Hooks.TestDataInMap.get("delnoteNo"));
			sendKeys(WebElements.journalorderNoStagingTable,Hooks.TestDataInMap.get("orderNo"));
			sendKeys(WebElements.journalsupplierCodeStagingTable,Hooks.TestDataInMap.get("vendor"));
			sendKeys(WebElements.journalgrnoStagingTable,Hooks.TestDataInMap.get("grNo"));
			sendKeys(WebElements.journalTypeStagingTable,Hooks.TestDataInMap.get("journalType"));
			sendKeys(WebElements.journalTransactionTypeStagingTable,Hooks.TestDataInMap.get("tranType"));
		}
		else if(stagingTableName.equalsIgnoreCase("Journal CSV Staging Table (Line)"))
		{
			sendKeys(WebElements.journalAccountStagingTable,Hooks.TestDataInMap.get("ItemLineAccount"));
			sendKeys(WebElements.journalcreditAmountStagingTable,Hooks.TestDataInMap.get("ItemLinecreditAmount"));
			sendKeys(WebElements.journalLocationTypeStagingTable,Hooks.TestDataInMap.get("location"));
		}
	}
	
	@When("User saves the record")
	public void user_saves_the_record() {
	    clickOnElement(WebElements.saveBtn);
	}
	
	@Then("User verifies the Netsuite is showing error message")
	public void user_verifies_the_netsuite_is_showing_error_message() throws InterruptedException {
		
		String errorMsg=getText(WebElements.stagingtableErrorMsg);
		verifyErrorMsg(errorMsg);
		Thread.sleep(2000);
	    clickOnElement(WebElements.gobackBtn);
	}
	
	@Then("User verifies the error message in the csv file")
	public void user_verifies_the_errorMsg_in_csvFile() throws Exception {
		
		  File lastModifiedFile = getLastModifiedFile("C:\\Users\\902303\\Downloads");
		  System.out.println(lastModifiedFile.getPath());
		  Map<String,String> DataInMap = readCsvData(lastModifiedFile.getPath());
		  System.out.println(DataInMap.get("Error").trim());
		  verifyErrorMsg(DataInMap.get("Error").trim());
	
	}
	private void verifyErrorMsg(String errorMsg) {
		
		if(!errorMsg.equalsIgnoreCase("You cannot create staging table custom records; all records must be created by CSV import from external systems."))
		{
			Assert.fail("The error Message displayed is not correct");
		}
	}
	
	@When("^User creates the record in (.+) using csv import$")
	public void user_creates_the_record_in_vendor_bill_credit_staging_table_main_using_csv_import(String stagingTableName) throws InterruptedException, AWTException {
	 
		Thread.sleep(1000);
		hoverOnElement(WebElements.Setup);
		hoverOnElement(WebElements.importExport);
		clickOnElement(WebElements.savedCsvImports);
		driver.findElement(By.xpath("//a[text()='"+stagingTableName+" Test']")).click();
		Thread.sleep(1000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		WebElement selectBtn= driver.findElement(WebElements.selectBtn);
		js.executeScript("arguments[0].scrollIntoView(true);", selectBtn);
		Actions action = new Actions(driver);
		action.moveToElement(selectBtn).click().perform();
		String fileName=stagingTableName.replaceAll("[\\s/()]+", "");
		uploadFile("C:\\Users\\902303\\eclipse-workspace\\FinanceAutomation\\src\\test\\resources\\TestData\\"+fileName+".csv");
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
	
	@When("User verifies the job status of the import")
	public void user_verifies_the_job_status_of_the_import() {
		clickOnElement(WebElements.importJobStatusBtn);

		Boolean scriptStatusPending=true;
		  while(scriptStatusPending) {
			  String scriptStatus= getText(WebElements.importJobStatus);
			  if(scriptStatus.equalsIgnoreCase("Complete"))
			  {
				  scriptStatusPending=false;
				  if(!getText(WebElements.importJobMessage).equalsIgnoreCase("0 of 1 records imported successfully"))
				  {
					  Assert.fail("The record is created in the staging table");
				  }
			  }
			  else
			  {
				  clickOnElement(WebElements.refreshBtn);
			  }
		  } 
		
		
	}

	@When("User downloads the csv response file of the import")
	public void user_downloads_the_csv_response_file_of_the_import() throws InterruptedException {
		Thread.sleep(1000);
		clickOnElement(WebElements.csvResponse);
		Thread.sleep(5000);
	}

	@When("User opens the {string} saved search")
	public void user_opens_the_saved_search(String savedSearchName) throws InterruptedException {
		Thread.sleep(1000);	
		if(!driver.getTitle().contains("Home"))
		{
		clickOnElement(WebElements.home);
		}
		findElement(By.xpath("//a[contains(text(),'"+savedSearchName+"')]")).click();
		
		try {
			if(findElement(WebElements.popupBox).isDisplayed())
			{
				clickOnElement(WebElements.popupCheckBox);
				clickOnElement(WebElements.popupCloseBtn);
			}
		}
		catch(Exception e){

		}
	}
	@Then("User verifies the Custom Record entry is created with error message for the failed records")
	public void user_verifies_the_custom_record_entry() throws Exception {
		
		clickOnElement(WebElements.dateCreatedHeader);
		clickOnElement(WebElements.dateCreatedHeader);
		clickOnElement(WebElements.lastCreatedCsvHoldRecord);
		
		if(!getText(WebElements.errorMessage).equalsIgnoreCase(Hooks.TestDataInMap.get("errorMessage")))
		{
			Assert.fail("Error Message displayed is not correct");
		}
	
	}
	
	@When("User gets the staging table record ID")
	public void user_gets_the_staging_table_record_id() {
	    
		clickOnElement(WebElements.dateCreatedHeader);
		clickOnElement(WebElements.dateCreatedHeader);
		clickOnElement(WebElements.lastCreatedCsvHoldRecord);
		stagingTableRecordId=getText(WebElements.csvImportStagingTableRecordId);
	}

	@When("User opens the staging table record")
	public void user_opens_the_staging_table_record() {
	    
		String id =findElement(WebElements.InternalIdColumnNo).getAttribute("id");
		int columnNo=Integer.parseInt(id.replaceAll("\\D+", ""));
		
		System.out.println(columnNo + stagingTableRecordId);
		System.out.println("td:nth-child("+columnNo+")");
		
		List<WebElement> vendors=driver.findElements(WebElements.stagingTableRecordList);
		vendors.stream().
		filter(vendor -> vendor.findElement(By.cssSelector("td:nth-child("+columnNo+")")).getText().equals(stagingTableRecordId))
		.findFirst()
		.ifPresent(vendor -> {WebElement viewButton = vendor.findElement(WebElements.stagingTableviewButton);
		viewButton.click();
		});
		
	}

	@Then("User verifies the staging table holds the value of the failed {string} record")
	public void user_verifies_the_staging_table_holds_the_value_of_the_failed_vendor_bill_credit_record(String TransactionType) {
	   
		if(TransactionType.contains("bill/credit"))
		{
		String externalId=getText(WebElements.StagingTableExternalIdValue);
		String tranId=getText(WebElements.StagingTableTranIdValue);
		String vendor=getText(WebElements.StagingTableVendorName);
		
		if(!(externalId.equals(Hooks.TestDataInMap.get("externalId")) && tranId.equals(Hooks.TestDataInMap.get("tranId"))&& vendor.equals(Hooks.TestDataInMap.get("vendor"))))
		{
			Assert.fail("The failed record is not stored in the staging table");
		}
		}
		else if(TransactionType.contains("journal entry"))
		{
			String externalId=getText(WebElements.StagingTableExternalIdValue);
			String supplierNumber=getText(WebElements.StagingTablejrnlsSupplierNumber);
			if(!(externalId.equals(Hooks.TestDataInMap.get("External ID")) && supplierNumber.equals(Hooks.TestDataInMap.get("supplier_number"))))
			{
				Assert.fail("The failed record is not stored in the staging table");
			}
		}else if((TransactionType.contains("bill edit")) || (TransactionType.contains("credit edit")) )
		{
			String internalId=getText(WebElements.StagingTableInternalIdValue);
			if(!(internalId.equals(Hooks.TestDataInMap.get("invalidInternalId"))))
			{
				Assert.fail("The failed record is not stored in the staging table");
			}
		}
		
	}



	@Given("User is in the {string} record")
	public void user_is_in_the_vendor_bill_credit_staging_table_main_record(String title) {
	   
		if(!driver.getTitle().contains(title))
		{
			Assert.fail("user is not in the "+title+" record");
		}

	}

	@When("User opens the staging table line record in edit mode")
	public void user_opens_the_staging_table_line_record_in_edit_mode() {
	  
		clickOnElement(WebElements.lineEditBtn);
	}
	
	@When("User opens the staging table record in edit mode")
	public void user_opens_the_staging_table_record_in_edit_mode() {
	  
		clickOnElement(WebElements.editBtn);
	}


	@When("User updates the {string} field")
	public void user_updates_the_taxcode_field(String field) {
	   
		if(field.equalsIgnoreCase("")) {
		findElement(WebElements.taxCodeStagingTable).clear();
		sendKeys(WebElements.taxCodeStagingTable,"VAT:S-GB");
		}
		else if(field.equalsIgnoreCase("supplier Number"))
		{
			findElement(WebElements.journalsupplierCodeStagingTable).clear();
			sendKeys(WebElements.journalsupplierCodeStagingTable,"M1225");
		}
		else if(field.equalsIgnoreCase("internalId"))
		{
			findElement(WebElements.internalIdStagingTable).clear();
			sendKeys(WebElements.internalIdStagingTable,internalId);
		}
		
	}



	@When("User saves the staging table record")
	public void user_saves_the_staging_table_record() {
	    
		clickOnElement(WebElements.saveBtn);
	}



	@Then("User verifies the {string} staging table record got saved with the updated data")
	public void user_verifies_the_staging_table_record_got_saved_with_the_updated_data(String transactionType) {

		if(transactionType.contains("Bill/Credit"))
		{
			if(!getText(WebElements.mainStagingTableTaxcode).equals("VAT:S-GB"))
			{
				Assert.fail("Vendor Bill/Credit staging table record is not updated");
			}
		}
		else if(transactionType.contains("Journal"))
		{
			if(!getText(WebElements.StagingTablejrnlsSupplierNumber).equals("M1225"))
			{
				Assert.fail("Vendor Bill/Credit staging table record is not updated");
			}
		}
		else if((transactionType.contains("bill edit")) || (transactionType.contains("credit edit")))
				{
			if(!getText(WebElements.StagingTableInternalIdValue).equals(internalId))
			{
				Assert.fail("Vendor Bill/Credit(edit) staging table record is not updated");
			}
		}
	}


	@Then("User verfies the processed flag is set to true for the record in the {string}")
	public void user_verfies_the_processed_flag_is_set_to_true_for_the_record_in_the(String stagingTable) {
	   
		if(stagingTable.equals("Vendor Bill/Credit Staging Table"))
		{
			driver.get("https://6087331-sb2.app.netsuite.com/app/common/custom/custrecordentry.nl?rectype=595&id="+stagingTableRecordId);
		}
		else if(stagingTable.contains("Journal"))
		{
			driver.get("https://6087331-sb2.app.netsuite.com/app/common/custom/custrecordentry.nl?rectype=598&id="+stagingTableRecordId);
		}
		else if(stagingTable.equals("Vendor Bill/Credit Staging Table (Edit)"))
		{
			driver.get("https://6087331-sb2.app.netsuite.com/app/common/custom/custrecordentry.nl?rectype=597&id="+stagingTableRecordId);
		}

		boolean processedCheckBox=findElement(WebElements.processedCheckBox).getAttribute("alt").equalsIgnoreCase("checked");

		if(!processedCheckBox)
		{
			Assert.fail("The record in the staging table is not marked as processed");
		}

		
	}

	
}
