package uz.learn.learningcentre.service.exam;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.exam.ExamCriteria;
import uz.learn.learningcentre.dto.exam.ExamCreateDto;
import uz.learn.learningcentre.dto.exam.ExamDto;
import uz.learn.learningcentre.dto.exam.ExamUpdateDto;
import uz.learn.learningcentre.entity.Exam;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.exam.ExamMapper;
import uz.learn.learningcentre.repository.exam.ExamRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.exam.ExamValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExamService extends AbstractService<ExamMapper, ExamValidator, ExamRepository>
        implements GenericCrudService<ExamDto, ExamCreateDto, ExamUpdateDto>,
        GenericService<ExamDto, ExamCriteria> {


    public ExamService(ExamMapper mapper, ExamValidator validator, ExamRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(ExamCreateDto examCreateDto) {
        try {
            validator.validOnCreate(examCreateDto);
            Exam exam = mapper.fromCreateDto(examCreateDto);
            Exam save = repository.save(exam);
            return new ResponseEntity<>(new DataDto<>(save.getId()));
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(ExamUpdateDto examUpdateDto) {
        try {
            validator.validOnUpdate(examUpdateDto);
            Exam exam = mapper.fromUpdateDto(examUpdateDto);
            Exam save = repository.save(exam);
            return new ResponseEntity<>(new DataDto<>(save.getId()));
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            validator.validOnId(id);
            repository.deleteById(id);
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        }
        return new ResponseEntity<>(new DataDto<>(Boolean.valueOf(true)));
    }

    @Override
    public ResponseEntity<DataDto<ExamDto>> get(Long id) {
        try {
            validator.validOnId(id);
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
        Optional<Exam> exam = repository.findById(id);
        if (exam.isPresent()) {
            ExamDto examDto = mapper.toDto(exam.get());
            return new ResponseEntity<>(new DataDto<>(examDto));
        } else {
            return new ResponseEntity<>(new DataDto<>
                    (new AppErrorDto(HttpStatus.NOT_FOUND, "EXAM NOT FOUND")));
        }
    }

    @Override
    public ResponseEntity<DataDto<List<ExamDto>>> getAll(ExamCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by("ball").descending());
        List<Exam> examList;
        if (Objects.nonNull(criteria.getGroupId())) {
            examList = repository.findAllByGroupId(criteria.getGroupId(), pageable);
        } else {
            examList = repository.findAll(pageable).getContent();
        }
        List<ExamDto> examDtoList = mapper.toDto(examList);
        return new ResponseEntity<>(new DataDto<>(examDtoList));
    }

}
