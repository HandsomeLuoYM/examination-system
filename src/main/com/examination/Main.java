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

import java.io.InputStream;

public class Main extends Application {

    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 1200.0;
    private final double MINIMUM_WINDOW_HEIGHT = 800.0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("四则运算考试系统");
        primaryStage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        primaryStage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        primaryStage.setMaxWidth(MINIMUM_WINDOW_WIDTH);
        primaryStage.setMaxWidth(MINIMUM_WINDOW_WIDTH);
        stage = primaryStage;

        primaryStage.show();
    }



    /**
     * 初始化的窗口，元窗口
     * 实例化登录窗口，窗口切换器
     * 函数名：replaceSceneContent
     * @param fxml 要构造的窗口的fxml文件名
     * @return Initializable
     * @throws Exception
     */
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try
        {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, MINIMUM_WINDOW_WIDTH, MINIMUM_WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
