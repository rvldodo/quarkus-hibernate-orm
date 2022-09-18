package org.dodo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Student extends PanacheEntityBase {
    @Id
    @Column(insertable = false)
    @Schema(readOnly = true)
    @GeneratedValue
    private UUID uuid;
    @Column(name = "first_name")
    @Schema(example = "Rivaldo")
    private String firstName;
    @Column(name = "last_name")
    @Schema(example = "Lawalata")
    private String lastName;
    @Column(nullable = false)
    @Schema(example = "21")
    private Integer age;
    @Column(nullable = false)
    @Schema(example = "Software Engineer")
    private String major;
    @Column(nullable = false)
    @Schema(example = "Kazan Aviation Institute")
    private String university;
    @Column(name = "start_study")
    @Schema(example = "2020")
    private Integer yearStartStudy;
    @Column(name = "finish_study")
    @Schema(example = "2024")
    private Integer yearFinishStudy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_uuid")
    private Subject subject;

    public Student() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Integer getYearStartStudy() {
        return yearStartStudy;
    }

    public void setYearStartStudy(Integer yearStartStudy) {
        this.yearStartStudy = yearStartStudy;
    }

    public Integer getYearFinishStudy() {
        return yearFinishStudy;
    }

    public void setYearFinishStudy(Integer yearFinishStudy) {
        this.yearFinishStudy = yearFinishStudy;
    }
}
