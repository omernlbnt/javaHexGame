package hexgame;

import hexgame.model.GameModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HexGameMain extends Application {

    public static GameModel gameModel;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HexGameMain.class.getResource("controller/GameView.fxml"));
        Pane root = fxmlLoader.load();;

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add( getClass().getResource("controller/GameStyle.css").toExternalForm());
        stage.setTitle("HexGame");

        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }

}