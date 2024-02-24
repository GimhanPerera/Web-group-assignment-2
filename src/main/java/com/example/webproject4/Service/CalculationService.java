package com.example.webproject4.Service;


import com.example.webproject4.Dto.CalculationDTO;
import com.example.webproject4.Dto.CalculatedDataDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class CalculationService {
    public CalculatedDataDTO getcalculation(CalculationDTO calculationDTO){
        DbConnectionService d = new DbConnectionService();
        d.connect();
        Double salary = calculationDTO.getGrossSalary();
        return calculate(salary);
    }
    private CalculatedDataDTO calculate(Double salary){
        CalculatedDataDTO calculatedDataDTO = new CalculatedDataDTO();
        double tax = 0;
        if (salary <= 100000) {
            tax =  0;
        } else if (salary <= 141667) {
            tax = (salary - 100000) * 0.06;
        } else if (salary <= 183333) {
            tax = 2500.02 + (salary - 141667) * 0.12;
        } else if (salary <= 225000) {
            tax = 2500.02 + 4999.92 + (salary - 183333) * 0.18;
        } else if (salary <= 266667) {
            tax = 2500.02 + 4999.92 + 7500.06 + (salary - 225000) * 0.24;
        } else if (salary <= 308333) {
            tax = 2500.02 + 4999.92 + 7500.06 + 10000.08 + (salary - 266667) * 0.30;
        } else {
            tax = 2500.02 + 4999.92 + 7500.06 + 10000.08 + 12499.8 + (salary - 308333) * 0.36;
        }
        double grossSalary = salary;
        double netSalary = (salary*0.92 - tax);
        double employeeEpf = salary*0.08;
        double employerEpf = salary*0.12;
        double employerEtf = salary*0.03;
        double totalEpfEtf = salary*0.23;

        DbActivities saveDB =new DbActivities();
        saveDB.SaveCalculatedData(grossSalary, tax, employeeEpf, employerEpf, employerEtf, netSalary, totalEpfEtf);
        calculatedDataDTO.setTax(tax);
        calculatedDataDTO.setGrossSalary(grossSalary);
        calculatedDataDTO.setNetSalary(netSalary);
        calculatedDataDTO.setEmployeeEpf(employeeEpf);
        calculatedDataDTO.setEmployerEpf(employerEpf);
        calculatedDataDTO.setEmployerEtf(employerEtf);
        calculatedDataDTO.setTotalEpfEtf(totalEpfEtf);
        return calculatedDataDTO;
    }


}
