package org.dodo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "subject")
public class Subject extends PanacheEntityBase {

    @Id
    @Column(insertable = false, name = "uuid")
    @Schema(readOnly = true)
    @GeneratedValue
    public UUID uuid;
    @Column(name = "subject_name")
    @Schema(example = "Math")
    public String subjectName;
    @Column(name = "point")
    @Schema(example = "85")
    public Double point;
    @Column(name = "passed")
    @Schema(example = "true", readOnly = true)
    public Boolean passed = true;
    @ManyToOne
    @JoinColumn(name = "student_uuid", referencedColumnName = "uuid")
    private Student student;

    @JsonIgnore
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getPassed() {
        if(point < 75) {
            return passed = false;
        }
        return passed;
    }

}
