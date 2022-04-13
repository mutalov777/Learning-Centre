package uz.learn.learningcentre.dto.speciality;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;

@Getter
@Setter
public class SpecialityUpdateDto extends GenericDto {
    private Long subjectId;
    private Short answerCount;
    private boolean main;
}
