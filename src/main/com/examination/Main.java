package main.com.examination;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.com.examination.controller.Controller;
import main.com.examination.controller.UserController;
import main.com.examination.view.ExGui;

import java.io.InputStream;

public class Main extends Application {


    public static void main(String[] args) {
        ExGui exercise = new ExGui();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
