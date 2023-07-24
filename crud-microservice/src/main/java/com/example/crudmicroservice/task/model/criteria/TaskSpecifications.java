package com.example.crudmicroservice.task.model.criteria;

import com.example.crudmicroservice.task.model.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> searchByCriteria(TaskSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (criteria.getTitle() !=  null && !criteria.getTitle().isBlank()){
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                                "%" + criteria.getTitle().toLowerCase().trim() + "%"));
            }
            if(criteria.getLevel() != null){
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("level"),criteria.getLevel()));
            }
            return predicate;
        };
    }
}
