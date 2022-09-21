package org.dodo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "personal_info")
public class PersonalInformation extends PanacheEntityBase {
    @Id
    @GeneratedValue
    @Schema(readOnly = true)
    public UUID uuid;
    @Schema(example = "21")
    @Min(value = 15)
    public Integer age;
    @Schema(example = "KI3123", maxLength = 6)
    @Column(name = "student_id_card")
    public String studentId;
    @Schema(example = "Adelya Kutuya 37, Kazan, Russia")
    @Column(name = "student_adddress")
    public String address;
    @Schema(example = "2001-03-28")
    @Column(name = "date_of_birth")
    public LocalDate dob;
}
