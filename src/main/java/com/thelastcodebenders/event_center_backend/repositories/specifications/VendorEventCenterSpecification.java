package com.thelastcodebenders.event_center_backend.repositories.specifications;

import com.thelastcodebenders.event_center_backend.models.VendorEventCenter;
import com.thelastcodebenders.event_center_backend.models.dto.Address;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class VendorEventCenterSpecification {

    public static Specification<VendorEventCenter> search(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return null;
            }

            Join<VendorEventCenter, Address> addressJoin = root.join("address");

            String pattern = "%" + keyword.toLowerCase() + "%";

            Predicate namePredicate = cb.like(cb.lower(root.get("name")), pattern);
            Predicate descriptionPredicate = cb.like(cb.lower(root.get("description")), pattern);

            Predicate streetPredicate = cb.like(cb.lower(addressJoin.get("streetAddress")), pattern);
            Predicate statePredicate = cb.like(cb.lower(addressJoin.get("state")), pattern);
            Predicate countryPredicate = cb.like(cb.lower(addressJoin.get("country")), pattern);

            return cb.or(namePredicate, descriptionPredicate, streetPredicate, statePredicate, countryPredicate);
        };
    }
}
