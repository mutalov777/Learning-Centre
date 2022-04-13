package uz.learn.learningcentre.controller.student;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.student.StudentCriteria;
import uz.learn.learningcentre.dto.student.ChangePasswordDto;
import uz.learn.learningcentre.dto.student.StudentCreateDto;
import uz.learn.learningcentre.dto.student.StudentDto;
import uz.learn.learningcentre.dto.student.StudentUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.student.StudentService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController extends AbstractController<StudentService> implements
        GenericCrudController<StudentCreateDto, StudentUpdateDto>,
        GenericController<StudentDto, StudentCriteria> {

    public StudentController(StudentService service) {
        super(service);
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody StudentCreateDto studentCreateDto) {
        return service.create(studentCreateDto);
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDto<Long>> update(StudentUpdateDto studentUpdateDto) {
        return service.update(studentUpdateDto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DataDto<StudentDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    /**
     * this method not used
     */
//    @PreDestroy
    @Override
    public ResponseEntity<DataDto<List<StudentDto>>> getAll(StudentCriteria criteria) {
        return null;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<DataDto<List<StudentDto>>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        return service.getAll(page, size);
    }


    @PatchMapping("/update/password")
    public ResponseEntity<DataDto<Long>> update(@Valid @ModelAttribute ChangePasswordDto password) {
        return service.changePassword(password);
    }

    @RequestMapping(value = "/random/students", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<StudentDto>>> getTenRandomStudents(@RequestParam(value = "count") @Min(10) Integer count) {
        List<StudentDto> list = service.getRandomList(count);
        return new ResponseEntity<>(new DataDto<>(list));
    }
}
