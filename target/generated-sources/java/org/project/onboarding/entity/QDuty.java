package org.project.onboarding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDuty is a Querydsl query type for Duty
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDuty extends EntityPathBase<Duty> {

    private static final long serialVersionUID = -210970227L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDuty duty = new QDuty("duty");

    public final QEmployee approvedByEmployee;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final DatePath<java.time.LocalDate> dutyDateFrom = createDate("dutyDateFrom", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> dutyDateTo = createDate("dutyDateTo", java.time.LocalDate.class);

    public final QEmployee employee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final DatePath<java.time.LocalDate> recordDate = createDate("recordDate", java.time.LocalDate.class);

    public final EnumPath<org.project.onboarding.enums.DutyStatusEnum> status = createEnum("status", org.project.onboarding.enums.DutyStatusEnum.class);

    public final EnumPath<org.project.onboarding.enums.DutyTypeEnum> type = createEnum("type", org.project.onboarding.enums.DutyTypeEnum.class);

    public final DatePath<java.time.LocalDate> updatedAt = createDate("updatedAt", java.time.LocalDate.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public final NumberPath<Float> workingHoursPerDay = createNumber("workingHoursPerDay", Float.class);

    public QDuty(String variable) {
        this(Duty.class, forVariable(variable), INITS);
    }

    public QDuty(Path<? extends Duty> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDuty(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDuty(PathMetadata metadata, PathInits inits) {
        this(Duty.class, metadata, inits);
    }

    public QDuty(Class<? extends Duty> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approvedByEmployee = inits.isInitialized("approvedByEmployee") ? new QEmployee(forProperty("approvedByEmployee"), inits.get("approvedByEmployee")) : null;
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
    }

}

