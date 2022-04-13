package uz.learn.learningcentre.dto.mock;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;

import java.time.LocalDate;

@Getter
@Setter
public class MockDto extends GenericDto {
    private String name;

    private LocalDate date;

    private String location;

    private String beginTime;

    private String endTime;
}
