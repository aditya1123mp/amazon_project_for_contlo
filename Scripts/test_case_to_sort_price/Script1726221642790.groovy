import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.File

// Load the Excel file
FileInputStream file = new FileInputStream(new File("C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\amazon_ls_speaker_product_data.xlsx"))
Workbook workbook = new XSSFWorkbook(file)
Sheet sheet = workbook.getSheetAt(0)

List<Map<String, Object>> productList = []

// Iterate through rows and read data
for (Row row : sheet) {
    if (row.getRowNum() == 0) continue // Skip header

    String productName = row.getCell(0)?.getStringCellValue() ?: ""
    
    // Handle numeric or string type for price
    Cell productPriceCell = row.getCell(1)
    String productPriceStr = ""
    if (productPriceCell != null) {
        if (productPriceCell.getCellType() == CellType.STRING) {
            productPriceStr = productPriceCell.getStringCellValue()
        } else if (productPriceCell.getCellType() == CellType.NUMERIC) {
            productPriceStr = productPriceCell.getNumericCellValue().toString()
        }
        // Clean the price and convert to a numeric value
        productPriceStr = productPriceStr.replaceAll("[^\\d.]", "")
    }
    if (productPriceStr.isEmpty()) continue
    double productPrice = Double.parseDouble(productPriceStr)

    String brandName = row.getCell(2)?.getStringCellValue() ?: ""
    String speakerOutput = row.getCell(3)?.getStringCellValue() ?: ""

    // Add product details to list
    productList.add([
        'PRODUCT_NAME': productName,
        'PRODUCT_PRICE': productPrice,
        'BRAND_NAME': brandName,
        'SPEAKER_OUTPUT': speakerOutput
    ])
}

// Sort the list by price (ascending)
productList.sort { it['PRODUCT_PRICE'] }

// Clear the existing rows (except the header)
int lastRowNum = sheet.getLastRowNum()
if (lastRowNum > 0) {
    for (int i = lastRowNum; i > 0; i--) {
        Row row = sheet.getRow(i)
        if (row != null) {
            sheet.removeRow(row)
        }
    }
}

// Rewrite sorted products to Excel
int rowNum = 1 // Start after header
productList.each { product ->
    Row row = sheet.createRow(rowNum++)

    // Write product details back to Excel
    row.createCell(0).setCellValue(product['PRODUCT_NAME'])
    row.createCell(1).setCellValue(product['PRODUCT_PRICE'])
    row.createCell(2).setCellValue(product['BRAND_NAME'])
    row.createCell(3).setCellValue(product['SPEAKER_OUTPUT'])
}

// Write updated Excel file
file.close() // Close the input stream before writing
FileOutputStream outFile = new FileOutputStream(new File("C:\\Users\\DELL\\OneDrive\\Desktop\\mail\\amazon_ls_speaker_product_data_sorted.xlsx"))
workbook.write(outFile)
outFile.close()

// Close the workbook
workbook.close()

println "Products sorted by price and updated in Excel file."
