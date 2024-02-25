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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class CalculationService {

    //Get the calculated value
    public CalculatedDataDTO getcalculation(CalculationDTO calculationDTO){
        Double salary = calculationDTO.getGrossSalary();
        CalculatedDataDTO calculatedDataDTO = new CalculatedDataDTO();
        double tax = 0;

        //Tax logic
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
        tax = TwoDecimalpoints(tax);
        //convert the calculated values for 2 decimal point values
        double grossSalary = TwoDecimalpoints(salary);
        double netSalary = TwoDecimalpoints((salary*0.92 - tax));
        double employeeEpf = TwoDecimalpoints(salary*0.08);
        double employerEpf = TwoDecimalpoints(salary*0.12);
        double employerEtf = TwoDecimalpoints(salary*0.03);
        double totalEpfEtf = TwoDecimalpoints(salary*0.23);

        //Save to the database
        DbActivities saveDB =new DbActivities();
        saveDB.SaveCalculatedData(grossSalary, tax, employeeEpf, employerEpf, employerEtf, netSalary, totalEpfEtf);

        //Prepare the response
        calculatedDataDTO.setTax(tax);
        calculatedDataDTO.setGrossSalary(grossSalary);
        calculatedDataDTO.setNetSalary(netSalary);
        calculatedDataDTO.setEmployeeEpf(employeeEpf);
        calculatedDataDTO.setEmployerEpf(employerEpf);
        calculatedDataDTO.setEmployerEtf(employerEtf);
        calculatedDataDTO.setTotalEpfEtf(totalEpfEtf);
        return calculatedDataDTO; //Prepare the response
    }

    //convert the calculated values for 2 decimal point values
    double TwoDecimalpoints(double value){
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedEtf = df.format(value);
        double roundedVal = Double.parseDouble(formattedEtf);
        return roundedVal;
    }
}
