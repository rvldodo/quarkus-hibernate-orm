package org.dodo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "courses")
public class Course extends PanacheEntityBase {
    @Id
    @Schema(readOnly = true)
    @GeneratedValue
    public UUID uuid;
    @Schema(example = "Javascript from Zero to Hero")
    @Column(name = "course_name")
    public String courseName;
    @Schema(example = "Jonas Schmedtmann")
    @Column(name = "instructor")
    public String instructor;
}