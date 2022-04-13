package uz.learn.learningcentre.dto.subject;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.BaseDto;

@Getter
@Setter
public class SubjectCreateDto implements BaseDto {
    private String name;
}
