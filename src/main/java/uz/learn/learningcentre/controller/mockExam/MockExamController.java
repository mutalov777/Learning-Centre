package uz.learn.learningcentre.controller.mockExam;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.mockExam.MockExamCriteria;
import uz.learn.learningcentre.dto.mockExam.MockExamCreateDto;
import uz.learn.learningcentre.dto.mockExam.MockExamDto;
import uz.learn.learningcentre.dto.mockExam.MockExamUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.mockExam.MockExamService;

import java.util.List;

@RestController
@RequestMapping("/mock_exam/")
public class MockExamController extends AbstractController<MockExamService>
        implements GenericCrudController<MockExamCreateDto, MockExamUpdateDto>,
        GenericController<MockExamDto, MockExamCriteria> {

   // private final MockService mockService;

    public MockExamController(MockExamService service/*, MockService mockService*/) {
        super(service);
        //this.mockService = mockService;
    }

    @Override
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create(@RequestBody MockExamCreateDto mockExamCreateDto) {
        return service.create(mockExamCreateDto);
    }

   /* //receptionist can  add student to mockExam table
    @RequestMapping(value = "add_student/{studentId}", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create(@PathVariable Long studentId, @RequestBody MockExamCreateDto mockExamCreateDto) {
        Long currentMockId = mockService.findById();
        return service.create(mockExamCreateDto);
    }

    @RequestMapping(value = "add_student/{studentId}", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> addStudent(@PathVariable("studentId") Long studentId) {
        return null;
    }*/

    @Override
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<Long>> update(MockExamUpdateDto mockExamUpdateDto) {
        return service.update(mockExamUpdateDto);
    }

    @Override
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @Override
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<MockExamDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<MockExamDto>>> getAll(MockExamCriteria criteria) {
        return null;
    }
}
