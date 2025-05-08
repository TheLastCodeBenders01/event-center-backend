package com.thelastcodebenders.event_center_backend.repositories.specifications;

import com.thelastcodebenders.event_center_backend.models.VendorEventCenter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class VendorEventCenterSpecification {

    public static Specification<VendorEventCenter> search(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return null;
            }

            String pattern = "%" + keyword.toLowerCase() + "%";

            Predicate namePredicate = cb.like(cb.lower(root.get("name")), pattern);
//            Predicate locationPredicate = cb.like(cb.lower(root.get("location")), pattern);
            Predicate descriptionPredicate = cb.like(cb.lower(root.get("description")), pattern);

            return cb.or(namePredicate, descriptionPredicate);
        };
    }
}
