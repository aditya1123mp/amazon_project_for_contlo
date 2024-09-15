import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.nio.file.*
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

// Define the Excel file path
String excelFilePath = 'C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\amazon_ls_speaker_product_data.xlsx'

// Open the Excel file
FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath))
Workbook workbook = new XSSFWorkbook(fileInputStream)

// Access the first sheet
Sheet sheet = workbook.getSheetAt(0)

// Create a list to store product names, prices, and speaker outputs
List<Map> productList = []

// Method to check if a string is a valid number
boolean isNumeric(String str) {
    try {
        Double.parseDouble(str)
        return true
    } catch (NumberFormatException e) {
        return false
    }
}

// Iterate over rows, starting from row 1 (assuming headers are in row 0)
sheet.eachWithIndex({ Row row, int rowIndex ->
    if (rowIndex > 0) {
        // Skip the header row

        // Get the product name, price, and speaker output (assuming columns 0, 1, and 3)
        Cell productNameCell = row.getCell(0) // Product name in column 0
        Cell priceCell = row.getCell(1) // Price in column 1
        Cell speakerOutputCell = row.getCell(3) // Speaker output in column 3

        // Convert the price to a string and remove ₹ sign and commas
        String priceString = priceCell != null ? priceCell.getStringCellValue().replace('₹', '').replace(',', '').trim() : null

        // Check if the price is numeric
        if (isNumeric(priceString)) {
            Double price = Double.parseDouble(priceString)

            // Store the product name, price, and speaker output in a map
            Map<String, Object> productData = [:]
            productData.put('name', productNameCell.getStringCellValue())
            productData.put('price', price.intValue()) // Convert price to an integer
            productData.put('speakerOutput', speakerOutputCell.getStringCellValue()) // Extract speaker output

            // Add the map to the list
            productList.add(productData)
        } else {
            println("Skipping invalid price for product: ${productNameCell.getStringCellValue()} (Price: $priceString)")
        }
    }
})

// Sort the product list by price in ascending order
productList.sort { it.price }

// Print the sorted product details in the desired format
productList.each { product ->
    println("${product.price} ${product.name} ${product.speakerOutput}")
}

// Close the workbook and file input stream
workbook.close()
fileInputStream.close()

//project
//ADITYA_AGARWAL
//project_for_contlo
