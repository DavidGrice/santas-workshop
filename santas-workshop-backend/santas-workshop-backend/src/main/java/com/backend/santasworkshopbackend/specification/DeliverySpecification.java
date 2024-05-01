package com.backend.santasworkshopbackend.specification;

import javax.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import com.backend.santasworkshopbackend.entity.Delivery;

public class DeliverySpecification implements Specification<Delivery> {

    private SearchCriteria criteria;

    public DeliverySpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Delivery> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path<?> path = getPath(root, criteria.getKey());

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(path.as(Long.class), builder.literal(Long.parseLong(criteria.getValue().toString())));
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(path.as(Long.class), builder.literal(Long.parseLong(criteria.getValue().toString())));
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (path.getJavaType() == String.class) {
                return builder.like(path.as(String.class), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(path, criteria.getValue());
            }
        }
        return null;
    }

    private Path<?> getPath(Root<Delivery> root, String path) {
        String[] steps = path.split("\\.");
        Path<?> result = root.get(steps[0]);
        for (int i = 1; i < steps.length; i++) {
            result = result.get(steps[i]);
        }
        return result;
    }
    
}
