# Ivan-Parnarev-employees
JavaFX project on topic: "Pair of employees who have worked together"

## Task

>Create an application that identifies the pair of employees who have worked together on common projects for the longest period of time.

Input data:
A CSV file with data in the following format:
```
EmpID, ProjectID, DateFrom, DateTo
```
Sample data:
```
143;12;2022-01-10;NULL
218;10;2022-05-15;NULL
143;10;2022-01-10;2022-05-14
100;12;2021-10-17;2022-01-12
```

Sample output:
```
Longest working pair: ( id: 143  <-> id: 100 ) 
worked on project with id: 12
for 2 days
```

## Requirements

- DateTo can be NULL, equivalent to today
- The input data must be loaded to the program from a CSV file
- Create an UI:
The user picks up a file from the file system and, after selecting it, all common
projects of the pair are displayed in datagrid with the following columns:
Employee ID #1, Employee ID #2, Project ID, Days worked
- Support multiple date formats

## Getting Started

### Prerequisites
- IDE: IntelliJ IDEA
- Java: JDK 17
- Build tool: Maven 3.10.1

### Dependencies
- OpenCSV 5.7.1
- JavaFX 17.0.2

## Author
- [LinkedIn](https://www.linkedin.com/in/ivan-parnarev/)
