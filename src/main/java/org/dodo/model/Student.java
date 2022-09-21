package org.dodo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private List<Subject> subject;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_uuid", referencedColumnName = "uuid")
    public PersonalInformation personalInformation;

    @ManyToMany
    @JoinTable(name = "student_courses",
    joinColumns = @JoinColumn(name = "student_uuid", referencedColumnName = "uuid"),
    inverseJoinColumns = @JoinColumn(name = "course_uuid", referencedColumnName = "uuid"))
    @Schema(readOnly = true)
    public List<Course> courses;
    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }
}
