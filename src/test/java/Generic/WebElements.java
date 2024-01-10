package Generic;

import org.openqa.selenium.By;


public class WebElements {


	//LoginPage

	public static By email  =By.id("email");
	public static By password=By.id("password");
	public static By submitBtn=By.name("submitButton");
	public static By chooseSB3AccountBtn=By.xpath("//td[contains(text(),'Wickes Building Supplies Limited__SB3')]//following-sibling::td[2]/a");
	public static By chooseSB2AccountBtn=By.xpath("//td[contains(text(),'Wickes Building Supplies Limited__SB2UAT')]//following-sibling::td[2]/a");
	public static By verifySandbox=By.xpath("//*[@id='uif43']");
	
	public static By verificationCode=By.xpath("//input[@placeholder='6-digit code']");
	public static By submitBtn2=By.xpath("//label[text()='Submit']");

	public static By trustDevice=By.xpath("//label[contains(text(),'Trust this device for 30 days')]/parent::div/span");
	public static By APManagerBtn=By.xpath("//td[contains(text(),'A/P Manager')]/following-sibling::td/a");

	public static By wickesDevBtn=By.xpath("//td[contains(text(),'Wickes - Developer')]/following-sibling::td/a");
	//HomePage
	public static By home=By.xpath("//div[@aria-label='Home']");
	public static By Lists=By.xpath("//span[text()='Lists']");
	public static By relationships=By.xpath("//*[contains(text(),'Relationships')]");
	public static By vendors=By.xpath("//*[contains(text(),'Vendors')]");
	public static By newVendor=By.xpath("//input[@value='New Vendor']");

	public static By Transactions=By.xpath("//span[text()='Transactions']");
	public static By payables=By.xpath("//*[contains(text(),'Payables')]");
	public static By enterBills=By.xpath("//*[contains(text(),'Enter Bills')]");
	public static By search=By.xpath("//*[@id='NS_MENU_ID0-item1']/a");
	public static By enterVendorCredits=By.xpath("//*[contains(text(),'Enter Vendor Credits')]");
	public static By financial=By.xpath("//*[contains(text(),'Financial')]");
	public static By makeJournalEntries=By.xpath("//*[contains(text(),'Make Journal Entries')]");
	public static By makeJournalEntriesList=By.xpath("//*[contains(text(),'Make Journal Entries')]/following-sibling::div/a[text()='List']");
	

	public static By Setup=By.xpath("//span[text()='Setup']");
	public static By company=By.xpath("//*[contains(text(),'Company')]");
	public static By costCentres=By.xpath("//*[contains(text(),'Cost Centres')]");
	public static By importExport=By.xpath("//*[contains(text(),'Import/Export')]");
	public static By savedCsvImports=By.xpath("//*[contains(text(),'Saved CSV Imports')]");
	public static By Payments=By.xpath("//span[text()='Payments']");
	public static By paymentProcessing=By.xpath("//*[contains(text(),'Payment Processing')]");
	public static By billPaymentProcessing=By.xpath("//*[contains(text(),'Bill Payment Processing')]");

	public static By purchases=By.xpath("//*[contains(text(),'Purchases')]");
	public static By enterPurchaseOrders=By.xpath("//*[contains(text(),'Enter Purchase Orders')]");

	public static By expandAll=By.xpath("//span[text()='Expand All']");




	public static By globalSearchBox=By.xpath("//input[@placeholder='Search']");
	public static By searchResult=By.xpath("//span[text()='Global Search Results']/ancestor::ul/li[2]");


	public static By journalsearchResult=By.xpath("//span[contains(text(),'Journal:')]");
	public static By billsearchResult=By.xpath("//span[contains(text(),'Bill:')]");
	public static By billCreditsearchResult=By.xpath("//span[text()='Bill Credit: ']");
	public static By vendorsearchResult=By.xpath("//span[contains(text(),'Vendor:')]");
	public static By scriptsearchResult=By.xpath("//span[text()='Current Page Results']/ancestor::ul/li[4]");
	


	//Autogeneration Supplier Number

