package uz.learn.learningcentre.dto.grouping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;
import uz.learn.learningcentre.dto.student.StudentDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupingDto extends GenericDto {

    private String name;

    private Long subjectId;

    private Long mentorId;

    private Boolean available; // nabor course da bormi or not

    private LocalDate closedDate;

    private List<StudentDto> students;
}
