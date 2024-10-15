package org.project.onboarding.predicates.Application;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.predicates.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ApplicationPredicateBuilder {
    private List<SearchCriteria> params;

    public ApplicationPredicateBuilder() {
        params = new ArrayList<>();
    }

    public org.project.onboarding.predicates.Application.ApplicationPredicateBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.isEmpty()) {
            return null;
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {
            ApplicationPredicate predicate = new ApplicationPredicate(param);
            try {
                return predicate.getPredicate();
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        }).filter(Objects::nonNull).toList();

        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }
        return result;
    }
}


