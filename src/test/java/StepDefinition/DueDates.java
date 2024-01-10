package StepDefinition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Generic.WebElements;
import Generic.genericFunction;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class DueDates extends genericFunction {

	public static String termDetails;
	public static String expectedDueDate;
	
	@When("User gets the Term details from the vendor record")
	public void user_gets_the_term_details_from_the_vendor_record() {
	   
		clickOnElement(WebElements.clickVendorId);
		clickOnElement(WebElements.financialSubtab);
		scrollToElement(WebElements.term);
		termDetails=getText(WebElements.term);
		
	}

	@When("User navigates back to the transaction record")
	public void user_navigates_back_to_the_vendor_bill() {
	    driver.navigate().back();
	}

	@When("User calculates due date from the supplier invoice date")
	public void user_calculates_due_date_from_the_supplier_invoice_date() throws ParseException {
		
		 String supplierInvoiceDate=getText(WebElements.supplierInvoiceDateTxt);
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
         Date invoiceDate = dateFormat.parse(supplierInvoiceDate);
         System.out.println(supplierInvoiceDate);
         String[] termsTxt =termDetails.split("-");
         String term = termsTxt[0].trim(); // Replace with the desired term (e.g., X31, Y15, D30, etc.)
         System.out.println(term);
          expectedDueDate = dateFormat.format(calculateDueDate(term, invoiceDate));
         System.out.println("Expected Due Date: " + dateFormat.format(calculateDueDate(term, invoiceDate)));
		
	}

	
	@Then("User verifies the due date is calculated correctly based on the terms mentioned in vendor record")
	public void user_verifies_the_due_date_is_calculated_correctly_based_on_the_terms_mentioned_in_vendor_record() throws InterruptedException {
		System.out.println("Before approval :");
		verifyDueDate();
	   
	}
	@Then("User verifies the due date is calculated correctly based on the terms mentioned in vendor record for approved bills")
	public void user_verifies_the_due_date_is_calculated_correctly_based_on_the_terms_mentioned_in_vendor_record_approved_bills() throws InterruptedException {
		
		
		String actualDueDate=getText(WebElements.dueDate);
		System.out.println("After approval :"+actualDueDate);
		if(!actualDueDate.equals(expectedDueDate))
		{
			String dueDateHoldingValue=getText(WebElements.dueDateHoldingValue);
			System.out.println("duedate holding value :"+dueDateHoldingValue);
			if(!actualDueDate.equals(dueDateHoldingValue))
			{
				rundueDateScript();
				sendKeys(WebElements.globalSearchBox,Hooks.TestDataInMap.get("invoiceNumber"));
				clickOnElement(WebElements.billsearchResult);
				checkScheduledDueDateCheckBox();
				System.out.println("after script :");
				verifyDueDate();
				
			}
			else
			{
				Assert.fail("Due date holding value field is not calculated correctly");
			}
		}
	   
	}
	
	@When("User approves the invoice")
	public void User_approves_the_invoice() {
	    
		clickOnElement(WebElements.approveBtn);
	}
	
	  public  Date calculateDueDate(String term, Date invoiceDate) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(invoiceDate);

	        char termType = term.charAt(0);
	        int termValue = Integer.parseInt(term.substring(1));

	        int maxDayInMonth;
	        switch (termType) {
	            case 'D':
	                // No need to check, just add the days
	                calendar.add(Calendar.DAY_OF_MONTH, termValue);
	                break;
	            case 'X':
	                // Add a month
	                calendar.add(Calendar.MONTH, 1);
	                maxDayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	                if (termValue > maxDayInMonth) {
	                    // If the specified day doesn't exist, set it to the last day of the month
	                    calendar.set(Calendar.DAY_OF_MONTH, maxDayInMonth);
	                } else {
	                    calendar.set(Calendar.DAY_OF_MONTH, termValue);
	                }
	                break;
	            case 'Y':
	                // Add two months
	                calendar.add(Calendar.MONTH, 2);
	                maxDayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	                if (termValue > maxDayInMonth) {
	                    calendar.set(Calendar.DAY_OF_MONTH, maxDayInMonth);
	                } else {
	                    calendar.set(Calendar.DAY_OF_MONTH, termValue);
	                }
	                break;
	            case 'Z':
	                // Add three months
	                calendar.add(Calendar.MONTH, 3);
	                maxDayInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	                if (termValue > maxDayInMonth) {
	                    calendar.set(Calendar.DAY_OF_MONTH, maxDayInMonth);
	                } else {
	                    calendar.set(Calendar.DAY_OF_MONTH, termValue);
	                }
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid term: " + term);
	        }

	        return calendar.getTime();
	    }

	  
	  public void rundueDateScript()
	  {
		  sendKeys(WebElements.globalSearchBox,"customdeploy_sch_due_date_processing_a");
		  clickOnElement(WebElements.scriptsearchResult);
		  clickOnElement(WebElements.editBtn);
		  hoverOnElement(WebElements.saveDropdown);
		  clickOnElement(WebElements.saveAndExecute);
		  
		Boolean scriptStatusPending=true;
		  while(scriptStatusPending) {
			  String scriptStatus= getText(WebElements.scriptStatus);
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
	  
	  public void verifyDueDate() throws InterruptedException
	  {
		  Thread.sleep(2000);
		  scrollToElement(WebElements.clickVendorId);
		  String actualDueDate=getText(WebElements.dueDate);
			System.out.println(actualDueDate);
			if(!actualDueDate.equals(expectedDueDate))
			{
				Assert.fail("Due date is not calculated correctly");
			}
	  }
	  
	  public void checkScheduledDueDateCheckBox() throws InterruptedException
	  {
		    clickOnElement(WebElements.customSubtab);
		    Thread.sleep(2000);
		    scrollToElement(WebElements.scheduledDueDateProcessedCheckBox);
			boolean scheduledDueDateProcessedCheckBox=findElement(WebElements.scheduledDueDateProcessedCheckBox).getAttribute("alt").equalsIgnoreCase("checked");
			if(!scheduledDueDateProcessedCheckBox)
			{
				Assert.fail("Script to update the due dates is not triggered");
			}
	  }
	  
	  
}
