package uz.learn.learningcentre.validator.subject;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.subject.SubjectCreateDto;
import uz.learn.learningcentre.dto.subject.SubjectDto;
import uz.learn.learningcentre.dto.subject.SubjectUpdateDto;
import uz.learn.learningcentre.entity.Subject;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;

@Component
public class SubjectValidator implements GenericValidator<Subject, SubjectDto, SubjectCreateDto, SubjectUpdateDto, Long> {

    @Override
    public void validOnUpdate(SubjectUpdateDto subjectUpdateDto) {
        validOnId(subjectUpdateDto.getId());
        if (Objects.isNull(subjectUpdateDto.getName())) {
            throw new BadRequestException("NAME IS NULL");
        }
    }

    @Override
    public void validOnCreate(SubjectCreateDto subjectCreateDto) {
        if (Objects.isNull(subjectCreateDto.getName())) {
            throw new BadRequestException("NAME IS NULL");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new BadRequestException("ID IS INVALID");
        }
    }
}
