package uz.learn.learningcentre.mapper.subject;

import org.mapstruct.Mapper;
import uz.learn.learningcentre.dto.subject.SubjectCreateDto;
import uz.learn.learningcentre.dto.subject.SubjectDto;
import uz.learn.learningcentre.dto.subject.SubjectUpdateDto;
import uz.learn.learningcentre.entity.Subject;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends GenericMapper<Subject, SubjectDto, SubjectCreateDto, SubjectUpdateDto> {

    Subject fromDto(SubjectDto dto);

    List<Subject> fromDto(List<SubjectDto> dtos);

    SubjectDto toDto(Subject entity);

    List<SubjectDto> toDto(List<Subject> entities);

    Subject fromUpdateDto(SubjectUpdateDto subjectUpdateDto);

    List<Subject> fromUpdateDto(List<SubjectUpdateDto> ud);

    Subject fromCreateDto(SubjectCreateDto subjectCreateDto);

    List<Subject> fromCreateDto(List<SubjectCreateDto> cd);
}
