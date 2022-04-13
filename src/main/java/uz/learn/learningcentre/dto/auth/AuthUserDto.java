package uz.learn.learningcentre.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.learn.learningcentre.dto.base.GenericDto;
import uz.learn.learningcentre.dto.subject.SubjectDto;
import uz.learn.learningcentre.enums.AuthRole;

import java.util.List;

@Getter
@Setter
public class AuthUserDto extends GenericDto {
    private String fullName;
    private String phoneNumber;
    private Short experienceYear;
    private List<SubjectDto> subjects;
    private AuthRole role;
    private Double salary;
}
