package com.thelastcodebenders.event_center_backend.repositories;

import com.thelastcodebenders.event_center_backend.models.User;
import com.thelastcodebenders.event_center_backend.models.VendorEventCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VendorEventCenterRepository extends JpaRepository<VendorEventCenter, UUID>, JpaSpecificationExecutor<VendorEventCenter> {
    List<VendorEventCenter> findAllByOwner(User owner);
}
