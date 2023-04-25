package com.project.employees.model;

import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public record EmployeeProject(int employeeId, int projectId, LocalDate dateFrom, LocalDate dateTo) {

    public static Map<Integer, List<EmployeeProject>> getProjectEmployees(List<EmployeeProject> projects) {
        Map<Integer, List<EmployeeProject>> projectEmployees = new HashMap<>();
        for (EmployeeProject project : projects) {
            int key = project.projectId();
            projectEmployees.computeIfAbsent(key, k -> new ArrayList<>()).add(project);
        }
        return projectEmployees;
    }

    public static List<EmployeesPair> getPairs(Map<Integer, List<EmployeeProject>> projectEmployeesMap) {
        List<EmployeesPair> pairs = new ArrayList<>();
        EmployeeProject currentEmployee;
        EmployeeProject nextEmployee;
        EmployeesPair result;
        for (List<EmployeeProject> projectEmployees : projectEmployeesMap.values()) {
            for (int currentEmployeeIndex = 0; currentEmployeeIndex < projectEmployees.size(); currentEmployeeIndex++) {
                for (int nextEmployeeIndex = currentEmployeeIndex + 1; nextEmployeeIndex < projectEmployees.size(); nextEmployeeIndex++) {
                    currentEmployee = projectEmployees.get(currentEmployeeIndex);
                    nextEmployee = projectEmployees.get(nextEmployeeIndex);
                    if (currentEmployee.employeeId() != nextEmployee.employeeId()) {
                        result = new EmployeesPair(new SimpleIntegerProperty(currentEmployee.employeeId()),
                                new SimpleIntegerProperty(nextEmployee.employeeId()),
                                new SimpleIntegerProperty(currentEmployee.projectId()),
                                new SimpleIntegerProperty(getDaysWorked(currentEmployee, nextEmployee))
                        );
                        if (result.daysWorked.get() > 0) {
                            pairs.add(result);
                        }
                    }
                }
            }
        }
        return pairs;
    }

    private static int getDaysWorked(EmployeeProject currentEmployee, EmployeeProject nextEmployee) {
        LocalDate startDate = currentEmployee.dateFrom().isAfter(nextEmployee.dateFrom()) ? currentEmployee.dateFrom() : nextEmployee.dateFrom();
        LocalDate endDate = currentEmployee.dateTo().isBefore(nextEmployee.dateTo()) ? currentEmployee.dateTo() : nextEmployee.dateTo();
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
}
