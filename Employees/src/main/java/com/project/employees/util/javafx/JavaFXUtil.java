package com.project.employees.util.javafx;

import com.project.employees.model.EmployeesPair;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

import static com.project.employees.util.csv.CsvUtil.getFormat;
import static com.project.employees.util.csv.CsvUtil.loadCsvFile;

public class JavaFXUtil {
    private static final TableView<EmployeesPair> table = new TableView<>();
    public static final Label resultLabel = new Label();

    public static Button initFileButton(Stage stage, ChoiceBox<String> formats) {
        Button selectFileButton = new Button("Select CSV File");
        selectFileButton.setOnAction(e -> loadCsvFile(stage, getFormat(formats)));
        return selectFileButton;
    }

    public static void populateColumns() {
        TableColumn<EmployeesPair, Integer> employeeId1Col = new TableColumn<>("Employee ID #1");
        employeeId1Col.setCellValueFactory(cellData -> cellData.getValue().employeeId1Property().asObject());

        TableColumn<EmployeesPair, Integer> employeeId2Col = new TableColumn<>("Employee ID #2");
        employeeId2Col.setCellValueFactory(cellData -> cellData.getValue().employeeId2Property().asObject());

        TableColumn<EmployeesPair, Integer> projectIdCol = new TableColumn<>("Project ID");
        projectIdCol.setCellValueFactory(cellData -> cellData.getValue().projectIdProperty().asObject());

        TableColumn<EmployeesPair, Integer> daysWorkedCol = new TableColumn<>("Days worked");
        daysWorkedCol.setCellValueFactory(cellData -> cellData.getValue().daysWorkedProperty().asObject());

        table.getColumns().addAll(employeeId1Col, employeeId2Col, projectIdCol, daysWorkedCol);
    }

    public static GridPane getGridPane(Button selectFileButton, ChoiceBox formats) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(formats, 0, 0);
        grid.add(selectFileButton, 0, 1);
        grid.add(table, 0, 2);
        grid.add(resultLabel, 0, 3, 3, 1);
        return grid;
    }

    public static void createStage(Stage stage, Scene scene) {
        stage.setTitle("Employee App");
        stage.setScene(scene);
        stage.show();
    }

    public static void updateTableView(List<EmployeesPair> pairs) {
        table.getItems().clear();
        table.getItems().addAll(pairs);
    }
}
