package org.project.onboarding.predicates.Team;

import com.querydsl.core.types.dsl.*;
import org.project.onboarding.dto.team.ListTeamDTO;
import org.project.onboarding.entity.Team;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.predicates.SearchCriteria;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.List;

import static org.project.onboarding.helperFunctions.PredicatesHelper.getAllFieldsIncludingInherited;

public class TeamPredicate {

    private SearchCriteria criteria;

    public TeamPredicate(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate() throws BusinessException {
        PathBuilder<Team> entityPath = new PathBuilder<>(Team.class, "team");

        String key = criteria.getKey();
        String operation = criteria.getOperation();
        Object value = criteria.getValue();
        if (!key.equalsIgnoreCase("isDeleted") && !isValidKey(key)) {
            throw new BusinessException(String.format("Key %s does not exist in Team table.", key), HttpStatus.BAD_REQUEST);
        }

        if (key.equalsIgnoreCase("id")) {
            NumberPath<Integer> path = entityPath.getNumber(key, Integer.class);
            String strValue = value.toString();
            if (operation.equalsIgnoreCase("=")) {
                return path.stringValue().contains(strValue);
            }
            throw new BusinessException(String.format("Operator %s is not recognized.", operation), HttpStatus.BAD_REQUEST);
        } else if (isBoolean(value.toString())) {
            BooleanPath path = entityPath.getBoolean(key);
            boolean boolValue = Boolean.parseBoolean(value.toString());
            if (operation.equals("=")) {
                return path.eq(boolValue);
            }
            throw new BusinessException(String.format("Operator %s is not recognized.", operation), HttpStatus.BAD_REQUEST);
        } else {
            StringPath path = entityPath.getString(key);
            if (operation.equalsIgnoreCase("=")) {
                return path.containsIgnoreCase(value.toString());
            }
        }
        throw new BusinessException(String.format("Criteria %s %s %s is not recognized.", key, operation, value), HttpStatus.BAD_REQUEST);

    }


    private boolean isBoolean(String str) {
        return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str);
    }

    private boolean isValidKey(String key) {
        List<Field> fields = getAllFieldsIncludingInherited(ListTeamDTO.class);
        return fields.stream().anyMatch(field -> field.getName().equalsIgnoreCase(key));
    }
}
