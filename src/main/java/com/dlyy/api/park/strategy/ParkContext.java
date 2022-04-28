package com.dlyy.api.park.strategy;

import com.dlyy.api.park.entity.ParkSystem;
import org.springframework.web.client.RestTemplate;

public class ParkContext {
    public static ParkStrategy getParkStrategy(ParkSystem parkSystem, RestTemplate restTemplate) {
        switch (parkSystem.getParkBrand()) {
            case Fuji:
                return new FujiParkStrategy(parkSystem, restTemplate);
            default:
                throw new RuntimeException("未找到对应车场信息");
        }
    }
}
