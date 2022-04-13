package uz.learn.learningcentre.controller.lesson;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.criteria.lesson.LessonCriteria;
import uz.learn.learningcentre.dto.lesson.LessonCreateDto;
import uz.learn.learningcentre.dto.lesson.LessonDto;
import uz.learn.learningcentre.dto.lesson.LessonUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.service.lesson.LessonService;

import java.util.List;


@RestController
@RequestMapping(value = "/lesson/")
public class LessonController extends AbstractController<LessonService>
        implements GenericCrudService<LessonDto, LessonCreateDto, LessonUpdateDto>,
        GenericService<LessonDto, LessonCriteria> {


    public LessonController(LessonService service) {
        super(service);
    }

    @Override
    @PostMapping(value = "create/")
    public ResponseEntity<DataDto<Long>> create(@RequestBody LessonCreateDto lessonCreateDto) {
        return service.create(lessonCreateDto);
    }

    @Override
    @PutMapping(value = "update/")
    public ResponseEntity<DataDto<Long>> update(@RequestBody LessonUpdateDto lessonUpdateDto) {
        return service.update(lessonUpdateDto);
    }

    @Override
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }


    @Override
    @GetMapping(value = "get/{id}")
    public ResponseEntity<DataDto<LessonDto>> get(@PathVariable("id") Long id) {
        return service.get(id);
    }


    @Override
    @GetMapping(value = "list/")
    public ResponseEntity<DataDto<List<LessonDto>>> getAll(LessonCriteria criteria) {
        return service.getAll(criteria);
    }
}
