package uz.learn.learningcentre.dto.student;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StudentUpdateDto extends GenericDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String phoneNumber;

    private String studyType;

    private String entranceYear;

    private String mothersNumber;

    private String fathersNumber;

}
