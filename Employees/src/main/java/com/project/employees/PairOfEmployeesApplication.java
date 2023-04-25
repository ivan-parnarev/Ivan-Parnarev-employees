package com.project.employees;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static com.project.employees.util.csv.CsvUtil.initFormatsBox;
import static com.project.employees.util.javafx.JavaFXUtil.*;

public class PairOfEmployeesApplication extends Application {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        ChoiceBox<String> formats = initFormatsBox();
        Button selectFileButton = initFileButton(stage, formats);
        populateColumns();

        GridPane grid = getGridPane(selectFileButton, formats);
        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        createStage(stage, scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}