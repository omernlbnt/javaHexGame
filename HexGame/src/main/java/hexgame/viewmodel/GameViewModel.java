package hexgame.viewmodel;

import hexgame.model.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class GameViewModel {

    private StringProperty moveCount;
    private StringProperty turn;
    private StringProperty pointLeft;

    public GameViewModel(int redPoints, int bluePoints) {
        moveCount = new SimpleStringProperty("Move Count : " + 0);
        turn = new SimpleStringProperty("RED" + "'s Turn");
        pointLeft = new SimpleStringProperty("Point Left [ RED: " + redPoints + ", BLUE: " + bluePoints + " ]");
    }



    public StringProperty moveCountProperty() {
        return moveCount;
    }

    public StringProperty turnProperty() {
        return turn;
    }

    public StringProperty pointLeftProperty() {
        return pointLeft;
    }

    public void setMoveCount(int count) {
        this.moveCount.set("Move Count : " + count);
    }

    public void setTurn(Player turn) {
        this.turn.set(turn.name() + "'s Turn");
    }
    public void setWinner(Player turn) {
        this.turn.set(turn.name() + " Wins");
    }

    public void setPointLeft(int redPoints, int bluePoints) {
        this.pointLeft.set("Point Left [ RED: " + redPoints + ", BLUE: " + bluePoints + " ]");
    }


}