	public static By companyName=By.xpath("//span[@id='companyname_fs']/input");
	public static By phoneNo=By.xpath("//input[@name='phone']");
	public static By vendorEmail=By.xpath("//input[@name='email']");
	public static By categoryDropdwn=By.xpath("//input[@name='inpt_category']");
	public static By stockCategory=By.xpath("//div[text()='Stock']");
	public static By nonStockCategory=By.xpath("//div[text()='Non-Stock']");
	public static By installerCategory=By.xpath("//div[text()='Installer']");
	public static By subsidiaryDropdwn=By.xpath("//input[@name='inpt_subsidiary']");
	public static By wickesSubsidiary=By.xpath("//div[contains(text(),'Wickes Building Supplies Limited')]");
	public static By financialSubtab=By.xpath("//a[@id='financialtxt']");
	public static By defaultItem=By.xpath("//input[@name='custentity_ns_wbs_default_item_display']");
	public static By currenciesSubtab=By.xpath("//a[text()='Currencies']");
	public static By saveBtn=By.xpath("//input[@id='btn_secondarymultibutton_submitter']");
	public static By saveDropdown=By.xpath("//td[@id='spn_multibutton_submitter']/following-sibling::td");
	public static By vendorSubmitBtn=By.xpath("//input[@value='Submit']");
	public static By approveBtn=By.xpath("//input[@value='Approve']");
	public static By rejectBtn=By.xpath("//input[@value='Reject']");
	public static By rejectionReason=By.xpath("//input[@id='custrecord_rejection_reason']");
	public static By rejectionSaveBtn=By.xpath("//td[@id='spn_multibutton_submitter']/input");
	public static By approvalSubtab=By.xpath("//a[text()='Appr']");
	public static By systemInformationSubtab=By.xpath("//a[@id='s_sysinfotxt']");
	public static By inactiveCheckBox=By.xpath("//span[@id='isinactive_fs']");
	public static By originalLineObject=By.xpath("//div[@data-nsps-label='Original Line Object']/span[2]");
	
	public static By rejectionReasonTxt=By.xpath("//span[@id='custentity_ns_va_rej_reas_fs_lbl_uir_label']/following-sibling::span");
	public static By editBtn=By.id("edit");
	public static By supplierIdUpdatedCheckBox=By.xpath("//span[@id='custentity_ns_wickes_supp_id_updt_fs']/img");


	public static By myRoles=By.xpath("//a[@id='uif44']");
	public static By currentRole=By.xpath("//span[text()='Logged in']/parent::div/parent::div/parent::div/descendant::span[1]");
	public static By APTeamLead=By.xpath("//label[text()='A/P Team Lead ']");
	public static By wickesDeveloper=By.xpath("//label[text()='Wickes - Developer']");
	public static By SB2Account=By.xpath("//span[normalize-space()='Wickes Building Supplies Limited__SB2UAT']");
	public static By authenticationQn=By.xpath("//td[contains(text(),'Question')]/following-sibling::td");
	public static By authenticationAnswer=By.xpath("//input[@type='password']");
	public static By authenticationSubmit=By.xpath("//input[@name='submitter']");
	public static By supplierId=By.xpath("//span[@id='entityid_fs_lbl_uir_label']/following-sibling::span");

	
	public static By vendorCreationSavedImport=By.xpath("//a[text()='Vendor Creation']");
	public static By selectBtn=By.xpath("//*[@id='tbl_select']");
	public static By nextBtn=By.xpath("//input[@id='next']");
	public static By fieldMappingnextBtn=By.xpath("//input[@id='secondarynext']");
	public static By saveAndRunBtn=By.xpath("//td[@data-nsps-label='Save & Run']");
	public static By RunBtn=By.xpath("//span[text()='Run']/parent::a");
	
	public static By vendorsList=By.cssSelector("table[id=div__bodytab] tr");
	public static By vendorNamesList=By.cssSelector("td:nth-child(5)");
	public static By vendorViewBtn=By.cssSelector("td:nth-child(3) a[class*='view']");
	//Cost Group and Business Function

