package MainPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConvertJPEG {
  private Stage primaryStage;
  private Group root;
  private boolean fileHovering = false;
  
  public ConvertJPEG(Stage primaryStage, Group root) {
      this.primaryStage = primaryStage;
      this.root = root;
  }

  public void start() {
      primaryStage.setTitle("Convert PDF to JPG");

      // Clear the root group
      root.getChildren().clear();

      // Back button
      Button backButton = new Button("Back");
      backButton.setLayoutX(10);
      backButton.setLayoutY(10);
      backButton.setOnAction(e -> goBack());

      // Message
      Text message = new Text("Convert PDF");
      message.setFont(Font.font("Arial", FontWeight.BOLD, 14));
      message.setFill(Color.BLACK);
      double centerX = primaryStage.getWidth() / 2;
      double centerY = 28; // 100 below the top
      message.setX(centerX - message.getBoundsInLocal().getWidth() / 2);
      message.setY(centerY);
      
      //Create text boxes for location to store and name of combined file
      TextField locationTextField = new TextField();
      locationTextField.setPromptText("Saving Filepath.");
      locationTextField.setTranslateX(217);
      locationTextField.setTranslateY(40);
      
      //second text box
      TextField fileNameTextField = new TextField();
      fileNameTextField.setPromptText("Enter file name");
      fileNameTextField.setTranslateX(217);
      fileNameTextField.setTranslateY(70);
      
      //pdf extention
      Text pdfExt = new Text(".pdf");
      pdfExt.setFont(Font.font("Arial", FontWeight.BOLD, 14));
      pdfExt.setLayoutX(377);
      pdfExt.setLayoutY(92);
      
    //pdf extention
      Text pdfno = new Text("Default: file dropped\n     location.");
      pdfno.setFont(Font.font("Arial", FontWeight.BLACK, 14));
      pdfno.setLayoutX(380);
      pdfno.setLayoutY(49);

      // Rounded rectangle box with light gray fill
      Rectangle box = new Rectangle(400, 150);
      box.setArcWidth(20);
      box.setArcHeight(20);
      box.setFill(Color.LIGHTGRAY);
      box.setTranslateY(46);

      // Text inside the box
      Text boxText = new Text("Drag and drop file.");
      boxText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
      boxText.setFill(Color.BLACK);
      boxText.setTranslateY(46);

      // StackPane to center the box and text
      StackPane stackPane = new StackPane();
      stackPane.setAlignment(Pos.CENTER);
      stackPane.setLayoutX((primaryStage.getWidth() - box.getWidth()) / 2);
      stackPane.setLayoutY((-150 + primaryStage.getHeight() - box.getHeight()) / 2);
      stackPane.getChildren().addAll(box, boxText);

      // Drag and drop functionality
      stackPane.setOnDragOver(e -> {
          if (e.getDragboard().hasFiles()) {
              e.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
              if (!fileHovering) {
                  box.setFill(Color.LIGHTGREEN);
              }
          }
          e.consume();
      });

      stackPane.setOnDragEntered(e -> {
          if (e.getDragboard().hasFiles()) {
              fileHovering = true;
              box.setFill(Color.LIGHTGREEN);
          }
          e.consume();
      });

      stackPane.setOnDragExited(e -> {
          if (e.getDragboard().hasFiles()) {
              fileHovering = false;
              box.setFill(Color.LIGHTGRAY);
          }
          e.consume();
      });
      
      // Create the gray box with rounded edges
      Rectangle grayBox = new Rectangle(100, 100);
      grayBox.setFill(Color.LIGHTGRAY);
      grayBox.setStroke(Color.TRANSPARENT);
      grayBox.setArcWidth(20);
      grayBox.setArcHeight(20);
      // Position the gray box 15 pixels away from the bottom and left borders
      grayBox.setX(250);
      grayBox.setY(265);

      // Create the VBox to hold smaller boxes
      HBox boxContainer = new HBox(10);
      boxContainer.setLayoutX(grayBox.getX() + 10);
      boxContainer.setLayoutY(grayBox.getY() + 10);
      
   // Add dropped files to the VBox
      List<String> filePaths = new ArrayList<>(); // List to store the file paths

      stackPane.setOnDragDropped(e -> {
    	    javafx.scene.input.Dragboard db = e.getDragboard();
    	    boolean success = false;
    	    if (db.hasFiles()) {
    	        List<File> droppedFiles = db.getFiles();
    	        if (droppedFiles.size() == 1) { // Only allow one file to be dropped
    	            success = true;
    	            File file = droppedFiles.get(0);
    	            if (file.getName().toLowerCase().endsWith(".pdf")) {
    	                // Process the dropped PDF file here

    	                // Rounded rectangle box with light gray fill
    	                Rectangle fileBox = new Rectangle(65, 73);
    	                fileBox.setFill(Color.WHITE);
    	                fileBox.setStroke(Color.TRANSPARENT);
    	                fileBox.setArcWidth(15);
    	                fileBox.setArcHeight(15);
    	                fileBox.setTranslateX(5);
    	                fileBox.setTranslateY(4);

    	                // Create a Pane to hold the file box, PDF icon, and file name
    	                Pane filePane = new Pane();
    	                filePane.setPrefSize(65, 73);

    	                // Create the ImageView for the PDF icon
    	                Image pdfIconImage = new Image(getClass().getResourceAsStream("PDFIcon.png"));
    	                ImageView pdfIcon = new ImageView(pdfIconImage);
    	                pdfIcon.setFitWidth(60);
    	                pdfIcon.setFitHeight(60);
    	                pdfIcon.setLayoutX(8);
    	                pdfIcon.setLayoutY(2.5);

    	                // Create text for the file name
    	                Text fileNameText = new Text(file.getName());
    	                fileNameText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    	                fileNameText.setLayoutX(0);
    	                fileNameText.setLayoutY(72);

    	                // Add the PDF icon and file name to the pane
    	                filePane.getChildren().addAll(pdfIcon, fileNameText);

    	                // Create a Tooltip to display the full file name
    	                Tooltip tooltip = new Tooltip(file.getName());
    	                Tooltip.install(filePane, tooltip);

    	                // Create a stack pane to hold the file box and the pane with the PDF icon and file name
    	                StackPane sp = new StackPane(fileBox, filePane);
    	                sp.setAlignment(Pos.CENTER);

    	                // Adjust the positioning of the stack pane
    	                sp.setLayoutX(25);
    	                sp.setLayoutY(primaryStage.getHeight() - 160); // 160

    	                // Clear previous file, if any
    	                boxContainer.getChildren().clear();
    	                filePaths.clear();

    	                // Add the stack pane to the VBox
    	                boxContainer.getChildren().add(sp);

    	                filePaths.add(file.getPath()); // Add the file path to the list
    	            }
    	        }
    	    }
    	    e.setDropCompleted(success);
    	    e.consume();
    	});

      
    //combine button
      Button combineButton = new Button("Convert");
      combineButton.setLayoutX(100);
      combineButton.setLayoutY(370);
      combineButton.setOnAction(e -> convert());

      // Button event handler for combining the files
      combineButton.setOnAction(event -> {
          // Use the filePaths list for further processing (e.g., combining the files)
          for (String filePath : filePaths) {
              // Process each file path
        	  System.out.println("path: " + filePath);
          }
          
    	  //convert files*********************************************************************
          if(!filePaths.isEmpty() && filePaths.size() == 1) {
        	  ConvertJPEGProcess convert = new ConvertJPEGProcess("", "");
        	  convert.setPdfFilePath(filePaths.get(0));
        	  
        	  //get the file name
        	  String fileName;
        	  if(fileNameTextField.getText().isEmpty()) {
        		  fileName = "PDFtoJPEG.jpg";
        	  } else {
//        		  fileName = fileNameTextField.getText() + ".jpg";
        		  fileName = fileNameTextField.getText() + ".jpg";
        	  }
        	  
        	  //get the path
        	  String wordFilePath;
        	  String firstDirPath = "";
        	  if(locationTextField.getText().isEmpty()) {
        		  File f = new File(filePaths.get(0));
        		  if(f.exists()) {
        			  firstDirPath = f.getParent();		  
        		  } else {
        			  System.out.println("The first file dropped doesn't exist.");
        			  System.exit(0);
        		  }        		  
        	  } else {//chosen file path
        		  File f = new File(locationTextField.getText());
        		  if(f.isDirectory()) {
        			  firstDirPath = locationTextField.getText();
        		  } else {
        			  locationTextField.setText("Not a Directory");
        			  //then combine them and save
        			  File fe = new File(filePaths.get(0));
            		  if(fe.exists()) {
            			  firstDirPath = fe.getParent();		  
            		  } else {
            			  System.out.println("The first file dropped doesn't exist.");
            			  System.exit(0);
            		  }      
        		  }
        	  }
        	  
        	  wordFilePath = firstDirPath + File.separator + fileName;
        	  //check if it exists. if so then create new one
        	  String baseFileName = wordFilePath.substring(0, wordFilePath.lastIndexOf('.'));
        	  String extension = wordFilePath.substring(wordFilePath.lastIndexOf('.'));
        	  
        	  File outputFile = new File(wordFilePath);
        	  int suffix = 1;
        	  while (outputFile.exists()) {
        		  wordFilePath = baseFileName + "(" + suffix + ")" + extension;
        		  outputFile = new File(wordFilePath);
        		  suffix++;
        	  }
        	  
        	  convert.setImageFilePath(wordFilePath);
        	  
        	  // Call the combine method in the new class
        	  String place = locationTextField.getText();
        	  System.out.println("Output combined file location: " + place);
        	  
        	  convert.ProcessFile();
        	  //place = combine.DoanloadPDFfile(wordFilePath);//where to place it
        	  
          } else {
        	  if(filePaths.isEmpty()) {
        		  System.out.println("No files entered.");        		  
        	  } else {
        		  System.out.println("Too many files.");
        	  }
          }///end if
          boxContainer.getChildren().clear(); // Clear the stack panes from the box
          filePaths.clear(); // Clear the file paths list
        //**********************************************************************************
      });
      
      //clear button
      Button clearButton = new Button("Clear File");
      clearButton.setLayoutX(425);
      clearButton.setLayoutY(370);
      clearButton.setOnAction(event -> {
          boxContainer.getChildren().clear(); // Clear the stack panes from the box
          filePaths.clear(); // Clear the file paths list
      });

      // Mouse events
      stackPane.setOnMouseEntered(e -> {
          if (fileHovering) {
              box.setFill(Color.LIGHTGREEN);
          } else {
              box.setFill(Color.LIGHTGRAY);
          }
          e.consume();
      });

      stackPane.setOnMouseExited(e -> {
          if (fileHovering) {
              box.setFill(Color.LIGHTGREEN);
          } else {
              box.setFill(Color.LIGHTGRAY);
          }
          e.consume();
      });

      // Create a group to contain the gray box and VBox
      Group grayBoxGroup = new Group(grayBox, boxContainer);

      // Add the gray box group to the root group
      root.getChildren().addAll(backButton, message, 
    		  stackPane, grayBoxGroup, combineButton, 
    		  clearButton, locationTextField, fileNameTextField,
    		  pdfExt, pdfno);
  }

  private void goBack() {
      Home homeAfterDrag = new Home();
      homeAfterDrag.start(primaryStage);
  }
  
  private void convert() {
	  return;
  }
}