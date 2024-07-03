module hexgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens hexgame.controller to javafx.fxml;
    opens hexgame to javafx.fxml;
    exports hexgame;
    exports hexgame.controller;
    exports hexgame.model;
    exports hexgame.service;
    exports hexgame.viewmodel;
}