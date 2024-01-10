Feature: Supplier ID Generation

  @Demo 
  Scenario: User creates a Stock vendor
    Given User is logged into the Netsuite Application with Wickes developer role
    When User creates the "stock" vendor
    When User logs in with AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "S".

  Scenario: User creates second Stock vendor
    Given User is in the Netsuite HomePage
    And User creates the second "stock" vendor
    When User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the second vendor ID
    Then Two newly created "stock" vendors should have sequential numbers prefixed with 'S'.

  # @Demo
  Scenario: User creates a Non Stock vendor
    Given User is in the Netsuite HomePage
    When User creates the "Non stock" vendor
    When User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "N".

  Scenario: User creates second Non Stock vendor
    Given User is in the Netsuite HomePage
    And User creates the second "Non stock" vendor
    When User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the second vendor ID
    Then Two newly created "Non stock" vendors should have sequential numbers prefixed with 'N'.

  Scenario: User creates a Installer vendor
    Given User is in the Netsuite HomePage
    When User creates the "Installer" vendor
    When User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "I".

  Scenario: User creates second Installer vendor
    Given User is in the Netsuite HomePage
    And User creates the second "Installer" vendor
    When User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the second vendor ID
    Then Two newly created "Installer" vendors should have sequential numbers prefixed with 'I'.

  @Demo
  Scenario: User rejects a Stock vendor during approval
    Given User is in the Netsuite HomePage
    When User creates the "stock" vendor
    And User switches to the AP manager role
    And User rejects the vendor with the reason
    And User logs back into the Wickes developer role
    Then User verifies the rejected vendor record has the rejection reason

  @Demo @closeDriver
  Scenario: User resubmits the rejected stock vendor
    Given User is on the rejected stock vendor record
    When User updates the vendor record with correct details
    And User resubmits the vendor record for approval
    And User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "S".

  Scenario: User rejects a NonStock vendor during approval
    Given User is in the Netsuite HomePage
    When User creates the "Non stock" vendor
    And User switches to the AP manager role
    And User rejects the vendor with the reason
    And User logs back into the Wickes developer role
    Then User verifies the rejected vendor record has the rejection reason

  Scenario: User resubmits the rejected NonStock vendor
    Given User is on the rejected stock vendor record
    When User updates the vendor record with correct details
    And User resubmits the vendor record for approval
    And User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "N".

  Scenario: User rejects a Installer vendor during approval
    Given User is in the Netsuite HomePage
    When User creates the "Installer" vendor
    And User switches to the AP manager role
    And User rejects the vendor with the reason
    And User logs back into the Wickes developer role
    Then User verifies the rejected vendor record has the rejection reason

  Scenario: User resubmits the rejected Installer vendor
    Given User is on the rejected stock vendor record
    When User updates the vendor record with correct details
    And User resubmits the vendor record for approval
    And User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "I".

  Scenario Outline: User changes the vendor category <updateVendorCategory> after the rejection
    Given User is in the Netsuite HomePage
    When User creates the "<vendorCategory>" vendor
    And User switches to the AP manager role
    And User rejects the vendor with the reason
    And User logs back into the Wickes developer role
    And User changes the vendor category to <updateVendorCategory>
    And User resubmits the vendor record for approval
    And User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "<prefix>".

    Examples: 
      | vendorCategory | updateVendorCategory | prefix |
      | stock          | Non stock            | N      |
      | Non stock      | stock                | S      |
      | Non stock      | Installer            | I      |


  Scenario: User creates a Stock vendor through CSV Import
    Given User is in the Netsuite HomePage
    When User creates the csv file with "Stock" vendor details
    And User imports the csv file
    And User opens the "stock" vendor record
    And User switches to the AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "S".

  @supplier
  Scenario: User creates a Non stock vendor through CSV Import
    Given User is in the Netsuite HomePage
    When User creates the csv file with "Non-Stock" vendor details
    And User imports the csv file
    And User opens the "stock" vendor record
    And User logs in with AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "N".

  @supplier
  Scenario: User creates a Installer vendor through CSV Import
    Given User is in the Netsuite HomePage
    When User creates the csv file with "Installer" vendor details
    And User imports the csv file
    And User opens the "Installer" vendor record
    And User logs in with AP manager role
    And User approves the vendor
    And User logs back into the Wickes developer role
    And User gets the vendor ID
    Then Vendor ID should start with "I".
