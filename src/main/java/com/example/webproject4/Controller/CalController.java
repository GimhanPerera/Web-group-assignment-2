package com.example.webproject4.Controller;
import com.example.webproject4.Dto.CalculatedDataDTO;
import com.example.webproject4.Dto.CalculationDTO;
import com.example.webproject4.Dto.HistoryDTO;
import com.example.webproject4.Service.CalculationService;
import com.example.webproject4.Service.GetHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.TimeUnit;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin
public class CalController {
    @Autowired
    private CalculationService calculationService;
    @Autowired
    private GetHistoryService getHistoryService;

    @PostMapping("/calculate")
    public ResponseEntity<CalculatedDataDTO> postData(@RequestBody CalculationDTO calculationDTO) {
        CalculatedDataDTO result = calculationService.getcalculation(calculationDTO);

        // Set cache headers
        CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic();
        return ResponseEntity.ok().cacheControl(cacheControl).body(result);
    }

    @GetMapping("/getHistory")
    public ResponseEntity<List<HistoryDTO>> getHistory() {
        List<HistoryDTO> history = getHistoryService.getHistory();

        // Set cache headers
        CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic();
        return ResponseEntity.ok().cacheControl(cacheControl).body(history);
    }
}
