package uz.learn.learningcentre.dto.speciality;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.BaseDto;
import uz.learn.learningcentre.entity.MockExam;

@Getter
@Setter
public class SpecialityCreateDto implements BaseDto {
    private MockExam mockExam;
    private Long subjectId;
    private Short answerCount;
    private boolean main;
}
