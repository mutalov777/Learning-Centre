package uz.learn.learningcentre.controller.mock;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.lesson.LessonCriteria;
import uz.learn.learningcentre.dto.mock.MockCreateDto;
import uz.learn.learningcentre.dto.mock.MockDto;
import uz.learn.learningcentre.dto.mock.MockUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.mock.MockService;

import java.util.List;

@RestController
@RequestMapping(value = "/mock/")
public class MockController extends AbstractController<MockService>
        implements GenericCrudController<MockCreateDto, MockUpdateDto>,
        GenericController<MockDto, LessonCriteria> {

    public MockController(MockService service) {
        super(service);
    }

    @Override
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create(@RequestBody MockCreateDto mockCreateDto) {
        return service.create(mockCreateDto);
    }

    @Override
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<Long>> update(MockUpdateDto mockUpdateDto) {
        return service.update(mockUpdateDto);
    }

    @Override
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @Override
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<MockDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<MockDto>>> getAll(LessonCriteria criteria) {
        return service.getAll(criteria);
    }
}
