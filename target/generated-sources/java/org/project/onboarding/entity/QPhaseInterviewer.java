package org.project.onboarding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhaseInterviewer is a Querydsl query type for PhaseInterviewer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhaseInterviewer extends EntityPathBase<PhaseInterviewer> {

    private static final long serialVersionUID = 1188182442L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhaseInterviewer phaseInterviewer = new QPhaseInterviewer("phaseInterviewer");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final QHiringPhase hiringPhase;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QEmployee interviewer;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath notes = createString("notes");

    public final NumberPath<Float> score = createNumber("score", Float.class);

    public final DatePath<java.time.LocalDate> updatedAt = createDate("updatedAt", java.time.LocalDate.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public QPhaseInterviewer(String variable) {
        this(PhaseInterviewer.class, forVariable(variable), INITS);
    }

    public QPhaseInterviewer(Path<? extends PhaseInterviewer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhaseInterviewer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhaseInterviewer(PathMetadata metadata, PathInits inits) {
        this(PhaseInterviewer.class, metadata, inits);
    }

    public QPhaseInterviewer(Class<? extends PhaseInterviewer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hiringPhase = inits.isInitialized("hiringPhase") ? new QHiringPhase(forProperty("hiringPhase"), inits.get("hiringPhase")) : null;
        this.interviewer = inits.isInitialized("interviewer") ? new QEmployee(forProperty("interviewer"), inits.get("interviewer")) : null;
    }

}

