package uz.learn.learningcentre.dto.exam;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;

@Getter
@Setter
public class ExamUpdateDto extends GenericDto {
    private Integer ball;
    private String title;
}