	//CostCentres Page
	public static By listButton=By.xpath("//li[@id='NS_MENU_ID0-item0']/a");
	public static By costCentresList=By.cssSelector("table[id=div__bodytab] tr");
	public static By costCentreNames=By.cssSelector("td:nth-child(2)");
	public static By costGroupNames=By.cssSelector("td:nth-child(8)");
	public static By businessFunctionNames=By.cssSelector("td:nth-child(9)");
	public static By costCentreEditBtn=By.cssSelector("td:nth-child(1) a");
	
	

	//Journals Page
	public static By linesSubtab=By.xpath("//a[@id='linestxt']");
	public static By journalCostCentresList=By.cssSelector("table[id='line_splits'] tr");
	public static By journalCostCentreNames=By.cssSelector("td:nth-child(2)");
	public static By journalcostGroupNames=By.cssSelector("td:nth-child(3)");
	public static By journalbusinessFunctionNames=By.cssSelector("td:nth-child(4)");

	public static By currencyDropdwn=By.xpath("//input[@name='inpt_currency']");
	public static By currencyGBP=By.xpath("//div[contains(text(),'GBP')]");
	public static By journalType=By.xpath("//input[@name='custbody_ns_wbs_journal_type_display']");

	//GL Impact
	public static By glImpactSubtab=By.xpath("//a[@id='glimpacttabtxt']");
	public static By glPageDropdown=By.xpath("//span[@id='glimpact_glimpactrange_fs']/descendant::img");
	public static By glPagesList=By.cssSelector("div[class='dropdownDiv'] div");
	public static By glCostCentresList=By.cssSelector("div[id='glimpact_layer'] div[id='glimpact__div'] tr");

	public static By glCostCentreNames=By.cssSelector("td:nth-child(10)");
	public static By glcostGroupNames=By.cssSelector("td:nth-child(9)");
	public static By glbusinessFunctionNames=By.cssSelector("td:nth-child(13)");

	//Line Level
	public static By enterAccount=By.xpath("//input[@name='account_display']");
	public static By clickCostCentre=By.xpath("//td[@data-ns-tooltip='COST CENTRE']/div");
	public static By enterCostCentre=By.xpath("//input[@name='location_display']");
	public static By clickDebitAmount=By.xpath("//td[@data-ns-tooltip='DEBIT']");
	public static By enterDebitAmount=By.xpath("//input[@name='debit_formattedValue']");
	public static By clickCreditAmount=By.xpath("//table[@id='line_splits']/tbody/tr[3]/td[@data-ns-tooltip='CREDIT']");
	public static By enterCreditAmount=By.xpath("//input[@name='credit_formattedValue']");
	public static By addBtn=By.xpath("//input[@name='line_addedit']");

	public static By clickItem=By.xpath("//td[@data-ns-tooltip='ITEM']");
	public static By enterItem=By.xpath("//input[@name='item_display']");
	public static By clickRate=By.xpath("//td[@data-ns-tooltip='RATE']");
	public static By enterRate=By.xpath("//input[@name='rate_formattedValue']");
	public static By enterCostCentreBill=By.xpath("//span[@id='item_location_fs']/div/input[@name='location_display']");

	//Vendor Bills Page
	public static By referenceNo=By.xpath("//input[@id='tranid']");
	public static By vendor=By.xpath("//input[@name='entity_display']");
	public static By vendorDropdown=By.xpath("//span[@id='parent_actionbuttons_entity_fs']/a[2]");
	public static By vendorListBtn=By.xpath("//a[@id='entity_popup_list']");
	public static By vendorSearchBox=By.xpath("//input[@name='st']");
	public static By vendorSearchBtn=By.xpath("//input[@value='Search']");
	public static By selectVendorResult=By.xpath("//table[@id='popup_outer_table']/descendant::table[7]/descendant::td[2]");

