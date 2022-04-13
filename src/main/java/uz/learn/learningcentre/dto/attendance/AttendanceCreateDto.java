package uz.learn.learningcentre.dto.attendance;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.BaseDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceCreateDto implements BaseDto {

    private Long studentId;

    private Long groupId;

    private Boolean here;// null if here / true if reasonable / false if reason not found(send message)

    private LocalDateTime createdAt;

    private Long createdBy;//teacherId
}
