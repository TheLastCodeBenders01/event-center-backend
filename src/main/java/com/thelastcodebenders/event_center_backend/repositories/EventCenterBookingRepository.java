package com.thelastcodebenders.event_center_backend.repositories;

import com.thelastcodebenders.event_center_backend.models.EventCenterBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCenterBookingRepository extends JpaRepository<EventCenterBooking, Integer> {
}
