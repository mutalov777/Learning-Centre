package uz.learn.learningcentre.service.subject;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.subject.SubjectCriteria;
import uz.learn.learningcentre.dto.subject.SubjectCreateDto;
import uz.learn.learningcentre.dto.subject.SubjectDto;
import uz.learn.learningcentre.dto.subject.SubjectUpdateDto;
import uz.learn.learningcentre.entity.Subject;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.subject.SubjectMapper;
import uz.learn.learningcentre.repository.subject.SubjectRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.subject.SubjectValidator;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService extends AbstractService<SubjectMapper, SubjectValidator, SubjectRepository>
        implements GenericCrudService<SubjectDto, SubjectCreateDto, SubjectUpdateDto>,
        GenericService<SubjectDto, SubjectCriteria> {

    public SubjectService(SubjectMapper mapper, SubjectValidator validator, SubjectRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(SubjectCreateDto subjectCreateDto) {
        try {
            validator.validOnCreate(subjectCreateDto);
            Subject subject = mapper.fromCreateDto(subjectCreateDto);
            Subject save = repository.save(subject);
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
    public ResponseEntity<DataDto<Long>> update(SubjectUpdateDto subjectUpdateDto) {
        try {
            validator.validOnUpdate(subjectUpdateDto);
            Subject subject = mapper.fromUpdateDto(subjectUpdateDto);
            Subject save = repository.save(subject);
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
    public ResponseEntity<DataDto<SubjectDto>> get(Long id) {
        try {
            validator.validOnId(id);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
        Optional<Subject> subject = repository.findById(id);
        if (subject.isPresent()) {
            SubjectDto subjectDto = mapper.toDto(subject.get());
            return new ResponseEntity<>(new DataDto<>(subjectDto));
        } else {
            return new ResponseEntity<>(new DataDto<>
                    (new AppErrorDto(HttpStatus.NOT_FOUND, "SUBJECT NOT FOUND")));
        }
    }

    @Override
    public ResponseEntity<DataDto<List<SubjectDto>>> getAll(SubjectCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Subject> subjectList = repository.findAll(pageable).getContent();
        List<SubjectDto> subjectDtoList = mapper.toDto(subjectList);
        return new ResponseEntity<>(new DataDto<>(subjectDtoList));
    }
}
