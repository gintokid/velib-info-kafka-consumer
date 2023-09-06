package com.madroid.velibdataconsume.jpa.repository;

import com.madroid.velibdataconsume.jpa.model.StationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StationStateRepository extends JpaRepository<StationState, Long>, JpaSpecificationExecutor<StationState> {
}
