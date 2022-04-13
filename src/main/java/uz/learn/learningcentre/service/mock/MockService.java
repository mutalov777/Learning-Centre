package uz.learn.learningcentre.service.mock;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.lesson.LessonCriteria;
import uz.learn.learningcentre.dto.mock.MockCreateDto;
import uz.learn.learningcentre.dto.mock.MockDto;
import uz.learn.learningcentre.dto.mock.MockUpdateDto;
import uz.learn.learningcentre.entity.Mock;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.mock.MockMapper;
import uz.learn.learningcentre.repository.mock.MockRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.mock.MockValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class MockService extends AbstractService<MockMapper, MockValidator, MockRepository>
        implements GenericCrudService<MockDto, MockCreateDto, MockUpdateDto>,
        GenericService<MockDto, LessonCriteria> {

    public MockService(MockMapper mapper, MockValidator validator, MockRepository repository) {
        super(mapper, validator, repository);
    }

    public Long findById() {
        Optional<Long> lastMockId = repository.findId();
        return lastMockId.orElse(null);
    }


    @Override
    public ResponseEntity<DataDto<MockDto>> get(Long id) {
        try {
            validator.validOnId(id);
        } catch (BadRequestException e) {
            return new ResponseEntity<>
                    (new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
        Optional<Mock> mock = repository.findById(id);
        if (mock.isPresent()) {
            MockDto examDto = mapper.toDto(mock.get());
            return new ResponseEntity<>(new DataDto<>(examDto));
        } else {
            return new ResponseEntity<>(new DataDto<>
                    (new AppErrorDto(HttpStatus.NOT_FOUND, "MOCK NOT FOUND")));
        }
    }

    @Override
    public ResponseEntity<DataDto<List<MockDto>>> getAll(LessonCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<MockDto> mockDto = mapper.toDto(repository.findAll(pageable).getContent());
        return new ResponseEntity<>(new DataDto<>(mockDto));
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(MockCreateDto mockCreateDto) {
        try {
            validator.validOnCreate(mockCreateDto);
            repository.updateEnabled();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(mockCreateDto.getDate(), formatter);

            Mock mock = mapper.fromCreateDto(mockCreateDto);
            mock.setDate(localDate);
            Mock save = repository.save(mock);
            return new ResponseEntity<>(new DataDto<>(save.getId()));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        }
    }


    @Override
    public ResponseEntity<DataDto<Long>> update(MockUpdateDto mockUpdateDto) {
        try {
            validator.validOnUpdate(mockUpdateDto);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(mockUpdateDto.getDate(), formatter);

            Optional<Mock> optionalMock = repository.findById(mockUpdateDto.getId());

            if (optionalMock.isPresent()) {
                Mock mock = mapper.fromUpdateDto(mockUpdateDto, optionalMock.get());
                mock.setDate(localDate);
                Mock save = repository.save(mock);
                return new ResponseEntity<>(new DataDto<>(save.getId()));
            }
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "MOCK_NOT_FOUND")));
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(
                    new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new DataDto<>(
                    new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
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
}
