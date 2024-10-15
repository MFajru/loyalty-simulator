package com.loyalty.loyalty_simulator.controllers;

import com.loyalty.loyalty_simulator.dto.PointHistoryResponse;
import com.loyalty.loyalty_simulator.dto.ResponseData;
import com.loyalty.loyalty_simulator.interfaces.IPointHistoryService;
import com.loyalty.loyalty_simulator.models.PointHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("point-history")
public class PointHistoryController {
    private final IPointHistoryService pointHistoryService;
    @Autowired
    public PointHistoryController(IPointHistoryService pointHistoryService) {
        this.pointHistoryService = pointHistoryService;
    }

    @GetMapping("{cif}")
    public ResponseEntity<ResponseData<List<PointHistoryResponse>>> getHistoryByCif(@PathVariable String cif) {
        List<PointHistory> pointHistories = pointHistoryService.getHistoryByCif(cif);
        PointHistoryResponse pHisRes = new PointHistoryResponse();
        ResponseData<List<PointHistoryResponse>> res = new ResponseData<>();
        res.setData(pHisRes.mappingPointHistories(pointHistories));
        res.setMessage("Successfully getting data.");
        return new ResponseEntity<ResponseData<List<PointHistoryResponse>>>(res, HttpStatus.OK);

    }
}