	public static By costCentreDropdown=By.xpath("//span[@id='parent_actionbuttons_location_fs']/a[2]");
	public static By costCentreListBtn=By.xpath("//div[@id='location_fs_tooltipMenu']/a[@id='location_popup_list']");
	public static By costCentreSearchBox=By.xpath("//input[@name='st']");
	public static By costCentreSearchBtn=By.xpath("//input[@value='Search']");
	public static By selectcostCentreResult=By.xpath("//table[@id='popup_outer_table']/descendant::table[7]/descendant::td[2]");
	public static By postingPeriod=By.xpath("//input[@name='inpt_postingperiod']");
	public static By supplierInvoiceDate=By.xpath("//input[@name='custbody_ns_wbs_invoicedate']");
	public static By enterCostCentreHeader=By.xpath("//input[@name='location_display']");
	public static By costCentreReferenceHeader=By.xpath("//input[@name='custbody_ns_wbs_store_reference_display']");
	public static By billAddBtn=By.xpath("//input[@name='item_addedit']");
	public static By costCentreHeader=By.xpath("//div[@data-walkthrough='Field:location']/span[2]/span");
	public static By costGroupHeader=By.xpath("//div[@data-walkthrough='Field:department']/span[2]/span");
	public static By businessFunctionHeader=By.xpath("//div[@data-walkthrough='Field:cseg_ns_wbs_bfunc']/span[2]/span");
	public static By expenseAndItemsSubtab=By.xpath("//a[@id='itemstxt']");
	public static By BillCostCentresList=By.cssSelector("table[id='item_splits'] tr");
	public static By BillCostCentreNames=By.cssSelector("td:nth-child(4)");
	public static By BillcostGroupNames=By.cssSelector("td:nth-child(6)");
	public static By BillbusinessFunctionNames=By.cssSelector("td:nth-child(5)");


	//Vendor Credits Page

	public static By itemsSubtab=By.xpath("//a[@id='itemtxt']");
	public static By clickCostCentreCredit=By.xpath("//table[@id='item_splits']//td[@data-ns-tooltip='COST CENTRE']");

	public static By creditCostCentresList=By.cssSelector("table[id='item_splits'] tr");
	public static By creditCostCentreNames=By.cssSelector("td:nth-child(14)");
	public static By creditcostGroupNames=By.cssSelector("td:nth-child(15)");
	public static By creditbusinessFunctionNames=By.cssSelector("td:nth-child(16)");

	//Bill Payments Page
	public static By bankAccount=By.xpath("//input[@name='inpt_custpage_2663_bank_account']");
	public static By selectStockAccount=By.xpath("//div[contains(text(),'WICKES BUILDING MAIN - STOCK')]");

	public static By apAccount=By.xpath("//input[@name='inpt_custpage_2663_ap_account']");
	public static By selectApAccount=By.xpath("//div[contains(text(),'305100 CURRENT LIABILITIES : ACCOUNTS PAYABLE CONTROL : PURCHASE LEDGER CONTROL A/C')]");

	public static By transactionType=By.xpath("//input[@name='inpt_custpage_2663_transtype']");
	public static By selectBillType=By.xpath("//div[contains(text(),'Bill')]");

	public static By fromDueDate=By.xpath("//input[@name='custpage_2663_date_from']");
	public static By toDueDate=By.xpath("//input[@name='custpage_2663_date_to']");
	public static By paymentRef=By.xpath("//input[@name='custpage_2663_payment_ref']");
	public static By payCheckBox=By.xpath("//table[@id='custpage_2663_sublist_splits']/tbody/tr[2]/td[4]/span/img");
	public static By paymentSubmitBtn=By.xpath("//input[@name='secondarycustpage_submitter']");
	public static By paymentRefreshBtn=By.xpath("//input[@name='custpage_eft_aba_payment_refresh']");
	public static By billPaymentRecord=By.xpath("//table[@id='customsublist26__tab']/tbody/tr/td[2]/a");

	// InstallerVendor Bill
	public static By vbSavedsearchResults=By.xpath("//span[contains(text(),'Search:')]");
	public static By editSavedSearch=By.id("savesearch");
	public static By criteriaDropdownBox=By.xpath("//td[text()='KB Order Number (Custom Body)']");
	public static By setDescription=By.xpath("//a[@title='Set Description']");
	public static By searchIframe=By.xpath("//iframe[@id='filter_frame']");
	public static By enterOrderNumber=By.xpath("//span[@id='CUSTBODY_NS_WBS_KB_ORDER_NUMBER_fs']/input");
	public static By setBtn=By.xpath("//input[@id='set']");
	public static By saveAndRun=By.xpath("//input[@value='Save & Run']");

