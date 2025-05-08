package com.thelastcodebenders.event_center_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@ToString(exclude = {"eventCenter", "bookedBy"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_center_bookings")
public class EventCenterBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Instant bookingDate;
    private String message;

    @ManyToOne
    @JoinColumn(name = "booked_by")
    @JsonIgnore
    private User bookedBy;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "vendor_event_center_id", nullable = false)
    @JsonIgnore
    private VendorEventCenter eventCenter;
}
