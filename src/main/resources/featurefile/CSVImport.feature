Feature: CSV Import
#@Demo
  #Scenario: User verifies the csv import script creates the vendor bill for the data sent from Truecommerce(EDI)
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    #When User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Vendor Bills - True Commerce - For Processing" folder
    #When User creates the "TCVendorBill" file
    #And User uploads the "TCVendorBill" file
    #And User executes the "TC VB - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #Then User opens the "TC Vendor bill"
#
#
 #@Demo
    #Scenario: User verifies the csv import script creates the vendor credit for the data sent from Truecommerce(EDI)
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    #When User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Vendor Credits - True Commerce - For Processing" folder
    #When User creates the "TCVendorCredit" file
    #And User uploads the "TCVendorCredit" file
    #And User executes the "TC VC - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #Then User opens the "TC Vendor credit"
    #
 #
 #
    #Scenario: User verifies the csv import script updates the vendor bill for the data sent from Didos
    #Given User is in the Netsuite HomePage
    #When User opens the "Stock Vendor bill"
    #And User gets the internal ID of the bill
    #And User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Vendor Bills - Didos - For Processing" folder
    #When User creates the "DidosVendorBill" file with the internal ID of the stock bill 
    #And User uploads the "DidosVendorBill" file
    #And User executes the "Didos VB - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #And User opens the "Vendor bill"
    #Then User verifies the bill status is updated to approved
    #
      #Scenario: User verifies the csv import script updates the vendor credit for the data sent from Didos
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    #When User opens the "Stock Vendor credit"
    #And User gets the internal ID of the bill
    #And User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Vendor Credits - Didos - For Processing" folder
    #When User creates the "DidosVendorCredit" file with the internal ID of the stock bill 
    #And User uploads the "DidosVendorCredit" file
    #And User executes the "Didos - VC - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #And User opens the "Vendor credit"
    #Then User verifies the didos verified is changed to true in Credit
    #
    #
    #Scenario: User verifies the csv import script creates the Journal entry for the data sent from Didos
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    #When User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Journal Entries - Didos - For Processing" folder
    #When User creates the "DidosJournalEntry" file
    #And User uploads the "DidosJournalEntry" file
    #And User executes the "Didos JE - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #Then User opens the "Didos Journal Entry"
    #
    #Background:
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role
    #
    #Scenario Outline: User creates a record in <stagingTableName> via UI
    #Given User is in the Netsuite HomePage
    #When User opens the <stagingTableName> list
    #And User opens  <stagingTableName> new record
    #And User enters the necessary details in the <stagingTableName> record
    #And User saves the record
    #Then User verifies the Netsuite is showing error message
    #
      #Examples: 
      #| stagingTableName   |
      #|Vendor Bill/Credit Staging Table (Main)|
      #|Vendor Bill/Credit Staging Table (Line)|
      #|Vendor Bill/Credit Staging Table (Edit)|
      #|Journal CSV Staging Table (Main)|
      #|Journal CSV Staging Table (Line)|
      #
   #
    #Scenario Outline: User creates a record in <stagingTableName> via CSV Import
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role
    #When  User creates the record in <stagingTableName> using csv import
    #And   User verifies the job status of the import
    #And User downloads the csv response file of the import
    #Then User verifies the error message in the csv file
    #
      #Examples: 
      #| stagingTableName   |
      #|Vendor Bill/Credit Staging Table (Main)|
      #|Vendor Bill/Credit Staging Table (Line)|
      #|Vendor Bill/Credit Staging Table (Edit)|
      #|Journal CSV Staging Table (Main)|
      #|Journal CSV Staging Table (Line)|
      #
      #
      #
      
     #Scenario: User Verifies the Custom Record entry is created with error message for the failed vendor bill record from Truecommerce(EDI)
      #
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    #When User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Vendor Bills - True Commerce - For Processing" folder
    #When User creates the "TCVendorBill" file
    #And User uploads the "TCVendorBill" file
    #And User executes the "TC VB - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #When User opens the "On Hold" saved search
    #Then User verifies the Custom Record entry is created with error message for the failed records
    #
    #
    #Scenario: User verifies the failed vendor bill record is stored in the Vendor Bill/Credit Staging Tables
    #
    #Given User is in the Netsuite HomePage
    #When User opens the "On Hold" saved search
    #And User gets the staging table record ID
    #And User opens the "Vendor Bill/Credit Staging Table (Main)" saved search
    #And User opens the staging table record 
    #Then User verifies the staging table holds the value of the failed vendor bill/credit record
    #
    #
    #Scenario: User updates the vendor bill record in the Vendor Bill/Credit Staging Table via UI
    #
    #Given User is in the "Vendor Bill/Credit Staging Table (Main)" record
    #When User opens the staging table line record in edit mode
    #And User updates the "taxcode" field 
    #And User saves the staging table record
    #Then User verifies the "Vendor Bill/Credit" staging table record got saved with the updated data
    #
    #
    #Scenario: User verifies the script processes the records in the staging table and creates EDI vendor bill
    #
    #Given User is in the Netsuite HomePage
    #When User executes the "TC VB - From Staging Table" script to import the files
    #And User waits for the script run to complete
    #Then User opens the "TC Vendor bill"
    #And User verfies the processed flag is set to true for the record in the "Vendor Bill/Credit Staging Table"
    
    
    
    
    #
    #Scenario: User Verifies the Custom Record entry is created with error message for the failed vendor credit record from Truecommerce(EDI)
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    #When User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Vendor Credits - True Commerce - For Processing" folder
    #When User creates the "TCVendorCredit" file
    #And User uploads the "TCVendorCredit" file
    #And User executes the "TC VC - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #When User opens the "On Hold" saved search
    #Then User verifies the Custom Record entry is created with error message for the failed records
    #
    #
     #Scenario: User verifies the failed vendor credit record is stored in the Vendor Bill/Credit Staging Tables
    #
    #Given User is in the Netsuite HomePage
    #When User opens the "On Hold" saved search
    #And User gets the staging table record ID
    #And User opens the "Vendor Bill/Credit Staging Table (Main)" saved search
    #And User opens the staging table record 
    #Then User verifies the staging table holds the value of the failed vendor bill/credit record
    #
    #
    #Scenario: User updates the vendor credit record in the Vendor Bill/Credit Staging Table via UI
    #
    #Given User is in the "Vendor Bill/Credit Staging Table (Main)" record
    #When User opens the staging table line record in edit mode
    #And User updates the "taxcode" field 
    #And User saves the staging table record
    #Then User verifies the "Vendor Bill/Credit" staging table record got saved with the updated data
    #
    #
    #Scenario: User verifies the script processes the records in the staging table and creates EDI vendor credit
    #
    #Given User is in the Netsuite HomePage
    #When User executes the "TC VC - From Staging Table" script to import the files
    #And User waits for the script run to complete
    #Then User opens the "TC Vendor credit"
    #And User verfies the processed flag is set to true for the record in the "Vendor Bill/Credit Staging Table"
    #
    
    
     #Scenario: User Verifies the Custom Record entry is created with error message for the failed journal entry record from Didos
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    #When User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Journal Entries - Didos - For Processing" folder
    #When User creates the "DidosJournalEntry" file
    #And User uploads the "DidosJournalEntry" file
    #And User executes the "Didos JE - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #When User opens the "On Hold" saved search
    #Then User verifies the Custom Record entry is created with error message for the failed records
    #
    #
     #Scenario: User verifies the failed journal entry record is stored in the Journal CSV Staging Table
    #
    #Given User is in the Netsuite HomePage
    #When User opens the "On Hold" saved search
    #And User gets the staging table record ID
    #And User opens the "Journal CSV Staging Table (Main)" saved search
    #And User opens the staging table record 
    #Then User verifies the staging table holds the value of the failed "journal entry" record
    #
    #
    #Scenario: User updates the journal entry record in the Journal CSV Staging Table via UI
    #
    #Given User is in the "Journal CSV Staging Table (Main)" record
    #When User opens the staging table record in edit mode
    #And User updates the "supplier Number" field 
    #And User saves the staging table record
    #Then User verifies the "Journal" staging table record got saved with the updated data
    #
    #
    #Scenario: User verifies the script processes the records in the staging table and creates didos journal entry
    #
    #Given User is in the Netsuite HomePage
    #When User executes the "Didos JE - From Staging Table" script to import the files
    #And User waits for the script run to complete
    #Then User opens the "Didos Journal Entry"
    #And User verfies the processed flag is set to true for the record in the "Journal CSV Staging Table"
    
    #Scenario: User Verifies the Custom Record entry is created with error message for the failed vendor bill record from Didos
    #
    #Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    #When User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Vendor Bills - True Commerce - For Processing" folder
    #When User creates the "TCVendorBill" file
    #And User uploads the "TCVendorBill" file
    #And User executes the "TC VB - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #And User opens the "TC Vendor bill"
    #And User gets the internal ID of the bill
    #And User navigates to the CSV Imports folder in the File cabinet
    #And User opens the "Vendor Bills - Didos - For Processing" folder
    #When User creates the "DidosVendorBill" file with the invalid internal ID of the stock bill 
    #And User uploads the "DidosVendorBill" file
    #And User executes the "Didos VB - From FOLDER" script to import the files
    #And User waits for the script run to complete
    #When User opens the "On Hold" saved search
    #Then User verifies the Custom Record entry is created with error message for the failed records
    #
    #Scenario: User verifies the failed vendor bill edit record from Didos is stored in the Vendor Bill/Credit Staging Table (Edit)
    #
    #Given User is in the Netsuite HomePage
    #When User opens the "On Hold" saved search
    #And User gets the staging table record ID
    #And User opens the "Vendor Bill/Credit Staging Table (Edit)" saved search
    #And User opens the staging table record 
    #Then User verifies the staging table holds the value of the failed "vendor bill edit" record
    #
      #Scenario: User updates the vendor bill edit record from Didos in the Vendor Bill/Credit Staging Table (Edit) via UI
    #
    #Given User is in the "Vendor Bill/Credit Staging Table (Edit)" record
    #When User opens the staging table record in edit mode
    #And User updates the "internalId" field 
    #And User saves the staging table record
    #Then User verifies the "vendor bill edit" staging table record got saved with the updated data
    #
     #Scenario: User verifies the script processes the records in the staging table and updates the vendor bill
    #
    #Given User is in the Netsuite HomePage
    #When User executes the "Didos VB - From Staging Table" script to import the files
    #And User waits for the script run to complete
    #And User opens the "Vendor bill"
    #Then User verifies the bill status is updated to approved
    #And User verfies the processed flag is set to true for the record in the "Vendor Bill/Credit Staging Table (Edit)"
    
    Scenario: User Verifies the Custom Record entry is created with error message for the failed vendor credit record from Didos
    
    Given User is logged into the Netsuite Sandbox2 with Wickes developer role 
    When User navigates to the CSV Imports folder in the File cabinet
    And User opens the "Vendor Credits - True Commerce - For Processing" folder
    When User creates the "TCVendorCredit" file
    And User uploads the "TCVendorCredit" file
    And User executes the "TC VC - From FOLDER" script to import the files
    And User waits for the script run to complete
     When User opens the "Stock Vendor credit"
    And User gets the internal ID of the bill
    And User navigates to the CSV Imports folder in the File cabinet
    And User opens the "Vendor Credits - Didos - For Processing" folder
   When User creates the "DidosVendorCredit" file with the invalid internal ID of the stock bill 
    And User uploads the "DidosVendorCredit" file
    And User executes the "Didos - VC - From FOLDER" script to import the files
    And User waits for the script run to complete
     When User opens the "On Hold" saved search
    Then User verifies the Custom Record entry is created with error message for the failed records
    
    
    Scenario: User verifies the failed vendor credit edit record from Didos is stored in the Vendor Bill/Credit Staging Table (Edit)
    
    Given User is in the Netsuite HomePage
    When User opens the "On Hold" saved search
    And User gets the staging table record ID
    And User opens the "Vendor Bill/Credit Staging Table (Edit)" saved search
    And User opens the staging table record 
    Then User verifies the staging table holds the value of the failed "vendor credit edit" record
    
      Scenario: User updates the vendor credit edit record from Didos in the Vendor Bill/Credit Staging Table (Edit) via UI
    
    Given User is in the "Vendor Bill/Credit Staging Table (Edit)" record
    When User opens the staging table record in edit mode
    And User updates the "internalId" field 
    And User saves the staging table record
    Then User verifies the "vendor credit edit" staging table record got saved with the updated data
    
     Scenario: User verifies the script processes the records in the staging table and updates the vendor credit
    
    Given User is in the Netsuite HomePage
    When User executes the "Didos VC - From Staging Table" script to import the files
    And User waits for the script run to complete
    And User opens the "Vendor credit"
    Then User verifies the didos verified is changed to true in Credit
    And User verfies the processed flag is set to true for the record in the "Vendor Bill/Credit Staging Table (Edit)"
    