package com.project.employees.model;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeesPair {
    SimpleIntegerProperty employeeId1;
    SimpleIntegerProperty employeeId2;
    SimpleIntegerProperty projectId;
    SimpleIntegerProperty daysWorked;

    public EmployeesPair(SimpleIntegerProperty employeeId1,
                         SimpleIntegerProperty employeeId2,
                         SimpleIntegerProperty projectId,
                         SimpleIntegerProperty daysWorked) {
        this.employeeId1 = employeeId1;
        this.employeeId2 = employeeId2;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public static EmployeesPair getLongestWorkingPair(List<EmployeesPair> pairs) {
        return Collections.max(pairs, Comparator.comparing(pair -> pair.daysWorked.get()));
    }

    public SimpleIntegerProperty employeeId1Property() {
        return employeeId1;
    }

    public SimpleIntegerProperty employeeId2Property() {
        return employeeId2;
    }

    public SimpleIntegerProperty projectIdProperty() {
        return projectId;
    }

    public SimpleIntegerProperty daysWorkedProperty() {
        return daysWorked;
    }

    @Override
    public String toString() {
        return "Longest working pair: \n(" +
                " id: " + employeeId1.get() +
                " <-> id:  " + employeeId2.get() +
                " ) \n worked on project with id: " + projectId.get() +
                "\n for " + daysWorked.get() +
                " days";
    }
}
