package com.example.webproject4.Service;


import com.example.webproject4.Dto.HistoryDTO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetHistoryService {

    public List<HistoryDTO> getHistory(){
        DbConnectionService con = new DbConnectionService();
        Connection connection = con.connect();
        List<HistoryDTO> historyRecords = new ArrayList<>();
// Create SQL query to insert a record into the History table
        // Prepare SELECT statement
        try {
        String selectQuery = "SELECT * FROM History";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);


        // Execute the SELECT statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Process the result set
        while (resultSet.next()) {
            HistoryDTO record = new HistoryDTO(
                    resultSet.getTimestamp("RecordedDateTime"),
                    resultSet.getDouble("grossSalary"),
                    resultSet.getDouble("netSalary"),
                    resultSet.getDouble("employeeEpf"),
                    resultSet.getDouble("employerEpf"),
                    resultSet.getDouble("employerEtf"),
                    resultSet.getDouble("totalEpfEtf"),
                    resultSet.getDouble("tax")
            );
            historyRecords.add(record);
        }

        // Close the ResultSet and PreparedStatement
        resultSet.close();
        preparedStatement.close();
        connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historyRecords;
        //return "Hi";
    }
}
