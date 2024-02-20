package com.example.webproject4.Controller;
import com.example.webproject4.Dto.CalculatedDataDTO;
import com.example.webproject4.Dto.CalculationDTO;
import com.example.webproject4.Dto.HistoryDTO;
import com.example.webproject4.Service.CalculationService;
import com.example.webproject4.Service.GetHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin
public class CalController {
    @Autowired
    private CalculationService calculationService;
    @Autowired
    private GetHistoryService getHistoryService;

    @GetMapping("/a")
    public String index() {
        return "index"; // This will return index.html from src/main/resources/static
    }

    @PostMapping("/calculate")
    public CalculatedDataDTO postData(@RequestBody CalculationDTO calculationDTO){
        return calculationService.getcalculation(calculationDTO);
    }
    @GetMapping("/getHistory")
    public List<HistoryDTO> getHistory(){
        return getHistoryService.getHistory();
    }

}
