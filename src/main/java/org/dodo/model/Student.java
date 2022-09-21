package org.dodo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student extends PanacheEntityBase {

    @Id
    @Column(insertable = false, name = "uuid")
    @Schema(readOnly = true)
    @GeneratedValue
    public UUID uuid;
    @Column(name = "first_name")
    @Schema(example = "Rivaldo")
    public String firstName;
    @Column(name = "last_name")
    @Schema(example = "Lawalata")
    public String lastName;
    @Column(nullable = false)
    @Schema(example = "21")
    public Integer age;
    @Column(nullable = false)
    @Schema(example = "Software Engineer")
    public String major;
    @Column(nullable = false)
    @Schema(example = "Kazan Aviation Institute")
    public String university;
    @Column(name = "start_study")
    @Schema(example = "2020")
    public Integer yearStartStudy;
    @Column(name = "finish_study")
    @Schema(example = "2024")
    public Integer yearFinishStudy;
    @OneToMany(mappedBy = "student")
    public List<Subject> subject;

}
