package uz.learn.learningcentre.mapper.exam;

import org.mapstruct.Mapper;
import uz.learn.learningcentre.dto.exam.ExamCreateDto;
import uz.learn.learningcentre.dto.exam.ExamDto;
import uz.learn.learningcentre.dto.exam.ExamUpdateDto;
import uz.learn.learningcentre.entity.Exam;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;


@Mapper(componentModel = "spring")
public
interface ExamMapper extends GenericMapper<Exam, ExamDto, ExamCreateDto, ExamUpdateDto> {

    @Override
    Exam fromDto(ExamDto dto);

    @Override
    List<Exam> fromDto(List<ExamDto> dtos);

    @Override
    ExamDto toDto(Exam entity);

    @Override
    List<ExamDto> toDto(List<Exam> entities);

    @Override
    Exam fromUpdateDto(ExamUpdateDto examUpdateDto);

    @Override
    List<Exam> fromUpdateDto(List<ExamUpdateDto> ud);

    @Override
    Exam fromCreateDto(ExamCreateDto examCreateDto);

    @Override
    List<Exam> fromCreateDto(List<ExamCreateDto> cd);
}
