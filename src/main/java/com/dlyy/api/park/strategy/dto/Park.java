package com.dlyy.api.park.strategy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * 停车场
 */
public class Park {

    //停车场ID，该ID为第三方业务系统调用使用
    private String parkId;

    //原系统对应停车场ID
    private String oldId;

    private String name;

    //总停车位
    private int lotCount;

    //可用停车位
    private int freeCount;

    //备注
    private String remark;

    /**
     * 查询参数，该部分不会返回给调用方，只是用来查询后续接口方使用
     */
    private Map<String, Object> queryParams = new HashMap<>();

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    @JsonIgnore
    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLotCount() {
        return lotCount;
    }

    public void setLotCount(int lotCount) {
        this.lotCount = lotCount;
    }

    public int getFreeCount() {
        return freeCount;
    }

    public void setFreeCount(int freeCount) {
        this.freeCount = freeCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonIgnore
    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }
}
