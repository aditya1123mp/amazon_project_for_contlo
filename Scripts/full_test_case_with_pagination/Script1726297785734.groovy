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

WebUI.callTestCase(findTestCase('test_case_to_access_data_for_soundbar'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('test_case_to_iterate_through_products_pages_and_saving_in_excel'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('test_case_for_price_sorting_for_pagination'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('test_case_to_save_data_in_txt_file'), [:], FailureHandling.STOP_ON_FAILURE)

//project
//ADITYA_AGARWAL
//project_for_contlo
