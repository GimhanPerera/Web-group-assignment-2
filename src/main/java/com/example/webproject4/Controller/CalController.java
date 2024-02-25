package com.example.webproject4.Controller;
import com.example.webproject4.Dto.CalculatedDataDTO;
import com.example.webproject4.Dto.CalculationDTO;
import com.example.webproject4.Dto.HistoryDTO;
import com.example.webproject4.Service.CalculationService;
import com.example.webproject4.Service.GetHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.TimeUnit;
import java.util.List;

import static com.microsoft.sqlserver.jdbc.StringUtils.isNumeric;

@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin
public class CalController {
    @Autowired
    private CalculationService calculationService;
    @Autowired
    private GetHistoryService getHistoryService;

    //get gross salary and return the calculated response
    @PostMapping("/calculate")
    public ResponseEntity<?> postData(@RequestBody CalculationDTO calculationDTO){
        // Server-side validation for the salary input
        if (calculationDTO.getGrossSalary() <= 0 || calculationDTO.getGrossSalary() >= 100000000) {
            // Invalid input, return a response with an error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid salary input. Salary should be greater than 0 and less than 100000000");
        }
        CalculatedDataDTO result = calculationService.getcalculation(calculationDTO);

        // Set cache headers
        CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic();
        return ResponseEntity.ok().cacheControl(cacheControl).body(result);//return the response
    }


    //Return the history
    @GetMapping("/getHistory")
    public ResponseEntity<List<HistoryDTO>> getHistory() {
        List<HistoryDTO> history = getHistoryService.getHistory();//list of HistoryDTO objects

        return ResponseEntity.ok().body(history);
    }

}