package uz.learn.learningcentre.validator.exam;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.exam.ExamCreateDto;
import uz.learn.learningcentre.dto.exam.ExamDto;
import uz.learn.learningcentre.dto.exam.ExamUpdateDto;
import uz.learn.learningcentre.entity.Exam;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;


@Component
public class ExamValidator implements GenericValidator<Exam, ExamDto, ExamCreateDto, ExamUpdateDto, Long> {

    @Override
    public void validOnUpdate(ExamUpdateDto examUpdateDto) {
        validOnId(examUpdateDto.getId());
    }

    @Override
    public void validOnCreate(ExamCreateDto examCreateDto) {
        if (Objects.isNull(examCreateDto.getBall())) {
            throw new BadRequestException("BALL IS NULL");
        }
        if (Objects.isNull(examCreateDto.getTitle())) {
            throw new BadRequestException("TITLE IS NULL");
        }
        if (Objects.isNull(examCreateDto.getGroupId())) {
            throw new BadRequestException("GROUP ID IS NULL");
        }
        if (Objects.isNull(examCreateDto.getStudentId())) {
            throw new BadRequestException("STUDENT ID IS NULL");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) throw new BadRequestException("ID IS INVALID");
    }
}
