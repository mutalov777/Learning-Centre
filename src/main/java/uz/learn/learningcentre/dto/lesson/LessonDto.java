package uz.learn.learningcentre.dto.lesson;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;

import java.time.LocalDate;

@Getter
@Setter
public class LessonDto extends GenericDto {

    private String days;// hafta kunlari

    private LocalDate lessonBegin;

    private LocalDate lessonEnd;

    private Long groupId;// write relationship

}
