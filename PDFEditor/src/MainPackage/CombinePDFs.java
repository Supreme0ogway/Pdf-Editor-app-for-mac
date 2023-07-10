package MainPackage;

//working but not the moving files
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.stage.Stage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;
import javafx.scene.Cursor;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;


public class CombinePDFs {
  private Stage primaryStage;
  private Group root;
  private boolean fileHovering = false;

  public CombinePDFs(Stage primaryStage, Group root) {
      this.primaryStage = primaryStage;
      this.root = root;
  }

  public void start() {
      primaryStage.setTitle("Combine PDFs");

      // Clear the root group
      root.getChildren().clear();

      // Back button
      Button backButton = new Button("Back");
      backButton.setLayoutX(10);
      backButton.setLayoutY(10);
      backButton.setOnAction(e -> goBack());

      // Message
      Text message = new Text("Combine PDF(s)");
      message.setFont(Font.font("Arial", FontWeight.BOLD, 14));
      message.setFill(Color.BLACK);
      double centerX = primaryStage.getWidth() / 2;
      double centerY = 28; // 100 below the top
      message.setX(centerX - message.getBoundsInLocal().getWidth() / 2);
      message.setY(centerY);
      
      //Create text boxes for location to store and name of combined file
      TextField locationTextField = new TextField();
      locationTextField.setPromptText("Saveing Filepath.");
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
      Text pdfno = new Text("Default: first file\ndropped location.");
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
      Text boxText = new Text("Drag and drop files.");
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
      Rectangle grayBox = new Rectangle(400, 100);
      grayBox.setFill(Color.LIGHTGRAY);
      grayBox.setStroke(Color.TRANSPARENT);
      grayBox.setArcWidth(20);
      grayBox.setArcHeight(20);
      // Position the gray box 15 pixels away from the bottom and left borders
      grayBox.setX(100);
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
              success = true;
              // Process the dropped file(s) here
              for (File file : db.getFiles()) {
                  if (file.getName().toLowerCase().endsWith(".pdf")) {
                      // Create a smaller box for each PDF file
                      Rectangle fileBox = new Rectangle(65, 73);
                      fileBox.setFill(Color.WHITE);
                      fileBox.setStroke(Color.TRANSPARENT);
                      fileBox.setArcWidth(15);
                      fileBox.setArcHeight(15);
                      fileBox.setTranslateY(-13);

                      // Create a VBox for the PDF icon and additional content if needed
                      VBox smallBox = new VBox(5);
                      smallBox.setAlignment(Pos.CENTER);

                      // Create the ImageView for the PDF icon
                      Image pdfIconImage = new Image(getClass().getResourceAsStream("PDFIcon.png"));
                      ImageView pdfIcon = new ImageView(pdfIconImage);
                      pdfIcon.setFitWidth(60);
                      pdfIcon.setFitHeight(60);

                      // Create text for the file name
                      Text fileNameText = new Text(file.getName());
                      fileNameText.setFont(Font.font("Arial", FontWeight.BOLD, 12));

                      // Create a stack pane to hold the file box, PDF icon, and file name
                      StackPane sp = new StackPane(fileBox, smallBox);
                      sp.setAlignment(Pos.CENTER);

                      // Add the PDF icon and file name to the small box
                      smallBox.getChildren().addAll(pdfIcon, fileNameText);

                      // Create the red circle for removal
                      Circle removeCircle = new Circle(10, Color.RED);
                      removeCircle.setVisible(false);

                      // Create the "X" button for removal
                      Text removeButton = new Text("X");
                      removeButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                      removeButton.setFill(Color.WHITE);
                      removeButton.setCursor(Cursor.HAND);
                      removeButton.setVisible(false);
                      removeButton.setTranslateX(-6);
                      removeButton.setTranslateY(3);
                      removeButton.setOnMouseClicked(event -> {
                          boxContainer.getChildren().remove(sp);
                          filePaths.remove(file.getPath()); // Remove the file path from the list
                      });

                      // Create a stack pane for the red circle and remove button
                      StackPane removeButtonPane = new StackPane(removeCircle, removeButton);
                      removeButtonPane.setAlignment(Pos.TOP_RIGHT);
                      removeButtonPane.setTranslateX(5);
                      removeButtonPane.setTranslateY(-85);

                      // Add the red circle and remove button to the small box
                      smallBox.getChildren().add(removeButtonPane);

                      // Adjust the positioning of the white box and red circle
                      sp.setLayoutX(25);
                      sp.setLayoutY(primaryStage.getHeight() - 160); // 160

                      // Mouse event handlers for displaying the red circle and "X" button
                      sp.setOnMouseEntered(event -> {
                          removeCircle.setVisible(true);
                          removeButton.setVisible(true);
                      });

                      sp.setOnMouseExited(event -> {
                          removeCircle.setVisible(false);
                          removeButton.setVisible(false);
                      });

                      // Add mouse event handlers for dragging and dropping within the VBox
                      sp.setOnDragDetected(event -> {
                          Dragboard dragboard = sp.startDragAndDrop(TransferMode.MOVE);
                          ClipboardContent content = new ClipboardContent();
                          content.putString(sp.getId());
                          dragboard.setContent(content);
                          event.consume();
                      });

                      sp.setOnDragOver(event -> {
                          if (event.getGestureSource() != sp && event.getDragboard().hasString()) {
                              event.acceptTransferModes(TransferMode.MOVE);
                          }
                          event.consume();
                      });

                      sp.setOnDragEntered(event -> {
                          if (event.getGestureSource() != sp && event.getDragboard().hasString()) {
                              sp.setStyle("-fx-border-color: black; -fx-border-width: 2;");
                          }
                          event.consume();
                      });

                      sp.setOnDragExited(event -> {
                          sp.setStyle("");
                          event.consume();
                      });

                      // move around order
                      sp.setOnDragDropped(event -> {
                    	    Dragboard dragboard = event.getDragboard();
                    	    boolean successDrop = false;
                    	    if (dragboard.hasString()) {
                    	        StackPane sourcePane = (StackPane) root.lookup("#" + dragboard.getString());
                    	        if (sourcePane != null) {
                    	            int sourceIndex = boxContainer.getChildren().indexOf(sourcePane);
                    	            int targetIndex = boxContainer.getChildren().indexOf(sp);
                    	            if (sourceIndex != -1 && targetIndex != -1) {
                    	                // Update the linked list (boxContainer)
                    	                boxContainer.getChildren().remove(sourcePane);
                    	                boxContainer.getChildren().add(targetIndex, sourcePane);

                    	                // Update the filePaths list
                    	                if (sourceIndex < filePaths.size() && targetIndex < filePaths.size()) {
                    	                    String filePath = filePaths.remove(sourceIndex);
                    	                    filePaths.add(targetIndex, filePath);
                    	                }

                    	                successDrop = true;
                    	            }
                    	        }
                    	    }
                    	    event.setDropCompleted(successDrop);
                    	    event.consume();
                    	});

                      // Set a unique ID for the stack pane
                      sp.setId("smallBox_" + System.currentTimeMillis());

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
      Button combineButton = new Button("Combine");
      combineButton.setLayoutX(100);
      combineButton.setLayoutY(370);
      combineButton.setOnAction(e -> combine());

      // Button event handler for combining the files
      combineButton.setOnAction(event -> {
          // Use the filePaths list for further processing (e.g., combining the files)
          for (String filePath : filePaths) {
              // Process each file path
        	  System.out.println("path: " + filePath);
          }
          
    	  //combine files*********************************************************************
          if(!filePaths.isEmpty() && filePaths.size() > 1) {
        	  CombinePDFFilesProcess combine = new CombinePDFFilesProcess(filePaths);
        	  
        	  //get the file name
        	  String fileName;
        	  if(fileNameTextField.getText().isEmpty()) {
        		  fileName = "Combined.pdf";
        	  } else {
        		  fileName = fileNameTextField.getText() + ".pdf";
        	  }
        	  
        	  //get the path
        	  String combinedFilePath;
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
        	  
        	  combinedFilePath = firstDirPath + File.separator + fileName;
        	  //check if it exists. if so then create new one
        	  String baseFileName = combinedFilePath.substring(0, combinedFilePath.lastIndexOf('.'));
        	  String extension = combinedFilePath.substring(combinedFilePath.lastIndexOf('.'));
        	  
        	  File outputFile = new File(combinedFilePath);
        	  int suffix = 1;
        	  while (outputFile.exists()) {
        		  combinedFilePath = baseFileName + "(" + suffix + ")" + extension;
        		  outputFile = new File(combinedFilePath);
        		  suffix++;
        	  }
        	  
        	  // Call the combine method in the new class
        	  String place = locationTextField.getText();
        	  System.out.println("Output combined file location: " + place);
//        	  if(place.isEmpty()) {
//        		  filePaths.get(0);
//        	  }
        	  
        	  place = combine.DoanloadPDFfile(combinedFilePath);//where to place it
        	  
          } else {
        	  if(filePaths.isEmpty()) {
        		  System.out.println("No files entered.");        		  
        	  }
        	  if(filePaths.size() == 1) {
        		  System.out.println("Only one file. Cannot combine one file.");
        	  }
          }///end if
          boxContainer.getChildren().clear(); // Clear the stack panes from the box
          filePaths.clear(); // Clear the file paths list
        //**********************************************************************************
      });
      
      //clear button
      Button clearButton = new Button("Clear Files");
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
  
  private void combine() {
	  return;
  }
}