import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.io.File as File
import java.io.FileInputStream as FileInputStream
import java.io.FileOutputStream as FileOutputStream
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.WebElement

// Define the Excel file path and sheet
String excelFilePath = "C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\amazon_ls_soundbar_pagination.xlsx"
String sheetName = 'Sheet1'

// Create the Excel file if it doesn't exist
File file = new File(excelFilePath)
Workbook workbook
Sheet sheet

if (!file.exists()) {
    println "Excel file doesn't exist. Creating new file."
    workbook = new XSSFWorkbook()
    sheet = workbook.createSheet(sheetName)
    FileOutputStream fos = new FileOutputStream(file)
    workbook.write(fos)
    fos.close()
    workbook.close()
}

// Now open the existing file
FileInputStream fis = new FileInputStream(file)
workbook = new XSSFWorkbook(fis)
sheet = workbook.getSheet(sheetName)

if (sheet == null) {
    println "Creating a new sheet."
    sheet = workbook.createSheet(sheetName)
} else {
    println "Sheet loaded successfully."
}

// Add headers if the sheet is empty
if (sheet.getPhysicalNumberOfRows() == 0) {
    Row headerRow = sheet.createRow(0)
    headerRow.createCell(0).setCellValue('PRODUCT_NAME')
    headerRow.createCell(1).setCellValue('PRODUCT_PRICE_IN_INR')
    headerRow.createCell(2).setCellValue('BRAND_NAME')
    headerRow.createCell(3).setCellValue('SPEAKER_OUTPUT')
    println "Added headers to the sheet."
}

// Loop to navigate through pages and extract data
boolean isNextButtonEnabled = true

while (isNextButtonEnabled) {
    // Extract data from the current page
    List<WebElement> productLinks = WebUI.findWebElements(findTestObject('Object Repository/link_to_be_clicked'), 10)
    
    if (productLinks.isEmpty()) {
        println "No product links found on this page."
        break
    }
    
    int productIndex = 1
    
    for (WebElement link : productLinks) {
        println "Clicking product link " + productIndex
        link.click()
        
        // Call the test case to extract product data (assuming it extracts data and sets it in GlobalVariable)
        WebUI.callTestCase(findTestCase('test_case_to_read_data_for_product'), [:], FailureHandling.STOP_ON_FAILURE)
        
        // Debugging: Print extracted product data
        println "Extracted Product Name: " + GlobalVariable.product_name
        println "Extracted Product Price: " + GlobalVariable.product_price
        println "Extracted Brand Name: " + GlobalVariable.brand_name
        println "Extracted Speaker Output: " + GlobalVariable.speaker_output_power
        
        // Store the product data in Excel
        int rowIndex = sheet.getLastRowNum() + 1
        Row row = sheet.createRow(rowIndex)
        row.createCell(0).setCellValue(GlobalVariable.product_name)
        row.createCell(1).setCellValue(GlobalVariable.product_price)
        row.createCell(2).setCellValue(GlobalVariable.brand_name)
        row.createCell(3).setCellValue(GlobalVariable.speaker_output_power)

        productIndex++
        
        // Close the newly opened tab and switch back to the main tab
       // WebUI.closeWindowIndex(1)
        //WebUI.switchToWindowIndex(0)
    }
    
    // Add a delay to allow the page to load before moving to the next page
    WebUI.delay(1)
    
    // Check if the next button is enabled or disabled
    isNextButtonEnabled = WebUI.verifyElementPresent(findTestObject('Object Repository/next_button'), 5, FailureHandling.OPTIONAL)
    
    if (isNextButtonEnabled) {
        println "Next button is enabled. Clicking to go to the next page."
        WebUI.click(findTestObject('Object Repository/next_button'))
        
        // Add a delay to allow the next page to load
        WebUI.delay(2)
    } else {
        println "Next button is not enabled. No more pages to scrape."
    }
}

// Save the Excel file
fis.close()
FileOutputStream fos = new FileOutputStream(file)
workbook.write(fos)
fos.close()
workbook.close()

println('Product data has been successfully saved in Excel.')

//project
//ADITYA_AGARWAL
//project_for_contlo
