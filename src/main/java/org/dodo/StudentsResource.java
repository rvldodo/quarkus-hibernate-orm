package org.dodo;

import org.dodo.model.Course;
import org.dodo.model.Student;
import org.dodo.model.Subject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("students")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAll() {
        List<Student> students = Student.listAll();
        return students;
    }

    @GET
    @Path("{uuid}")
    public Response getData(UUID uuid) {
        return Student.findByIdOptional(uuid)
                .map(student -> Response.ok(student).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Student createData(Student student){
        Student.persist(student);

        List<Subject> subjects = student.getSubject();
        if(student.isPersistent()){
            for(Subject subject : subjects) {
                subject.setStudent(student);
                subject.persist();
            }
        }
        return student;
    }


    @DELETE
    @Path("{uuid}")
    @Transactional
    public Response deleteData(UUID uuid){
        boolean deleted = Student.deleteById(uuid);
        if(deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
