package uz.learn.learningcentre.mapper.attendance;

import org.mapstruct.Mapper;
import uz.learn.learningcentre.dto.attendance.AttendanceCreateDto;
import uz.learn.learningcentre.dto.attendance.AttendanceDto;
import uz.learn.learningcentre.dto.attendance.AttendanceUpdateDto;
import uz.learn.learningcentre.entity.Attendance;
import uz.learn.learningcentre.mapper.base.GenericMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper extends GenericMapper<Attendance, AttendanceDto, AttendanceCreateDto, AttendanceUpdateDto> {

    @Override
    Attendance fromDto(AttendanceDto dto);

    @Override
    List<Attendance> fromDto(List<AttendanceDto> dtos);

    @Override
    AttendanceDto toDto(Attendance entity);

    @Override
    List<AttendanceDto> toDto(List<Attendance> entities);

    @Override
    Attendance fromUpdateDto(AttendanceUpdateDto attendanceUpdateDto);

    @Override
    List<Attendance> fromUpdateDto(List<AttendanceUpdateDto> ud);

    @Override
    Attendance fromCreateDto(AttendanceCreateDto attendanceCreateDto);

    @Override
    List<Attendance> fromCreateDto(List<AttendanceCreateDto> cd);
}
