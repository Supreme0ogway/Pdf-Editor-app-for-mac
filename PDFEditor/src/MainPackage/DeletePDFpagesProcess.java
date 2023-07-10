package MainPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

class DeletePDFpagesProcess {
	private String pdfFilePath;
	private String editedFilePath;
	private String secondPageNumber;
	private String firstPageNumber;
	
	public void ProcessFile() {
	    File pdfFile = new File(getPdfFilePath());
	    File editedFile = new File(getNewPDFfilePath());

	    // Check if the PDF file exists
	    if (!pdfFile.exists()) {
	        System.out.println("The specified PDF file does not exist.");
	        return;
	    }

	    try {
	        // Read the PDF file using iText
	        PdfReader reader = new PdfReader(pdfFile.getAbsolutePath());

	        // Get the total number of pages
	        int totalPages = reader.getNumberOfPages();

	        // Convert page numbers to integers
	        int firstPage = Integer.parseInt(getFirstPage());
	        int secondPage = Integer.parseInt(getSecondPage());

	        // Check if page numbers are within the valid range
	        if (firstPage < 1 || firstPage > totalPages || secondPage < 1 || secondPage > totalPages) {
	            String errorMessage = "Invalid page numbers. Page numbers should be within the range of 1 to " + totalPages;
	            
	            // Show JavaFX popup with error message
	            Platform.runLater(() -> {
	                Alert alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Invalid Page Numbers");
	                alert.setHeaderText(null);
	                alert.setContentText(errorMessage);
	                alert.showAndWait();
	            });
	            
	            reader.close();
	            return;
	        }

	        // Create a new PDF document
	        Document document = new Document();
	        PdfCopy copy;
	        try {
	            copy = new PdfCopy(document, new FileOutputStream(editedFile));
	        } catch (DocumentException e) {
	            e.printStackTrace();
	            reader.close();
	            return;
	        }
	        document.open();

	        // Add pages before the range
	        for (int pageNumber = 1; pageNumber < firstPage; pageNumber++) {
	            PdfImportedPage page = copy.getImportedPage(reader, pageNumber);
	            copy.addPage(page);
	        }

	        // Add pages after the range
	        for (int pageNumber = secondPage + 1; pageNumber <= totalPages; pageNumber++) {
	            PdfImportedPage page = copy.getImportedPage(reader, pageNumber);
	            copy.addPage(page);
	        }

	        // Close the new PDF document
	        document.close();

	        // Close the PdfReader
	        reader.close();

	        System.out.println("PDF pages deletion completed successfully.");
	        System.out.println("Location: " + editedFile.getAbsolutePath());

	    } catch (IOException | DocumentException e) {
	        e.printStackTrace();
	    }
	}
    
    public String getPdfFilePath() {
    	return pdfFilePath;
    }
    
    public void setPdfFilePath(String pdf) {
    	pdfFilePath = pdf;
    }
    
    public String getNewPDFfilePath() {
    	return editedFilePath;
    }
    
    public void setNewPDFfilePath(String txt) {
    	editedFilePath = txt;
    }
    
    public void setFirstPage(String fpn) {
    	firstPageNumber = fpn;
    }
    
    public String getFirstPage() {
    	return firstPageNumber;
    }
    
    public void setSecondPage(String fpn) {
    	secondPageNumber = fpn;
    }
    
    public String getSecondPage() {
    	return secondPageNumber;
    }
}

