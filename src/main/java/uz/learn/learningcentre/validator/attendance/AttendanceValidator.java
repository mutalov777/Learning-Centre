package uz.learn.learningcentre.validator.attendance;

import org.springframework.stereotype.Component;
import uz.learn.learningcentre.dto.attendance.AttendanceCreateDto;
import uz.learn.learningcentre.dto.attendance.AttendanceDto;
import uz.learn.learningcentre.dto.attendance.AttendanceUpdateDto;
import uz.learn.learningcentre.entity.Attendance;
import uz.learn.learningcentre.exceptions.BadRequestException;
import uz.learn.learningcentre.validator.base.GenericValidator;

import java.util.Objects;


@Component
public class AttendanceValidator implements GenericValidator<Attendance, AttendanceDto, AttendanceCreateDto, AttendanceUpdateDto, Long> {
    @Override
    public void validOnUpdate(AttendanceUpdateDto attendanceUpdateDto) {
        if (Objects.isNull(attendanceUpdateDto)) {
            throw new BadRequestException("Dto is invalid");
        }
        validOnId(attendanceUpdateDto.getId());
        validOnId(attendanceUpdateDto.getGroupId());
        validOnId(attendanceUpdateDto.getStudentId());

    }

    @Override
    public void validOnCreate(AttendanceCreateDto attendanceCreateDto) {
        if (Objects.isNull(attendanceCreateDto.getCreatedAt())
                || Objects.isNull(attendanceCreateDto.getGroupId())
                || Objects.isNull(attendanceCreateDto.getStudentId())) {
            throw new BadRequestException("Dto is invalid");
        }
    }

    @Override
    public void validOnId(Long id) {
        if (Objects.isNull(id) || id <= 0) throw new BadRequestException("ID IS INVALID");
    }
}
