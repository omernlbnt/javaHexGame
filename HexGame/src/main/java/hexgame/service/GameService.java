package hexgame.service;

import hexgame.model.Hex;
import hexgame.model.Player;
import javafx.scene.layout.Pane;

import java.util.*;

public class GameService {

    private Hex[][] boardArray;
    private Pane boardPane;
    private Set<Hex> visitedHex;


    public void setNewGame(int h, int v, double hexSize) {

        boardArray = new Hex[h][v];
        final double boardWidth = ((h - 1) * hexSize) + (v * hexSize * 0.5) + (hexSize * .5);
        final double boardHeight = (v - 1) * hexSize * 0.72 + hexSize;

        boardPane = new Pane();
        boardPane.setLayoutX((900.0 / 2.0) - (boardWidth / 2.0));
        boardPane.setLayoutY((550.0 / 2.0) - (boardHeight / 2.0));

        boardPane.setMinSize(boardWidth, boardHeight);
        boardPane.setPrefSize(boardWidth, boardHeight);
        boardPane.setMaxSize(boardWidth, boardHeight);


        for (int i = 0; i < h; i++) {
            for (int j = 0; j < v; j++) {

                Hex hex = new Hex(i, j, hexSize);

                boardArray[i][j] = hex;

                boardPane.getChildren().add(hex.getButton());
            }
        }
    }


    public boolean isGameOver(Player player) {
        visitedHex = new HashSet<>();

        List<Hex> firstRow = new ArrayList<>();
        for (int i = 0; i < boardArray.length; i++) {
            Hex hex = null;
            switch (player){
                case RED -> hex = boardArray[0][i];
                case BLUE -> hex = boardArray[i][0];
            }
            if (Objects.nonNull(hex) && player.equals(hex.getOwner())) {
                firstRow.add(hex);
            }
        }
        for (Hex middle : firstRow) {
            if (checkWins(middle, player)) {
                return true;
            }
        }
        return false;

    }


    public Pane getBoardPane() {
        return boardPane;
    }





    private boolean checkWins(Hex middle, Player player) {
        this.visitedHex.add(middle);
        List<Hex> neighbours = getNeighbours(middle, player);


        if ( Player.RED.equals(player) && (middle.getX() == boardArray.length - 1) ) {

            return true;
        }
        else if( Player.BLUE.equals(player) && (middle.getY() == boardArray.length - 1) ){

            return true;
        }
        else {
            for (Hex hex : neighbours) {
                if(checkWins(hex, player)){ return true; }
            }
        }
        return false;
    }



    private List<Hex> getNeighbours(Hex middle, Player player) {
        List<Hex> neighbours = new ArrayList<>();

        isValidHex(middle.getX()-1, middle.getY(), player).ifPresent(neighbours::add);
        isValidHex(middle.getX()+1, middle.getY(), player).ifPresent(neighbours::add);
        isValidHex(middle.getX(), middle.getY()-1, player).ifPresent(neighbours::add);
        isValidHex(middle.getX(), middle.getY()+1, player).ifPresent(neighbours::add);
        isValidHex(middle.getX()+1, middle.getY()-1, player).ifPresent(neighbours::add);
        isValidHex(middle.getX()-1, middle.getY()+1, player).ifPresent(neighbours::add);

        return neighbours;
    }


    private Optional<Hex> isValidHex(int x, int y, Player player){
        Optional<Hex> valid = Optional.empty();
        if(x >= 0 && x < boardArray.length && y >= 0 && y < boardArray.length ){
            Hex hex = boardArray[x][y];
            if( player.equals(hex.getOwner()) && !visitedHex.contains(hex)){
                valid = Optional.of(boardArray[x][y]);
            }
        }
        return valid;
    }
}
