package com.dlyy.api.park.service;

import com.dlyy.api.common.service.AbstractBaseService;
import com.dlyy.api.park.dao.ParkSystemDao;
import com.dlyy.api.park.entity.ParkSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkSystemService extends AbstractBaseService<ParkSystem, Long> {

    private ParkSystemDao parkSystemDao;

    public ParkSystemService(ParkSystemDao parkSystemDao) {
        super(parkSystemDao);
        this.parkSystemDao = parkSystemDao;
    }

    @Transactional(readOnly = true)
    public ParkSystem getParkSystem(String parkId) {
        return this.parkSystemDao.findByParkId(parkId);
    }
}
