package org.project.onboarding.predicates.Application;

import com.querydsl.core.types.dsl.*;
import lombok.Data;
import org.project.onboarding.dto.application.ListApplicationDTO;
import org.project.onboarding.entity.Application;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.predicates.SearchCriteria;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.List;

import static org.project.onboarding.helperFunctions.PredicatesHelper.getAllFieldsIncludingInherited;

@Data
public class ApplicationPredicate {
    private SearchCriteria criteria;

    public ApplicationPredicate(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate() throws BusinessException {
        PathBuilder<Application> entityPath = new PathBuilder<>(Application.class, "application");

        String key = criteria.getKey();
        String operation = criteria.getOperation();
        Object value = criteria.getValue();

        if (!key.equalsIgnoreCase("isDeleted") && !isValidKey(key)) {
            throw new BusinessException(String.format("Key %s does not exist in Application table.", key), HttpStatus.BAD_REQUEST);
        }

        if (isNumeric(value.toString()) && (key.equalsIgnoreCase("id") || key.equalsIgnoreCase("manager.id"))) {
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

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBoolean(String str) {
        return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str);
    }

    private boolean isValidKey(String key) {
        List<Field> fields = getAllFieldsIncludingInherited(ListApplicationDTO.class);
        return fields.stream().anyMatch(field -> field.getName().equalsIgnoreCase(key));
    }


}
