Feature: Cost Group and Business Function Off Cost Centre Selection

  Scenario: Cost group and business function are selected based on the cost group for journal entries created through Integration
    Given User is on the Netsuite HomePage
    And User gets the cost centre details from the Cost centre page
    When User opens the "journal entry" created through Integration
    And User views the cost group and business functions in the Lines subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre for journal
    When User navigates to the GL Impact subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in GL Impact

  Scenario: Cost group and business function are selected based on the cost group for Vendor Bills created through Integration
    Given User is on the Netsuite HomePage
    And User gets the cost centre details from the Cost centre page
    When User opens the "vendor Bills" created through Integration
    Then User verifies the Cost group and business function are populated based on the Cost centre in the header level
    When User views the cost group and business functions in the Expenses and Items subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in the line level for vendorbill
    When User navigates to the GL Impact subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in GL Impact

  Scenario: Cost group and business function are selected based on the cost group for Vendor Credits created through Integration
    Given User is on the Netsuite HomePage
    And User gets the cost centre details from the Cost centre page
    When User opens the "vendor Credits" created through Integration
    Then User verifies the Cost group and business function are populated based on the Cost centre in the header level
    When User views the cost group and business functions in the Items subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in the line level for vendorcredit
    When User navigates to the GL Impact subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in GL Impact

  #@Demo
  Scenario: Cost group and business function are selected based on the cost group for journal entries created through UI
    Given User is logged into the Netsuite Application with Wickes developer role
    And User gets the cost centre details from the Cost centre page
    When User creates a new journal entry
    And User views the cost group and business functions in the Lines subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre for journal
    When User navigates to the GL Impact subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in GL Impact

  #@Demo
  Scenario: Cost group and business function are selected based on the cost group for Vendor Bills created through UI
    Given User is on the Netsuite HomePage
    And User gets the cost centre details from the Cost centre page
    When User creates a new vendor bill
    Then User verifies the Cost group and business function are populated based on the Cost centre in the header level
    When User views the cost group and business functions in the Expenses and Items subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in the line level for vendorbill
    When User navigates to the GL Impact subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in GL Impact

  Scenario: Cost group and business function are selected based on the cost group for Vendor Credits created through UI
    Given User is logged into the Netsuite Application with Wickes developer role
    And User gets the cost centre details from the Cost centre page
    When User creates a new vendor credit
    Then User verifies the Cost group and business function are populated based on the Cost centre in the header level
    When User views the cost group and business functions in the Items subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in the line level for vendorcredit
    When User navigates to the GL Impact subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in GL Impact

  @Demo @closeDriver
  Scenario: Cost group and business function are selected based on the cost group for Bill payments
    Given User is on the Netsuite HomePage
    And User gets the cost centre details from the Cost centre page
    When User makes payment for bills using EFT bill payments
    And User opens the Bill payment record
    Then User verifies the Cost group and business function are populated based on the Cost centre in the header level
    When User navigates to the GL Impact subtab
    Then User verifies the Cost group and business function are populated based on the Cost centre in GL Impact

  Scenario: Cost group and business function are selected based on the cost group for Purchase Order created throuh UI
    Given User is on the Netsuite HomePage
    And User gets the cost centre details from the Cost centre page
    When User creates the purchase order
    Then User verifies the Cost group and business function are populated based on the Cost centre in the header level