	public static By installerBillSearchList=By.cssSelector("table[id=div__bodytab] tr");
	public static By vendorNames=By.cssSelector("td:nth-child(7)");
	public static By remedialReasons=By.cssSelector("td:nth-child(11)");
	public static By viewButton=By.cssSelector("td a[aria-label*='View']");

	public static By getVendorId=By.xpath("//div[@data-walkthrough='Field:entity']/span[2]/span");
	public static By clickVendorId=By.xpath("//div[@data-walkthrough='Field:entity']/span[2]/span/a");
	public static By getCostCentreRefHeader=By.xpath("//div[@data-walkthrough='Field:custbody_ns_wbs_store_reference']/span[2]/span");


	public static By itemsRowList=By.cssSelector("table[id=item_splits] tr");
	public static By items=By.cssSelector("td:nth-child(2)");
	public static By itemCostAccount=By.cssSelector("td:nth-child(3)");
	public static By itemCostCentre=By.cssSelector("td:nth-child(4)");
	public static By itemDescription=By.cssSelector("td:nth-child(11)");
	public static By rate=By.cssSelector("td:nth-child(12)");
	public static By visionGrossAmount=By.cssSelector("td:nth-child(15)");
	public static By amount=By.cssSelector("td:nth-child(14)");
	public static By customerReference=By.cssSelector("td:nth-child(20)");
	public static By installerDataSubtab=By.xpath("//a[contains(text(),'nstaller Data')]");

	public static By kbOrderNumber=By.xpath("//a[contains(text(),'KB Order Number')]/ancestor::span/following-sibling::span");
	public static By paymentDifference=By.xpath("//a[contains(text(),'Payment Difference')]/ancestor::span/following-sibling::span");
	public static By remedialReason=By.xpath("//a[contains(text(),'Remedial Reason')]/ancestor::span/following-sibling::span");

	public static By cisLevel=By.xpath("//div[@data-walkthrough='Field:custentity_ns_wbs_cislevel']/span[2]/span/a");
	public static By getDefaultItem=By.xpath("//div[@data-walkthrough='Field:custentity_ns_wbs_default_item']/span[2]/span/a");
	public static By term=By.xpath("//div[@data-walkthrough='Field:terms']/span[2]/span");
	
	public static By glLines=By.cssSelector("div[id='glimpact_layer'] div[id='glimpact__div'] tr");
	public static By glCostAccount=By.cssSelector("td:nth-child(2)");
	public static By glDebitAmount=By.cssSelector("td:nth-child(3)");
	public static By glCreditAmount=By.cssSelector("td:nth-child(4)");
	public static By glPosting=By.cssSelector("td:nth-child(5)");
	public static By glMemo=By.cssSelector("td:nth-child(6)");
	public static By glName=By.cssSelector("td:nth-child(7)");
	public static By glCostCentre=By.cssSelector("td:nth-child(10)");
	
	
	//Cost Centre
	public static By newCostCentre=By.xpath("//span[text()='New']");
	public static By costCentreName=By.xpath("//span[@id='name_fs']/input");
	public static By costGroupDropDown=By.xpath("//input[@name='inpt_custrecord_ns_wbs_loc_cost_group']");
	public static By selectCostGroup=By.xpath("//div[contains(text(),'Store')]");
	public static By businessFunctionDropDown=By.xpath("//input[@name='inpt_custrecord_ns_wbs_loc_business_function']");
	public static By selectBusinessFunction=By.xpath("//div[contains(text(),'N/A')]");
	
	//APCoding
	public static By itemReplacementCheckBox=By.xpath("//span[@id='custbody_ns_wbs_item_replacement_trigg_fs']/img");
	
	public static By invoiceItemsList=By.cssSelector("table[id=item_splits] tr td:nth-child(2)");
	public static By creditItemsList=By.cssSelector("table[id=item_splits] tr td:nth-child(1)");
	


