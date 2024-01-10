Feature: Due Dates

   @Demo
  Scenario Outline: User verifies the Due date set script populates the correct due date on the <transactionType> received from Readsoft for the <term>
    Given User is logged into the Readsoft Application with AP Manager role
    When User creates the "<transactionType>"
    And User logs into Netsuite Application
    And User opens the "<transactionType>" record
    And User gets the Term details from the vendor record
    And User navigates back to the transaction record
    And User calculates due date from the supplier invoice date
    Then User verifies the due date is calculated correctly based on the terms mentioned in vendor record

    Examples: 
      | transactionType | term   |
      #| GFR invoice     | D term |
      #| GFR invoice     | X term |
      #| GFR invoice     | Y term |
      #| GFR invoice     | Z term |
      | GNFR invoice    | X term |
      #| GNFR invoice    | D term |
      #| GNFR invoice    | Y term |
      #| GNFR invoice    | Z term |
      #| GFR credit      | D term |
      #| GNFR credit     | X term |
      #| GFR credit      | Y term |
      #| GNFR credit     | Z term |

  @Demo
  Scenario Outline: User verifies the Due date is correct after the approval of the GNFR invoice with <term>
    Given User is in the Netsuite HomePage
    When User opens the "<transactionType>" record
    And User approves the invoice
    And User gets the Term details from the vendor record
     And User navigates back to the transaction record
    And User calculates due date from the supplier invoice date
    Then User verifies the due date is calculated correctly based on the terms mentioned in vendor record for approved bills

    Examples: 
      | transactionType | term   |
      | GNFR invoice    | X term |
      #| GNFR invoice    | D term |
      #| GNFR invoice    | Y term |
      #| GNFR invoice    | Z term |

      
    Scenario Outline: User verifies the Due date set script populates the correct due date on the vendor bill received from Truecommerce(EDI) for the <term>
    Given User is in the Netsuite HomePage
    When User navigates to the CSV Imports folder in the File cabinet
    And User opens the "Vendor Bills - True Commerce - For Processing" folder
    And User creates the "TCVendorBill" file
    And User uploads the "TCVendorBill" file
    And User executes the "TC VB - From FOLDER" script to import the files
    And User waits for the script run to complete
    And User opens the "TC Vendor bill"
    And User gets the Term details from the vendor record
    And User navigates back to the transaction record
    And User calculates due date from the supplier invoice date
    Then User verifies the due date is calculated correctly based on the terms mentioned in vendor record
    
    Examples: 
      | term   |
      | X term |
      | D term |
      | Y term |
      | Z term |
    
    
    Scenario Outline: User verifies the Due date set script populates the correct due date on the vendor credit received from Truecommerce(EDI) for the <term>
    Given User is in the Netsuite HomePage
    When User navigates to the CSV Imports folder in the File cabinet
    When User navigates to the CSV Imports folder in the File cabinet
    And User opens the "Vendor Credits - True Commerce - For Processing" folder
    When User creates the "TCVendorCredit" file
    And User uploads the "TCVendorCredit" file
    And User executes the "TC VC - From FOLDER" script to import the files
    And User waits for the script run to complete
    And User opens the "TC Vendor credit"
    And User gets the Term details from the vendor record
    And User navigates back to the transaction record
    And User calculates due date from the supplier invoice date
    Then User verifies the due date is calculated correctly based on the terms mentioned in vendor record
    
    Examples: 
      | term   |
      | X term |
      | D term |
      | Y term |
      | Z term |
      
      Scenario Outline: User verifies the Due date is correct after the approval of the GFR invoice with <term>
      
    Given User is logged into the Netsuite Application with Wickes developer role
    When User opens the "Stock Vendor bill"
    And User gets the internal ID of the bill
    And User navigates to the CSV Imports folder in the File cabinet
    And User opens the "Vendor Bills - Didos - For Processing" folder
    When User creates the "DidosVendorBill" file with the internal ID of the stock bill 
    And User uploads the "DidosVendorBill" file
    And User executes the "Didos VB - From FOLDER" script to import the files
    And User waits for the script run to complete
    And User opens the "Vendor bill"
    And User gets the Term details from the vendor record
    And User navigates back to the transaction record
    And User calculates due date from the supplier invoice date
    Then User verifies the due date is calculated correctly based on the terms mentioned in vendor record
    
       Examples: 
      | term   |
      | X term |
      | D term |
      | Y term |
      | Z term |
    