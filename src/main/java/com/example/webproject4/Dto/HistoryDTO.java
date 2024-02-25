package com.example.webproject4.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class HistoryDTO {
    private java.sql.Timestamp recordedDateTime;
    private double grossSalary;
    private double netSalary;
    private double employeeEpf;
    private double employerEpf;
    private double employerEtf;
    private double totalEpfEtf;
    private double tax;


    public HistoryDTO(java.sql.Timestamp recordedDateTime, double grossSalary, double netSalary, double employeeEpf,
                         double employerEpf, double employerEtf, double totalEpfEtf, double tax) { //use to send history from Service to Controller
        this.recordedDateTime = recordedDateTime;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.employeeEpf = employeeEpf;
        this.employerEpf = employerEpf;
        this.employerEtf = employerEtf;
        this.totalEpfEtf = totalEpfEtf;
        this.tax = tax;
    }

    // Getters and setters (not shown for brevity)

    @Override
    public String toString() {
        return "HistoryRecord{" +
                "recordedDateTime=" + recordedDateTime +
                ", grossSalary=" + grossSalary +
                ", netSalary=" + netSalary +
                ", employeeEpf=" + employeeEpf +
                ", employerEpf=" + employerEpf +
                ", employerEtf=" + employerEtf +
                ", totalEpfEtf=" + totalEpfEtf +
                ", tax=" + tax +
                '}';
    }


}

