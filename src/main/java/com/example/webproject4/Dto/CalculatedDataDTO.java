package com.example.webproject4.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalculatedDataDTO {
    private double tax;
    private double grossSalary;
    private double netSalary;
    private double employeeEpf;
    private double employerEpf;
    private double employerEtf;
    private double totalEpfEtf;
}
