Feature: Monthly report application
#Field Validation
  @FieldValidation 
  Scenario Outline: Verify format of Standard Header sections in the Report Layout.

    Given Application consumes report data JSON file 
    When Report JSON file  validated against the JSON schema 
    Then Report field values match with expected field values as per "<ReportSections>" value displayed

    Examples:
      | ReportSections |
      | SH1            |
      | SH2            |
      | SH3            |

 