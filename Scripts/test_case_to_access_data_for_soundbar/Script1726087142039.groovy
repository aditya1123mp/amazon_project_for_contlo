import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType as ConditionType
WebUI.openBrowser('')

'maximize windows'
WebUI.maximizeWindow()

'navigate to amazon website'
WebUI.navigateToUrl('https://www.amazon.in/')

WebUI.delay(3)

'Refresh the current web page'
WebUI.refresh()

//WebUI.verifyElementPresent(findTestObject('sign_in_amazon_account/amazon_logo'),10)

//WebUI.click(findTestObject('sign_in_amazon_account/amazon_logo'))

//WebUI.click(findTestObject('Object Repository/sign_in_amazon_account/click_on_sign_in_button'))

//*************************************************** FOR SEARCH BOX ***************************************************//
// Generate the random string
//String randomString_001 = RandomStringUtils.randomNumeric(6)
String name_value = 'lg soundbar'

// Define the XPath
String xpath_for_full_name = '//input[@id=\'twotabsearchtextbox\']'

// Locate the input field using the XPath
TestObject inputField_001 = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpath_for_full_name)

// Use JavaScript to set the value and trigger the input event
WebUI.executeJavaScript("""
    var element = document.evaluate("$xpath_for_full_name", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
    if (element) {
        element.value = '$name_value';
        var event = new Event('input', { bubbles: true });
        element.dispatchEvent(event);
   }
    """, 
	null
)

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/submit_button'))

//project
//ADITYA_AGARWAL
//project_for_contlo
