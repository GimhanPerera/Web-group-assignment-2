package com.example.webproject4.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalculationDTO { //use to send gross salary from Controller to service
    private Double grossSalary;
}
