package com.example.webproject4.Service;

import java.sql.*;

public class DbActivities {
    public void SaveCalculatedData(Double grossSalary, Double tax, Double employeeEpf, Double employerEpf, Double employerEtf, Double netSalary, Double totalEpfEtf) {
        DbConnectionService con = new DbConnectionService();
        Connection connection = con.connect();
        String storedProcedureCall = "{CALL InsertHistoryRecord(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement statement = connection.prepareCall(storedProcedureCall)) {
            // Set values for parameters
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis())); // RecordedDateTime
            statement.setDouble(2, grossSalary); // GrossSalary
            statement.setDouble(3, netSalary); // NetSalary
            statement.setDouble(4, employeeEpf); // EmployeeEPF
            statement.setDouble(5, employerEpf); // EmployerEPF
            statement.setDouble(6, employerEtf); // EmployerETF
            statement.setDouble(7, totalEpfEtf); // TotalEPFETF
            statement.setDouble(8, tax); // Tax

            // Execute the stored procedure
            statement.execute();
            System.out.println("Record inserted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
