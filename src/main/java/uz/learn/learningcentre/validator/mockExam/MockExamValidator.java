package uz.learn.learningcentre.validator.mockExam;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.mockExam.MockExamCreateDto;
import uz.learn.learningcentre.dto.mockExam.MockExamDto;
import uz.learn.learningcentre.dto.mockExam.MockExamUpdateDto;
import uz.learn.learningcentre.entity.MockExam;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;

@Component
public class MockExamValidator implements
        GenericValidator<MockExam, MockExamDto, MockExamCreateDto, MockExamUpdateDto, Long> {

    @Override
    public void validOnUpdate(MockExamUpdateDto mockExamUpdateDto) {

    }

    @Override
    public void validOnCreate(MockExamCreateDto mockExamCreateDto) {
        if (Objects.isNull(mockExamCreateDto.getMockId())) {
            throw new BadRequestException("MOCK ID IS NULL");
        }
        if (Objects.isNull(mockExamCreateDto.getPhoneNumber())) {
            throw new BadRequestException("PHONE NUMBER IS NULL");
        }
        if (Objects.isNull(mockExamCreateDto.getSpeciality())) {
            throw new BadRequestException("SPECIALITY TIME IS NULL");
        }
        if (Objects.isNull(mockExamCreateDto.getStudentName())) {
            throw new BadRequestException("NAME IS NULL");
        }
    }

    @Override
    public void validOnId(Long id) {

    }
}
