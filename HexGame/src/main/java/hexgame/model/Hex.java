package hexgame.model;

import hexgame.HexGameMain;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Hex {

    private int x;
    private int y;
    private Button button;
    private StackPane point;

    private Player owner = Player.NONE;

    public Hex(int x, int y, double hexSize) {
        this.x = x;
        this.y = y;

        button = new Button();
        button.setMinSize(hexSize, hexSize);
        button.setPrefSize(hexSize, hexSize);
        button.setMaxSize(hexSize, hexSize);
        button.getStyleClass().addAll("hex-button", "hex-button-active");
        button.setLayoutX((x * hexSize) + (y * hexSize * 0.5));
        button.setLayoutY(y * hexSize * 0.72);

        point = new StackPane();
        point.getStyleClass().addAll("hex-pointer");
        point.setMinSize(hexSize / 2.0, hexSize / 2.0);
        point.setPrefSize(hexSize / 2.0, hexSize / 2.0);
        point.setMaxSize(hexSize / 2.0, hexSize / 2.0);
        button.setGraphic(point);

        button.setOnAction(this::hexClicked);
        button.setUserData(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Button getButton() {
        return button;
    }

    public StackPane getPoint() {
        return point;
    }


    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        switch (player) {
            case RED -> {
                getPoint().getStyleClass().add("hex-pointer-player1");
            }
            case BLUE -> {
                getPoint().getStyleClass().add("hex-pointer-player2");
            }
        }
        button.getStyleClass().remove("hex-button-active");
    }

    public void hexClicked(ActionEvent event) {
        HexGameMain.gameModel.place(this);
    }

    @Override
    public String toString() {
        return String.format("Hex { x = %d, y =%d}", getX(), getY());
    }
}
