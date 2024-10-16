package org.project.onboarding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployeeTraining is a Querydsl query type for EmployeeTraining
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployeeTraining extends EntityPathBase<EmployeeTraining> {

    private static final long serialVersionUID = -189462049L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployeeTraining employeeTraining = new QEmployeeTraining("employeeTraining");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final QEmployee employee;

    public final DatePath<java.time.LocalDate> enrollmentDate = createDate("enrollmentDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final QTraining training;

    public final DatePath<java.time.LocalDate> updatedAt = createDate("updatedAt", java.time.LocalDate.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public QEmployeeTraining(String variable) {
        this(EmployeeTraining.class, forVariable(variable), INITS);
    }

    public QEmployeeTraining(Path<? extends EmployeeTraining> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployeeTraining(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployeeTraining(PathMetadata metadata, PathInits inits) {
        this(EmployeeTraining.class, metadata, inits);
    }

    public QEmployeeTraining(Class<? extends EmployeeTraining> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.training = inits.isInitialized("training") ? new QTraining(forProperty("training"), inits.get("training")) : null;
    }

}

