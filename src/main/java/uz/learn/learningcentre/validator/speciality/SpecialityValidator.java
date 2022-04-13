package uz.learn.learningcentre.validator.speciality;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.speciality.SpecialityCreateDto;
import uz.learn.learningcentre.dto.speciality.SpecialityDto;
import uz.learn.learningcentre.dto.speciality.SpecialityUpdateDto;
import uz.learn.learningcentre.entity.Speciality;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;
@Component
public class SpecialityValidator implements
        GenericValidator<Speciality, SpecialityDto, SpecialityCreateDto, SpecialityUpdateDto, Long> {

    @Override
    public void validOnUpdate(SpecialityUpdateDto specialityUpdateDto) {
        validOnId(specialityUpdateDto.getId());
    }

    @Override
    public void validOnCreate(SpecialityCreateDto specialityCreateDto) {
        if (Objects.isNull(specialityCreateDto.getMockExam())) {
            throw new BadRequestException("MOCK EXAM IS NULL");
        }
        if (Objects.isNull(specialityCreateDto.getAnswerCount())) {
            throw new BadRequestException("ANSWER COUNT IS NULL");
        }
        if (Objects.isNull(specialityCreateDto.getSubjectId())) {
            throw new BadRequestException("SUBJECT ID IS NULL");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("ID IS INVALID");
        }
    }
}
