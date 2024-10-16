package org.project.onboarding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHiringPhase is a Querydsl query type for HiringPhase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHiringPhase extends EntityPathBase<HiringPhase> {

    private static final long serialVersionUID = 1508984499L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHiringPhase hiringPhase = new QHiringPhase("hiringPhase");

    public final QApplication application;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final NumberPath<Integer> finalScore = createNumber("finalScore", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final EnumPath<org.project.onboarding.enums.PhaseNameEnum> name = createEnum("name", org.project.onboarding.enums.PhaseNameEnum.class);

    public final EnumPath<org.project.onboarding.enums.PhaseStatusEnum> status = createEnum("status", org.project.onboarding.enums.PhaseStatusEnum.class);

    public final DatePath<java.time.LocalDate> updatedAt = createDate("updatedAt", java.time.LocalDate.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public QHiringPhase(String variable) {
        this(HiringPhase.class, forVariable(variable), INITS);
    }

    public QHiringPhase(Path<? extends HiringPhase> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHiringPhase(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHiringPhase(PathMetadata metadata, PathInits inits) {
        this(HiringPhase.class, metadata, inits);
    }

    public QHiringPhase(Class<? extends HiringPhase> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.application = inits.isInitialized("application") ? new QApplication(forProperty("application"), inits.get("application")) : null;
    }

}

