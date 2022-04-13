package uz.learn.learningcentre.service.speciality;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.learn.learningcentre.criteria.speciality.SpecialityCriteria;
import uz.learn.learningcentre.dto.speciality.SpecialityCreateDto;
import uz.learn.learningcentre.dto.speciality.SpecialityDto;
import uz.learn.learningcentre.dto.speciality.SpecialityUpdateDto;
import uz.learn.learningcentre.entity.Speciality;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.mapper.speciality.SpecialityMapper;
import uz.learn.learningcentre.repository.speciality.SpecialityRepository;
import uz.learn.learningcentre.response.AppErrorDto;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.base.AbstractService;
import uz.learn.learningcentre.service.base.GenericCrudService;
import uz.learn.learningcentre.service.base.GenericService;
import uz.learn.learningcentre.validator.speciality.SpecialityValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SpecialityService extends AbstractService<SpecialityMapper, SpecialityValidator, SpecialityRepository>
        implements GenericCrudService<SpecialityDto, SpecialityCreateDto, SpecialityUpdateDto>,
        GenericService<SpecialityDto, SpecialityCriteria> {

    public SpecialityService(SpecialityMapper mapper, SpecialityValidator validator, SpecialityRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(SpecialityCreateDto specialityCreateDto) {
        try {
            validator.validOnCreate(specialityCreateDto);
            Speciality speciality = mapper.fromCreateDto(specialityCreateDto);
            Speciality save = repository.save(speciality);
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
    public ResponseEntity<DataDto<Long>> update(SpecialityUpdateDto specialityUpdateDto) {
        try {
            validator.validOnUpdate(specialityUpdateDto);
            Speciality speciality = mapper.fromUpdateDto(specialityUpdateDto);
            Speciality save = repository.save(speciality);
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
    public ResponseEntity<DataDto<SpecialityDto>> get(Long id) {
        try {
            validator.validOnId(id);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, e.getMessage())));
        }
        Optional<Speciality> speciality = repository.findById(id);
        if (speciality.isPresent()) {
            SpecialityDto specialityDto = mapper.toDto(speciality.get());
            return new ResponseEntity<>(new DataDto<>(specialityDto));
        } else {
            return new ResponseEntity<>(new DataDto<>
                    (new AppErrorDto(HttpStatus.NOT_FOUND, "SPECIALITY NOT FOUND")));
        }
    }

    @Override
    public ResponseEntity<DataDto<List<SpecialityDto>>> getAll(SpecialityCriteria specialityCriteria) {
        Pageable pageable = PageRequest.of(specialityCriteria.getPage(), specialityCriteria.getSize());
        List<Speciality> specialityList;
        if (Objects.nonNull(specialityCriteria.getSubjectId())) {
            specialityList = repository.findAllByMainAndSubjectId(specialityCriteria.isMain(), specialityCriteria.getSubjectId(), pageable);
        } else {
            specialityList = repository.findAllByMain(specialityCriteria.isMain(), pageable);
        }
        List<SpecialityDto> specialityDtoList = mapper.toDto(specialityList);
        return new ResponseEntity<>(new DataDto<>(specialityDtoList));
    }
}
