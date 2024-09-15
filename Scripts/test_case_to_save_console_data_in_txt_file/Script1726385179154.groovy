import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.*
import java.io.*

// Path to the Excel file
FileInputStream file = new FileInputStream(new File("C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\amazon_ls_speaker_product_data.xlsx"))

// Open the workbook
XSSFWorkbook workbook = new XSSFWorkbook(file)

// Get the first sheet (assuming the data is on the first sheet)
Sheet sheet = workbook.getSheetAt(0)

// Create a new FileWriter to write the output to a text file
FileWriter writer = new FileWriter("C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\ls_speaker_data.txt")

// Iterate through rows, starting from row 1 to skip the header
for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	Row row = sheet.getRow(i)
	
	// Read the PRODUCT_PRICE_IN_INR, PRODUCT_NAME, and SPEAKER_OUTPUT columns
	Cell productPrice = row.getCell(1)
	Cell productName = row.getCell(0)
	Cell speakerOutput = row.getCell(3)
	
	// Convert cell values to string (handle special cases for Zero)
	String price = productPrice.toString().equalsIgnoreCase("Zero") ? "0" : productPrice.toString()
	String product = productName.toString()
	String speaker = speakerOutput.toString().equalsIgnoreCase("Not_available") ? "N/A" : speakerOutput.toString()
	
	// Write the output in the required format
	writer.write(price + " " + product + " " + speaker + "\n")
}

// Close the writer and workbook
writer.close()
workbook.close()
file.close()

println "Data successfully written to the text file."

WebUI.delay(3)

//project
//ADITYA_AGARWAL
//project_for_contlo
