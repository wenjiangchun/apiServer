package com.dlyy.api.park.entity;

import com.dlyy.api.common.jpa.entity.AbstractBaseEntity;
import com.dlyy.api.park.util.ParkBrand;

import javax.persistence.*;

@Entity
@Table(name = "api_park_system")
public class ParkSystem extends AbstractBaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String oldId;

    private String parkId;

    private String url;

    private ParkBrand parkBrand;

    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Enumerated(value = EnumType.STRING)
    public ParkBrand getParkBrand() {
        return parkBrand;
    }

    public void setParkBrand(ParkBrand parkBrand) {
        this.parkBrand = parkBrand;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(unique = true)
    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    @Override
    public String toString() {
        return "ParkSystem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", oldId='" + oldId + '\'' +
                ", parkId='" + parkId + '\'' +
                ", url='" + url + '\'' +
                ", parkBrand=" + parkBrand +
                '}';
    }
}
