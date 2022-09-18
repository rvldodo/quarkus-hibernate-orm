package org.dodo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "subject")
public class Subject extends PanacheEntityBase {

    @Id
    @Column(insertable = false, name = "subject_uuid")
    @Schema(readOnly = true)
    @GeneratedValue
    private UUID uuid;
    @Column(name = "subject_name")
    @Schema(example = "Math")
    private String subjectName;
    @Column(name = "point")
    @Schema(example = "85")
    private Double point;
    @Column(name = "passed")
    @Schema(example = "true")
    private Boolean passed;
    @OneToOne(mappedBy = "subject")
    private Student student;

    public Subject() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Integer point) {
        if(point >= 70) {
            this.passed = true;
        } else{
            this.passed = false;
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
