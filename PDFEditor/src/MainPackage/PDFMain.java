package MainPackage;

import javafx.application.Application;
import javafx.stage.Stage;

public class PDFMain extends Application {
    public static void main(String[] args) {
    	Application.launch(args);
        //launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Home home = new Home();
        home.start(primaryStage);
    }
}