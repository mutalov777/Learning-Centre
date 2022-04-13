package uz.learn.learningcentre.mapper.lesson;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import uz.learn.learningcentre.dto.lesson.LessonCreateDto;
import uz.learn.learningcentre.dto.lesson.LessonDto;
import uz.learn.learningcentre.dto.lesson.LessonUpdateDto;
import uz.learn.learningcentre.entity.Lesson;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper extends GenericMapper<Lesson, LessonDto, LessonCreateDto, LessonUpdateDto> {


    @Override
    Lesson fromDto(LessonDto dto);

    @Override
    List<Lesson> fromDto(List<LessonDto> dtos);

    @Override
    LessonDto toDto(Lesson entity);

    @Override
    List<LessonDto> toDto(List<Lesson> entities);

    @Override
    Lesson fromUpdateDto(LessonUpdateDto lessonUpdateDto);

    @Override
    Lesson fromUpdateDto(LessonUpdateDto lessonUpdateDto,@MappingTarget Lesson lesson);

    @Override
    List<Lesson> fromUpdateDto(List<LessonUpdateDto> ud);

    @Override
    Lesson fromCreateDto(LessonCreateDto lessonCreateDto);

    @Override
    List<Lesson> fromCreateDto(List<LessonCreateDto> cd);
}
