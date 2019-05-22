/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandvert.java;

import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author ahmed
 */
public class GrandVertJava extends Application {
  
    private static final BorderPane root = new BorderPane();
    public static Stage stage;
    
    public static BorderPane getRoot() {
        return root;
}

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        stage=primaryStage;
        
        URL content = getClass().getResource("/com/behindthemachines/grandvert/Membre/Landing.fxml");
        AnchorPane middle = FXMLLoader.load(content);
        root.setCenter(middle);
        URL login = getClass().getResource("/com/behindthemachines/grandvert/Membre/Login.fxml");
        AnchorPane left = FXMLLoader.load(login);
        root.setLeft(left);
        
        Scene scene = new Scene(root,1300,900);
        scene.getStylesheets().add("/styles/Styles.css");
        
        primaryStage.setTitle("GrandVert");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
