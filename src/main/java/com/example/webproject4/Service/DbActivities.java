package com.example.webproject4.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DbActivities {
    public void SaveCalculatedData(Double grossSalary,Double tax,Double employeeEpf,Double employerEpf,Double employerEtf,Double netSalary, Double totalEpfEtf){
        DbConnectionService con = new DbConnectionService();
        Connection connection = con.connect();
// Create SQL query to insert a record into the History table
        String insertQuery = "INSERT INTO History (RecordedDateTime, grossSalary, netSalary, employeeEpf, employerEpf, employerEtf, totalEpfEtf , tax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Prepare the statement with parameters
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertQuery);


        // Set values for parameters
        preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis())); // RecordedDateTime
        preparedStatement.setDouble(2, grossSalary); // grossSalary
        preparedStatement.setDouble(3, netSalary); // netSalary
        preparedStatement.setDouble(4, employeeEpf); // employeeEpf
        preparedStatement.setDouble(5, employerEpf); // employerEpf
        preparedStatement.setDouble(6, employerEtf); // employerEtf
        preparedStatement.setDouble(7, totalEpfEtf); // totalEpfEtf
            preparedStatement.setDouble(8, tax); // tax

        // Execute the insert statement
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Record inserted successfully!");
        } else {
            System.out.println("Failed to insert record!");
        }
        connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
