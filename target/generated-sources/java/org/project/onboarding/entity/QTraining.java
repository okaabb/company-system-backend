package org.project.onboarding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTraining is a Querydsl query type for Training
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTraining extends EntityPathBase<Training> {

    private static final long serialVersionUID = 358708241L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTraining training = new QTraining("training");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> createdDate = createDate("createdDate", java.time.LocalDate.class);

    public final QEmployee creator;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath name = createString("name");

    public final NumberPath<Float> trainingHours = createNumber("trainingHours", Float.class);

    public final DatePath<java.time.LocalDate> updatedAt = createDate("updatedAt", java.time.LocalDate.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public QTraining(String variable) {
        this(Training.class, forVariable(variable), INITS);
    }

    public QTraining(Path<? extends Training> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTraining(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTraining(PathMetadata metadata, PathInits inits) {
        this(Training.class, metadata, inits);
    }

    public QTraining(Class<? extends Training> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.creator = inits.isInitialized("creator") ? new QEmployee(forProperty("creator"), inits.get("creator")) : null;
    }

}

