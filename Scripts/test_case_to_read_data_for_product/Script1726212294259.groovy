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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

//WebUI.click(findTestObject('Object Repository/To_get_data_of_the_smart_phone/link_to_click_to_get_data'))

// Switch to the new window
WebUI.switchToWindowIndex(1)

//if (WebUI.verifyElementPresent(findTestObject('Object Repository/product_name'), 10, FailureHandling.OPTIONAL)) {
   // product_name = WebUI.getText(findTestObject('Object Repository/To_get_data_of_the_smart_phone/get_product_name'))

    //System.out.println('Product Name: ' + GlobalVariable.product_name)
//} else {
//    GlobalVariable.product_name = 'Not_available'
//}
if (WebUI.verifyElementPresent(findTestObject('Object Repository/product_name'), 10, FailureHandling.OPTIONAL)) {
	// Get the product name from the web element
	String productName = WebUI.getText(findTestObject('Object Repository/product_name'))
	
	// Use regular expression to split the product name by the first occurrence of ',' or '|'
	String[] parts = productName.split("[,|]", 2) // Limit to 2 parts to split only at the first occurrence
	
	// Extract the name (before the first delimiter)
	GlobalVariable.product_name = parts[0].trim()

	// Print the extracted name
	System.out.println('Product Name: ' + GlobalVariable.product_name)
} else {
	// If the product name is not found, set it to 'Not_available'
	GlobalVariable.product_name = 'Not_available'
}



//System.out.println(GlobalVariable.product_name)
//if (WebUI.verifyElementPresent(findTestObject('Object Repository/product_price'), 10, FailureHandling.OPTIONAL)) {
//    GlobalVariable.product_price = WebUI.getText(findTestObject('Object Repository/product_price'))

//    System.out.println('Product price: ' + GlobalVariable.product_price)
//} else {
//    GlobalVariable.product_price = 'Zero'
//}

// Check if the element is present and get the price text
if (WebUI.verifyElementPresent(findTestObject('Object Repository/product_price'), 10, FailureHandling.OPTIONAL)) {
	GlobalVariable.product_price = WebUI.getText(findTestObject('Object Repository/product_price'))

	// Remove ₹ sign and commas, then parse the price
	String cleanedPrice = GlobalVariable.product_price.replaceAll("[₹,]", "").trim()

	// Set the cleaned price to the global variable
	GlobalVariable.product_price = cleanedPrice

	System.out.println('Product price: ' + GlobalVariable.product_price)
} else {
	GlobalVariable.product_price = 'Zero'
}


//System.out.println(GlobalVariable.product_name)
if (WebUI.verifyElementPresent(findTestObject('Object Repository/brand_name'), 10, FailureHandling.OPTIONAL)) {
	GlobalVariable.brand_name = WebUI.getText(findTestObject('Object Repository/brand_name'))

	System.out.println('Brand Name: ' + GlobalVariable.brand_name)
} else {
	GlobalVariable.brand_name = 'Not_available'
}


if (WebUI.verifyElementPresent(findTestObject('Object Repository/speaker_output_power'), 10, FailureHandling.OPTIONAL)) {
    GlobalVariable.speaker_output_power = WebUI.getText(findTestObject('Object Repository/speaker_output_power'))

    System.out.println('speaker output: ' + GlobalVariable.speaker_output_power)
} else {
    GlobalVariable.speaker_output_power = 'Not_available'
}

// Print combined output
System.out.println('******************** Print combined output *******************')
System.out.println(GlobalVariable.product_name + GlobalVariable.product_price + GlobalVariable.speaker_output_power)

// Close the new window and switch back to the original window
WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

// Optional delay before clicking the next link
WebUI.delay(2)

//project
//ADITYA_AGARWAL
//project_for_contlo
