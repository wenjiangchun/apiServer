package com.dlyy.api.park.dao;

import com.dlyy.api.common.jpa.repository.BaseRepository;
import com.dlyy.api.park.entity.ParkSystem;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkSystemDao extends BaseRepository<ParkSystem, Long> {

    ParkSystem findByParkId(String parkId);
}
