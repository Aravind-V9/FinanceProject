package runnerClass;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {
//				  "src/main/resources/featurefile/costGroup_And_BusinessFunction.feature",
				  "src/main/resources/featurefile/AutoGeneration_Supplier.feature",
//				  "src/main/resources/featurefile/InstallerVendorBill.feature",
//				  "src/main/resources/featurefile/ReadsoftIntegration.feature",
//				  "src/main/resources/featurefile/DueDates.feature",
//				"src/main/resources/featurefile/CSVImport.feature"
				    }
		,glue = {"StepDefinition","Generic"}
	    ,tags="@Demo"
		,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}		
		,monochrome = true
		,publish = true	
		)

public class TestRunner {	
} 