	//readsoft
	public static By loginSSO=By.xpath("//a[contains(text(),'Log in using Wickes SSO')]");
	public static By readsoftUserName=By.xpath("//input[@id='userName']");
	public static By readsoftPassword=By.xpath("//input[@id='password']");
	public static By readsoftLoginBtn=By.xpath("//input[@id='loginButton']");
	public static By pdfDocument=By.xpath("//*[@class='grid__body']/div/div[1]");
	public static By documentType=By.xpath("//select[@id='input-4_DocumentSubType']");
	public static By currencyType=By.xpath("//select[@id='input-16']");
	public static By startBtn=By.xpath("//span[contains(text(),'Start')]");


	public static By trackID=By.xpath("//span[contains(text(),'Track ID')]");
	public static By documentNumber=By.xpath("//input[@id='input-5']");
	public static By documentDate=By.xpath("//input[@id='input-6']");
	public static By readsoftCostCentre=By.xpath("//input[@id='input-10-display-mode']");
	public static By readsoftCostCentre1=By.xpath("//input[@id='input-10']");

	public static By supplierName=By.xpath("//span[contains(text(),'Supplier name')]");
	public static By supplierSearchBox=By.xpath("//input[@id='searchPhrase']");
	public static By supplierSearchResult=By.xpath("//div[@id='searchResultViewer']/descendant::div[@class='grid__row ']");
	public static By supplierReset=By.xpath("//div[@id='resetFiltersButton']");
	public static By supplierOkButton=By.xpath("//div[@class='dialog__footer']//button[text()='OK']");

	public static By readsoftCC=By.xpath("//span[contains(text(),'Cost Centre')]");
	public static By readsoftCookies=By.xpath("//span[text()='OK']");

	public static By totalNetAmount=By.xpath("//input[@id='input-11']");
	public static By totalVatRate=By.xpath("//input[@id='input-12']");
	public static By totalVatAmount=By.xpath("//input[@id='input-13']");
	public static By deliveryCost=By.xpath("//input[@id='input-14']");
	public static By totalGrossAmount=By.xpath("//input[@id='input-15']");


	public static By allLinesCheckBox=By.xpath("//div[@id='lineItems']//input[@type='checkbox']");
	public static By deleteLineItems=By.xpath("//div[@id='panel-LineItem-delete']/span");
	public static By deleteOkBtn=By.xpath("//button[@id='okPopoverBtn']");

	public static By addLineItems=By.xpath("//div[@id='panel-LineItem-add']/span");

	public static By LineItemsRows=By.cssSelector("div[id='LineItem-itemPanel'] div[class='row']");

	//public static By lineItemDescription=By.xpath("//input[@id='input-1-1-LineItem']");
	public static By lineItemDescription=By.cssSelector("div:nth-child(4) input[id*='LineItem']");
	public static By lineItemQuantity=By.cssSelector("div:nth-child(6) input");
	public static By lineItemUnitPrice=By.cssSelector("div:nth-child(7) input");
	public static By lineItemNetAmount=By.cssSelector("div:nth-child(8) input");
	public static By lineItemSupplierProductCode=By.cssSelector("div:nth-child(10) input");
	public static By lineItemWickesProductCode=By.cssSelector("div:nth-child(11) input");
	public static By readsoftOkBtn=By.xpath("//button[@id='approveButton']");
	
	public static By processed=By.xpath("//span[contains(text(),'Processed')]");
	public static By verify=By.xpath("//span[contains(text(),'Verify')]");
	public static By trackIdTextBox=By.xpath("//input[@id='grd-txt-TrackId']");
	public static By processedSearchResults=By.xpath("//div[@class='grid__body']//div[@class='grid__row ']");
   // public static By DocSearchResults=By.xpath("//span[contains(text(),'No documents matched')]");
	public static By DocSearchResults=By.xpath("//*[@id=\"window\"]/div/div/div[1]/div/div/div[2]/div/div");
	public static By readsoftRefreshBtn=By.xpath("//button[@id='refreshButton']");
	public static By explore=By.xpath("//label[text()='Explore']");
	public static By masterData=By.xpath("//label[text()='Master data']");
	public static By readsoftSuppliersPage=By.xpath("//label[text()='Suppliers']");
	public static By readsoftSupplierSearch=By.xpath("//span[text()='SUPPLIER NUMBER']/ancestor::thead/tr[2]/th[2]/input");
	public static By readsoftSearchResult=By.xpath("//div[contains(text(),' Total: 1')]");
	public static By InactiveSearchResult=By.xpath("//div[contains(text(),' Total: 0')]");
	
