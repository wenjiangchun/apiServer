package com.dlyy.api.park.rest;

import com.dlyy.api.park.entity.ParkSystem;
import com.dlyy.api.park.service.ParkSystemService;
import com.dlyy.api.park.strategy.ParkContext;
import com.dlyy.api.park.strategy.ParkStrategy;
import com.dlyy.api.park.strategy.dto.LotReservation;
import com.dlyy.api.park.strategy.dto.Park;
import com.dlyy.api.park.util.ParkParams;
import com.dlyy.api.web.vo.RestCode;
import com.dlyy.api.web.vo.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rest/")
public class ParkRestController {

    @Autowired
    private ParkSystemService parkSystemService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取停车场信息列表
     * @param parkId 停车场ID
     * @return
     */
    @GetMapping("getParkList/{parkId}")
    public RestResult getParkList(@PathVariable String parkId) {
        RestResult result = new RestResult();
        ParkSystem parkSystem = parkSystemService.getParkSystem(parkId);
        if (Objects.nonNull(parkSystem)) {
            ParkStrategy parkStrategy = ParkContext.getParkStrategy(parkSystem, restTemplate);
            List<Park> parkList= parkStrategy.getAllParkList();
            result.setCode(RestCode.SUCCESS.getCodeValue());
            result.setData(parkList);
        } else {
            result.setCode(RestCode.FAILURE_PARK_NOT_EXIST.getCodeValue());
            result.setMsg("停车场不存在，请检查停车场编号");
        }
        return result;
    }

    /**
     * 获取停车场信息
     * @param parkId 停车场ID
     * @return
     */
    @GetMapping("getPark/{parkId}")
    public RestResult reserveLotNo(@PathVariable String parkId) {
        RestResult result = new RestResult();
        try {
            ParkSystem parkSystem = parkSystemService.getParkSystem(parkId);
            if (Objects.nonNull(parkSystem)) {
                ParkStrategy parkStrategy = ParkContext.getParkStrategy(parkSystem, restTemplate);
                Park park = parkStrategy.getPark();
                result.setCode(RestCode.SUCCESS.getCodeValue());
                result.setData(park);
            } else {
                result.setCode(RestCode.FAILURE_PARK_NOT_EXIST.getCodeValue());
                result.setMsg(RestCode.FAILURE_PARK_NOT_EXIST.getDesp());
            }
        } catch (Exception e) {
            result.setCode(RestCode.FAILURE_OTHER.getCodeValue());
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 车位预约
     * @param parkParams 预约参数
     * @return
     */
    @PostMapping("reserveLotNo")
    public RestResult reserveLotNo(@RequestBody ParkParams parkParams) {
        RestResult result = new RestResult();
        try {
            ParkSystem parkSystem = parkSystemService.getParkSystem(parkParams.getParkId());
            if (Objects.nonNull(parkSystem)) {
                ParkStrategy parkStrategy = ParkContext.getParkStrategy(parkSystem, restTemplate);
                LotReservation lotReservation = parkStrategy.reserveLotNo(parkParams.getLotNo(), parkParams.getPlateNo(), parkParams.getStartTime(), parkParams.getEndTime(), parkParams.getMobile(), parkParams.getOrderFrom());
                result.setCode(RestCode.SUCCESS.getCodeValue());
                result.setData(lotReservation);
            } else {
                result.setCode(RestCode.FAILURE_PARK_NOT_EXIST.getCodeValue());
                result.setMsg(RestCode.FAILURE_PARK_NOT_EXIST.getDesp());
            }
        } catch (Exception e) {
            result.setCode(RestCode.FAILURE_OTHER.getCodeValue());
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
