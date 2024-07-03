package hexgame.controller;

import hexgame.HexGameMain;
import hexgame.model.GameModel;
import hexgame.service.GameService;
import hexgame.viewmodel.GameViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML private Pane boardPane;

    @FXML private ToggleGroup boardSize;

    @FXML private Button startButton;

    @FXML private Label moveCountLabel;
    @FXML private Label turnLabel;
    @FXML private Label pointLabel;

    private GameModel gameModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameViewModel gameViewModel = new GameViewModel(25, 24);

        moveCountLabel.textProperty().bindBidirectional(gameViewModel.moveCountProperty());
        turnLabel.textProperty().bindBidirectional(gameViewModel.turnProperty());
        pointLabel.textProperty().bindBidirectional(gameViewModel.pointLeftProperty());


        gameModel = new GameModel(new GameService(), gameViewModel);
        HexGameMain.gameModel = this.gameModel;

        this.setNewGame(7,7, 80);
    }

    @FXML private void startButtonAction() {
        RadioButton selected = (RadioButton)boardSize.getSelectedToggle();
        switch (selected.getText()) {
            case "17x17": { this.setNewGame(17,17, 32); }
                break;
            case "11x11": { this.setNewGame(11,11, 50); }
                break;
            case "7x7": { this.setNewGame(7,7, 80); }
            default:
                break;
        }
    }

    private void setNewGame(int h, int v, double hexSize) {
        Pane hexPane = this.gameModel.setNewGame(h, v, hexSize);
        this.boardPane.getChildren().clear();
        this.boardPane.getChildren().add(hexPane);
    }



}
