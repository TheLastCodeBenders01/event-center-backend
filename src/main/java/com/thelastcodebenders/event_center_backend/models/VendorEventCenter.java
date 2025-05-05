package com.thelastcodebenders.event_center_backend.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor_event_center")
public class VendorEventCenter {

    @Id
    private UUID id;

    private String eventName;

    @ElementCollection
    @CollectionTable(name = "event_center_medias", joinColumns = @JoinColumn(name = "vendor_event_center_id"))
    @Column(name = "media")
    private List<String> media;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