	public static By synchronizeBtn=By.xpath("//span[text()='Synchronize']");
	public static By resetAllFilters=By.xpath("//a[@title='Reset all filters']");
	
	public static By readsoftlocationsPage=By.xpath("//label[text()='location']");
	public static By readsoftLocationSearch=By.xpath("//span[text()='NAME']/ancestor::thead/tr[2]/th[4]/input");
	
	//Due Dates
	public static By supplierInvoiceDateTxt=By.xpath("//div[@data-walkthrough='Field:custbody_ns_wbs_invoicedate']/span[2]");
	public static By dueDate=By.xpath("//div[@data-walkthrough='Field:duedate']/span[2]");
	public static By dueDateHoldingValue=By.xpath("//div[@data-walkthrough='Field:custbodywickes_due_date_holding222']/span[2]");
	public static By saveAndExecute=By.xpath("//span[text()='Save and Execute']");
	public static By scriptStatus=By.xpath("//tr[@id='row0']/td[4]");
	public static By refreshBtn=By.xpath("//input[@name='refresh']");
	public static By customSubtab=By.xpath("//a[@id='customtxt']");
	public static By scheduledDueDateProcessedCheckBox=By.xpath("//span[@id='custbody_ns_sch_due_date_processed_fs']/img");
	
	
	//CSV Import
	public static By documents=By.xpath("//span[text()='Documents']");
	public static By files=By.xpath("//span[text()='Files']");
	public static By fileCabinet=By.xpath("//span[text()='File Cabinet']");
	public static By csvImportsFolder=By.xpath("//a[text()='CSV Imports']");
	
	public static By addFileBtn=By.xpath("//input[@id='mediafile']");
	public static By deploymentsSubtab=By.xpath("//a[text()='eployments']");
	
	public static By expandFilters=By.xpath("//span[@aria-label='Expand/Collapse filters']");
	public static By MapReduceScriptStatus=By.xpath("//tr[@id='row0']/td[6]");
	public static By scriptType=By.xpath("//input[@name='inpt_scripttype']");
	public static By deploymentId=By.xpath("//input[@name='inpt_primarykey']");
	public static By approvalStatus=By.xpath("//div[@data-walkthrough='Field:approvalstatus']/span[2]/span");
	public static By gfrDataSubtab=By.xpath("//a[contains(text(),'GFR')]");
	public static By didosVerifiedCheckBox=By.xpath("//span[@id='custbody_ns_wbs_didos_verified_fs']/img");
	
	
	public static By newStagingTableRecordBtn=By.xpath("//input[@id='new']");
	public static By externalIdStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_externalid']");
	public static By tranIDStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_tranid']");
	public static By vendorStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_entity']");
	public static By sourceStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_source']");
	public static By locationStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_location']");
	public static By storeRefdStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_store_ref']");
	public static By subsidiaryStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_subsidiary']");
	public static By currencyStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_currency']");
	public static By exchangeRateStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_exchange_rate']");
	public static By poRefStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_po_ref']");
	public static By tranDateStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_trandate']");
	public static By dueDateStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_due_date']");
	public static By memoStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_memo']");
	public static By grossValueStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_edi_gross_valu']");
	public static By tranTypeStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_vbvc_tran_type']");
	
	public static By itemStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_line_level_item']");
	public static By productCodeStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_product_code']");
	public static By quantityStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_line_level_qty']");
	public static By descriptionStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_line_level_memo']");
	public static By rateStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_line_level_rate']");
	public static By taxCodeStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_line_level_tax_code']");
	
