package hexgame.model;

import hexgame.service.GameService;
import hexgame.viewmodel.GameViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameModel {


    private int redPointCount = 60;
    private int bluePointCount = 61;

    private int moveCount = 0;

    private boolean isGameOver = false;

    private Player turn = Player.RED;

    private final GameService gameService;
    private final GameViewModel viewModel;


    public GameModel(GameService gameService, GameViewModel viewModel) {

        this.gameService = gameService;
        this.viewModel = viewModel;
    }

    public Pane setNewGame(int h, int v, double hexSize) {


        this.redPointCount = ((h*v) / 2) +1;


        this.bluePointCount = (h*v) / 2;

        this.viewModel.setPointLeft(redPointCount, bluePointCount);

        this.moveCount = 0;
        this.viewModel.setMoveCount(moveCount);


        this.turn = Player.RED;
        this.viewModel.setTurn(turn);

        this.isGameOver = false;


        this.gameService.setNewGame(h, v, hexSize);
        return this.gameService.getBoardPane();
    }

    public void place(Hex hex) {

        if( !arePointsLeft() || isGameOver){
            return;
        }
        /** Swap Rule Action */
        if( moveCount == 0 && Player.BLUE.equals(turn) && Player.RED.equals(hex.getOwner()) ) {
            hex.setOwner(turn);
            changeTurn();
        }

        else if ( Player.NONE.equals(hex.getOwner()) ) {
            hex.setOwner(turn);
            if(gameService.isGameOver(turn)){
                viewModel.setWinner(turn);
                this.isGameOver = true;
                return;
            }
            changeTurn();
        }
    }


    private boolean arePointsLeft() {
        if(Player.RED.equals(turn) && redPointCount == 0){
            return false;
        }
        else if(Player.BLUE.equals(turn) && bluePointCount == 0){
            return false;
        }
        return true;
    }
    private void changeTurn() {
        switch (turn) {
            case RED -> {
                decreaseRedPoints();
                this.turn = Player.BLUE;
            }
            case BLUE -> {
                decreaseBluePoints();
                this.turn = Player.RED;
                increaseMoveCount();
            }
        }
        this.viewModel.setTurn(this.turn);

    }
    private void increaseMoveCount() {
        moveCount++;
        this.viewModel.setMoveCount(moveCount);
    }
    private void decreaseRedPoints() {
        this.redPointCount--;
        this.viewModel.setPointLeft(redPointCount, bluePointCount);
    }
    private void decreaseBluePoints() {
        this.bluePointCount--;
        this.viewModel.setPointLeft(redPointCount, bluePointCount);
    }
}