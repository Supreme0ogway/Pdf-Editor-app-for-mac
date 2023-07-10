package MainPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

class ConvertToCSVProcess {
    private String pdfFilePath;
    private String csvFilePath;

    public void ProcessFile() {
        File pdfFile = new File(getPdfFilePath());
        File csvFile = new File(getCSVFilePath());

        // Check if the PDF file exists
        if (!pdfFile.exists()) {
            System.out.println("The specified PDF file does not exist.");
            return;
        }

        try {
            // Read the PDF file using iText
            PdfReader pdfReader = new PdfReader(pdfFile.getAbsolutePath());

            // Extract text from the PDF file
            StringBuilder textBuilder = new StringBuilder();
            for (int page = 1; page <= pdfReader.getNumberOfPages(); page++) {
                String pageText = PdfTextExtractor.getTextFromPage(pdfReader, page);
                textBuilder.append(pageText);
            }

            pdfReader.close();

            // Write the extracted text to the TXT file
            FileOutputStream outputStream = new FileOutputStream(csvFile);
            outputStream.write(textBuilder.toString().getBytes());
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("PDF to CSV conversion completed successfully.");
        System.out.println("Location: " + getCSVFilePath());
    }

    public String getPdfFilePath() {
        return pdfFilePath;
    }

    public void setPdfFilePath(String pdf) {
        pdfFilePath = pdf;
    }

    public String getCSVFilePath() {
        return csvFilePath;
    }

    public void setCSVFilePath(String csv) {
    	csvFilePath = csv;
    }
}
