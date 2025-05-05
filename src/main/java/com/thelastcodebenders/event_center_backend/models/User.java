package com.thelastcodebenders.event_center_backend.models;

import com.thelastcodebenders.event_center_backend.models.dto.UserResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Builder.Default private UUID userId = UUID.randomUUID();

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    @Builder.Default private boolean isVendor = false;

    @Builder.Default private boolean isPrinter = false;
    @Builder.Default private boolean accountNonExpired = true;
    @Builder.Default private boolean accountNonLocked = true;
    @Builder.Default private boolean enabled = true;
    @Builder.Default private boolean credentialsNonExpired = true;
    @Builder.Default private boolean emailVerified = false;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorEventCenter> eventCenters;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public UserResponse toDto() {
        return UserResponse.builder()
                .userId(userId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
