package uz.learn.learningcentre.service.lesson;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.lesson.LessonCriteria;
import uz.learn.learningcentre.dto.lesson.LessonCreateDto;
import uz.learn.learningcentre.dto.lesson.LessonDto;
import uz.learn.learningcentre.dto.lesson.LessonUpdateDto;
import uz.learn.learningcentre.entity.Lesson;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.exceptions.NotFoundException;
import uz.learn.learningcentre.mapper.lesson.LessonMapper;
import uz.learn.learningcentre.repository.lesson.LessonRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.lesson.LessonValidator;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService extends AbstractService<LessonMapper, LessonValidator, LessonRepository>
        implements GenericCrudService<LessonDto, LessonCreateDto, LessonUpdateDto>,
        GenericService<LessonDto, LessonCriteria> {


    public LessonService(LessonMapper mapper, LessonValidator validator, LessonRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(LessonCreateDto lessonCreateDto) {
        try {
            validator.validOnCreate(lessonCreateDto);
            Lesson lesson = repository.save(mapper.fromCreateDto(lessonCreateDto));
            return new ResponseEntity<>(new DataDto<>(lesson.getId()));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(LessonUpdateDto lessonUpdateDto) {
        try {
            validator.validOnUpdate(lessonUpdateDto);
            Lesson lesson = repository.update(mapper.fromUpdateDto(lessonUpdateDto));
            return new ResponseEntity<>(new DataDto<>(lesson.getId()));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            validator.validOnId(id);
            repository.deleteById(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<LessonDto>> get(Long id) {
        try {
            validator.validOnId(id);
            Optional<Lesson> optionalLesson = repository.findById(id);
            return optionalLesson.map(lesson -> new ResponseEntity<>(new DataDto<>(mapper.toDto(lesson)))).orElseGet(() -> new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "NOT FOUND"))));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (NotFoundException | NullPointerException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<List<LessonDto>>> getAll(LessonCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<LessonDto> lessonDtos = mapper.toDto(repository.findAll(pageable).getContent());
        return new ResponseEntity<>(new DataDto<>(lessonDtos));
    }
}
