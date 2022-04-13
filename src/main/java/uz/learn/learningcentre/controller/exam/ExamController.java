package uz.learn.learningcentre.controller.exam;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.exam.ExamCriteria;
import uz.learn.learningcentre.dto.exam.ExamCreateDto;
import uz.learn.learningcentre.dto.exam.ExamDto;
import uz.learn.learningcentre.dto.exam.ExamUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.exam.ExamService;

import java.util.List;

@RestController
@RequestMapping(value = "/exam")
public class ExamController extends AbstractController<ExamService>
        implements GenericCrudController<ExamCreateDto, ExamUpdateDto>,
        GenericController<ExamDto, ExamCriteria> {

    public ExamController(ExamService service) {
        super(service);
    }


    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create( ExamCreateDto examCreateDto) {
        return service.create(examCreateDto);
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDto<Long>> update(ExamUpdateDto examUpdateDto) {
        return service.update(examUpdateDto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DataDto<ExamDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<DataDto<List<ExamDto>>> getAll(ExamCriteria examCriteria) {
        return service.getAll(examCriteria);
    }
}
