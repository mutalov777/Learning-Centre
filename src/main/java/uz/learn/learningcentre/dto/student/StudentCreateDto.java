package uz.learn.learningcentre.dto.student;

import lombok.Data;
import uz.learn.learningcentre.controller.student.MockExamStudents;
import uz.learn.learningcentre.dto.base.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class StudentCreateDto implements BaseDto {

    @NotBlank(groups = MockExamStudents.class, message = "fullName can not be blank")
    private String fullName;

    @NotBlank(groups = MockExamStudents.class)
    @Size(min = 9, groups = MockExamStudents.class, message = "phoneNumber is invalid")
    private String phoneNumber;

    @NotBlank(groups = MockExamStudents.class)
    @Size(min = 4, max = 15, message = "length of password should be between {min} and {max}")
    private String password;

    @NotBlank
    private String studyType; // Grant / Contract / None

    private String entranceYear;

    @NotBlank
    @Size(min = 9)
    private String mothersNumber;

    @NotBlank
    @Size(min = 9)
    private String fathersNumber;

}
