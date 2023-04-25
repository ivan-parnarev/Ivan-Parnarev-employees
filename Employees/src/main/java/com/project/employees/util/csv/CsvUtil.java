package com.project.employees.util.csv;

import com.project.employees.model.EmployeeProject;
import com.project.employees.model.EmployeesPair;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.project.employees.model.EmployeeProject.getPairs;
import static com.project.employees.model.EmployeeProject.getProjectEmployees;
import static com.project.employees.model.EmployeesPair.getLongestWorkingPair;
import static com.project.employees.util.date.DateUtil.CUSTOM_DATE_FORMATS;
import static com.project.employees.util.date.DateUtil.parseDate;
import static com.project.employees.util.javafx.JavaFXUtil.resultLabel;
import static com.project.employees.util.javafx.JavaFXUtil.updateTableView;

public class CsvUtil {

    public static void loadCsvFile(Stage stage, String format) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            List<EmployeeProject> projects = parseCsvFile(file, format);
            Map<Integer, List<EmployeeProject>> projectEmployees = getProjectEmployees(projects);
            List<EmployeesPair> pairs = getPairs(projectEmployees);
            updateTableView(pairs);
            EmployeesPair longestWorkingPair = getLongestWorkingPair(pairs);

            resultLabel.setText(longestWorkingPair.toString());
        }
    }

    private static List<EmployeeProject> parseCsvFile(File file, String format) {
        List<EmployeeProject> projects = new ArrayList<>();
        EmployeeProject project;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] rowCells = line.split(";");

                if (rowCells.length == 4) {
                    int employeeId = Integer.parseInt(rowCells[0]);
                    int projectId = Integer.parseInt(rowCells[1]);
                    LocalDate startDate = parseDate(rowCells[2].trim(), format);
                    LocalDate endDate = rowCells[3].trim().equalsIgnoreCase("NULL") ? LocalDate.now() : parseDate(rowCells[3].trim(), format);

                    project = new EmployeeProject(employeeId, projectId, startDate, endDate);
                    projects.add(project);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return projects;
    }

    public static ChoiceBox<String> initFormatsBox() {
        ChoiceBox<String> formats = new ChoiceBox<>();
        formats.getItems().addAll(CUSTOM_DATE_FORMATS);
        formats.setValue(CUSTOM_DATE_FORMATS[0]);
        return formats;
    }

    public static String getFormat(ChoiceBox<String> formats) {
        return formats.getValue();
    }
}
