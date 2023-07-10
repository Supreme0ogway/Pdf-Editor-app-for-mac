package MainPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

public class ConvertToPngProcess {
    private String pdfFilePath;
    private String imageFilePath;

    public ConvertToPngProcess(String pdfFilePath, String imageFilePath) {
        this.pdfFilePath = pdfFilePath;
        this.imageFilePath = imageFilePath;
    }

    public void ProcessFile() {
        File pdfFile = new File(pdfFilePath);
        File imageFile = new File(imageFilePath);

        // Check if the PDF file exists
        if (!pdfFile.exists()) {
            System.out.println("The specified PDF file does not exist.");
            return;
        }

        try {
            // Read the PDF file using iText
            PdfReader pdfReader = new PdfReader(pdfFile.getAbsolutePath());

            // Extract images from the PDF file
            PdfReaderContentParser parser = new PdfReaderContentParser(pdfReader);

            for (int page = 1; page <= pdfReader.getNumberOfPages(); page++) {
//                ImageRenderListener listener = new ImageRenderListener(imageFile.getAbsolutePath() + "-" + page + ".png");
            	ImageRenderListener listener = new ImageRenderListener(imageFile.getAbsolutePath());
                parser.processContent(page, listener);
            }

            pdfReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("PDF to PNG conversion completed successfully.");
        System.out.println("Location: " + imageFilePath);
    }
    
    public String getPdfFilePath() {
	      return pdfFilePath;
	}
	
	public void setPdfFilePath(String pdf) {
	      pdfFilePath = pdf;
	}
	
	public String getImageFilePath() {
	      return imageFilePath;
	}
	
	public void setImageFilePath(String image) {
	      imageFilePath = image;
	}

    class ImageRenderListener implements RenderListener {
        private String imageFilePath;

        public ImageRenderListener(String imagePath) {
            this.imageFilePath = imagePath;
        }

        @Override
        public void renderImage(ImageRenderInfo renderInfo) {
            try {
                PdfImageObject imageObject = renderInfo.getImage();
                BufferedImage image = imageObject.getBufferedImage();
                if (image != null) {
                    // Process the image as desired
                    // In this example, we are saving the BufferedImage to a file
                    File outputFile = new File(imageFilePath);
                    ImageIO.write(image, "png", outputFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void beginTextBlock() {
            // Do nothing
        }

        @Override
        public void renderText(TextRenderInfo renderInfo) {
            // Do nothing
        }

        @Override
        public void endTextBlock() {
            // Do nothing
        }
    }
}



//package MainPackage;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
//import com.itextpdf.text.pdf.parser.RenderListener;
//import com.itextpdf.text.pdf.parser.ImageRenderInfo;
//import com.itextpdf.text.pdf.parser.PdfImageObject;
//import com.itextpdf.text.pdf.parser.RenderListener;
//import com.itextpdf.text.pdf.parser.TextRenderInfo;
//
//public class ConvertToPngProcess {
//    private String pdfFilePath;
//    private String imageFilePath;
//
//    public void ProcessFile() {
//        File pdfFile = new File(getPdfFilePath());
//        File imageFile = new File(getImageFilePath());
//
//        // Check if the PDF file exists
//        if (!pdfFile.exists()) {
//            System.out.println("The specified PDF file does not exist.");
//            return;
//        }
//
//        try {
//            // Read the PDF file using iText
//            PdfReader pdfReader = new PdfReader(pdfFile.getAbsolutePath());
//
//            // Extract images from the PDF file
//            PdfReaderContentParser parser = new PdfReaderContentParser(pdfReader);
//
//            for (int page = 1; page <= pdfReader.getNumberOfPages(); page++) {
//                ImageRenderListener listener = new ImageRenderListener(imageFile.getAbsolutePath() + "-" + page + ".png");
//                parser.processContent(page, listener);
//            }
//
//            pdfReader.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("PDF to PNG conversion completed successfully.");
//        System.out.println("Location: " + getImageFilePath());
//    }
//
//    public String getPdfFilePath() {
//        return pdfFilePath;
//    }
//
//    public void setPdfFilePath(String pdf) {
//        pdfFilePath = pdf;
//    }
//
//    public String getImageFilePath() {
//        return imageFilePath;
//    }
//
//    public void setImageFilePath(String image) {
//        imageFilePath = image;
//    }
//
//    class ImageRenderListener implements RenderListener {
//        private String imageFilePath;
//
//        public ImageRenderListener(String imagePath) {
//            this.imageFilePath = imagePath;
//        }
//
//        @Override
//        public void renderImage(ImageRenderInfo renderInfo) {
//            try {
//                PdfImageObject imageObject = renderInfo.getImage();
//                BufferedImage image = imageObject.getBufferedImage();
//                if (image != null) {
//                    // Process the image as desired
//                    // In this example, we are saving the BufferedImage to a file
//                    File outputFile = new File(imageFilePath);
//                    ImageIO.write(image, "png", outputFile);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void beginTextBlock() {
//            // Do nothing
//        }
//
//        @Override
//        public void renderText(TextRenderInfo renderInfo) {
//            // Do nothing
//        }
//
//        @Override
//        public void endTextBlock() {
//            // Do nothing
//        }
//    }
//}
