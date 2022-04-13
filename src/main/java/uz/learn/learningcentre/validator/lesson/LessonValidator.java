package uz.learn.learningcentre.validator.lesson;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.lesson.LessonCreateDto;
import uz.learn.learningcentre.dto.lesson.LessonDto;
import uz.learn.learningcentre.dto.lesson.LessonUpdateDto;
import uz.learn.learningcentre.entity.Lesson;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;


@Component
public class LessonValidator implements GenericValidator<Lesson, LessonDto, LessonCreateDto, LessonUpdateDto, Long> {

    @Override
    public void validOnUpdate(LessonUpdateDto lessonUpdateDto) {
        if (Objects.isNull(lessonUpdateDto)) {
            throw new BadRequestException("Dto is invalid");
        }
        validOnId(lessonUpdateDto.getId());
        validOnId(lessonUpdateDto.getGroupId());

    }

    @Override
    public void validOnCreate(LessonCreateDto lessonCreateDto) {
        if (Objects.isNull(lessonCreateDto)
                || Objects.isNull(lessonCreateDto.getDays())
                || Objects.isNull(lessonCreateDto.getGroupId())
                || Objects.isNull(lessonCreateDto.getLessonBegin())
                || Objects.isNull(lessonCreateDto.getLessonEnd())) {
            throw new BadRequestException("Dto is invalid");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) throw new BadRequestException("ID IS INVALID");
    }
}
