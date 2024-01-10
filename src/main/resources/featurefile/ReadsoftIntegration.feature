Feature: Readsoft Integration

 @AP @Demo
  Scenario: User creates the GFR invoice
    Given User is logged into the Readsoft Application with AP Manager role
    When User creates the "GFR invoice"
    When User logs into Netsuite Application
    Then User verifies the newly created "GFR invoice" is present in Netsuite

  @AP @Demo
  Scenario: User verifies AP coding script replaces the item with default item for the GFR invoice created via Readsoft
    Given User is in the GFR invoice record
    When User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the expenses and items subtab
   
   
    @AP
    Scenario: User verifies the AP coding script stores the original line level data of invoice in original line object field in json format
    Given User is in the GFR invoice record
    When User navigates to the system information subtab
    Then User verifies the Line level data of "invoice" is stored in line object field in json format
    @AP
    Scenario: User edits item in the GFR invoice after the creation from readsoft
    Given User is in the GFR invoice record
    When User opens the record in edit mode
    And User navigates to the expense and item subtab
    And User updates the item and saves the record
    Then User verifies the "GFR invoice" got saved with the updated item
    
    
    
    
#
  @Demo
  Scenario: User creates the GFR credit
    Given User is in the readsoft HomePage
    When User creates the "GFR credit"
    And User logs into Netsuite Application
    Then User verifies the newly created "GFR credit" is present in Netsuite

  @Demo @closeDriver
  Scenario: User verifies AP coding script replaces the item with default item for the GFR credit created via Readsoft
    Given User is in the GFR credit record
    When User gets the default item from the supplier record
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the items subtab for the credit
 @asp
    Scenario: User edits item in the GFR credit after the creation from readsoft
    Given User is in the GFR credit record
    When User opens the record in edit mode
    And User navigates to the expense and item subtab
    And User updates the item and saves the record
    Then User verifies the "GFR credit" got saved with the updated item
 
  Scenario: User creates the GNFR invoice
    Given User is in the readsoft HomePage
    When User creates the "GNFR invoice"
    And User logs into Netsuite Application
    Then User verifies the newly created "GNFR invoice" is present in Netsuite

  Scenario: User verifies AP coding script replaces the item with default item for the GNFR invoice created via Readsoft
    Given User is in the GNFR invoice record
    When User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the expenses and items subtab

  Scenario: User creates the GFR import invoice
    Given User is in the readsoft HomePage
    When User creates the "GFR import invoice"
    And User logs into Netsuite Application
    Then User verifies the newly created "GFR import invoice" is present in Netsuite

  Scenario: User verifies AP coding script replaces the item with default item for the GFR import invoice created via Readsoft
    Given User is in the GFR import invoice record
    When User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the expenses and items subtab

  Scenario: User creates the GNFR import invoice
    Given User is in the readsoft HomePage
    When User creates the "GNFR import invoice"
    And User logs into Netsuite Application
    Then User verifies the newly created "GNFR import invoice" is present in Netsuite

  Scenario: User verifies AP coding script replaces the item with default item for the GFR import invoice created via Readsoft
    Given User is in the GFR import invoice record
    When User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the expenses and items subtab

  Scenario: User creates the GNFR credit
    Given User is in the readsoft HomePage
    When User creates the "GNFR credit"
    And User logs into Netsuite Application
    Then User verifies the newly created "GNFR credit" is present in Netsuite

  Scenario: User verifies AP coding script replaces the item with default item for the GNFR credit created via Readsoft
    Given User is in the GNFR credit record
    When User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the expenses and items subtab

  Scenario: User verifies AP coding script replaces the item with default item for the GFR invoice with multiple lines created via Readsoft
    Given User is logged into the Readsoft Application with AP Manager role
    When User creates the "GFR invoice with multiple Line items"
    And User logs into Netsuite Application
    And User opens the "GFR invoice" record
    And User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the expenses and items subtab
    
    
    Scenario: User verifies AP coding script replaces the item with default item for the GNFR invoice with multiple lines created via Readsoft
    Given User is in the readsoft HomePage
    When User creates the "GNFR invoice with multiple Line items"
    And User logs into Netsuite Application
    And User opens the "GNFR invoice" record
    And User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the expenses and items subtab
    @APs
    Scenario: User verifies AP coding script replaces the item with default item for the GFR credit with multiple lines created via Readsoft
    Given User is in the readsoft HomePage
    When User creates the "GFR credit with multiple Line items"
    And User logs into Netsuite Application
    And User opens the "GFR credit" record
    And User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the items subtab for the credit
    @APs
    Scenario: User verifies AP coding script replaces the item with default item for the GNFR credit with multiple lines created via Readsoft
    Given User is in the readsoft HomePage
    When User creates the "GNFR credit with multiple Line items"
    And User logs into Netsuite Application
    And User opens the "GNFR credit" record
    And User gets the default item from the supplier record
    And User navigates to the system information subtab
    Then User verifies the item replacement is triggered
    And User verifies the item is replaced by the default item in the items subtab for the credit
    
    
    
    
    
  @NetsuiteToReadsoft
  Scenario: User verifies the stock supplier created in Netsuite is sent to Readsoft
    Given User is logged into the Netsuite Application with Wickes developer role
    When User creates the "stock" vendor
    And User logs in with AP manager role
    And User approves the vendor
    And User logs into Readsoft application with Admin role
    And User navigates to the supplier master data
    Then User verifies the "stock" supplier created in Netsuite is sent to Readsoft

  @NetsuiteToReadsoft
  Scenario: User verifies the stock supplier updated in Netsuite is sent to Readsoft
    Given User logs into Netsuite Application
    When User updates the "stock" vendor
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the "stock" supplier updated in Netsuite is sent to Readsoft

  @NetsuiteToReadsoft
  Scenario: User verifies the stock supplier made inactive in Netsuite is updated in Readsoft
    Given User logs into Netsuite Application
    When User updates the "stock" vendor to inactive
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the inactive supplier is not present in Readsoft
    
    
     @NetsuiteToReadsoft
  Scenario: User verifies the Non stock supplier created in Netsuite is sent to Readsoft
    Given User logs into Netsuite Application
    When User creates the "Non stock" vendor
    And User switches to the AP manager role
    And User approves the vendor
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the "Non stock" supplier created in Netsuite is sent to Readsoft

  @NetsuiteToReadsoft
  Scenario: User verifies the Non stock supplier updated in Netsuite is sent to Readsoft
    Given User logs into Netsuite Application
    When User updates the "Non stock" vendor
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the "Non stock" supplier updated in Netsuite is sent to Readsoft

  @NetsuiteToReadsoft
  Scenario: User verifies the Non stock supplier made inactive in Netsuite is updated in Readsoft
    Given User logs into Netsuite Application
    When User updates the "Non stock" vendor to inactive
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the inactive supplier is not present in Readsoft
    
    @NetsuiteToReadsoft
  Scenario: User verifies the Installer supplier created in Netsuite is sent to Readsoft
    Given User logs into Netsuite Application
    When User creates the "Installer" vendor
    And User switches to the AP manager role
    And User approves the vendor
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the "Installer" supplier created in Netsuite is sent to Readsoft

  @NetsuiteToReadsoft
  Scenario: User verifies the Installer supplier updated in Netsuite is sent to Readsoft
    Given User logs into Netsuite Application
    When User updates the "Installer" vendor
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the "Installer" supplier updated in Netsuite is sent to Readsoft

  @NetsuiteToReadsoft
  Scenario: User verifies the Installer supplier made inactive in Netsuite is updated in Readsoft
    Given User logs into Netsuite Application
    When User updates the "Installer" vendor to inactive
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the inactive supplier is not present in Readsoft
    
    
    
    @NetsuiteToReadsoft
    Scenario: User verifies the cost centre created in Netsuite is sent to Readsoft
    Given User is logged into the Netsuite Application with Wickes developer role
    When User creates a new cost centre
    And User logs into Readsoft application with Admin role
    And User navigates to the location master data
    Then User verifies the newly created cost centre created in Netsuite is sent to Readsoft

  @NetsuiteToReadsoft
  Scenario: User verifies the cost centre updated in Netsuite is sent to Readsoft
    Given User logs into Netsuite Application
    When User updates the cost centre
   And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the cost centre updated in Netsuite is sent to Readsoft

  @NetsuiteToReadsoft
  Scenario: User verifies the cost centre made inactive in Netsuite is updated in Readsoft
    Given User logs into Netsuite Application
    When User updates the cost centre to inactive
    And User switches to the Readsoft application with Admin role
    And User clears the filter criteria
    Then User verifies the inactive cost centre is not present in Readsoft
    
  
