package org.dodo;

import org.dodo.model.Course;
import org.dodo.model.Student;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {

    @Inject
    EntityManager entityManager;

    @POST
    @Path("{uuid}")
    @Transactional
    public Course addCourse(@PathParam("uuid") UUID studentUuid, Course course) {
        entityManager.persist(course);
        if(course.isPersistent()) {
            Student student = entityManager.find(Student.class, studentUuid);
            student.courses.add(course);
        }

        return course;
    }
}
