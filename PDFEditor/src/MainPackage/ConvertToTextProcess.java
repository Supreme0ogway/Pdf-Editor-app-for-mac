package MainPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

class ConvertToTextProcess {
	private String pdfFilePath;
	private String txtFilePath;
	
    public void ProcessFile() {
        File pdfFile = new File(getPdfFilePath());
        File txtFile = new File(getTxtFilePath());

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
        	FileOutputStream outputStream = new FileOutputStream(txtFile);
        	outputStream.write(textBuilder.toString().getBytes());
        	outputStream.close();
        	
        } catch(IOException e) {
        	e.printStackTrace();
        }

        System.out.println("PDF to TXT conversion completed successfully.");
        System.out.println("Location: " + txtFilePath);
    }
    
    public String getPdfFilePath() {
    	return pdfFilePath;
    }
    
    public void setPdfFilePath(String pdf) {
    	pdfFilePath = pdf;
    }
    
    public String getTxtFilePath() {
    	return txtFilePath;
    }
    
    public void setTxtFilePath(String txt) {
    	txtFilePath = txt;
    }
}

