package uz.learn.learningcentre.validator.student;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.student.StudentCreateDto;
import uz.learn.learningcentre.dto.student.StudentDto;
import uz.learn.learningcentre.dto.student.StudentUpdateDto;
import uz.learn.learningcentre.entity.Student;
import uz.learn.learningcentre.validator.base.GenericValidator;

@Component
public class StudentValidator implements GenericValidator<Student, StudentDto, StudentCreateDto, StudentUpdateDto, Long> {

    @Override
    public void validOnUpdate(StudentUpdateDto studentUpdateDto) {

    }

    @Override
    public void validOnCreate(StudentCreateDto studentCreateDto) {

    }

    @Override
    public void validOnId(Long id) {

    }
}
