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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.support.ui.WebDriverWait
import com.kms.katalon.core.webui.driver.DriverFactory

// Get the current WebDriver
WebDriver driver = DriverFactory.getWebDriver()

// Wait for the elements to be visible (up to 10 seconds)
WebDriverWait wait = new WebDriverWait(driver, 10)
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath('//a[@class="a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal"]')))

// Find elements by XPath
List<WebElement> elements = driver.findElements(By.xpath('//a[@class="a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal"]'))

// Get the count of elements
int count = elements.size()
println("Number of products found: " + count)

// Define the Excel file path and sheet
String excelFilePath = "C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\amazon_ls_speaker_product_data.xlsx"
String sheetName = 'Sheet1'

// Create the Excel file if it doesn't exist
File file = new File(excelFilePath)
Workbook workbook
Sheet sheet

if (!(file.exists())) {
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
	sheet = workbook.createSheet(sheetName)
}

// Add headers if the sheet is empty
if (sheet.getPhysicalNumberOfRows() == 0) {
	Row headerRow = sheet.createRow(0)
	headerRow.createCell(0).setCellValue('PRODUCT_NAME')
	headerRow.createCell(1).setCellValue('PRODUCT_PRICE_IN_INR')
	headerRow.createCell(2).setCellValue('BRAND_NAME')
	headerRow.createCell(3).setCellValue('SPEAKER_OUTPUT')
}

// Iterate through the product links
for (int i = 0; i < elements.size(); i++) {
	WebElement link = elements.get(i)
	
	// Click on each link
	link.click()

	// Wait for the page to load (adjust this as needed)
	WebUI.delay(3)

	// Call the test case to extract product data (assuming it extracts data and sets it in GlobalVariable)
	WebUI.callTestCase(findTestCase('test_case_to_read_data_for_product'), [:], FailureHandling.STOP_ON_FAILURE)

	// Store the product data in Excel
	int rowIndex = sheet.getLastRowNum() + 1
	Row row = sheet.createRow(rowIndex)
	row.createCell(0).setCellValue(GlobalVariable.product_name)
	row.createCell(1).setCellValue(GlobalVariable.product_price)
	row.createCell(2).setCellValue(GlobalVariable.brand_name)
	row.createCell(3).setCellValue(GlobalVariable.speaker_output_power)

	// Close the newly opened tab and switch back to the main tab (optional)
	//WebUI.closeWindowIndex(1)
	//WebUI.switchToWindowIndex(0)

	// Add a short delay before the next iteration
	WebUI.delay(1)

	// Navigate back to the main product listing page (if needed)
	//driver.navigate().back()
}

// Save the Excel file
fis.close()
FileOutputStream fos = new FileOutputStream(file)
workbook.write(fos)
fos.close()
workbook.close()

println('Product data has been successfully saved in Excel.')

WebUI.delay(5)


//project
//ADITYA_AGARWAL
//project_for_contlo
