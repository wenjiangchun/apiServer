package com.dlyy.api.park.strategy;

import com.dlyy.api.park.strategy.dto.LotReservation;
import com.dlyy.api.park.strategy.dto.Park;
import com.dlyy.api.web.vo.RestResult;

import java.util.Date;
import java.util.List;

public interface ParkStrategy {

    /**
     * 获取停车场品牌
     * @return 停车场品牌
     */
    String getParkBrand();

    /**
     * 获取可用停车位信息
     * @param departmentNo 所属企业编号
     * @return 可用停车位信息
     */
    List<RestResult> getAvailableList(String departmentNo);

    List<Park> getAllParkList();

    Park getPark();

    /**
     * 预约停车位
     * @param lotNo 停车位编号
     * @param mobile 手机号
     * @param plateNo 车牌号
     * @param startTime 预约开始时间
     * @param endTime 预约结束时间
     * @return 预约是否成功对象
     */
    LotReservation reserveLotNo(String lotNo, String plateNo, Date startTime, Date endTime, String mobile, String orderFrom);
}
