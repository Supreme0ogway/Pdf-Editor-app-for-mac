package MainPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class ConvertToWordProcess {
	private String readingPath;
	private String writingPath;
	
	public String ProcessFile() {
		
		try {
            // Read the PDF file using iText
            PdfReader pdfReader = new PdfReader(getReadingPath());

            // Create a Word document using Apache POI
            XWPFDocument document = new XWPFDocument();

            // Extract text from PDF and add it to the Word document
            for (int page = 1; page <= pdfReader.getNumberOfPages(); page++) {
                String text = PdfTextExtractor.getTextFromPage(pdfReader, page);
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(text);
            }

            // Save the Word document
            FileOutputStream outputStream = new FileOutputStream(new File(getWritingPath()));
            document.write(outputStream);
            outputStream.close();

            System.out.println("PDF to Word conversion completed successfully.");
            
            document.close();
        } catch (IOException e) {
            System.err.println("Error converting PDF to Word: " + e.getMessage());
        }
		
		
		return getWritingPath();
	}
	
	public String getReadingPath() {
		return readingPath;
	}
	
	public void setReadingPath(String rp) {
		readingPath = rp;
	}
	
	public String getWritingPath() {
		return writingPath;
	}
	
	public void setWritingPath(String wp) {
		writingPath = wp;
	}
}
