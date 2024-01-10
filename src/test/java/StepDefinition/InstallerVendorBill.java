package StepDefinition;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Generic.WebElements;
import Generic.genericFunction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class InstallerVendorBill extends genericFunction {



	public static double installationAmount;
	public static double remedialAmount;
	public static double cisAmount;
	public static String cisLevel;

	@When("User searches the vendor bill using saved search")
	public void user_searches_the_vendor_bill_using_saved_search() throws InterruptedException {


		sendKeys(WebElements.globalSearchBox,"Installer Vendor Bill");
		clickOnElement(WebElements.vbSavedsearchResults);
		clickOnElement(WebElements.editSavedSearch);
		//sendKeys(WebElements.criteriaDropdownBox,"KB");
		//findElement(WebElements.criteriaDropdownBox).sendKeys(Keys.ENTER);
		clickOnElement(WebElements.criteriaDropdownBox);
		clickOnElement(WebElements.setDescription);
		driver.switchTo().frame(findElement(WebElements.searchIframe));
		findElement(WebElements.enterOrderNumber).clear();
		sendKeys(WebElements.enterOrderNumber,Hooks.TestDataInMap.get("VisionOrdNo"));
		clickOnElement(WebElements.setBtn);
		driver.switchTo().parentFrame();
		clickOnElement(WebElements.saveAndRun);



	}

	@When("User opens the Installer vendor bill")
	public void user_opens_the_installer_vendor_bill() {

		List<WebElement> installerBills=driver.findElements(WebElements.installerBillSearchList);

		for(int i=1;i<installerBills.size();i++)
		{
			WebElement installerBill=installerBills.get(i);

			if(installerBill.findElement(WebElements.vendorNames).getText().equalsIgnoreCase(Hooks.TestDataInMap.get("SuppCode")))
			{
				if(!Hooks.TestDataInMap.get("RemDiffReason").equalsIgnoreCase(""))
				{
					if(installerBill.findElement(WebElements.remedialReasons).getText().equalsIgnoreCase(Hooks.TestDataInMap.get("RemDiffReason")))
					{
						installerBill.findElement(WebElements.viewButton).click();
						break;
					}
				}
				else
				{
					installerBill.findElement(WebElements.viewButton).click();
					break;
				}
			}	 
		}
	}

	@Then("User verifies the header level information are populated correctly")
	public void user_verifies_the_header_level_information_are_populated_correctly() {

		cisLevel=getCisLevel();

		String vendorId=getText(WebElements.getVendorId);

		if(!vendorId.equals(Hooks.TestDataInMap.get("SuppCode")))
		{
			Assert.fail("Vendor Id is not displayed correctly");
		}
		String costCentreReference=getText(WebElements.getCostCentreRefHeader);

		if(!costCentreReference.contains("1"+Hooks.TestDataInMap.get("StoreCode")))
		{
			Assert.fail("cost centre Reference is not displayed correctly");
		}		

	}

	@SuppressWarnings("unlikely-arg-type")
	@Then("User verifies the amounts are calculated correctly in Expense and Items subtab for the installation only transaction")
	public void user_verifies_the_amounts_are_calculated_correctly_in_expense_and_items_subtab_for_the_installation_only_transaction() {



		clickOnElement(WebElements.expenseAndItemsSubtab);

		List<WebElement> installerBillLines=driver.findElements(WebElements.itemsRowList);
		WebElement InstallationWorksLine=installerBillLines.get(1);
		moveToElement(InstallationWorksLine.findElement(WebElements.itemDescription));
		//actions.moveToElement(InstallationWorksLine.findElement(WebElements.itemDescription)).perform();
		installationAmount=validateInstallationLine(InstallationWorksLine);
		remedialAmount=0;

		WebElement SubtotalLine=installerBillLines.get(2);
		moveToElement(SubtotalLine.findElement(WebElements.items));
		//actions.moveToElement(SubtotalLine.findElement(WebElements.items)).perform();
		double subTotalAmount=validateSubtotalLine(SubtotalLine,installationAmount);

		
		WebElement cisLine=installerBillLines.get(3);
		cisAmount=validateCisLine(cisLine,cisLevel,subTotalAmount);
		
	}



	@Given("User is in the Installer vendor Bill Page")
	public void user_is_in_the_installer_vendor_bill_page() {

	}

	@Then("User verifies the amounts are posted to the correct cost accounts in GL Impact for the Installation only transaction")
	public void user_verifies_the_amounts_are_posted_to_the_correct_cost_accounts_for_the_installation_only_transaction() {


		List<WebElement> glLines=driver.findElements(WebElements.glLines);
		WebElement purchaseLedgerglLine=glLines.get(1);
		validatePurchaseLedgerGlLine(purchaseLedgerglLine);

		WebElement installationGlLine=glLines.get(2);
		validateinstallationGlLine(installationGlLine);

		if(!cisLevel.equals("G"))
		{
		WebElement cisGlLine=glLines.get(3);
		validateCisGlLine(cisGlLine,cisLevel);
		}
	}
	@Then("User verifies the amounts are calculated correctly in Expense and Items subtab for the remedial only transaction")
	public void user_verifies_the_amounts_are_calculated_correctly_in_expense_and_items_subtab_for_the_remedial_only_transaction() {

		validateInstallerData();
		clickOnElement(WebElements.expenseAndItemsSubtab);
		List<WebElement> installerBillLines=driver.findElements(WebElements.itemsRowList);

		//Installation
		WebElement InstallationWorksLine=installerBillLines.get(1);
		Actions actions = new Actions(driver);
		actions.moveToElement(InstallationWorksLine.findElement(WebElements.itemDescription)).perform();
		installationAmount=validateInstallationLine(InstallationWorksLine);

		//Remedial
		WebElement RemedialWorksLine=installerBillLines.get(2);
		actions.moveToElement(RemedialWorksLine.findElement(WebElements.itemDescription)).perform();
		remedialAmount = validateRemedialLine(RemedialWorksLine);

		//SubtotalLine
		WebElement SubtotalLine=installerBillLines.get(3);
		actions.moveToElement(SubtotalLine.findElement(WebElements.items)).perform();
		double subTotalAmount=validateSubtotalLine(SubtotalLine,remedialAmount);

		WebElement cisLine=installerBillLines.get(4);
		cisAmount=validateCisLine(cisLine,cisLevel,subTotalAmount);

	}

	@Then("User verifies the amounts are posted to the correct cost accounts in GL Impact for the remedial only transaction")
	public void user_verifies_the_amounts_are_posted_to_the_correct_cost_accounts_in_gl_impact_for_the_remedial_only_transaction() {
		List<WebElement> glLines=driver.findElements(WebElements.glLines);
		WebElement purchaseLedgerglLine=glLines.get(1);
		validatePurchaseLedgerGlLine(purchaseLedgerglLine);

		WebElement remedialGlLine=glLines.get(2);
		validateRemedialGlLine(remedialGlLine);
		
		if(!cisLevel.equals("G"))
		{
		WebElement cisGlLine=glLines.get(3);
		validateCisGlLine(cisGlLine,cisLevel);
		}
	}	

	@Then("User verifies the amounts are calculated correctly in Expense and Items subtab for the installation and remedial only transaction")
	public void user_verifies_the_amounts_are_calculated_correctly_in_expense_and_items_subtab_for_the_installation_and_remedial_only_transaction() {


		validateInstallerData();

		clickOnElement(WebElements.expenseAndItemsSubtab);
		List<WebElement> installerBillLines=driver.findElements(WebElements.itemsRowList);

		//Installation
		WebElement InstallationWorksLine=installerBillLines.get(1);
		Actions actions = new Actions(driver);
		actions.moveToElement(InstallationWorksLine.findElement(WebElements.itemDescription)).perform();
		installationAmount=validateInstallationLine(InstallationWorksLine);

		//Remedial
		WebElement RemedialWorksLine=installerBillLines.get(2);
		actions.moveToElement(RemedialWorksLine.findElement(WebElements.itemDescription)).perform();
		remedialAmount = validateRemedialLine(RemedialWorksLine);

		//SubtotalLine
		WebElement SubtotalLine=installerBillLines.get(3);
		actions.moveToElement(SubtotalLine.findElement(WebElements.items)).perform();
		double subTotalAmount=validateSubtotalLine(SubtotalLine,installationAmount,remedialAmount);

		WebElement cisLine=installerBillLines.get(4);
		cisAmount=validateCisLine(cisLine,cisLevel,subTotalAmount);

	}

	@Then("User verifies the amounts are posted to the correct cost accounts in GL Impact for the installation and remedial transaction")
	public void user_verifies_the_amounts_are_posted_to_the_correct_cost_accounts_in_gl_impact_for_the_installation_and_remedial_transaction() {
		List<WebElement> glLines=driver.findElements(WebElements.glLines);
		WebElement purchaseLedgerglLine=glLines.get(1);
		validatePurchaseLedgerGlLine(purchaseLedgerglLine);

		WebElement installationGlLine=glLines.get(2);
		validateinstallationGlLine(installationGlLine);

		WebElement remedialGlLine=glLines.get(3);
		validateRemedialGlLine(remedialGlLine);

		if(!cisLevel.equals("G"))
		{
		WebElement cisGlLine=glLines.get(4);
		validateCisGlLine(cisGlLine,cisLevel);
		}
	}
	public double validateInstallationLine(WebElement InstallationWorksLine)
	{
		if(!InstallationWorksLine.findElement(WebElements.itemDescription).getText().equals("\"Installation Works\""))
		{
			Assert.fail("Item Description for the Installation line is not displayed correctly");
		}

		if(!InstallationWorksLine.findElement(WebElements.items).getText().equals("INSTALLATION FITTER CONTROL A/C"))
		{
			Assert.fail("Item is not correct for the installation line");
		}
		if(!InstallationWorksLine.findElement(WebElements.itemCostAccount).getText().equals("335360 ACCRUALS : OTHER - ACCRUALS : INSTALLATION FITTER CONTROL A/C"))
		{
			Assert.fail("Item Cost account is not correct for the installation line");
		}
		if(!InstallationWorksLine.findElement(WebElements.itemCostCentre).getText().equals("9000 General Cost Centre"))
		{
			Assert.fail("Item cost centre is not correct for the installation line");
		}

		double visionGrossAmount=convertStringToDouble(InstallationWorksLine.findElement(WebElements.visionGrossAmount).getText());
		double saleValue=(convertStringToDouble(Hooks.TestDataInMap.get("SaleValue")));
		if(!(Double.compare(visionGrossAmount, saleValue)==0))
		{
			Assert.fail("ORIGINAL GROSS AMOUNT (VISION) is not correct for the installation line");
		}

		double actualAmount=convertStringToDouble(InstallationWorksLine.findElement(WebElements.amount).getText());
		double expectedAmount=calculateGrossAmountFromVisonGrossAmount(visionGrossAmount);
		if(!(Double.compare(actualAmount, expectedAmount)==0))
		{
			Assert.fail("AMOUNT is not correct for the installation line");
		}

		if(!InstallationWorksLine.findElement(WebElements.customerReference).getText().equals(Hooks.TestDataInMap.get("CustRef")))
		{
			Assert.fail("Customer Reference is not correct for the installation line");
		}

		return actualAmount;

	}

	public double validateRemedialLine(WebElement RemedialWorksLine)
	{

		if(!RemedialWorksLine.findElement(WebElements.itemDescription).getText().equals("\"Remedial Works\""))
		{
			Assert.fail("Item Description for the Remedial line is not displayed correctly");
		}

		if(!RemedialWorksLine.findElement(WebElements.items).getText().equals("Installer Remedial Costs"))
		{
			Assert.fail("Item is not correct for the remedial line");
		}
		if(!RemedialWorksLine.findElement(WebElements.itemCostAccount).getText().equals("837100 Other Costs : Installer Remedial : Installer Remedial Costs"))
		{
			Assert.fail("Item Cost account is not correct for the remedial line");
		}
		if(!RemedialWorksLine.findElement(WebElements.itemCostCentre).getText().contains("1"+Hooks.TestDataInMap.get("StoreCode")))
		{
			Assert.fail("Item cost centre is not correct for the remedial line");
		}

		double RemedialVisionGrossAmount=convertStringToDouble(RemedialWorksLine.findElement(WebElements.visionGrossAmount).getText());
		double RemDiffValue=(convertStringToDouble(Hooks.TestDataInMap.get("RemDiffValue")));
		double expectedVisionGrossAmount=RemDiffValue*-1;
		if(!(Double.compare(RemedialVisionGrossAmount, expectedVisionGrossAmount)==0))
		{
			Assert.fail("ORIGINAL GROSS AMOUNT (VISION) is not correct for the remedial line");
		}

		double actualAmount=convertStringToDouble(RemedialWorksLine.findElement(WebElements.amount).getText());
		double expectedAmount=calculateGrossAmountFromVisonGrossAmount(RemedialVisionGrossAmount);
		if(!(Double.compare(actualAmount, expectedAmount)==0))
		{
			Assert.fail("AMOUNT is not correct for the remedial line");
		}

		if(!RemedialWorksLine.findElement(WebElements.customerReference).getText().equals(Hooks.TestDataInMap.get("CustRef")))
		{
			Assert.fail("Customer Reference is not correct for the installation line");
		}	
		return actualAmount;
	}

	public double validateSubtotalLine(WebElement SubtotalLine,double expectedAmount)
	{
		if(!SubtotalLine.findElement(WebElements.items).getText().equals("Subtotal"))
		{
			Assert.fail("Item is not correct in the subtotal line ");
		}
		if(!SubtotalLine.findElement(WebElements.itemCostCentre).getText().equals("9000 General Cost Centre"))
		{
			Assert.fail("Item cost centre is not correct for the subtotal line");
		}

		double actualAmount=convertStringToDouble(SubtotalLine.findElement(WebElements.amount).getText());
		if(!(Double.compare(actualAmount, expectedAmount)==0))
		{
			Assert.fail("AMOUNT is not correct for the subtotal line");
		}

		return actualAmount;
	}

	public double validateSubtotalLine(WebElement SubtotalLine,double installationAmount,double remedialAmount)
	{
		if(!SubtotalLine.findElement(WebElements.items).getText().equals("Subtotal"))
		{
			Assert.fail("Item is not correct in the subtotal line ");
		}
		if(!SubtotalLine.findElement(WebElements.itemCostCentre).getText().equals("9000 General Cost Centre"))
		{
			Assert.fail("Item cost centre is not correct for the subtotal line");
		}

		double expectedAmount=roundOff(installationAmount+remedialAmount);
		double actualAmount=convertStringToDouble(SubtotalLine.findElement(WebElements.amount).getText());
		//System.out.println(actualAmount);
		if(!(Double.compare(actualAmount, expectedAmount)==0))
		{
			Assert.fail("AMOUNT is not correct for the subtotal line");
		}
		return actualAmount;
	}

	public double validateCisLine(WebElement cisLine, String cisLevel, double subTotalAmount) {

		moveToElement(cisLine.findElement(WebElements.items));

		if(!cisLine.findElement(WebElements.itemCostAccount).getText().equals("321100 OTHER CREDITORS : OTHER CREDS - OTHER : CIS CONTROL A/C"))
		{
			Assert.fail("Item Cost account is not correct for the CIS line");
		}
		if(!cisLine.findElement(WebElements.itemCostCentre).getText().equals("9000 General Cost Centre"))
		{
			Assert.fail("Item cost centre is not correct for the CIS line");
		}

		if(cisLevel.equalsIgnoreCase("G"))
		{
			if(!cisLine.findElement(WebElements.items).getText().equals("G"))
			{
				Assert.fail("Item is not correct for the CIS line");
			}

			if(!cisLine.findElement(WebElements.itemDescription).getText().equals("CIS Tax Deducted @ 0%"))
			{
				Assert.fail("Item description is not correct for the CIS line");
			}

			if(!cisLine.findElement(WebElements.rate).getText().equals("0.0%"))
			{
				Assert.fail("Rate is not correct for the CIS line");
			}
			cisAmount=0;

			if(!cisLine.findElement(WebElements.amount).getText().equals("0.00"))
			{
				Assert.fail("Amount is not correct for the CIS line");
			}
		}
		else if(cisLevel.equalsIgnoreCase("S"))
		{
			if(!cisLine.findElement(WebElements.items).getText().equals("S"))
			{
				Assert.fail("Item is not correct for the CIS line");
			}

			if(!cisLine.findElement(WebElements.itemDescription).getText().equals("CIS Tax Deducted @ 20%"))
			{
				Assert.fail("Item description is not correct for the CIS line");
			}

			if(!cisLine.findElement(WebElements.rate).getText().equals("-20.0%"))
			{
				Assert.fail("Rate is not correct for the CIS line");
			}
			cisAmount=convertStringToDouble(cisLine.findElement(WebElements.amount).getText());
			double expectedAmount=calculateCisAmountS(subTotalAmount)*-1;
			if(!(Double.compare(cisAmount, expectedAmount)==0))
			{
				Assert.fail("Amount is not correct for the CIS line");
			}
		}
		else if(cisLevel.equalsIgnoreCase("H"))
		{
			if(!cisLine.findElement(WebElements.items).getText().equals("H"))
			{
				Assert.fail("Item is not correct for the CIS line");
			}

			if(!cisLine.findElement(WebElements.itemDescription).getText().equals("CIS Tax Deducted @ 30%"))
			{
				Assert.fail("Item description is not correct for the CIS line");
			}

			if(!cisLine.findElement(WebElements.rate).getText().equals("-30.0%"))
			{
				Assert.fail("Item description is not correct for the CIS line");
			}
			cisAmount=convertStringToDouble(cisLine.findElement(WebElements.amount).getText());
			double expectedAmount=calculateCisAmountH(subTotalAmount)*-1;
			if(!(Double.compare(cisAmount, expectedAmount)==0))
			{
				Assert.fail("Amount is not correct for the CIS line");
			}
		}
		return cisAmount;

	}

	public void validateInstallerData()
	{
		//Thread.sleep(2000);
		clickOnElement(WebElements.installerDataSubtab);
		Actions actions = new Actions(driver);
		actions.moveToElement(findElement(WebElements.paymentDifference)).perform();
		if(!findElement(WebElements.kbOrderNumber).getText().equals(Hooks.TestDataInMap.get("VisionOrdNo")))
		{
			Assert.fail("KB Order number is not correct in the installer Data");
		}	

		if(!findElement(WebElements.remedialReason).getText().equals(Hooks.TestDataInMap.get("RemDiffReason")))
		{
			Assert.fail("Remedial Reason is not correct in the installer Data");
		}
		double ActualpaymentDifference=convertStringToDouble(findElement(WebElements.paymentDifference).getText());
		double ExpectedpaymentDifference=(convertStringToDouble(Hooks.TestDataInMap.get("RemDiffValue")));
		if(!(Double.compare(ActualpaymentDifference, ExpectedpaymentDifference)==0))
		{
			Assert.fail("Payment Difference is not correct in the installation Data");
		}
	}

	public String getCisLevel()
	{

		clickOnElement(WebElements.clickVendorId);
		clickOnElement(WebElements.financialSubtab);
		findElement(WebElements.cisLevel);
		String cisLevel=getText(WebElements.cisLevel);
		driver.navigate().back();
		return cisLevel;

	}

	public void validatePurchaseLedgerGlLine(WebElement purchaseLedgerglLine )
	{
		moveToElement(purchaseLedgerglLine.findElement(WebElements.glCostAccount));

		//System.out.println("GL 1");
		//System.out.println(purchaseLedgerglLine.findElement(WebElements.glCostAccount).getText());
		if(!purchaseLedgerglLine.findElement(WebElements.glCostAccount).getText().equals("305100 CURRENT LIABILITIES : ACCOUNTS PAYABLE CONTROL : PURCHASE LEDGER CONTROL A/C"))
		{
			Assert.fail("Cost account is not correct for the PurchaseLedger line in GLImpact");
		}

		String purchaseLedgerAmount=addCurrency(installationAmount+remedialAmount+cisAmount);
		//System.out.println(installationAmount);
		//System.out.println(remedialAmount);
		//System.out.println(cisAmount);
		//System.out.println(purchaseLedgerAmount);
		//System.out.println(purchaseLedgerglLine.findElement(WebElements.glCreditAmount).getText());
		if(!purchaseLedgerglLine.findElement(WebElements.glCreditAmount).getText().equals(purchaseLedgerAmount))
		{
			Assert.fail("credit Amount is not correct for the PurchaseLedger line in GLImpact");
		}
		//System.out.println(purchaseLedgerglLine.findElement(WebElements.glPosting).getText());
		if(!purchaseLedgerglLine.findElement(WebElements.glPosting).getText().equals("Yes"))
		{
			Assert.fail("Posting field is not correct for the PurchaseLedger line in GLImpact");
		}
		//System.out.println(purchaseLedgerglLine.findElement(WebElements.glName).getText());
		//System.out.println(Hooks.TestDataInMap.get("SuppCode"));
		if(!purchaseLedgerglLine.findElement(WebElements.glName).getText().equals(Hooks.TestDataInMap.get("SuppCode")))
		{
			Assert.fail("Name field is not correct for the PurchaseLedger line in GLImpact");
		}
		//System.out.println(purchaseLedgerglLine.findElement(WebElements.glCostCentre).getText());
		if(!purchaseLedgerglLine.findElement(WebElements.glCostCentre).getText().equals("9000 General Cost Centre"))
		{
			Assert.fail("cost centre is not correct for the PurchaseLedger line in GLImpact");
		}
	}
	public void validateinstallationGlLine(WebElement installationGlLine )
	{
		moveToElement(installationGlLine.findElement(WebElements.glCostAccount));
		//System.out.println("GL line 2");
		//System.out.println(installationGlLine.findElement(WebElements.glCostAccount).getText());
		if(!installationGlLine.findElement(WebElements.glCostAccount).getText().equals("335360 ACCRUALS : OTHER - ACCRUALS : INSTALLATION FITTER CONTROL A/C"))
		{
			Assert.fail("Cost account is not correct for the Installation line in GLImpact");
		}
		//System.out.println(installationGlLine.findElement(WebElements.glDebitAmount).getText());
		String InstallationGlAmount=addCurrency(installationAmount);
		if(!installationGlLine.findElement(WebElements.glDebitAmount).getText().equals(InstallationGlAmount))
		{
			Assert.fail("Debit Amount is not correct for the Installation line in GLImpact");
		}
		//System.out.println(installationGlLine.findElement(WebElements.glPosting).getText());
		if(!installationGlLine.findElement(WebElements.glPosting).getText().equals("Yes"))
		{
			Assert.fail("Posting field is not correct for the Installation line in GLImpact");
		}
		//System.out.println(installationGlLine.findElement(WebElements.glMemo).getText());
		if(!installationGlLine.findElement(WebElements.glMemo).getText().equals("\"Installation Works\""))
		{
			Assert.fail("Memo field is not correct for the Installation line in GLImpact");
		}
		//System.out.println(installationGlLine.findElement(WebElements.glCostCentre).getText());
		if(!installationGlLine.findElement(WebElements.glCostCentre).getText().equals("9000 General Cost Centre"))
		{
			Assert.fail("cost centre is not correct for the Installation line in GLImpact");
		}
	}
	public void validateRemedialGlLine(WebElement remedialGlLine)
	{
		moveToElement(remedialGlLine.findElement(WebElements.glCostAccount));
		//System.out.println("GL line 2");
		//System.out.println(remedialGlLine.findElement(WebElements.glCostAccount).getText());
		if(!remedialGlLine.findElement(WebElements.glCostAccount).getText().equals("837100 Other Costs : Installer Remedial : Installer Remedial Costs"))
		{
			Assert.fail("Cost account is not correct for the Remedial line in GLImpact");
		}

		if(remedialAmount>0) {
			//System.out.println(remedialGlLine.findElement(WebElements.glDebitAmount).getText());
			String remedialGlAmount=addCurrency(remedialAmount);
			if(!remedialGlLine.findElement(WebElements.glDebitAmount).getText().equals(remedialGlAmount))
			{
				Assert.fail("Debit Amount is not correct for the Remedial line in GLImpact");
			}
		}else if(remedialAmount<0)
		{
			//System.out.println(remedialGlLine.findElement(WebElements.glCreditAmount).getText());
			String remedialGlAmount=addCurrency(remedialAmount*-1);
			if(!remedialGlLine.findElement(WebElements.glCreditAmount).getText().equals(remedialGlAmount))
			{
				Assert.fail("credit Amount is not correct for the Remedial line in GLImpact");
			}
		}
		//System.out.println(remedialGlLine.findElement(WebElements.glPosting).getText());
		if(!remedialGlLine.findElement(WebElements.glPosting).getText().equals("Yes"))
		{
			Assert.fail("Posting field is not correct for the Remedial line in GLImpact");
		}
		//System.out.println(remedialGlLine.findElement(WebElements.glMemo).getText());
		if(!remedialGlLine.findElement(WebElements.glMemo).getText().equals("\"Remedial Works\""))
		{
			Assert.fail("Memo field is not correct for the Remedial line in GLImpact");
		}
		//System.out.println(remedialGlLine.findElement(WebElements.glCostCentre).getText());
		if(!remedialGlLine.findElement(WebElements.glCostCentre).getText().contains("1"+Hooks.TestDataInMap.get("StoreCode")))
		{
			Assert.fail("cost centre is not correct for the Remedial line in GLImpact");
		}
	}
	public void validateCisGlLine(WebElement cisGlLine, String cisLevel)
	{

		
			moveToElement(cisGlLine.findElement(WebElements.glCostAccount));
			//System.out.println("GL line 2");
			//System.out.println(cisGlLine.findElement(WebElements.glCostAccount).getText());
			if(!cisGlLine.findElement(WebElements.glCostAccount).getText().equals("321100 OTHER CREDITORS : OTHER CREDS - OTHER : CIS CONTROL A/C"))
			{
				Assert.fail("Cost account is not correct for the CIS line in GLImpact");
			}
			//System.out.println(cisGlLine.findElement(WebElements.glCreditAmount).getText());

			String cisGlAmount=addCurrency(cisAmount*-1);
			//System.out.println(cisGlAmount);
			if(!cisGlLine.findElement(WebElements.glCreditAmount).getText().equals(cisGlAmount))
			{
				Assert.fail("Credit Amount is not correct for the CIS line in GLImpact");
			}
			//System.out.println(cisGlLine.findElement(WebElements.glPosting).getText());
			if(!cisGlLine.findElement(WebElements.glPosting).getText().equals("Yes"))
			{
				Assert.fail("Posting field is not correct for the CIS line in GLImpact");
			}
			//System.out.println(cisGlLine.findElement(WebElements.glMemo).getText());
			if(cisLevel.equals("S")) {
				if(!cisGlLine.findElement(WebElements.glMemo).getText().equals("CIS Tax Deducted @ 20%"))
				{
					Assert.fail("Memo field is not correct for the CIS line in GLImpact");
				}
			}else if(cisLevel.equals("H"))
			{
				if(!cisGlLine.findElement(WebElements.glMemo).getText().equals("CIS Tax Deducted @ 30%"))
				{
					Assert.fail("Memo field is not correct for the CIS line in GLImpact");
				}
			}
			//System.out.println(cisGlLine.findElement(WebElements.glCostCentre).getText());
			if(!cisGlLine.findElement(WebElements.glCostCentre).getText().equals("9000 General Cost Centre"))
			{
				Assert.fail("cost centre is not correct for the CIS line in GLImpact");
			}
		
	}

	public double convertStringToDouble(String amountTxt)
	{
		double amount = Double.parseDouble(amountTxt.replace(",", ""));
		return amount;
	}

	public double calculateGrossAmountFromVisonGrossAmount(double visionGrossAmount)
	{
		double grossvalue=visionGrossAmount/1.2;
		double grossAmount=roundOff(grossvalue);
		return grossAmount;	
	}
	public double calculateCisAmountS(double subtotalamount)
	{
		double cisValue=subtotalamount*0.2;
		double cisAmount=roundOff(cisValue);
		return cisAmount;	
	}
	public double calculateCisAmountH(double subtotalamount)
	{
		double cisValue=subtotalamount*0.3;
		double cisAmount=roundOff(cisValue);
		return cisAmount;	
	}

	public double roundOff(double value)
	{
		int decimalplaces=2;
		BigDecimal roundedNumber= new BigDecimal(value).setScale(decimalplaces, RoundingMode.HALF_UP);
		double roundedValue=roundedNumber.doubleValue();
		return roundedValue;
	}

	public String addCurrency(double amount)
	{
		NumberFormat nf=NumberFormat.getCurrencyInstance(Locale.UK);
		String formattedValue=nf.format(amount);
		return formattedValue;
	}
}
