package org.dodo;

import org.dodo.model.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.*;

@Path("orm")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentsResource {

    @GET
    public Response getAll() {
        List<Student> students = Student.listAll();
        return Response.ok(students).build();
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
    public Response createData(Student student){
        Student.persist(student);
        if(student.isPersistent()){
            return Response.created(URI.create("/students" + student.getUuid())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
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
