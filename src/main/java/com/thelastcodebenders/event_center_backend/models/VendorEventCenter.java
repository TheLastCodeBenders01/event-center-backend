package com.thelastcodebenders.event_center_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thelastcodebenders.event_center_backend.models.dto.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
import java.util.UUID;

@ToString(exclude = {"owner"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor_event_center")
public class VendorEventCenter {

    @Id
    @Builder.Default private UUID id = UUID.randomUUID();

    private String name;
    private String description;
    private long amount;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "event_center_medias", joinColumns = @JoinColumn(name = "vendor_event_center_id"))
    @Column(name = "media")
    private List<String> medias;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "event_center_bookings", joinColumns = @JoinColumn(name = "vendor_event_center_id"))
//    @Column(name = "booking")
    @OneToMany(mappedBy = "eventCenter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EventCenterBooking> bookings;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;

    @Transient
    private UUID ownerId;

    @Transient
    private String phoneNumber;

    public VendorEventCenter toDto() {
        this.ownerId = owner.getUserId();
        this.phoneNumber = owner.getPhoneNumber();
        return this;
    }
}
