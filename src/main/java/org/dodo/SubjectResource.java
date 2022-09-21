package org.dodo;

import org.dodo.model.Student;
import org.dodo.model.Subject;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("student/{uuid}/subjects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubjectResource {

    @Inject
    EntityManager entityManager;

    @GET
    public List<Subject> getAllSubjects(@PathParam("uuid") UUID uuid) {
        List<Subject> subjects = entityManager.createQuery("select s from Subject s where s.student.uuid = :uuid", Subject.class).setParameter("uuid", uuid).getResultList();
        return subjects;
    }

    @POST
    @Transactional
    public Subject addSubjectToStudent(@PathParam("uuid") UUID uuid, Subject subject) {
        Student student = Student.findById(uuid);
        subject.setStudent(student);
        subject.persist();

        return subject;
    }

    @DELETE
    @Transactional
    @Path("{subjectUuid}")
    public List<Subject> deleteSubject(UUID subjectUuid, @PathParam("uuid") UUID uuid) {
        Subject.deleteById(subjectUuid);
        List<Subject> subjects = entityManager.createQuery("select s from Subject s where s.student.uuid = :uuid", Subject.class).setParameter("uuid", uuid).getResultList();

        return subjects;
    }

}
