package org.project.onboarding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApplicant is a Querydsl query type for Applicant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplicant extends EntityPathBase<Applicant> {

    private static final long serialVersionUID = -464971093L;

    public static final QApplicant applicant = new QApplicant("applicant");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath mobileNumber = createString("mobileNumber");

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> updatedAt = createDate("updatedAt", java.time.LocalDate.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public QApplicant(String variable) {
        super(Applicant.class, forVariable(variable));
    }

    public QApplicant(Path<? extends Applicant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplicant(PathMetadata metadata) {
        super(Applicant.class, metadata);
    }

}

