package uz.learn.learningcentre.validator.mock;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.mock.MockCreateDto;
import uz.learn.learningcentre.dto.mock.MockDto;
import uz.learn.learningcentre.dto.mock.MockUpdateDto;
import uz.learn.learningcentre.entity.Mock;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;

@Component
public class MockValidator implements
        GenericValidator<Mock, MockDto, MockCreateDto, MockUpdateDto, Long> {

    @Override
    public void validOnUpdate(MockUpdateDto mockUpdateDto) {
        validOnId(mockUpdateDto.getId());
    }

    @Override
    public void validOnCreate(MockCreateDto mockCreateDto) {
        if (Objects.isNull(mockCreateDto.getName())) {
            throw new BadRequestException("NAME IS NULL");
        }
        if (Objects.isNull(mockCreateDto.getDate())) {
            throw new BadRequestException("DATE IS NULL");
        }
        if (Objects.isNull(mockCreateDto.getBeginTime())) {
            throw new BadRequestException("BEGIN TIME IS NULL");
        }
        if (Objects.isNull(mockCreateDto.getEndTime())) {
            throw new BadRequestException("END TIME IS NULL");
        }
        if (Objects.isNull(mockCreateDto.getLocation())) {
            throw new BadRequestException("LOCATION IS NULL");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) throw new BadRequestException("ID IS INVALID");
    }
}
