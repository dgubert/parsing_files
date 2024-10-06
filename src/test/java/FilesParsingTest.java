import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTest {

    private final ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private final String archiveName = "archive.zip";

    @Test
    void pdfFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(archiveName))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("sample.pdf")) {
                    PDF pdf = new PDF(zis);

                    assertThat(pdf.creator).isEqualTo("Writer");
                    assertThat(pdf.title).isEqualTo("PDF Form Example");
                }
            }
        }
    }

    @Test
    void csvFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(archiveName))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("sample.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();

                    assertThat(data.size()).isEqualTo(101);
                    assertThat(data.get(0)).isEqualTo(new String[] {"Region",
                            "Country",
                            "Item Type",
                            "Sales Channel",
                            "Order Priority",
                            "Order Date",
                            "Order ID",
                            "Ship Date",
                            "Units Sold",
                            "Unit Price",
                            "Unit Cost",
                            "Total Revenue",
                            "Total Cost",
                            "Total Profit"});
                }
            }
        }
    }

    @Test
    void xlsFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(archiveName))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("sample.xlsx")) {
                    XLS xls = new XLS(zis);

                    Sheet sheet = xls.excel.getSheetAt(0);

                    assertThat(sheet.getSheetName()).isEqualTo("Sheet1");

                    String[] headers = {"Postcode", "Sales_Rep_ID", "Sales_Rep_Name", "Year", "Value"};

                    for(int i = 0; i < headers.length; i++) {
                        assertThat(sheet.getRow(0).getCell(i).getStringCellValue()).isEqualTo(headers[i]);
                    }
                }
            }
        }
    }
}
