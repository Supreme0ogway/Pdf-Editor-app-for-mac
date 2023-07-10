package MainPackage;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.awt.Taskbar;
import java.awt.Taskbar.Feature;

public class Home {
	
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Select Option");

        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        
     // Set icon on the application bar (taskbar/dock)
        if (Taskbar.isTaskbarSupported() && Taskbar.getTaskbar().isSupported(Feature.ICON_IMAGE)) {
//            java.awt.Image awtIcon = new javax.swing.ImageIcon(getClass().getResource("/icon/icon.png")).getImage();
        	java.awt.Image awtIcon = new javax.swing.ImageIcon(getClass().getResource("icon.png")).getImage();
        	Taskbar.getTaskbar().setIconImage(awtIcon);
        }

        // Set the icon for the primaryStage
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(icon);
        
        //icon
//        Image icon = new Image(getClass().getResourceAsStream("/icon/icon.png"));
//        primaryStage.getIcons().add(icon);
        
//        primaryStage.getIcons().add(new Image(Home.class.getResourceAsStream("icon.png")));
//        primaryStage.setTitle("PDF Crusher");
        
        
        //*********************************** buttons on the page ****************************

        Button combinePdfButton = new Button("Combine PDF Files");
        combinePdfButton.setLayoutX(235);
        combinePdfButton.setLayoutY(25);
        combinePdfButton.setOnAction(e -> openCombinePDFs(primaryStage, root));

        Button convertToWordButton = new Button("Convert PDF to Word");
        convertToWordButton.setLayoutX(235);
        convertToWordButton.setLayoutY(65);
        convertToWordButton.setOnAction(e -> openConvertWord(primaryStage, root));
        
        Button convertToCsvButton = new Button("Convert PDF to CSV");
        convertToCsvButton.setLayoutX(235);
        convertToCsvButton.setLayoutY(105);
        convertToCsvButton.setOnAction(e -> openConvertCSV(primaryStage, root));

        Button convertToTextButton = new Button("Convert PDF to Text");
        convertToTextButton.setLayoutX(235);
        convertToTextButton.setLayoutY(145);
        convertToTextButton.setOnAction(e -> openConvertTXT(primaryStage, root));

        Button convertToPngButton = new Button("Convert PDF to PNG");
        convertToPngButton.setLayoutX(235);
        convertToPngButton.setLayoutY(185);
        convertToPngButton.setOnAction(e -> openConvertPNG(primaryStage, root));

        Button convertToJpegButton = new Button("Convert PDF to JPEG");
        convertToJpegButton.setLayoutX(235);
        convertToJpegButton.setLayoutY(225);
        convertToJpegButton.setOnAction(e -> openConvertJPEG(primaryStage, root));
        
        Button reArrangePagesButton = new Button("Re-arrange PDF pages");
        reArrangePagesButton.setLayoutX(235);
        reArrangePagesButton.setLayoutY(265);
        reArrangePagesButton.setOnAction(e -> openArrangePages(primaryStage, root));
        
        Button DeletePagesButton = new Button("Delete PDF pages");
        DeletePagesButton.setLayoutX(235);
        DeletePagesButton.setLayoutY(305);
        DeletePagesButton.setOnAction(e -> openDelPages(primaryStage, root));
        
        // Back button
        Button infoButton = new Button("Information");
        infoButton.setLayoutX(10);
        infoButton.setLayoutY(10);
        infoButton.setOnAction(e -> information(primaryStage, root));

        root.getChildren().addAll(combinePdfButton, convertToWordButton, convertToTextButton,
                convertToCsvButton, convertToPngButton, convertToJpegButton, reArrangePagesButton,
                DeletePagesButton, infoButton);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void information(Stage primaryStage, Group root) {
    	InformationPage i = new InformationPage(primaryStage, root);
    	i.start();
    }
    
    private void openDelPages(Stage primaryStage, Group root) {
    	DeletePDFpages d = new DeletePDFpages(primaryStage, root);
    	d.start();
    }
    
    private void openArrangePages(Stage primaryStage, Group root) {
    	RearrangePDFPages a = new RearrangePDFPages(primaryStage, root);
    	a.start();
    }

    private void openCombinePDFs(Stage primaryStage, Group root) {
        CombinePDFs combinePDFs = new CombinePDFs(primaryStage, root);
        combinePDFs.start();
    }

    private void openConvertCSV(Stage primaryStage, Group root) {
        ConvertCSV convertCSV = new ConvertCSV(primaryStage, root);
        convertCSV.start();
    }

    private void openConvertTXT(Stage primaryStage, Group root) {
        ConvertTXT convertTXT = new ConvertTXT(primaryStage, root);
        convertTXT.start();
    }

    private void openConvertWord(Stage primaryStage, Group root) {
        ConvertWORD convertWORD = new ConvertWORD(primaryStage, root);
        convertWORD.start();
    }

    private void openConvertPNG(Stage primaryStage, Group root) {
        ConvertPNG convertPNG = new ConvertPNG(primaryStage, root);
        convertPNG.start();
    }

    private void openConvertJPEG(Stage primaryStage, Group root) {
        ConvertJPEG convertJPEG = new ConvertJPEG(primaryStage, root);
        convertJPEG.start();
    }
}
