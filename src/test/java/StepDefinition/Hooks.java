package StepDefinition;

import java.net.URI;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Generic.genericFunction;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends genericFunction {
	
	
	public static String scenarioName;
	public static Map<String,String> TestDataInMap;
	
	@Before
	public  void getScenarioName(Scenario scenario) throws Exception
	{
		
		String featurePath=scenario.getUri().toString();
		String[] parts=featurePath.split("/");
		String lastPart=parts[parts.length-1];
		String[] featurefileName=lastPart.split("\\.");
		String featureName=featurefileName[0];
	   scenarioName=scenario.getName();
       TestDataInMap=genericFunction.getTestDataInMap("./src/test/resources/Test data.xlsx", featureName, scenarioName);
	
	}
	
	@After
	public void takeScreenshot(Scenario scenario)
	{
		
		if(scenario.isFailed()) {
			TakesScreenshot ts=(TakesScreenshot) driver;
			byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		
	}
	
	
   @After("@closeDriver")
   public void teardown()
   {
	   driver.quit();
	   if(tempdriver!= null)
	   {
	   tempdriver.quit();
	   }
	   driver=null;
   }
	
	

}
