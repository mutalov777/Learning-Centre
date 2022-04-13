package uz.learn.learningcentre.controller.subject;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.subject.SubjectCriteria;
import uz.learn.learningcentre.dto.subject.SubjectCreateDto;
import uz.learn.learningcentre.dto.subject.SubjectDto;
import uz.learn.learningcentre.dto.subject.SubjectUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.subject.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController extends AbstractController<SubjectService>
        implements GenericCrudController<SubjectCreateDto, SubjectUpdateDto>,
        GenericController<SubjectDto, SubjectCriteria> {

    public SubjectController(SubjectService service) {
        super(service);
    }


    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(SubjectCreateDto subjectCreateDto) {
        return service.create(subjectCreateDto);
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDto<Long>> update(SubjectUpdateDto subjectUpdateDto) {
        return service.update(subjectUpdateDto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DataDto<SubjectDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<DataDto<List<SubjectDto>>> getAll(SubjectCriteria subjectCriteria) {
        return service.getAll(subjectCriteria);
    }
}
