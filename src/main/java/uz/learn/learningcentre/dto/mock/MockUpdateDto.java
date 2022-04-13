package uz.learn.learningcentre.dto.mock;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;

@Getter
@Setter
public class MockUpdateDto extends GenericDto {

    private String name;

    private String date;

    private String location;

    private String beginTime;

    private String endTime;

}
