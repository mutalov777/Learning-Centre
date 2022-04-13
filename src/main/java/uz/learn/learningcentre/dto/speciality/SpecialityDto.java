package uz.learn.learningcentre.dto.speciality;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;
import uz.learn.learningcentre.entity.MockExam;

@Getter
@Setter
public class SpecialityDto extends GenericDto {
    private MockExam mockExam;
    private Long subjectId;
    private Short answerCount;
    private boolean main;
}
