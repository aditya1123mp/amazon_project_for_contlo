import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import internal.GlobalVariable as GlobalVariable


// Get the current WebDriver
WebDriver driver = DriverFactory.getWebDriver()

// Wait for the elements to be visible (up to 10 seconds)
//WebDriverWait wait = new WebDriverWait(driver, 10)
//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath('//a[@class="a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal"]')))

// Find elements by XPath
List<WebElement> elements = driver.findElements(By.xpath('//a[@class="a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal"]'))

// Get the count of elements
int count = elements.size()
println("Number of products found: " + count)

// Assign the count to the global variable
GlobalVariable.productcount = count

// Print the global variable value
println(GlobalVariable.productcount)


//project
//ADITYA_AGARWAL
//project_for_contlo
