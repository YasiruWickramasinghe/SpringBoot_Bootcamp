package com.rootlabs.spring.data.jpa.tutorial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    // Description
    // One Course can have only One Course Material

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String courseTitle;
    private Integer credits;

    //Implement Bi-Directional relationship
        //before this we can only get courses using our course_material table, but cannot get course_material using course table
        //becours foreign key only available in course_material table
        //here we implement using course table we can get course material

    // One to One Relationship - Bi-directional
    @OneToOne(
            mappedBy = "course"  // this name comes from courseMaterial class
    )
    private CourseMaterial courseMaterial;

    // Many to One Relationship - Uni-directional
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    // Many to Many Relationship
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable( // Here create new table to join many to many
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    // add records to particular student
    public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }

}
