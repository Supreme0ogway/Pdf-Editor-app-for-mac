package MainPackage;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InformationPage {
  private Stage primaryStage;
  private Group root;
  
  public InformationPage(Stage primaryStage, Group root) {
      this.primaryStage = primaryStage;
      this.root = root;
  }

  public void start() {
      primaryStage.setTitle("Delete PDF Pages");

      // Clear the root group
      root.getChildren().clear();

      // Back button
      Button backButton = new Button("Back");
      backButton.setLayoutX(10);
      backButton.setLayoutY(10);
      backButton.setOnAction(e -> goBack());
      
      //information 
      Text info = new Text();
      info.setText("Combine PDF files:            The order you drag and drop is the order combined.\n          "
      		+ "                                         You can rearrange.");
      info.setTranslateX(30);
      info.setTranslateY(60);
      
    //information 
      Text info1 = new Text();
      info1.setText("Convert PDF to Word:      One file only. Entering a file name and path are optional");
      info1.setTranslateX(30);
      info1.setTranslateY(100);
      
      Text info2 = new Text();
      info2.setText("Convert PDF to CSV:        One file only. Entering a file name and path are optional");
      info2.setTranslateX(30);
      info2.setTranslateY(120);
      
      Text info3 = new Text();
      info3.setText("Convert PDF to txt:            One file only. Entering a file name and path are optional");
      info3.setTranslateX(30);
      info3.setTranslateY(140);
      
      Text info4 = new Text();
      info4.setText("Convert PDF to PNG:        One file only. Entering a file name and path are optional\n"
      		+ "                                                   It only extracts the image.");
      info4.setTranslateX(30);
      info4.setTranslateY(160);
      
      Text info5 = new Text();
      info5.setText("Convert PDF to JPG:        One file only. Entering a file name and path are optional\n"
      		+ "                                                  It only extracts the image.");
      info5.setTranslateX(30);
      info5.setTranslateY(200);
      
      Text info6 = new Text();
      info6.setText("Re-arrange PDF:                One file only. Entering a file name and path are optional\n"
      		+ "                                                  It page numbers required. Only rearranges entered page numbers (not a range).");
      info6.setTranslateX(30);
      info6.setTranslateY(240);
      
      Text info7 = new Text();
      info7.setText("Delete PDF pages:             One file only. Entering a file name and path are optional\n"
      		+ "                                                   It page numbers required. Deletes in range.\n"
      		+ "                                                   Page numbers can be the same for a single page deletion.");
      info7.setTranslateX(30);
      info7.setTranslateY(280);

      // Add the gray box group to the root group
      root.getChildren().addAll(backButton, info, info1, info2, info3, info4, info5, info6, info7);
  }

  private void goBack() {
      Home homeAfterDrag = new Home();
      homeAfterDrag.start(primaryStage);
  }
}