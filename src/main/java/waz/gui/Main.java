package waz.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import waz.Waz;

/**
 * A GUI for Waz using FXML.
 */
public class Main extends Application {

    private Waz waz = new Waz();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Waz");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWaz(waz); // inject the Waz instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
