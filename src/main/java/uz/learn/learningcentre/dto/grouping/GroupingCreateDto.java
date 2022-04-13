package uz.learn.learningcentre.dto.grouping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.BaseDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupingCreateDto implements BaseDto {

    private String name;

    private Long subject;

    private Long mentor;

    private Double price;

}
