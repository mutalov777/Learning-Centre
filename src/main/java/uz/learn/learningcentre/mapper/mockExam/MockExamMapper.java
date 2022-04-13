package uz.learn.learningcentre.mapper.mockExam;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.mockExam.MockExamCreateDto;
import uz.learn.learningcentre.dto.mockExam.MockExamDto;
import uz.learn.learningcentre.dto.mockExam.MockExamUpdateDto;
import uz.learn.learningcentre.entity.MockExam;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface MockExamMapper extends GenericMapper<MockExam, MockExamDto, MockExamCreateDto, MockExamUpdateDto> {

    @Override
    MockExam fromDto(MockExamDto dto);

    @Override
    List<MockExam> fromDto(List<MockExamDto> dtos);

    @Override
    MockExamDto toDto(MockExam entity);

    @Override
    List<MockExamDto> toDto(List<MockExam> entities);

    @Override
    MockExam fromUpdateDto(MockExamUpdateDto mockExamUpdateDto);

    @Override
    List<MockExam> fromUpdateDto(List<MockExamUpdateDto> ud);

    @Override
    @Mapping(target = "speciality", ignore = true)
    MockExam fromCreateDto(MockExamCreateDto mockExamCreateDto);

    @Override
    List<MockExam> fromCreateDto(List<MockExamCreateDto> cd);
}
