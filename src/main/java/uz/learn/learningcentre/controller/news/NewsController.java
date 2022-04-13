package uz.learn.learningcentre.controller.news;

import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.lesson.LessonCriteria;
import uz.learn.learningcentre.dto.news.NewsCreateDto;
import uz.learn.learningcentre.dto.news.NewsDto;
import uz.learn.learningcentre.dto.news.NewsUpdateDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.news.NewsService;

import java.util.List;

@RestController
@RequestMapping(value = "/news/")
public class NewsController extends AbstractController<NewsService>
        implements GenericCrudController<NewsCreateDto, NewsUpdateDto>,
        GenericController<NewsDto, LessonCriteria> {

    public NewsController(NewsService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "get/{id}")
    public ResponseEntity<DataDto<NewsDto>> get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "list/")
    public ResponseEntity<DataDto<List<NewsDto>>> getAll(LessonCriteria criteria) {
        return service.getAll(criteria);
    }

    @Override
    @PostMapping(value = "create/")
    public ResponseEntity<DataDto<Long>> create(@RequestBody NewsCreateDto newsCreateDto) {
        return service.create(newsCreateDto);
    }

    @Override
    @PutMapping(value = "update/")
    public ResponseEntity<DataDto<Long>> update(@RequestBody NewsUpdateDto newsUpdateDto) {
        return service.update(newsUpdateDto);
    }

    @Override
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
