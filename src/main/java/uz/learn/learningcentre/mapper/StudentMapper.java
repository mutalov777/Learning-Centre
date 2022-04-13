package uz.learn.learningcentre.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.student.StudentCreateDto;
import uz.learn.learningcentre.dto.student.StudentDto;
import uz.learn.learningcentre.dto.student.StudentUpdateDto;
import uz.learn.learningcentre.entity.Student;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;
@Component
@Mapper( componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface StudentMapper extends GenericMapper<Student, StudentDto, StudentCreateDto, StudentUpdateDto> {

    @Override
    Student fromDto( StudentDto dto );

    @Override
    List<Student> fromDto( List<StudentDto> dtos );

    @Override
    StudentDto toDto( Student entity );

    @Override
    List<StudentDto> toDto( List<Student> entities );

    @Override
    Student fromUpdateDto( StudentUpdateDto studentUpdateDto );

    @Override
    Student fromUpdateDto( StudentUpdateDto studentUpdateDto ,@MappingTarget Student student );

    @Override
    List<Student> fromUpdateDto( List<StudentUpdateDto> ud );

    @Override
    Student fromCreateDto( StudentCreateDto studentCreateDto );

    @Override
    List<Student> fromCreateDto( List<StudentCreateDto> cd );
}