	public static By internalIdStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_internal_id']");
	public static By didosVerifiedStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_didos_verified']");
	
	
	public static By journalexternalIdStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_externalid']");
	public static By journalsourceStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_source']");
	public static By journalstoreRefdStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_store_ref']");
	public static By journalsubsidiaryStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_subsidiary']");
	public static By journalcurrencyStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_currency']");
	public static By journalmemoStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_memo']");
	public static By journaldelNoteStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_del_note']");
	public static By journalorderNoStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_order_no']");
	public static By journalsupplierCodeStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_supplier_code']");
	public static By journalgrnoStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_grno_no']");
	public static By journalTypeStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_journal_type_hl']");
	public static By journalTransactionTypeStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_tran_type']");
	
	public static By journalAccountStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_ll_account']");
	public static By journaldebitAmountStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_ll_debit']");
	public static By journalcreditAmountStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_ll_credit']");
	public static By journalLocationTypeStagingTable=By.xpath("//input[@name='custrecord_ns_wbs_jrnl_ll_location']");
	
	
	
	
	public static By stagingtableErrorMsg=By.xpath("//td[contains(text(),'Notice')]/ancestor::tbody[1]/descendant::td[@class='text'][2]");
	
	public static By gobackBtn=By.xpath("//input[@id='goback']");
	public static By importJobStatusBtn=By.xpath("//a[text()='Import Job Status.']");
	public static By importJobStatus=By.xpath("//tr[@id='row0']/td[3]");
	public static By importJobMessage=By.xpath("//tr[@id='row0']/td[5]");
	public static By csvResponse=By.xpath("//tr[@id='row0']/td[6]/a");
	public static By lastCreatedJournal=By.cssSelector("table[id=div__bodytab] tr[id=row0] td:nth-child(1)  a[class*='view']");
	public static By JournalExternalId=By.xpath("//div[@data-walkthrough='Field:custbody_ns_wbs_external_id']/span[2]");
	public static By lastCreatedCsvHoldRecord=By.xpath("//tr[@id='row0']//a[@class='dottedlink viewitem']");
	public static By dateCreatedHeader=By.xpath("//div[contains(text(),'Date Created')]");
	public static By popupBox=By.xpath("//div[@data-style='popup']");
	public static By popupCloseBtn=By.xpath("//div[@data-style='popup']/div[1]/div");
	public static By popupCheckBox=By.xpath("//div[@data-style='popup']/descendant::div[@data-widget='CheckBox']");
	public static By errorMessage=By.xpath("//div[@data-walkthrough='Field:custrecord_ns_wbs_e_message']/span[2]");
	public static By csvImportStagingTableRecordId=By.xpath("//div[@data-walkthrough='Field:custrecord_ns_wbs_linked_staging_record']/span[2]");
	
	
	public static By InternalIdColumnNo=By.xpath("//td[@data-label='Internal ID']");
	public static By stagingTableRecordList=By.cssSelector("table[id=div__bodytab] tr");
	public static By internalId=By.cssSelector("td:nth-child(7)");
	public static By stagingTableviewButton=By.cssSelector("td:nth-child(1) a[class*='view']");
	
	public static By StagingTableExternalIdValue=By.xpath("//div[@data-nsps-label='externalId']/span[2]");
	public static By StagingTableTranIdValue=By.xpath("//div[@data-walkthrough='Field:custrecord_ns_wbs_tranid']/span[2]");
	public static By StagingTableVendorName=By.xpath("//div[@data-walkthrough='Field:custrecord_ns_wbs_entity']/span[2]");
	public static By StagingTablejrnlsSupplierNumber=By.xpath("//div[@data-walkthrough='Field:custrecord_ns_wbs_jrnl_supplier_code']/span[2]");
	public static By lineEditBtn=By.xpath("//a[text()='Edit']");
	public static By mainStagingTableTaxcode=By.xpath("//table[@id='recmachcustrecord_ns_wbs_table_reference__tab']/tbody/tr/td[8]");
	public static By processedCheckBox=By.xpath("//div[@data-nsps-label='Processed']/span/span/img");
	public static By StagingTableInternalIdValue=By.xpath("//div[@data-walkthrough='Field:custrecord_ns_wbs_internal_id']/span[2]");
	
}
