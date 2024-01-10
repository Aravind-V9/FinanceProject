Feature: Vision Installer Vendor Bills

  #@Demo
  #Scenario: User verifies the vendor bill is created in Netsuite for the installation payment made in vision for the installer with CIS level G
    #Given User is in the Netsuite HomePage
    #When User searches the vendor bill using saved search
    #And User opens the Installer vendor bill
    #Then User verifies the header level information are populated correctly
    #And User verifies the amounts are calculated correctly in Expense and Items subtab for the installation only transaction
#
  #@Demo
  #Scenario: User verifies the GL Impact details of the installer vendor bill for the installation only transaction with CIS level G
    #Given User is in the Installer vendor Bill Page
    #When User navigates to the GL Impact subtab
    #Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the Installation only transaction
#
  #@Demo
  #Scenario: User verifies the vendor bill is created in Netsuite for the installation payment made in vision for the installer with CIS level S
    #Given User is in the Netsuite HomePage
    #When User searches the vendor bill using saved search
    #And User opens the Installer vendor bill
    #Then User verifies the header level information are populated correctly
    #And User verifies the amounts are calculated correctly in Expense and Items subtab for the installation only transaction
#
  #@Demo
  #Scenario: User verifies the GL Impact details of the installer vendor bill for the installation only transaction with CIS level S
    #Given User is in the Installer vendor Bill Page
    #When User navigates to the GL Impact subtab
    #Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the Installation only transaction

  Scenario: User verifies the vendor bill is created in Netsuite for the installation payment made in vision for the installer with CIS level H
    Given User is in the Netsuite HomePage
    When User searches the vendor bill using saved search
    And User opens the Installer vendor bill
    Then User verifies the header level information are populated correctly
    And User verifies the amounts are calculated correctly in Expense and Items subtab for the installation only transaction

  Scenario: User verifies the GL Impact details of the installer vendor bill for the installation only transaction with CIS level H
    Given User is in the Installer vendor Bill Page
    When User navigates to the GL Impact subtab
    Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the Installation only transaction

  Scenario: User verifies the installer vendor bill is created in Netsuite for the remedial payment made in vision for the installer with CIS level G
    Given User is in the Netsuite HomePage
    When User searches the vendor bill using saved search
    And User opens the Installer vendor bill
    Then User verifies the header level information are populated correctly
    And User verifies the amounts are calculated correctly in Expense and Items subtab for the remedial only transaction

  Scenario: User verifies the GL Impact details of the installer vendor bill for the remedial only transaction with CIS level G
    Given User is in the Installer vendor Bill Page
    When User navigates to the GL Impact subtab
    Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the remedial only transaction

  Scenario: User verifies the installer vendor bill is created in Netsuite for the remedial payment made in vision for the installer with CIS level S
    Given User is in the Netsuite HomePage
    When User searches the vendor bill using saved search
    And User opens the Installer vendor bill
    Then User verifies the header level information are populated correctly
    And User verifies the amounts are calculated correctly in Expense and Items subtab for the remedial only transaction

  Scenario: User verifies the GL Impact details of the installer vendor bill for the remedial only transaction with CIS level S
    Given User is in the Installer vendor Bill Page
    When User navigates to the GL Impact subtab
    Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the remedial only transaction

  Scenario: User verifies the installer vendor bill is created in Netsuite for the remedial payment made in vision for the installer with CIS level H
    Given User is in the Netsuite HomePage
    When User searches the vendor bill using saved search
    And User opens the Installer vendor bill
    Then User verifies the header level information are populated correctly
    And User verifies the amounts are calculated correctly in Expense and Items subtab for the remedial only transaction

  Scenario: User verifies the GL Impact details of the installer vendor bill for the remedial only transaction with CIS level H
    Given User is in the Installer vendor Bill Page
    When User navigates to the GL Impact subtab
    Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the remedial only transaction

  Scenario: User verifies the installer vendor bill is created in Netsuite for the installation and remedial payment made in vision for the installer with CIS level G
    Given User is in the Netsuite HomePage
    When User searches the vendor bill using saved search
    And User opens the Installer vendor bill
    Then User verifies the header level information are populated correctly
    And User verifies the amounts are calculated correctly in Expense and Items subtab for the installation and remedial only transaction

  Scenario: User verifies the GL Impact details of the installer vendor bill for the installation and remedial transaction with CIS level G
    Given User is in the Installer vendor Bill Page
    When User navigates to the GL Impact subtab
    Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the installation and remedial transaction

  Scenario: User verifies the installer vendor bill is created in Netsuite for the installation and remedial payment made in vision for the installer with CIS level S
    Given User is in the Netsuite HomePage
    When User searches the vendor bill using saved search
    And User opens the Installer vendor bill
    Then User verifies the header level information are populated correctly
    And User verifies the amounts are calculated correctly in Expense and Items subtab for the installation and remedial only transaction

  Scenario: User verifies the GL Impact details of the installer vendor bill for the installation and remedial transaction with CIS level S
    Given User is in the Installer vendor Bill Page
    When User navigates to the GL Impact subtab
    Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the installation and remedial transaction

  Scenario: User verifies the installer vendor bill is created in Netsuite for the installation and remedial payment made in vision for the installer with CIS level H
    Given User is in the Netsuite HomePage
    When User searches the vendor bill using saved search
    And User opens the Installer vendor bill
    Then User verifies the header level information are populated correctly
    And User verifies the amounts are calculated correctly in Expense and Items subtab for the installation and remedial only transaction

  Scenario: User verifies the GL Impact details of the installer vendor bill for the installation and remedial transaction with CIS level H
    Given User is in the Installer vendor Bill Page
    When User navigates to the GL Impact subtab
    Then User verifies the amounts are posted to the correct cost accounts in GL Impact for the installation and remedial transaction
