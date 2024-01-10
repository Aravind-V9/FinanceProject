package StepDefinition;

import java.io.IOException;
import java.util.*;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Generic.WebElements;
import Generic.genericFunction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class Cost_GroupAndBusiness_Function extends genericFunction {

	public static HashMap<String,String[]> hashMap=new HashMap<>();

	@Given("User is logged into Netsuite Application")
	public void user_is_logged_into_netsuite_application() throws InterruptedException, IOException {
		driver=InitializeDriver();
		LaunchNetsuite();
		LoginToNetsuite();

	}

	@Given("User gets the cost centre details from the Cost centre page")
	public void user_gets_the_cost_centre_details_from_the_cost_centre_page() throws InterruptedException {

		if(hashMap.isEmpty())
		{

			driver.get("https://6087331-sb3.app.netsuite.com/app/common/otherlists/locationlist.nl?whence= ");	
			
			
			/*hoverOnElement(WebElements.Setup);
			hoverOnElement(WebElements.company);
			clickOnElement(WebElements.costCentres);*/
		    		

			List<WebElement> costCentres=driver.findElements(WebElements.costCentresList);

			for(int i=1;i<costCentres.size();i++)
			{
				WebElement costCentre=costCentres.get(i);
				moveToElement(costCentre);
				String costCentreName= costCentre.findElement(WebElements.costCentreNames).getText();
				String costGroupName= costCentre.findElement(WebElements.costGroupNames).getText();
				String businessFunctionName=costCentre.findElement(WebElements.businessFunctionNames).getText();

				String[] values=new String[2];
				values[0]=costGroupName;
				values[1]=businessFunctionName;
				hashMap.put(costCentreName, values);	 

			}
		}
		Thread.sleep(1000);

	}

	@When("User opens the {string} created through Integration")
	public void user_opens_the_created_through_integration(String transactionType) throws InterruptedException {
		sendKeys(WebElements.globalSearchBox,Hooks.TestDataInMap.get("transactionNumber"));
		Thread.sleep(3000);
		try {
		if(transactionType.equalsIgnoreCase("vendor Credits")){
			clickOnElement(WebElements.billCreditsearchResult);
		}
		else if(transactionType.equalsIgnoreCase("vendor Bills")){
		clickOnElement(WebElements.billsearchResult);
		}
		else if(transactionType.equalsIgnoreCase("journal entry")){
			clickOnElement(WebElements.journalsearchResult);
			}
		}catch (Exception e) {

			Assert.fail("Transaction is not found in Netsuite");

		}
	}

	@When("User views the cost group and business functions in the Lines subtab")
	public void user_views_the_cost_group_and_business_functions_in_the_lines_subtab() {

		clickOnElement(WebElements.linesSubtab);
	}
	
	@Then("User verifies the Cost group and business function are populated based on the Cost centre in GL Impact")
	public void user_verifies_the_cost_group_and_business_function_are_populated_based_on_the_cost_centre_in_gl_Impact() throws InterruptedException {

		
		////System.out.println("Entered gl impact verification");
		verifyCostGroupAndBusinessFunctionLineLevel(WebElements.glCostCentresList,WebElements.glCostCentreNames,WebElements.glcostGroupNames,WebElements.glbusinessFunctionNames);
		
		boolean checkMultiplePages=findElement(WebElements.glPageDropdown).isDisplayed();
		
		if(checkMultiplePages)
		{
		clickOnElement(WebElements.glPageDropdown);
		List<WebElement> Pages=findElements(WebElements.glPagesList);
		
		for(int i=1;i<Pages.size();i++)
		{
			////System.out.println("Page"+ i);
			WebElement Page=Pages.get(i);
			Page.click();
			Thread.sleep(5000);
			verifyCostGroupAndBusinessFunctionLineLevel(WebElements.glCostCentresList,WebElements.glCostCentreNames,WebElements.glcostGroupNames,WebElements.glbusinessFunctionNames);
			
			
		}
		}
		

	}

	@When("User navigates to the GL Impact subtab")
	public void user_views_the_GL_Impact_subtab() throws InterruptedException {
        
		//Thread.sleep(3000);
		clickOnElement(WebElements.glImpactSubtab);
		Thread.sleep(5000);
	
	}
	@Then("User verifies the Cost group and business function are populated based on the Cost centre for journal")
	public void user_verifies_the_cost_group_and_business_function_are_populated_based_on_the_cost_centre() throws InterruptedException {

		verifyCostGroupAndBusinessFunctionLineLevel(WebElements.journalCostCentresList,WebElements.journalCostCentreNames,WebElements.journalcostGroupNames,WebElements.journalbusinessFunctionNames);

	}


	@Given("User is on the Netsuite HomePage")
	public void user_is_on_the_netsuite_home_page() {

		clickOnElement(WebElements.home);

	}


	@Then("User verifies the Cost group and business function are populated based on the Cost centre in the header level")
	public void user_verifies_the_cost_group_and_business_function_are_populated_based_on_the_cost_centre_in_the_header_level() {

		String costCentreHeaderName=getText(WebElements.costCentreHeader);
		String costGroupHeaderName=getText(WebElements.costGroupHeader);
		String businessFunctionHeaderName=getText(WebElements.businessFunctionHeader);

		compareWithExpectedValues(costCentreHeaderName,costGroupHeaderName,businessFunctionHeaderName); 
	}

	@When("User views the cost group and business functions in the Expenses and Items subtab")
	public void user_views_the_cost_group_and_business_functions_in_the_expenses_and_items_subtab() throws InterruptedException {

		clickOnElement(WebElements.expenseAndItemsSubtab);
		Thread.sleep(1000);

	}

	@Then("User verifies the Cost group and business function are populated based on the Cost centre in the line level for vendorbill")
	public void user_verifies_the_cost_group_and_business_function_are_populated_based_on_the_cost_centre_in_the_line_level() {

		
		verifyCostGroupAndBusinessFunctionLineLevel(WebElements.BillCostCentresList,WebElements.BillCostCentreNames,WebElements.BillcostGroupNames,WebElements.BillbusinessFunctionNames);


	}

	@When("User views the cost group and business functions in the Items subtab")
	public void user_views_the_cost_group_and_business_functions_in_the_items_subtab() {

		clickOnElement(WebElements.expenseAndItemsSubtab);
	}


	@Then("User verifies the Cost group and business function are populated based on the Cost centre in the line level for vendorcredit")
	public void user_verifies_the_cost_group_and_business_function_are_populated_based_on_the_cost_centre_in_the_line_level_for_vendorcredit()
	{
		verifyCostGroupAndBusinessFunctionLineLevel(WebElements.creditCostCentresList,WebElements.creditCostCentreNames,WebElements.creditcostGroupNames,WebElements.creditbusinessFunctionNames);
	}

	
	@When("User creates a new journal entry")
	public void user_creates_a_new_journal_entry() throws InterruptedException
	{
		clickOnElement(WebElements.Transactions);
		clickOnElement(WebElements.expandAll);
		clickOnElement(WebElements.makeJournalEntries);
		Thread.sleep(2000);
		clickOnElement(WebElements.subsidiaryDropdwn);
		clickOnElement(WebElements.wickesSubsidiary);
		//clickOnElement(WebElements.currencyDropdwn);
		//Thread.sleep(2000);
		sendKeys(WebElements.journalType,Hooks.TestDataInMap.get("journalType"));
		Thread.sleep(2000);
		sendKeys(WebElements.enterAccount,Hooks.TestDataInMap.get("costAccount1"));
		clickOnElement(WebElements.addBtn);
		Thread.sleep(5000);
		clickOnElement(WebElements.clickCostCentre);
		sendKeys(WebElements.enterCostCentre,Hooks.TestDataInMap.get("costCentre1"));
		clickOnElement(WebElements.addBtn);
		Thread.sleep(1000);
		clickOnElement(WebElements.clickDebitAmount);
		sendKeys(WebElements.enterDebitAmount,Hooks.TestDataInMap.get("debitAmount"));
		clickOnElement(WebElements.addBtn);
		
		sendKeys(WebElements.enterAccount,Hooks.TestDataInMap.get("costAccount2"));
		clickOnElement(WebElements.addBtn);
		clickOnElement(WebElements.clickCostCentre);
		sendKeys(WebElements.enterCostCentre,Hooks.TestDataInMap.get("costCentre2"));
		clickOnElement(WebElements.addBtn);
		Thread.sleep(1000);
		clickOnElement(WebElements.clickCreditAmount);
		clickOnElement(WebElements.saveBtn);
			
	}
	
	@When("User creates a new vendor bill")
	public void user_creates_new_vendor_bill() throws InterruptedException
	{
		
		hoverOnElement(WebElements.Transactions);
		hoverOnElement(WebElements.payables);
		clickOnElement(WebElements.enterBills);
		

		sendKeys(WebElements.referenceNo,Hooks.TestDataInMap.get("referenceNo"));
		clickOnElement(WebElements.vendorDropdown);
		clickOnElement(WebElements.vendorListBtn);
		sendKeys(WebElements.vendorSearchBox,Hooks.TestDataInMap.get("vendor"));
		clickOnElement(WebElements.vendorSearchBtn);
		Thread.sleep(2000);
		clickOnElement(WebElements.selectVendorResult);
		sendKeys(WebElements.supplierInvoiceDate,Hooks.TestDataInMap.get("supplierInvoiceDate"));
		Thread.sleep(2000);
		clickOnElement(WebElements.enterCostCentreHeader);
		sendKeys(WebElements.enterCostCentreHeader,Hooks.TestDataInMap.get("costCentreHeader"));
		Thread.sleep(1000);
		findElement(WebElements.enterCostCentreHeader).sendKeys(Keys.ENTER);
		sendKeys(WebElements.costCentreReferenceHeader,Hooks.TestDataInMap.get("costCentreRefHeader"));
		Thread.sleep(1000);
		findElement(WebElements.costCentreReferenceHeader).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		clickOnElement(WebElements.expenseAndItemsSubtab);
		clickOnElement(WebElements.clickItem);
		sendKeys(WebElements.enterItem,Hooks.TestDataInMap.get("item1"));
		clickOnElement(WebElements.billAddBtn);
		clickOnElement(WebElements.clickCostCentre);
		sendKeys(WebElements.enterCostCentreBill,Hooks.TestDataInMap.get("costCentre1"));
		clickOnElement(WebElements.billAddBtn);
		Thread.sleep(1000);
		clickOnElement(WebElements.clickRate);
		sendKeys(WebElements.enterRate,Hooks.TestDataInMap.get("rate1"));
		Thread.sleep(1000);
		clickOnElement(WebElements.saveBtn);
		
		
	}
	
	@When("User creates a new vendor credit")
	public void user_creates_a_new_vendor_credit() throws InterruptedException {
		
		hoverOnElement(WebElements.Transactions);
		hoverOnElement(WebElements.payables);
		clickOnElement(WebElements.enterVendorCredits);
		
		sendKeys(WebElements.referenceNo,Hooks.TestDataInMap.get("referenceNo"));
		clickOnElement(WebElements.vendorDropdown);
		clickOnElement(WebElements.vendorListBtn);
		sendKeys(WebElements.vendorSearchBox,Hooks.TestDataInMap.get("vendor"));
		clickOnElement(WebElements.vendorSearchBtn);
		Thread.sleep(2000);
		clickOnElement(WebElements.selectVendorResult);
		sendKeys(WebElements.supplierInvoiceDate,Hooks.TestDataInMap.get("supplierInvoiceDate"));
		Thread.sleep(1000);
		
		
		
//		clickOnElement(WebElements.enterCostCentreHeader);
//		sendKeys(WebElements.enterCostCentreHeader,Hooks.TestDataInMap.get("costCentreHeader"));
//		Thread.sleep(2000);
//		findElement(WebElements.enterCostCentreHeader).sendKeys(Keys.ENTER);
		
		clickOnElement(WebElements.costCentreDropdown);
		clickOnElement(WebElements.costCentreListBtn);
		sendKeys(WebElements.costCentreSearchBox,Hooks.TestDataInMap.get("costCentreHeader"));
		clickOnElement(WebElements.costCentreSearchBtn);
		Thread.sleep(2000);
		clickOnElement(WebElements.selectcostCentreResult);
		
		sendKeys(WebElements.costCentreReferenceHeader,Hooks.TestDataInMap.get("costCentreRefHeader"));
		Thread.sleep(1000);
		findElement(WebElements.costCentreReferenceHeader).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		clickOnElement(WebElements.expenseAndItemsSubtab);
		clickOnElement(WebElements.itemsSubtab);
		clickOnElement(WebElements.clickItem);
		sendKeys(WebElements.enterItem,Hooks.TestDataInMap.get("item1"));
		clickOnElement(WebElements.billAddBtn);
		clickOnElement(WebElements.clickCostCentreCredit);
		sendKeys(WebElements.enterCostCentreBill,Hooks.TestDataInMap.get("costCentre1"));
		clickOnElement(WebElements.billAddBtn);
		Thread.sleep(1000);
		clickOnElement(WebElements.clickRate);
		sendKeys(WebElements.enterRate,Hooks.TestDataInMap.get("rate1"));
		clickOnElement(WebElements.billAddBtn);
		Thread.sleep(1000);
		clickOnElement(WebElements.saveBtn);

		
	  
		
	}
	
	@When("User makes payment for bills using EFT bill payments")
	public void user_makes_payment_using_eft_bill_payments() throws InterruptedException {
	    
		hoverOnElement(WebElements.Payments);
		hoverOnElement(WebElements.paymentProcessing);
		clickOnElement(WebElements.billPaymentProcessing);
		
		clickOnElement(WebElements.bankAccount);
		clickOnElement(WebElements.selectStockAccount);
		
		clickOnElement(WebElements.transactionType);
		clickOnElement(WebElements.selectBillType);
		sendKeys(WebElements.fromDueDate,Hooks.TestDataInMap.get("fromDueDate"));
		sendKeys(WebElements.toDueDate,Hooks.TestDataInMap.get("toDueDate"));
		
		clickOnElement(WebElements.apAccount);
		clickOnElement(WebElements.selectApAccount);
	
		sendKeys(WebElements.paymentRef,Hooks.TestDataInMap.get("paymentRef"));
		
		WebElement element=findElement(WebElements.paymentSubmitBtn);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
		Thread.sleep(1000);
		findElement(WebElements.payCheckBox).click();
		clickOnElement(WebElements.paymentSubmitBtn);
		
	}

	@When("User opens the Bill payment record")
	public void user_opens_the_bill_payment_record() {
	    
		
		boolean elementFound=false;
		
		while(!elementFound)
		{
			
			try {
				
				WebElement BillPayment=driver.findElement(WebElements.billPaymentRecord);
			if(BillPayment.isDisplayed())
			{
				clickOnElement(WebElements.billPaymentRecord);
				elementFound=true;
			}
			}
			catch(Exception e)
			{
				clickOnElement(WebElements.paymentRefreshBtn);
			}
			
		}
		driver.navigate().refresh();
	}

	@When("User creates the purchase order")
	public void user_creates_the_purchase_order() throws InterruptedException {
		
	    hoverOnElement(WebElements.Transactions);
	    hoverOnElement(WebElements.purchases);
	    clickOnElement(WebElements.enterPurchaseOrders);
		
		clickOnElement(WebElements.vendorDropdown);
		Thread.sleep(1000);
		clickOnElement(WebElements.vendorListBtn);
		sendKeys(WebElements.vendorSearchBox,Hooks.TestDataInMap.get("vendor"));
		clickOnElement(WebElements.vendorSearchBtn);
		Thread.sleep(2000);
		clickOnElement(WebElements.selectVendorResult);
		driver.switchTo().alert().accept();
		sendKeys(WebElements.enterCostCentreHeader,Hooks.TestDataInMap.get("costCentreHeader"));
		clickOnElement(WebElements.expenseAndItemsSubtab);
		Thread.sleep(1000);
		clickOnElement(WebElements.expenseAndItemsSubtab);
		clickOnElement(WebElements.itemsSubtab);
		clickOnElement(WebElements.clickItem);
		sendKeys(WebElements.enterItem,Hooks.TestDataInMap.get("item1"));
		clickOnElement(WebElements.billAddBtn);
		clickOnElement(WebElements.clickRate);
		Thread.sleep(1000);
		sendKeys(WebElements.enterRate,Hooks.TestDataInMap.get("rate1"));
		clickOnElement(WebElements.saveBtn);
	}
	
	
	public void verifyCostGroupAndBusinessFunctionLineLevel(By CostCentresListElement,By CostCentreNamesElement,By costGroupNamesElement,By businessFunctionNamesElement )
	{

		////System.out.println("Entered into verification");
		List<WebElement> costCentres=findElements(CostCentresListElement);
		
		////System.out.println(costCentres.size());
		for(int i=1;i<costCentres.size();i++)
		{
			
			////System.out.println("line"+i);
			WebElement costCentre=costCentres.get(i);
			WebElement element = costCentre.findElement(CostCentreNamesElement);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();

			String costCentreName= costCentre.findElement(CostCentreNamesElement).getText();

			if(costCentreName.contains(":"))
			{
				String[] costCentreValues=costCentreName.split(":", 3);
				costCentreName= costCentreValues[2].trim();
			}

			String actualCostGroupName= costCentre.findElement(costGroupNamesElement).getText();
			String actualBusinessFunctionName=costCentre.findElement(businessFunctionNamesElement).getText();
			compareWithExpectedValues(costCentreName,actualCostGroupName,actualBusinessFunctionName);
		/*	if(i!=10) {}
			else
			{
				//System.out.println("line"+i);
				WebElement costCentre=costCentres.get(i);
				WebElement element = costCentre.findElement(CostCentreNamesElement);
				Actions actions = new Actions(driver);
				actions.moveToElement(element).perform();

				String costCentreName= costCentre.findElement(CostCentreNamesElement).getText();

				if(costCentreName.contains(":"))
				{
					String[] costCentreValues=costCentreName.split(":", 3);
					costCentreName= costCentreValues[2].trim();
				}

				String actualCostGroupName=" ";
				String actualBusinessFunctionName=" ";
				compareWithExpectedValues(costCentreName,actualCostGroupName,actualBusinessFunctionName);
			}*/

			
		}	
	}

	public String[] getCostCentreDetails(String costCentre)
	{
		String[] retrievedValues=hashMap.get(costCentre);
		return retrievedValues;
	}

	public void compareWithExpectedValues(String costCentreName,String actualCostGroupName,String actualBusinessFunctionName )
	{
		String[] ActualValues= {actualCostGroupName,actualBusinessFunctionName};
		String[] ExpectedValues=getCostCentreDetails(costCentreName);

		if(!Arrays.equals(ActualValues, ExpectedValues))
		{
			Assert.fail("Cost group and Business function are not populated based on costcentre.Actual values are :"+ costCentreName+actualCostGroupName+actualBusinessFunctionName);
			//System.out.println(ExpectedValues[0]);
			//System.out.println(ExpectedValues[1]);
			//System.out.println(ActualValues[0]);
			//System.out.println(ActualValues[1]);
		}	

	}

}
