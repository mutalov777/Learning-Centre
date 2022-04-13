package uz.learn.learningcentre.dto.exam;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.BaseDto;

@Getter
@Setter
public class ExamCreateDto implements BaseDto {
    private Integer ball;
    private String title;
    private Long groupId;
    private Long studentId;
}
