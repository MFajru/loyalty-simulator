package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.EarningRequest;
import com.loyalty.loyalty_simulator.dto.ResponseWithoutData;
import com.loyalty.loyalty_simulator.interfaces.ICalculatePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calculate-point")
public class CalculatePointController {
    private final ICalculatePoint calculatePoint;
    @Autowired
    public CalculatePointController(ICalculatePoint calculatePoint) {
        this.calculatePoint = calculatePoint;
    }

    @PostMapping("earning")
    public ResponseEntity<ResponseWithoutData> earning(@RequestBody EarningRequest earningRequest) {
        boolean isSuccess = calculatePoint.earning(earningRequest);
        ResponseWithoutData res = new ResponseWithoutData();
        if (!isSuccess) {
            res.setMessage("Point not added!");
            return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.BAD_REQUEST);
        }
        res.setMessage("Point added!");
        return new ResponseEntity<ResponseWithoutData>(res, HttpStatus.OK);
    }
}
