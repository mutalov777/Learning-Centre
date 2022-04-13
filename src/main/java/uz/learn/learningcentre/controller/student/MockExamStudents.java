package uz.learn.learningcentre.controller.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.student.StudentCriteria;
import uz.learn.learningcentre.dto.student.StudentCreateDto;
import uz.learn.learningcentre.dto.student.StudentDto;
import uz.learn.learningcentre.dto.student.StudentUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.student.StudentService;

import java.util.List;

@RestController
@RequestMapping("/mock/student")
public class MockExamStudents extends AbstractController<StudentService> implements
        GenericCrudController<StudentCreateDto, StudentUpdateDto>,
        GenericController<StudentDto, StudentCriteria> {

    public MockExamStudents(StudentService service) {
        super(service);
    }

    @Override
    public ResponseEntity<DataDto<StudentDto>> get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<List<StudentDto>>> getAll(StudentCriteria criteria) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(StudentCreateDto studentCreateDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(StudentUpdateDto studentUpdateDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        return null;
    }
}
