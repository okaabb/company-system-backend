package org.project.onboarding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplication is a Querydsl query type for Application
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplication extends EntityPathBase<Application> {

    private static final long serialVersionUID = -160449863L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplication application = new QApplication("application");

    public final QApplicant applicant;

    public final EnumPath<org.project.onboarding.enums.ApplicationStatusEnum> applicationStatus = createEnum("applicationStatus", org.project.onboarding.enums.ApplicationStatusEnum.class);

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final QDepartment department;

    public final QEmployee hr;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath linkedinProfileUrl = createString("linkedinProfileUrl");

    public final EnumPath<org.project.onboarding.enums.ApplyingPositionEnum> position = createEnum("position", org.project.onboarding.enums.ApplyingPositionEnum.class);

    public final QEmployee referralEmployee;

    public final EnumPath<org.project.onboarding.enums.ReferralTypeEnum> referralType = createEnum("referralType", org.project.onboarding.enums.ReferralTypeEnum.class);

    public final DatePath<java.time.LocalDate> updatedAt = createDate("updatedAt", java.time.LocalDate.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public QApplication(String variable) {
        this(Application.class, forVariable(variable), INITS);
    }

    public QApplication(Path<? extends Application> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplication(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplication(PathMetadata metadata, PathInits inits) {
        this(Application.class, metadata, inits);
    }

    public QApplication(Class<? extends Application> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.applicant = inits.isInitialized("applicant") ? new QApplicant(forProperty("applicant")) : null;
        this.department = inits.isInitialized("department") ? new QDepartment(forProperty("department"), inits.get("department")) : null;
        this.hr = inits.isInitialized("hr") ? new QEmployee(forProperty("hr"), inits.get("hr")) : null;
        this.referralEmployee = inits.isInitialized("referralEmployee") ? new QEmployee(forProperty("referralEmployee"), inits.get("referralEmployee")) : null;
    }

}

