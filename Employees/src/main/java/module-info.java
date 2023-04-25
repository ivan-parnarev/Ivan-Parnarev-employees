module com.project.Employees {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.employees to javafx.fxml;
    exports com.project.employees;
    exports com.project.employees.model;
    opens com.project.employees.model to javafx.fxml;
}