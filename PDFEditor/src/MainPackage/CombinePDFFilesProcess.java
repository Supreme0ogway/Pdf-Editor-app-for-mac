package MainPackage;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CombinePDFFilesProcess {
	
	//instance
	private List<String> filePaths;
	
	//constructor set variables
	public CombinePDFFilesProcess (List<String> filePaths) {
		this.filePaths = filePaths;
	}
	
	//downloads the 
	public String DoanloadPDFfile(String combinedFilePath) {
		String finalFilePath = "";
		//read in the files
		finalFilePath = readPdfFiles(combinedFilePath);
		//store the files
		//create the file
		//return the path
		
		return finalFilePath;
	}
	
	private String readPdfFiles(String combinedFilePath) {
		File outputFile = new File(combinedFilePath);
		
		try {
            //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfSmartCopy copy = new PdfSmartCopy(document, new FileOutputStream(outputFile));
            document.open();

            for (String filePath : filePaths) {
                PdfReader pdfReader = new PdfReader(filePath);
                int pageCount = pdfReader.getNumberOfPages();

                for (int i = 0; i < pageCount; i++) {
                    copy.addPage(copy.getImportedPage(pdfReader, i + 1));
                }

                pdfReader.close();
            }

            document.close();
            return combinedFilePath;
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return null;
        }

    }
}
