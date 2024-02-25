package com.example.webproject4.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalculatedDataDTO { //use to send calculated from Service to Controller
    private double tax;
    private double grossSalary;
    private double netSalary;
    private double employeeEpf;
    private double employerEpf;
    private double employerEtf;
    private double totalEpfEtf;
}
